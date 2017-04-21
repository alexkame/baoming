/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.wx.entity.SalesmanBase;
import com.thinkgem.jeesite.wx.entity.excel.CitySalesman;
import com.thinkgem.jeesite.wx.entity.excel.OrganizationSalesman;
import com.thinkgem.jeesite.wx.entity.excel.ProvinceSalesman;

/**
 */
@MyBatisDao
public interface SalesmanBaseDao extends CrudDao<SalesmanBase> {

    /**
     * 根据省份证 姓名 工号查询营销员
     *
     * @param Idnum
     * @return
     */
    public SalesmanBase getByIdNumAndNameAndJobNum(@Param("idNum") String idNum,
            @Param("realName") String realName, @Param("jobNum") String jobNum);

    /**
     * 获取市县
     *
     * @return
     */
    public List<Map> getCities();

    /**
     * 市
     *
     * @return
     */
    public List<CitySalesman> getCitySalesmanList(@Param("beginDate") String beginDate,
            @Param("endDate") String endDate, @Param("province") String province);

    /**
     * 机构参与数
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.health.OrganizationSalesman> getOrganizationSalesmanCountList(
            @Param("province") String province, @Param("city") String city,
            @Param("organization") String organization);

    /**
     * 四级机构
     *
     * @return
     */
    public List<OrganizationSalesman> getOrganizationSalesmanList(
            @Param("beginDate") String beginDate, @Param("endDate") String endDate,
            @Param("province") String province, @Param("city") String city);

    /**
     * 获取省市
     *
     * @return
     */
    public List<Map> getProvinces();

    /**
     * 省
     *
     * @return
     */
    public List<ProvinceSalesman> getProvinceSalesmanList(@Param("beginDate") String beginDate,
            @Param("endDate") String endDate);

    /**
     * 营销员列表
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.health.Salesman> getSalesmanList(
            @Param("qudao") String qudao, @Param("province") String province,
            @Param("city") String city, @Param("organization") String organization);

    /**
     * 市
     *
     * @return
     */
    public CitySalesman getSumCitySalesmanList(@Param("beginDate") String beginDate,
            @Param("endDate") String endDate, @Param("province") String province);

    /**
     * 四级机构
     *
     * @return
     */
    public OrganizationSalesman getSumOrganizationSalesmanList(
            @Param("beginDate") String beginDate, @Param("endDate") String endDate,
            @Param("province") String province, @Param("city") String city);

    /**
     * 省
     *
     * @return
     */
    public ProvinceSalesman getSumProvinceSalesmanList(@Param("beginDate") String beginDate,
            @Param("endDate") String endDate);

    /**
     * 客户列表
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.health.User> getUserList(
            @Param("province") String province, @Param("city") String city,
            @Param("organization") String organization);

}
