/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.VoteLog;

/**
 */
@MyBatisDao
public interface VoteLogDao extends CrudDao<VoteLog> {

    /**
     * 获取今天投票记录
     * 
     * @param userId
     * @return
     */
    public VoteLog getTodayByUserId(@Param("userId") long userId, @Param("lock") boolean lock);

    /**
     * 获取今天投票记录
     * 
     * @param userId
     * @return
     */
    public List<VoteLog> getTodaysByUserId(@Param("userId") long userId);

}
