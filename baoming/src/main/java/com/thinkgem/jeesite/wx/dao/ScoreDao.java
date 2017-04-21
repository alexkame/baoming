/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.Score;

/**
 */
@MyBatisDao
public interface ScoreDao extends CrudDao<Score> {


    /**
     * 
     * @param userId
     * @return
     */
    public Score getByUserIdAndType(@Param("userId") long userId,@Param("type") int type);
    
    public int updateScore(@Param("userId") long userId,@Param("type") int type,@Param("score") int score,@Param("question") String question);
    
    public List<Score> getBySalmesManId(@Param("salesManId") long salesManId,@Param("name") String name);

}
