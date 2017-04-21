/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.entity.Lottery;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.User;
import com.thinkgem.jeesite.wx.service.CacheService;
import com.thinkgem.jeesite.wx.service.LotteryService;
import com.thinkgem.jeesite.wx.service.SalesmanService;
import com.thinkgem.jeesite.wx.service.UserService;

/**
 */
@Controller
@RequestMapping(value = "wx")
public class WxController extends BaseController {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private UserService userService;

    /**
     * 活动页
     */
    @RequestMapping("/activity")
    public String activity(Long salesManId, HttpServletRequest request, Model model) {
        if (salesManId != null && salesManId > 0) {
            Salesman salesman = salesmanService.getWithBaseById(salesManId);
            if(salesman==null){
            	throw new RuntimeException("no salesManId");
            }
            model.addAttribute("salesman", salesman);
        }else{
        	throw new RuntimeException("no salesManId");
        }
        return "wx/activity";
    }

    /**
     * 滴滴
     */
    @RequestMapping("/didi/{key}")
    public String didi(@PathVariable("key") String key) {
        return "wx/didi/" + key;
    }

    /**
     * 抽奖页
     */
    @RequestMapping("/drawLottery")
    public String drawLottery(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        // test
        // session.setAttribute(ConStant.WEIXIN_SEESION.USER,
        // userService.getByOpenid("123"));
        User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
        if (user == null || StringUtils.isBlank(user.getRealName())) {
            return userLogin(request, model);
        }
        Lottery lottery = lotteryService.getNoLiuliangByUserId(user.getId());
        if (lottery != null && lottery.getId() > 0) {
            return lottery(lottery.getId(), request, response, model);
        }
        return "wx/drawLottery";
    }

    /**
     * 用户得到的奖品
     */
    @RequestMapping("/lottery")
    public String lottery(Long id, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
        if (user == null || StringUtils.isBlank(user.getRealName())) {
            return userLogin(request, model);
        }
        try {
            Lottery lottery = lotteryService.getById(id);
            if (lottery != null && lottery.getUserId() == user.getId()) {
                model.addAttribute("lottery", lottery);
                // return "wx/lottery_" + lottery.getType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "wx/lottery";
    }

    /**
     * 获奖用户列表
     */
    @RequestMapping("/lotteryMore")
    public String lotteryMore(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Lottery> lotteries = cacheService.getOrderByType(0, 1000);
        model.addAttribute("lotteries", lotteries);
        return "wx/lotteryMore";
    }

    /**
     * 营销员
     */
    @RequestMapping("/newsalesman")
    public String newSalesman(long salesManId, HttpServletRequest request,
            HttpServletResponse response, Model model) {

        // 判断能否分享名片
        HttpSession session = request.getSession();
        Salesman nowSalesman = (Salesman) session.getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);
        if (nowSalesman != null) {
            model.addAttribute("salesman", nowSalesman);
            return "wx/salesman/main";
        } else {
            return "wx/salesmanSubmit";
        }

        // if (salesman != null && nowSalesman != null && salesman.getId() ==
        // nowSalesman.getId()) {
        // String shareUrl = request.getHeader("Referer");
        // if (shareUrl != null && shareUrl.indexOf("salesmanSubmitPage") > -1)
        // {
        // model.addAttribute("showDidi", true);
        // }
        // return "wx/salesman/main";
        // }
        //
        // User userSeesion = (User)
        // session.getAttribute(ConStant.WEIXIN_SEESION.USER);
        // if (userSeesion == null) {
        // String openid = (String)
        // session.getAttribute(ConStant.WEIXIN_SEESION.OPENID);
        // userSeesion = userService.add(openid, salesManId, null);
        // session.removeAttribute(ConStant.WEIXIN_SEESION.USER);
        // session.setAttribute(ConStant.WEIXIN_SEESION.USER, userSeesion);
        // }
        // session.removeAttribute(ConStant.WEIXIN_SEESION.VOTE_SALESMANID);
        // session.setAttribute(ConStant.WEIXIN_SEESION.VOTE_SALESMANID,
        // salesManId);
        //
        // model.addAttribute("userCount", cacheService.getUserCount());
        // model.addAttribute("score",
        // userService.getByUserIdAndType(userSeesion.getId(),
        // ConStant.HEALTH_TYPE));
        //
        // return "wx/salesman";
    }

    /**
     * 营销员测试链接
     */
    @RequestMapping("/salesman")
    public String salesman(long salesManId, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        return "wx/salesmantest";
    }

    /**
     * 活动首页
     */
    @RequestMapping("/salesmanSubmit")
    public String salesmanLogin() {
        return "wx/activity";
    }

    /**
     * 营销员登录
     */
    @RequestMapping("/salesmanLogin")
    public String salesmanLoginPage() {
        return "wx/salesmanLogin";
    }

    /**
     * 营销员列表
     */
    @RequestMapping("/salesmanMore")
    public String salesmanMore(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Salesman> salesmans = cacheService.getWithBaseOrderByVotes(0, 2016);
        model.addAttribute("salesmans", salesmans);
        return "wx/salesmanMore";
    }

    /**
     * 营销员数据录入
     */
    @RequestMapping("/salesmanSubmitPage")
    public String salesmanSubmit() {
        return "wx/salesmanSubmit";
    }

    /**
     * 用户评测页
     */
    @RequestMapping("/test")
    public String test() {
        return "wx/test/" + ConStant.HEALTH_TYPE;
    }

    @RequestMapping("/testResult")
    public String testResult(int score, String question, String hasTest,
            HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
        userService.score(user.getId(), ConStant.HEALTH_TYPE, score, question);

        String[] result;
        switch (ConStant.HEALTH_TYPE) {
        case 1:
            result = ConStant.scoreResult[score];
            break;
        case 2:
            result = ConStant.scoreResult2[score];
            break;
        default:
            result = ConStant.scoreResult[score];
            break;
        }

        model.addAttribute("result", result[0]);
        model.addAttribute("hasTest", "1".equals(hasTest) ? true : false);
        return "wx/test/result/" + ConStant.HEALTH_TYPE + "_" + result[1];
    }

    /**
     * 用户登录页
     */
    @RequestMapping("/userLogin")
    public String userLogin(HttpServletRequest request, Model model) {

        model.addAttribute("salesManId", request.getParameter("salesManId"));
        return "wx/userLogin";
    }

    /**
     * 新华币
     */
    @RequestMapping("/userNciMoeny")
    public String userNciMoeny(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ConStant.WEIXIN_SEESION.USER);
        if (user == null) {
            return userLogin(request, model);
        }
        user = userService.getByOpenid(user.getOpenid());
        model.addAttribute("nciMoney", user.getNciMoney());
        model.addAttribute("num", cacheService.getCountByNciMoneny(user.getNciMoney()) + 1);
        return "wx/userNciMoeny";
    }
}
