/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.constant.ConStant.WEIXIN_SEESION;
import com.thinkgem.jeesite.wx.entity.Lottery;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.User;
import com.thinkgem.jeesite.wx.entity.VoteLog;
import com.thinkgem.jeesite.wx.service.LotteryService;
import com.thinkgem.jeesite.wx.service.SalesmanBaseService;
import com.thinkgem.jeesite.wx.service.SalesmanService;
import com.thinkgem.jeesite.wx.service.UserService;
import com.thinkgem.jeesite.wx.service.VoteService;
import com.thinkgem.jeesite.wx.untils.CusAccessObjectUtil;
import com.thinkgem.jeesite.wx.untils.ImageCompressUtil;
import com.thinkgem.jeesite.wx.untils.NciUtils;
import com.thinkgem.jeesite.wx.untils.RegexUtils;
import com.thinkgem.jeesite.wx.untils.WeiXinSign;
import com.thinkgem.jeesite.wx.untils.WeiXinUtil;
import com.thinkgem.jeesite.wx.untils.sms.SmsTask;
import com.thinkgem.jeesite.wx.untils.sms.SmsUtil;

/**
 */
@Controller
@RequestMapping(value = "weixin")
public class WeiXinController extends BaseController {

    @Autowired
    private SalesmanService salesmanService;

    @Autowired
    private SalesmanBaseService salesmanBaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private LotteryService lotteryService;

    private String upload = "upload/salesman";

    /**
     * 营销员数据提交
     *
     * @return
     */
    @RequestMapping("/addSalesman")
    @ResponseBody
    public Object addSalesman(String filiale, String channel, String jobNum, String name,
            String phone, HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        try {

            if (StringUtils.isBlank(jobNum)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "业务编号不能为空，请输入");
                return resultMap;
            }

            if (StringUtils.isBlank(name)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "姓名不能为空，请输入");
                return resultMap;
            }
            name = URLDecoder.decode(name, "UTF-8");

            String openid = (String) session.getAttribute(ConStant.WEIXIN_SEESION.OPENID);
            if (StringUtils.isBlank(openid)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "微信授权失败,请刷新页面重试");
                return resultMap;
            }
            Salesman salesmanSeesion = (Salesman) session
                    .getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);
            if (salesmanSeesion != null && !salesmanSeesion.getIdNum().equals(jobNum)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "该微信号已绑定其他账号，无法提交");
                return resultMap;
            }

            Salesman jobNumSalesman = salesmanService.getByIdnum(jobNum);
            if (jobNumSalesman != null && !jobNumSalesman.getOpenid().equals(openid)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "该业务编号已绑定其他微信，无法提交");
                return resultMap;
            }

            if (StringUtils.isBlank(phone)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "手机号不能为空，请输入");
                return resultMap;
            }

            salesmanService.add(filiale, channel, jobNum, name, phone, openid, null);

            Salesman salesman = salesmanService.getWithBaseByOpenid(openid);
            if (salesman != null) {
                resultMap.put("code", 0);
                resultMap.put("msg", salesman.getId());
                // session.removeAttribute(WEIXIN_SEESION.USER);
                if (salesman != null) {
                    session.removeAttribute(WEIXIN_SEESION.SALESMAN);
                    session.setAttribute(WEIXIN_SEESION.SALESMAN, salesman);
                }
                session.removeAttribute(WEIXIN_SEESION.SALESMAN_PHOTO);
            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "提交失败,请重试");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传异常：" + e.getMessage());
            resultMap.put("code", -1);
            resultMap.put("msg", "提交失败,请重试");
        }
        return resultMap;
    }

    /**
     * 请求抽奖
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/drawLotterying")
    @ResponseBody
    public Object drawLotterying(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
            if (user != null && StringUtils.isNotBlank(user.getRealName())) {
                if ("0".equals(ConStant.ACTIVITY_BEGIN)) {
                    resultMap.put("code", -1);
                    resultMap.put("msg", "活动将于2015年11月16日正式开始，敬请期待！");
                    return resultMap;
                }
                user = userService.getByOpenid(user.getOpenid());
                if (user.getLuckDraw() - user.getUseLuckDraw() > 0) {
                    Salesman salesman = salesmanService.getWithBaseById(user.getSalesManId());
                    Lottery lottery = lotteryService.drawLottery(user, salesman);
                    if (lottery != null) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", lottery.getId());

                        if (lottery.getType() == ConStant.PRIZE_TYPE.IWACTH
                                && user.getSalesManId() > 0) {

                            if (salesman != null) {
                                SmsTask smsTask = new SmsTask(
                                        salesman.getPhone(),
                                        Integer.parseInt((lottery.getId() + 1000000)
                                                % Integer.MAX_VALUE + ""),
                                        "lsm:3:"
                                                + Integer.parseInt((lottery.getId() + 1000000)
                                                        % Integer.MAX_VALUE + "")
                                                + ":尊敬的"
                                                + salesman.getPhone()
                                                + "用户，您的客户在创业天使活动中获得苹果iwatch一台，手机尾号："
                                                + user.getPhone().substring(7)
                                                + "，详情通过“我是候选人”“个人查询”查看。客户奖品请您与营业部内勤联系领取，并以客户接收的公司短信为凭证及时为客户送达礼品。新华保险24小时服务热线95567。");
                                SmsUtil.executor.execute(smsTask);
                            }
                        }
                        if (lottery.getType() == ConStant.PRIZE_TYPE.IWACTH) {
                            SmsTask smsTask = new SmsTask(
                                    user.getPhone(),
                                    Integer.parseInt(lottery.getId() % Integer.MAX_VALUE + ""),
                                    "lsm:3:"
                                            + Integer.parseInt(lottery.getId() % Integer.MAX_VALUE
                                                    + "")
                                            + ":尊敬的"
                                            + user.getPhone()
                                            + "用户，恭喜您在新华保险创业天使E起来活动中获得幸运大奖：苹果iwatch一台，您投票的营销员将与您联系，并将奖品送到您手中，本条短信为您的兑奖凭证，请妥善保管，转发无效。新华保险健康无忧火热销售，欢迎咨询新华保险24小时服务热线95567。");
                            SmsUtil.executor.execute(smsTask);
                        }
                    } else {
                        resultMap.put("code", -1);
                        resultMap.put("msg", "抽奖人数太多，请稍后再试");
                    }

                } else {
                    VoteLog votelog = voteService.getTodayByUserId(user.getId());
                    if (votelog == null) {
                        resultMap.put("code", -1);
                        resultMap.put("msg", "请您先投票");
                    } else {
                        resultMap.put("code", -1);
                        resultMap.put("msg", "今日抽奖次数已用完，明天再来吧！");
                    }
                }
            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "登录超时");
            }

        } catch (Exception e) {
            logger.error("系统异常:" + e.getMessage());
            e.printStackTrace();
            resultMap.put("code", -1);
            resultMap.put("msg", "抽奖人数太多，请稍后再试");
        }
        return resultMap;
    }

    /**
     * 营销员当前图像
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/getPhoto")
    @ResponseBody
    public void getPhoto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        byte[] phtots = (byte[]) session.getAttribute(WEIXIN_SEESION.SALESMAN_PHOTO);
        if (phtots != null) {
            response.setContentType("image/jpeg");
            OutputStream stream = response.getOutputStream();
            stream.write(phtots);
            stream.flush();
            stream.close();
        }
    }

    /**
     * 营销员头像
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("/getSalesmanPhoto")
    @ResponseBody
    public void getSalesmanPhoto(long salesManId, HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        Salesman salesman = salesmanService.getWithBaseById(salesManId);
        response.setContentType("image/jpeg");

        OutputStream stream = response.getOutputStream();
        if (salesman != null && salesman.getPhoto() != null) {
            stream.write(salesman.getPhoto());
        } else {

            String savePath = request.getSession().getServletContext().getRealPath("/") + upload
                    + "/";
            File imgFile = new File(savePath + "222.png");
            if (imgFile.exists()) {
                stream.write(FileUtils.readFileToByteArray(imgFile));
            }
        }
        stream.flush();
        stream.close();
    }

    @RequestMapping("/jsApi")
    @ResponseBody
    public Object jsApi(String url, HttpServletRequest request, HttpServletResponse responsel) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            url = URLDecoder.decode(url, "UTF-8");
            String ticket = WeiXinUtil.getWeiXinTicket();
            JSONObject json = WeiXinSign.sign(ticket, url);
            resultMap.put("appId", WeiXinUtil.APPID);
            resultMap.put("timestamp", json.get("timestamp"));
            resultMap.put("nonceStr", json.get("nonceStr"));
            resultMap.put("signature", json.get("signature"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 抽奖
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/lottery")
    @ResponseBody
    public Object lottery(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
            if (user != null) {
                // Lottery lottery =
                // lotteryService.getNoLiuliangByUserId(user.getId());
                // if (lottery != null && lottery.getId() > 0) {
                // resultMap.put("code", 0);
                // resultMap.put("msg", "OK");
                // return resultMap;
                // }
                //
                // user = userService.getByOpenid(user.getOpenid());
                // if (user.getLuckDraw() - user.getUseLuckDraw() > 0) {
                // resultMap.put("code", 0);
                // resultMap.put("msg", "OK");
                // } else {
                // VoteLog votelog = voteService.getTodayByUserId(user.getId());
                // if (votelog == null) {
                // resultMap.put("code", -1);
                // resultMap.put("msg", "请您先投票");
                // } else {
                // resultMap.put("code", -1);
                // resultMap.put("msg", "今日抽奖次数已用完，明天再来吧！");
                // }
                // }
            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "请您先投票");
            }

        } catch (Exception e) {
            logger.error("系统异常:" + e.getMessage());
            e.printStackTrace();
            resultMap.put("code", -1);
            resultMap.put("msg", "系统异常，请稍后再试");
        }
        return resultMap;
    }

    /**
     * 流量包请求
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/mch")
    @ResponseBody
    public Object mch(Long id, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
            if (user == null || StringUtils.isBlank(user.getRealName())) {
                resultMap.put("code", -1);
                resultMap.put("msg", "登录超时，请重新进入");
            } else {
                Lottery lottery = lotteryService.getById(id);
                if (lottery != null && lottery.getUserId() == user.getId()
                        && lottery.getType() == ConStant.PRIZE_TYPE.LIULIANG
                        && (lottery.getStatus() == 0 || lottery.getStatus() == 2)) {
                    String url = lotteryService.getMchRedictUrl(lottery.getId(), user,
                            CusAccessObjectUtil.getIpAddress(request));
                    if (StringUtils.isBlank(url)) {
                        resultMap.put("code", -1);
                        resultMap.put("msg", "领取失败，请重试");
                    } else {
                        resultMap.put("code", 0);
                        resultMap.put("msg", url);
                    }
                } else {
                    resultMap.put("code", -1);
                    resultMap.put("msg", "奖品已提交，不能重复领奖");
                }
            }
        } catch (Exception e) {
            logger.error("流量包请求异常:" + e.getMessage());
            e.printStackTrace();
            resultMap.put("code", -1);
            resultMap.put("msg", "系统异常，请稍后再试");
        }
        return resultMap;

    }

    /**
     * 营销员登录
     *
     * @return
     */
    @RequestMapping("/salesmanLogining")
    @ResponseBody
    public Object salesmanLogining(String jobNum, HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        try {

            if (StringUtils.isBlank(jobNum)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "工号不能为空，请输入");
                return resultMap;
            }

            String openid = (String) session.getAttribute(ConStant.WEIXIN_SEESION.OPENID);
            // test
            // openid = "oveOYuJ_6foAYBolQRnD6uSE_7eM";

            if (StringUtils.isBlank(openid)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "微信授权失败,请刷新页面重试");
                return resultMap;
            }

            // 处理万能OPenid可以登录所有账号查看
            if (openid.equals(ConStant.LOGIN_OPENID)) {
                Salesman salesman = salesmanService.getWithBaseByJobNum(jobNum);
                if (salesman != null) {
                    session.removeAttribute(WEIXIN_SEESION.SALESMAN);
                    session.setAttribute(ConStant.WEIXIN_SEESION.SALESMAN, salesman);
                    resultMap.put("code", 0);
                    resultMap.put("msg", salesman.getId());
                    return resultMap;
                }
            }
            // 处理万能OPenid可以登录所有账号查看 end

            Salesman salesmanSeesion = (Salesman) session
                    .getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);

            // test
            // salesmanSeesion = salesmanService.getWithBaseByOpenid(openid);

            if (salesmanSeesion != null && jobNum.equals(salesmanSeesion.getIdNum())) {
                // logger.info("登录:"+com.alibaba.fastjson.JSONObject.toJSONString(salesmanSeesion));
                session.removeAttribute(WEIXIN_SEESION.SALESMAN);
                session.setAttribute(ConStant.WEIXIN_SEESION.SALESMAN, salesmanSeesion);
                resultMap.put("code", 0);
                resultMap.put("msg", salesmanSeesion.getId());
                return resultMap;
            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "您不是候选人，无权查看。");
                return resultMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("登录异常：" + e.getMessage());
            resultMap.put("code", -1);
            resultMap.put("msg", "登录异常,请重试");
        }
        return resultMap;
    }

    @RequestMapping("/share")
    @ResponseBody
    public Object share(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            HttpSession session = request.getSession();
            Salesman salesman = (Salesman) session.getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);

            Long salesManId = null;
            Long repeatUserId = null;

            Object salesManIdObj = session.getAttribute(ConStant.WEIXIN_SEESION.VOTE_SALESMANID);
            if (salesManIdObj != null) {
                try {
                    salesManId = Long.parseLong(salesManIdObj + "");
                } catch (Exception e) {
                    logger.error("分享异常salesManId：" + salesManIdObj);
                }
            }
            Object repeatUserIdObj = session
                    .getAttribute(ConStant.WEIXIN_SEESION.VOTE_REPEATUSERID);
            if (repeatUserIdObj != null) {
                try {
                    repeatUserId = Long.parseLong(repeatUserIdObj + "");
                } catch (Exception e) {
                    logger.error("分享异常repeatUserId：" + repeatUserIdObj);
                }
            }

            if (salesman == null) {
                User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
                if (user != null) {
                    boolean addVoteRes = salesmanService.addShare(salesManId, user.getId(),
                            repeatUserId);
                    if (addVoteRes) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", "分享成功");
                    } else {
                        resultMap.put("code", -1);
                        resultMap.put("msg", "已投过不能再分享");
                    }
                }
            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "营销员不能分享");
            }
        } catch (Exception e) {
            logger.error("分享操作异常:" + e.getMessage());
            e.printStackTrace();
            resultMap.put("code", -1);
            resultMap.put("msg", "分享操作异常，请稍后再试");
        }
        return resultMap;
    }

    @RequestMapping("/shareurl")
    @ResponseBody
    public Object shareurl(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            String shareurl = null;
            Long salesManId = null;
            Long repeatUserId = null;
            HttpSession session = request.getSession();
            Salesman salesman = (Salesman) session
                    .getAttribute(ConStant.WEIXIN_SEESION.SALESMAN_LOGIN);
            if (salesman != null) {
                salesManId = salesman.getId();
            } else {
                Object salesManIdObj = session
                        .getAttribute(ConStant.WEIXIN_SEESION.VOTE_SALESMANID);
                if (salesManIdObj != null) {
                    try {
                        salesManId = Long.parseLong(salesManIdObj + "");
                    } catch (Exception e) {
                        logger.error("分享异常投票营销员：" + salesManIdObj);
                    }
                }
                User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
                if (salesManId == null && user != null) {
                    salesManId = user.getSalesManId();
                }

                Object repeatUserIdObj = session
                        .getAttribute(ConStant.WEIXIN_SEESION.VOTE_REPEATUSERID);
                if (repeatUserIdObj != null) {
                    try {
                        repeatUserId = Long.parseLong(repeatUserIdObj + "");
                    } catch (Exception e) {
                        logger.error("分享异常转发客户：" + repeatUserIdObj);
                    }
                }

                if (repeatUserId == null && user != null && user.getType() == 0
                        && user.getSalesManId() == salesManId) {
                    repeatUserId = user.getId();
                }
            }

            if (salesManId != null) {
                shareurl = "salesManId=" + salesManId;
                if (repeatUserId != null) {
                    shareurl += "&repeatUserId=" + repeatUserId;
                }
            }
            resultMap.put("shareurl", shareurl);
            logger.debug("分享链接：" + shareurl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 上传头像
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/uploadphoto")
    @ResponseBody
    public Object uploadphoto(MultipartFile file, HttpServletRequest request) {

        int imgWidth = 300;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        String[] fielTypes = new String[] { "jpg", "png", "bmp" };
        String fileType = "jpg";
        try {
            if (file == null) {
                resultMap.put("code", -1);
                resultMap.put("msg", "上传失败,请选择文件");
                return resultMap;
            }
            // String ofileName = file.getOriginalFilename();
            // if (ofileName.indexOf(".") > -1) {
            // fileType = ofileName.split("\\.")[1];
            // fileType = fileType.toLowerCase();
            // if (!ArrayUtils.contains(fielTypes, fileType)) {
            // resultMap.put("code", -1);
            // resultMap.put("msg", "图片格式有误,请重试");
            // return resultMap;
            // }
            // }
            String savePath = session.getServletContext().getRealPath("/") + upload + "/";
            String openid = (String) session.getAttribute(ConStant.WEIXIN_SEESION.OPENID);
            // openid = "123";
            if (StringUtils.isBlank(openid)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "微信授权失败,请刷新页面重试");
                return resultMap;
            }
            file.transferTo(new File(savePath + openid + "." + fileType));
            ImageCompressUtil.saveMinPhoto(savePath + openid + "." + fileType, savePath + "samll/"
                    + openid + ".jpg", imgWidth, 0.9d);
            File imgFile = new File(savePath + "samll/" + openid + ".jpg");
            if (!imgFile.exists()) {
                resultMap.put("code", -1);
                resultMap.put("msg", "上传失败,请重试");
            } else {
                session.removeAttribute(WEIXIN_SEESION.SALESMAN_PHOTO);
                session.setAttribute(WEIXIN_SEESION.SALESMAN_PHOTO,
                        FileUtils.readFileToByteArray(imgFile));
                imgFile.delete();
                new File(savePath + openid + "." + fileType).delete();
                resultMap.put("code", 0);
                resultMap.put("msg", "OK");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传异常：" + e.getMessage());
            resultMap.put("code", -1);
            resultMap.put("msg", "上传失败,请重试");
        }
        return resultMap;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/userLogining")
    @ResponseBody
    public Object userLogining(String name, String phone, HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        try {

            if (StringUtils.isBlank(name)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "姓名不能为空，请输入");
                return resultMap;
            }
            if (StringUtils.isBlank(phone)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "手机号不能为空，请输入");
                return resultMap;
            }
            name = URLDecoder.decode(name, "UTF-8");
            name = name.trim();
            phone = phone.trim();
            if (!RegexUtils.isValidName(name)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "姓名有误，请重新输入");
                return resultMap;
            }

            if (!RegexUtils.isMobileNum(phone)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "手机号有误，请重新输入");
                return resultMap;
            }
            String openid = (String) session.getAttribute(ConStant.WEIXIN_SEESION.OPENID);
            if (StringUtils.isBlank(openid)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "微信授权失败,请刷新页面重试");
                return resultMap;
            }
            User userSeesion = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);

            if (userSeesion != null) {

                userService.updateNameAndPhone(userSeesion.getId(), phone, name);
                userSeesion.setPhone(phone);
                userSeesion.setRealName(name);
                session.removeAttribute(ConStant.WEIXIN_SEESION.USER);
                session.setAttribute(ConStant.WEIXIN_SEESION.USER, userSeesion);

                resultMap.put("code", 0);
                resultMap.put("msg", "OK");
                return resultMap;
            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "您不是有效用户，无权操作。");
                return resultMap;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("登录异常：" + e.getMessage());
            resultMap.put("code", -1);
            resultMap.put("msg", "登录异常,请重试");
        }
        return resultMap;
    }

    /**
     * 用户开始测试
     *
     * @return
     */
    @RequestMapping("/userTest")
    @ResponseBody
    public Object userTest(String sex, Integer childrenClass, String realName, String phone,
            String city, String childrenName, Long salesManId, HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        try {

            if (StringUtils.isBlank(realName)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "姓名不能为空，请输入");
                return resultMap;
            }
            realName = URLDecoder.decode(realName, "UTF-8");

            if (StringUtils.isNotBlank(childrenName)) {
                childrenName = URLDecoder.decode(childrenName, "UTF-8");
            }

            if (StringUtils.isBlank(sex)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "性别不能为空，请选择");
                return resultMap;
            }
            sex = sex.trim();
            if (!"男".equals(sex) && !"女".equals(sex)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "性别有误，请重新选择");
                return resultMap;
            }

            String openid = (String) session.getAttribute(ConStant.WEIXIN_SEESION.OPENID);
            if (StringUtils.isBlank(openid)) {
                resultMap.put("code", -1);
                resultMap.put("msg", "微信授权失败,请刷新页面重试");
                return resultMap;
            }
            User userSeesion = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);

            if (userSeesion == null) {
                userSeesion = userService.add(openid, salesManId, null);
            }
            userService.updateUserInfo(userSeesion.getId(), realName, phone, sex, city,
                    childrenName, childrenClass);
            userSeesion.setSex(sex);
            userSeesion.setRealName(realName);
            userSeesion.setPhone(phone);
            userSeesion.setCity(city);
            userSeesion.setChildrenName(childrenName);
            userSeesion.setChildrenClass(childrenClass);
            session.removeAttribute(ConStant.WEIXIN_SEESION.USER);
            session.setAttribute(ConStant.WEIXIN_SEESION.USER, userSeesion);

            resultMap.put("code", 0);
            resultMap.put("msg", "OK");
            return resultMap;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("提交异常：" + e.getMessage());
            resultMap.put("code", -1);
            resultMap.put("msg", "提交异常,请重试");
        }
        return resultMap;
    }

    @RequestMapping("/vote")
    @ResponseBody
    public Object vote(Long salesManId, Long repeatUserId, HttpServletRequest request,
            HttpServletResponse response) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            long endTimes = NciUtils.getTimesEnd();
            if (endTimes < 0) {
                resultMap.put("code", -1);
                resultMap.put("msg", "此活动已经结束，感谢您的关注，祝您新年快乐");
                return resultMap;
            }

            HttpSession session = request.getSession();
            Salesman salesman = (Salesman) session.getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);

            if (salesman == null) {
                salesman = salesmanService.getWithBaseById(salesManId);

                if (salesman == null) {
                    resultMap.put("code", -1);
                    resultMap.put("msg", "候选人已被删除，不能为他投票。");
                } else {
                    User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
                    if (user == null) {
                        String openidStr = (String) session
                                .getAttribute(ConStant.WEIXIN_SEESION.OPENID);
                        // openidStr = "user123";
                        if (StringUtils.isBlank(openidStr)) {
                            resultMap.put("code", -1);
                            resultMap.put("msg", "微信授权超时，投票失败");
                            return resultMap;
                        } else {
                            user = userService.add(openidStr, salesManId, repeatUserId);
                            if (user != null) {
                                session.removeAttribute(ConStant.WEIXIN_SEESION.USER);
                                session.setAttribute(ConStant.WEIXIN_SEESION.USER, user);
                            }
                        }
                    }

                    boolean addVoteRes = salesmanService.addVotes(salesManId, user.getId(),
                            repeatUserId);
                    if (addVoteRes) {
                        resultMap.put("code", 0);
                        resultMap.put("msg", "投票成功");
                        if (salesManId != null) {
                            session.removeAttribute(ConStant.WEIXIN_SEESION.VOTE_SALESMANID);
                            session.setAttribute(ConStant.WEIXIN_SEESION.VOTE_SALESMANID,
                                    salesManId);
                        }
                        if (repeatUserId != null) {
                            session.removeAttribute(ConStant.WEIXIN_SEESION.VOTE_REPEATUSERID);
                            session.setAttribute(ConStant.WEIXIN_SEESION.VOTE_REPEATUSERID,
                                    repeatUserId);
                        }

                    } else {
                        resultMap.put("code", -1);
                        resultMap.put("msg", "每天限投1票，您已投过票。");
                    }
                }

            } else {
                resultMap.put("code", -1);
                resultMap.put("msg", "您是候选人，不能投票。");
            }
        } catch (Exception e) {
            logger.error("投票操作异常:" + e.getMessage());
            e.printStackTrace();
            resultMap.put("code", -1);
            resultMap.put("msg", "投票操作异常，请稍后再试");
        }
        return resultMap;
    }
}
