/**
 */
package com.thinkgem.jeesite.wx.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.wx.Mch.MchUtil;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.constant.ConStant.PRIZE_TYPE;
import com.thinkgem.jeesite.wx.dao.LotteryDao;
import com.thinkgem.jeesite.wx.dao.NciUserDao;
import com.thinkgem.jeesite.wx.dao.PrizeDao;
import com.thinkgem.jeesite.wx.entity.Lottery;
import com.thinkgem.jeesite.wx.entity.Prize;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.User;
import com.thinkgem.jeesite.wx.untils.RegexUtils;
import com.thinkgem.jeesite.wx.untils.XMLUtil;

/**
 */
@Service
@Transactional(readOnly = true)
public class LotteryService extends BaseService {

    @Autowired
    private LotteryDao lotteryDao;

    @Autowired
    private NciUserDao nciUserDao;

    @Autowired
    private PrizeDao   prizeDao;

    // 抽奖
    @Transactional(readOnly = false)
    public Lottery drawLottery(User user, Salesman salesman) {

        user = nciUserDao.getByOpenid(user.getOpenid(), true);
        if (user.getLuckDraw() - user.getUseLuckDraw() <= 0) {
            return null;
        }
        int type = PRIZE_TYPE.NCIMONEY;
        int num = 0;
        String flowCode = null;

        Random random = new Random();
        if (random.nextInt(100) <= ConStant.LOTTERY_NUM) {
            Prize prize = prizeDao.getTopUse();
            if (prize != null) {
                // 增加随机时间范围，小于时间范围的不能得奖
                // int minute = random.nextInt(60);
                // Date prizeDate =
                // DateUtils.parseDate(DateUtils.formatDate(prize.getCreateDate(),
                // "yyyy-MM-dd")+" "+DateUtils.formatDate(new
                // Date(prize.getCreateTime().getTime()),"HH:mm:ss"));
                // long pastMinute = DateUtils.pastMinutes(prizeDate);

                // if(pastMinute>=minute){

                prize = prizeDao.getById(prize.getId(), true);
                if (prize != null && prize.getNum() > prize.getPutOutNum()) {
                    switch (prize.getType()) {
                    case PRIZE_TYPE.IWACTH:

                        String okUserId = JedisUtils.get("okUserId");
                        if ((StringUtils.isNotBlank(okUserId) && okUserId.equals(user.getId() + ""))
                                || StringUtils.isBlank(okUserId)) {

                            if (salesman != null && StringUtils.isNotBlank(salesman.getPhone())) {
                                long count = lotteryDao.getIwatchCountByUserId(user.getId(), true);
                                if (count == 0) {
                                    type = prize.getType();
                                    num = 1;
                                }
                            }
                        }

                        break;
                    case 21:
                        flowCode = "10001";
                        type = PRIZE_TYPE.LIULIANG;
                        num = 1;
                        break;
                    case 22:
                        flowCode = "10002";
                        type = PRIZE_TYPE.LIULIANG;
                        num = 1;
                        break;
                    case 23:
                        flowCode = "10003";
                        type = PRIZE_TYPE.LIULIANG;
                        num = 1;
                        break;
                    case 24:
                        flowCode = "10004";
                        type = PRIZE_TYPE.LIULIANG;
                        num = 1;
                        break;
                    default:
                        break;
                    }
                    if (num > 0) {
                        prizeDao.addPutOutNum(num, prize.getId());
                    }
                }
            }
        }

        // }

        if (type == PRIZE_TYPE.NCIMONEY) {
            Random radom = new Random();
            num = 10 + radom.nextInt(90);
        }
        if (type == PRIZE_TYPE.LIULIANG) {
            int mobileCom = RegexUtils.getMobileComByPhone(user.getPhone());
            if (mobileCom > 0) {
                num = ConStant.MCH_NUM[mobileCom - 1][Integer.parseInt(flowCode) - 10001];
            } else {
                num = ConStant.MCH_NUM[0][Integer.parseInt(flowCode) - 10001];
            }
        }

        Lottery lottery = new Lottery();
        lottery.setUserId(user.getId());
        lottery.setType(type);
        lottery.setNum(num);
        lottery.setCreateTime(new Date());
        lottery.setFlowCode(flowCode);
        if (type == PRIZE_TYPE.NCIMONEY) {
            lottery.setStatus(1);
            lotteryDao.insert(lottery);
            nciUserDao.addNciMoney(user.getId(), num);
        } else {
            lottery.setStatus(0);
            lotteryDao.insert(lottery);
        }
        nciUserDao.addUseLuckDraw(user.getId());
        return lottery;
    }

    /**
     * 获取用户某一所得奖品
     * 
     * @param id
     * @return
     */
    public Lottery getById(long id) {
        return lotteryDao.get(id + "");
    }

    /**
     * 获取流量包领取地址
     * 
     * @return
     */
    @Transactional(readOnly = false)
    public String getMchRedictUrl(long lotteryId, User user, String ip) {
        String url = null;
        Lottery lottery = lotteryDao.getById(lotteryId, true);
        if (lottery != null && lottery.getType() == ConStant.PRIZE_TYPE.LIULIANG) {
            if (lottery.getStatus() == 0) {
                String result = MchUtil.getMch(lottery, user.getOpenid(), ip);
                logger.info("获取流量包领取地址result:" + result);
                Map<String, String> resultMap = XMLUtil.doXMLParse(result);
                if ("SUCCESS".equals(resultMap.get("return_code"))
                        && "SUCCESS".equals(resultMap.get("result_code"))
                        && StringUtils.isNotBlank(resultMap.get("redirect_url"))) {
                    url = resultMap.get("redirect_url");
                    lotteryDao.updateStatus(lotteryId, 3, result);
                } else {
                    lotteryDao.updateStatus(lotteryId, 0, result);
                }
            } else {
                String result = lottery.getRemark();
                Map<String, String> resultMap = XMLUtil.doXMLParse(result);
                if ("SUCCESS".equals(resultMap.get("return_code"))
                        && "SUCCESS".equals(resultMap.get("result_code"))
                        && StringUtils.isNotBlank(resultMap.get("redirect_url"))) {
                    url = resultMap.get("redirect_url");
                    lotteryDao.updateStatus(lotteryId, 3, result);
                }
            }

        }
        return url;
    }

    /**
     * 查询用户未领取的流量包奖品
     * 
     * @param id
     * @return
     */
    public Lottery getNoLiuliangByUserId(long id) {
        return lotteryDao.getNoLiuliangByUserId(id);
    }

    /**
     * 查询中奖前1000名用户
     * 
     * @return
     */
    @Cacheable(value = "wxCache", key = "#root.methodName")
    public List<Lottery> getOrderByType() {
        return lotteryDao.getOrderByType();
    }

    /**
     * 更新 状态
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = false)
    public int updateStatus(long id, int status, String remark) {
        return lotteryDao.updateStatus(id, 1, remark);
    }
}
