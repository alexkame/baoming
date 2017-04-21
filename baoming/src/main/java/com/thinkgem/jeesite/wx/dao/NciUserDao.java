/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.User;

/**
 */
@MyBatisDao
public interface NciUserDao extends CrudDao<User> {

    /**
     * 增加抽奖次数
     *
     * @param userId
     * @return
     */
    public int addLuckDraw(@Param("userId") long userId);

    /**
     * 增加新华币
     *
     * @param userId
     * @return
     */
    public int addNciMoney(@Param("userId") long userId, @Param("nciMoney") int nciMoney);

    /**
     * 增加使用抽奖次数
     *
     * @param userId
     * @return
     */
    public int addUseLuckDraw(@Param("userId") long userId);

    /**
     * 根据微信openid查询
     *
     * @param openid
     * @return
     */
    public User getByOpenid(@Param("openid") String openid, @Param("lock") boolean lock);

    /**
     * 查询
     *
     * @return
     */
    public User getByPhone(@Param("phone") String phone);

    public List<User> getBySalmesManId(@Param("salesManId") long salesManId);

    /**
     *
     * @return
     */
    public long getCount();

    /**
     * 查询大于某新华币人数
     *
     * @return
     */
    public long getCountByNciMoneny(@Param("nciMoney") long nciMoney);

    /**
     * 更新用户
     *
     * @param id
     * @param phone
     * @param realName
     * @return
     */
    public int updateNameAndPhone(@Param("id") long id, @Param("phone") String phone,
            @Param("realName") String realName);

    /**
     * 更新用户
     *
     * @param id
     * @return
     */
    public int updateSexAndAge(@Param("id") long id, @Param("sex") String sex, @Param("age") int age);

    /**
     * 更新用户
     *
     * @param id
     * @return
     */
    public int updateUserInfo(@Param("id") long id, @Param("realName") String realName,
            @Param("phone") String phone, @Param("sex") String sex, @Param("city") String city,
            @Param("childrenName") String childrenName,
            @Param("childrenClass") Integer childrenClass);

}
