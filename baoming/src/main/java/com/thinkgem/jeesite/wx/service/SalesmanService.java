/**
 */
package com.thinkgem.jeesite.wx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.dao.LotteryDao;
import com.thinkgem.jeesite.wx.dao.NciUserDao;
import com.thinkgem.jeesite.wx.dao.SalesmanDao;
import com.thinkgem.jeesite.wx.dao.ScoreDao;
import com.thinkgem.jeesite.wx.dao.ShareLogDao;
import com.thinkgem.jeesite.wx.dao.UserSalesmanVoteDao;
import com.thinkgem.jeesite.wx.dao.VoteLogDao;
import com.thinkgem.jeesite.wx.entity.Lottery;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.Score;
import com.thinkgem.jeesite.wx.entity.ShareLog;
import com.thinkgem.jeesite.wx.entity.User;
import com.thinkgem.jeesite.wx.entity.UserSalesmanVote;
import com.thinkgem.jeesite.wx.entity.VoteLog;

/**
 */
@Service
@Transactional(readOnly = true)
public class SalesmanService extends BaseService {

    @Autowired
    private SalesmanDao salesmanDao;
    @Autowired
    private VoteLogDao voteLogDao;
    @Autowired
    private ShareLogDao shareLogDao;
    @Autowired
    private UserSalesmanVoteDao userSalesmanVoteDao;
    @Autowired
    private NciUserDao nciUserDao;
    @Autowired
    private LotteryDao lotteryDao;
    @Autowired
    private ScoreDao scoreDao;

    /**
     * 添加营销员信息
     *
     * @param idNum
     * @param phone
     * @param openid
     * @param photo
     */
    @Transactional(readOnly = false)
    public void add(String filiale, String channel, String jobNum, String name, String phone,
            String openid, byte[] photo) {
        Salesman salesman = salesmanDao.getByIdNum(jobNum, true);
        if (salesman == null) {
            salesman = new Salesman();
            salesman.setIdNum(jobNum);
            salesman.setPhone(phone);
            salesman.setOpenid(openid);
            salesman.setPhoto(photo);
            salesman.setFiliale(filiale);
            salesman.setChannel(channel);
            salesman.setName(name);
            salesman.setCreateTime(new Date());
            salesmanDao.insert(salesman);
        } else {
            salesman.setPhone(phone);
            salesman.setOpenid(openid);
            salesman.setPhoto(photo);
            salesman.setFiliale(filiale);
            salesman.setChannel(channel);
            salesman.setName(name);
            salesmanDao.update(salesman);
        }
    }

    /**
     * 添加分享并给分享者添加抽奖机会
     *
     * @param salesManId
     * @param userId
     * @param repeatUserId
     */
    @Transactional(readOnly = false)
    public boolean addShare(Long salesManId, long userId, Long repeatUserId) {

        ShareLog shareLog = shareLogDao.getTodayByUserId(userId, false);
        if (shareLog != null) {
            return false;
        }
        shareLog = new ShareLog();
        shareLog.setSalesManId(salesManId);
        shareLog.setUserId(userId);
        shareLog.setRepeatUserId(repeatUserId);
        shareLog.setCreateTime(new Date());
        shareLogDao.insert(shareLog);
        try {
            shareLog = shareLogDao.getTodayByUserId(userId, false);
        } catch (Exception e) {
            throw new RuntimeException("分享数有误：" + userId);
        }

        nciUserDao.addLuckDraw(userId);
        return true;
    }

    /**
     * 添加投票并给投票者添加抽奖机会
     *
     * @param salesManId
     * @param userId
     * @param repeatUserId
     */
    @Transactional(readOnly = false)
    public boolean addVotes(long salesManId, long userId, Long repeatUserId) {

        VoteLog voteLog = voteLogDao.getTodayByUserId(userId, false);
        if (voteLog != null) {
            return false;
        }
        salesmanDao.addVotes(salesManId);

        voteLog = new VoteLog();
        voteLog.setSalesManId(salesManId);
        voteLog.setUserId(userId);
        voteLog.setRepeatUserId(repeatUserId);
        voteLog.setCreateTime(new Date());
        voteLogDao.insert(voteLog);

        List list = voteLogDao.getTodaysByUserId(userId);
        if (list != null && list.size() == 1) {
            UserSalesmanVote userSalesmanVote = userSalesmanVoteDao.getByUserIdAndSalesManId(
                    userId, salesManId, true);
            if (userSalesmanVote == null) {
                userSalesmanVote = new UserSalesmanVote();
                userSalesmanVote.setSalesManId(salesManId);
                userSalesmanVote.setUserId(userId);
                userSalesmanVoteDao.insert(userSalesmanVote);
            } else {
                userSalesmanVoteDao.addVotes(userSalesmanVote.getId());
            }

            nciUserDao.addLuckDraw(userId);
            return true;
        } else {
            throw new RuntimeException("投票数有误：" + userId);
        }

    }

    /**
     * 根据省份证查询营销员
     *
     * @param idNum
     * @return
     */
    public Salesman getByIdnum(String idNum) {
        return salesmanDao.getByIdNum(idNum, false);
    }

    /**
     * 查询大于某票数的人数
     *
     * @return
     */
    @Cacheable(value = "wxCache", key = "#root.methodName+#vote")
    public long getCountByVote(long vote) {
        return salesmanDao.getCountByVote(vote);
    }

    /**
     * 营销员清单列表
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.Salesman> getSalesmanList(String beginDate,
            String endDate, String province, String city, String organization, int fromIndex,
            int pageSize) {
        return salesmanDao.getSalesmanList(beginDate, endDate, province, city, organization,
                fromIndex, pageSize);
    }

    /**
     * 营销员清单汇总
     *
     * @return
     */
    public com.thinkgem.jeesite.wx.entity.excel.Salesman getSumSalesman(String beginDate,
            String endDate, String province, String city, String organization) {
        return salesmanDao.getSumSalesman(beginDate, endDate, province, city, organization);
    }

    /**
     * 获取客户信息
     *
     * @return
     */
    @Cacheable(value = "wxCache", key = "#root.methodName+#salesmanId")
    public List<Map<String, Object>> getUserBySalesmanId(long salesmanId) {
        List<Map<String, Object>> iwacthList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<UserSalesmanVote> userSalesmanVotes = userSalesmanVoteDao.getBySalesManId(salesmanId);
        if (userSalesmanVotes != null && userSalesmanVotes.size() > 0) {
            Map<String, Object> map;
            List<Lottery> lotteries;
            boolean hasIwacth;
            for (UserSalesmanVote userSalesmanVote : userSalesmanVotes) {
                hasIwacth = false;
                map = new HashMap<String, Object>();
                map.put("name", StringUtils.isBlank(userSalesmanVote.getUser().getRealName()) ? "无"
                        : userSalesmanVote.getUser().getRealName());
                map.put("phone", StringUtils.isBlank(userSalesmanVote.getUser().getPhone()) ? "无"
                        : userSalesmanVote.getUser().getPhone());
                map.put("num", userSalesmanVote.getNum());
                if (userSalesmanVote.getUser() != null) {
                    if (userSalesmanVote.getUser().getRepeatUserId() != null) {
                        User repeatUser = nciUserDao.get(userSalesmanVote.getUser()
                                .getRepeatUserId() + "");
                        map.put("repeatUserName",
                                repeatUser == null ? null : repeatUser.getRealName());
                    } else {
                        map.put("repeatUserName", "自己");
                    }
                    lotteries = lotteryDao.getSumByUserId(userSalesmanVote.getUser().getId());
                    map.put("lotteries", lotteries);
                    if (lotteries != null && lotteries.size() > 0) {
                        for (Lottery lottery : lotteries) {
                            if (lottery.getType() == ConStant.PRIZE_TYPE.IWACTH) {
                                hasIwacth = true;
                                break;
                            }
                        }
                    }
                }
                if (hasIwacth) {
                    iwacthList.add(map);
                } else {
                    list.add(map);
                }

            }
        }
        iwacthList.addAll(list);
        return iwacthList;
    }

    @Cacheable(value = "wxCache", key = "#root.methodName+#salesManId+#name")
    public List<Score> getUserScoresBySalmesManId(long salesManId, String name) {
        return scoreDao.getBySalmesManId(salesManId, name);
    }

    /**
     * 查询得票数
     *
     * @return
     */
    @Cacheable(value = "wxCache", key = "#root.methodName+#id")
    public long getVotes(long id) {
        return salesmanDao.getVotes(id);
    }

    /**
     * 根据id查询营销员
     *
     * @param Id
     * @return
     */
    public Salesman getWithBaseById(long id) {
        return salesmanDao.getWithBaseById(id);
    }

    /**
     * 根据省份证查询营销员
     *
     * @param idNum
     * @return
     */
    public Salesman getWithBaseByIdNum(String idNum) {
        return salesmanDao.getWithBaseByIdNum(idNum);
    }

    public Salesman getWithBaseByJobNum(String jobNum) {
        return salesmanDao.getWithBaseByJobNum(jobNum);
    }

    /**
     * 根据微信openid查询营销员
     *
     * @param openid
     * @return
     */
    public Salesman getWithBaseByOpenid(String openid) {
        return salesmanDao.getWithBaseByOpenid(openid);
    }

    /**
     * 查询得票数前2016名营销员
     *
     * @return
     */
    @Cacheable(value = "wxCache", key = "#root.methodName")
    public List<Salesman> getWithBaseOrderByVotes() {
        return salesmanDao.getWithBaseOrderByVotes();
    }

}
