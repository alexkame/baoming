/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.ShareLog;

/**
 */
@MyBatisDao
public interface ShareLogDao extends CrudDao<ShareLog> {

    /**
     * 获取今天分享记录
     * 
     * @param userId
     * @return
     */
    public ShareLog getTodayByUserId(@Param("userId") long userId, @Param("lock") boolean lock);

}
