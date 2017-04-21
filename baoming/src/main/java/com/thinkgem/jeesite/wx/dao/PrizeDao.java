/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.Prize;

/**
 */
@MyBatisDao
public interface PrizeDao extends CrudDao<Prize> {

    /**
     * 增加发放数量
     * 
     * @return
     */
    public int addPutOutNum(@Param("putOutNum") long putOutNum, @Param("id") long id);

    /**
     * 查询当前可用的奖池
     * 
     * @param userId
     * @return
     */
    public Prize getTopUse();
    
    /**
     * 
     * @return
     */
    public Prize getById(@Param("id") long id, @Param("lock") boolean lock);
    

}
