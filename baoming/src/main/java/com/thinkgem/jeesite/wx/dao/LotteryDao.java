/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.Lottery;

/**
 */
@MyBatisDao
public interface LotteryDao extends CrudDao<Lottery> {

    public Lottery getById(@Param("id") long id, @Param("lock") boolean lock);

    /**
     * 查询用户中iwatch的次数
     * 
     * @param userId
     * @return
     */
    public long getIwatchCountByUserId(@Param("userId") long userId, @Param("lock") boolean lock);

    /**
     * 查询用户未领取的流量包奖品
     * 
     * @param userId
     * @return
     */
    public Lottery getNoLiuliangByUserId(@Param("userId") long userId);

    /**
     * 查询中奖前1000名用户
     * 
     * @return
     */
    public List<Lottery> getOrderByType();

    /**
     * 查询中奖
     * 
     * @return
     */
    public List<Lottery> getSumByUserId(@Param("userId") long userId);

    /**
     * 更新 状态
     * 
     * @param id
     * @return
     */
    public int updateStatus(@Param("id") long id, @Param("status") int status,
            @Param("remark") String remark);
}
