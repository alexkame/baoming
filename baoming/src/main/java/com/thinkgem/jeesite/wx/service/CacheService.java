/**
 */
package com.thinkgem.jeesite.wx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.wx.entity.Lottery;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.Score;
import com.thinkgem.jeesite.wx.entity.User;

/**
 */
@Service
public class CacheService extends BaseService {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private SalesmanBaseService salesmanBaseService;

    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private UserService userService;

    /**
     * 获取省市
     *
     * @return
     */
    public List<Map> getCities() {
        return salesmanBaseService.getCities();
    }

    /**
     * 查询大于某新华币人数
     *
     * @return
     */
    public long getCountByNciMoneny(long nciMoney) {
        return userService.getCountByNciMoneny(nciMoney);
    }

    /**
     * 查询大于某票数的人数
     *
     * @return
     */
    public long getCountByVote(long vote) {
        return salesmanService.getCountByVote(vote);
    }

    /**
     * 查询中奖前1000名用户
     *
     * @return
     */
    public List<Lottery> getOrderByType(int pageNum, int pageSize) {

        List<Lottery> lotteries = new ArrayList<Lottery>();
        List<Lottery> list = lotteryService.getOrderByType();
        if (list != null && list.size() > 0) {
            int length = list.size();
            if (pageNum * pageSize < length) {
                lotteries = list.subList(pageNum * pageSize,
                        (pageNum + 1) * pageSize < length ? (pageNum + 1) * pageSize : length);
            }
        }
        return lotteries;
    }

    /**
     * 获取省市
     *
     * @return
     */
    public List<Map> getProvinces() {
        return salesmanBaseService.getProvinces();
    }

    /**
     * 获取客户信息
     *
     * @return
     */
    public List<Map<String, Object>> getUserBySalesmanId(long salesmanId) {
        return salesmanService.getUserBySalesmanId(salesmanId);
    }

    public long getUserCount() {
        return userService.getCount();
    }

    public List<User> getUserListBySalmesManId(long salesManId) {
        return userService.getBySalmesManId(salesManId);
    }

    public List<Score> getUserScoresBySalmesManId(long salesManId, String name) {
        return salesmanService.getUserScoresBySalmesManId(salesManId, name);
    }

    /**
     * 查询得票数
     *
     * @return
     */
    public long getVotes(long id) {
        return salesmanService.getVotes(id);
    }

    /**
     * 查询得票数前2016名营销员
     *
     * @return
     */
    public List<Salesman> getWithBaseOrderByVotes(int pageNum, int pageSize) {

        List<Salesman> salesmans = new ArrayList<Salesman>();
        List<Salesman> list = salesmanService.getWithBaseOrderByVotes();
        if (list != null && list.size() > 0) {
            int length = list.size();
            if (pageNum * pageSize < length) {
                salesmans = list.subList(pageNum * pageSize,
                        (pageNum + 1) * pageSize < length ? (pageNum + 1) * pageSize : length);
            }
        }
        return salesmans;
    }

}
