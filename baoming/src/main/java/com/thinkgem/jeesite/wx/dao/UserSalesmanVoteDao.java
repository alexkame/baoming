/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.UserSalesmanVote;

/**
 */
@MyBatisDao
public interface UserSalesmanVoteDao extends CrudDao<UserSalesmanVote> {

    /**
     * 增加一个投票数
     * 
     * @return
     */
    public int addVotes(@Param("id") long id);

    /**
     * 获取客户信息
     * 
     * @param userId
     * @return
     */
    public List<UserSalesmanVote> getBySalesManId(@Param("salesManId") long salesManId);

    /**
     * 获取投票记录
     * 
     * @param userId
     * @return
     */
    public UserSalesmanVote getByUserIdAndSalesManId(@Param("userId") long userId,
            @Param("salesManId") long salesManId, @Param("lock") boolean lock);
}
