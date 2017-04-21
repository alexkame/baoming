/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.Salesman;

/**
 */
@MyBatisDao
public interface SalesmanDao extends CrudDao<Salesman> {

    /**
     * 增加一个投票数
     *
     * @return
     */
    public int addVotes(@Param("id") long id);

    /**
     * 根据省份证查询营销员
     *
     * @param Idnum
     * @param lock
     *            是否加锁
     * @return
     */
    public Salesman getByIdNum(@Param("idNum") String idNum, @Param("lock") boolean lock);

    /**
     * 查询大于某票数的人数
     *
     * @return
     */
    public long getCountByVote(@Param("vote") long vote);

    /**
     * 营销员清单列表
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.Salesman> getSalesmanList(
            @Param("beginDate") String beginDate, @Param("endDate") String endDate,
            @Param("province") String province, @Param("city") String city,
            @Param("organization") String organization, @Param("fromIndex") int fromIndex,
            @Param("pageSize") int pageSize);

    /**
     * 营销员清单总数
     *
     * @return
     */
    public long getSalesmanListCount(@Param("province") String province,
            @Param("city") String city, @Param("organization") String organization);

    /**
     * 营销员清单汇总
     *
     * @return
     */
    public com.thinkgem.jeesite.wx.entity.excel.Salesman getSumSalesman(
            @Param("beginDate") String beginDate, @Param("endDate") String endDate,
            @Param("province") String province, @Param("city") String city,
            @Param("organization") String organization);

    /**
     * 查询得票数
     *
     * @return
     */
    public long getVotes(@Param("id") long id);

    /**
     * 根据id查询营销员
     *
     * @param Id
     * @return
     */
    public Salesman getWithBaseById(@Param("id") long id);

    /**
     * 根据省份证查询营销员
     *
     * @param Idnum
     * @return
     */
    public Salesman getWithBaseByIdNum(@Param("idNum") String idNum);

    /**
     * @return
     */
    public Salesman getWithBaseByJobNum(@Param("jobNum") String jobNum);

    /**
     * 根据微信openid查询营销员
     *
     * @param openid
     * @return
     */
    public Salesman getWithBaseByOpenid(@Param("openid") String openid);

    /**
     * 查询得票数前2016名营销员
     *
     * @param Id
     * @return
     */
    public List<Salesman> getWithBaseOrderByVotes();

}
