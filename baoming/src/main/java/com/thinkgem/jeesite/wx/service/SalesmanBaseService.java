/**
 */
package com.thinkgem.jeesite.wx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.wx.dao.SalesmanBaseDao;
import com.thinkgem.jeesite.wx.entity.SalesmanBase;
import com.thinkgem.jeesite.wx.entity.excel.CitySalesman;
import com.thinkgem.jeesite.wx.entity.excel.OrganizationSalesman;
import com.thinkgem.jeesite.wx.entity.excel.ProvinceSalesman;

/**
 */
@Service
@Transactional(readOnly = true)
public class SalesmanBaseService extends BaseService {

    @Autowired
    private SalesmanBaseDao salesmanBaseDao;

    private int lastHour = 25;

    private int expTime = 23 * 60 * 60;

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 50, 200,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    public SalesmanBase getByIdNumAndNameAndJobNum(String idNum, String realName, String jobNum) {
        return salesmanBaseDao.getByIdNumAndNameAndJobNum(idNum, realName, jobNum);
    }

    /**
     * 获取市县
     *
     * @return
     */
    @Cacheable(value = "userCache", key = "#root.methodName")
    public List<Map> getCities() {
        return salesmanBaseDao.getCities();
    }

    /**
     * 市
     *
     * @return
     */
    public List<CitySalesman> getCitySalesmanList(String beginDate, String endDate, String province) {

        List<CitySalesman> citySalesmans;
        String key = "getCitySalesmanList";
        if (StringUtils.isNotBlank(province)) {
            key += "-p" + province;
        }
        if (StringUtils.isNotBlank(beginDate)) {
            key += "-b" + beginDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
            key += "-e" + endDate;
        }

        List<Object> list = JedisUtils.getObjectList(key);
        if (list != null && list.size() > 0) {
            citySalesmans = new ArrayList<CitySalesman>();
            CitySalesman citySalesman;
            for (Object obj : list) {
                citySalesman = (CitySalesman) obj;
                citySalesmans.add(citySalesman);
            }
        } else {
            citySalesmans = salesmanBaseDao.getCitySalesmanList(beginDate, endDate, province);
            if (citySalesmans != null && citySalesmans.size() > 0
                    && DateUtils.pastHour(DateUtils.parseDate(endDate)) > lastHour) {
                list = new ArrayList<Object>();
                for (CitySalesman citySalesman : citySalesmans) {
                    list.add(citySalesman);
                }
                JedisUtils.setObjectList(key, list, expTime);
            }
        }
        return citySalesmans;
    }

    /**
     * 机构参与数
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.health.OrganizationSalesman> getOrganizationSalesmanCountList(
            String province, String city, String organization) {
        return salesmanBaseDao.getOrganizationSalesmanCountList(province, city, organization);
    }

    /**
     * 四级机构
     *
     * @return
     */
    public List<OrganizationSalesman> getOrganizationSalesmanList(String beginDate, String endDate,
            String province, String city) {
        List<OrganizationSalesman> organizationSalesmans;
        String key = "getOrganizationSalesmanList";
        if (StringUtils.isNotBlank(province)) {
            key += "-p" + province;
        }
        if (StringUtils.isNotBlank(city)) {
            key += "-c" + city;
        }
        if (StringUtils.isNotBlank(beginDate)) {
            key += "-b" + beginDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
            key += "-e" + endDate;
        }
        List<Object> list = JedisUtils.getObjectList(key);
        if (list != null && list.size() > 0) {
            organizationSalesmans = new ArrayList<OrganizationSalesman>();
            OrganizationSalesman organizationSalesman;
            for (Object obj : list) {
                organizationSalesman = (OrganizationSalesman) obj;
                organizationSalesmans.add(organizationSalesman);
            }

        } else {
            organizationSalesmans = salesmanBaseDao.getOrganizationSalesmanList(beginDate, endDate,
                    province, city);
            if (organizationSalesmans != null && organizationSalesmans.size() > 0
                    && DateUtils.pastHour(DateUtils.parseDate(endDate)) > lastHour) {
                list = new ArrayList<Object>();
                for (OrganizationSalesman organizationSalesman : organizationSalesmans) {
                    list.add(organizationSalesman);
                }
                JedisUtils.setObjectList(key, list, expTime);
            }
        }
        return organizationSalesmans;
    }

    /**
     * 获取省市
     *
     * @return
     */
    @Cacheable(value = "userCache", key = "#root.methodName")
    public List<Map> getProvinces() {
        return salesmanBaseDao.getProvinces();
    }

    /**
     * 省
     *
     * @return
     */
    public List<ProvinceSalesman> getProvinceSalesmanList(String beginDate, String endDate) {

        List<ProvinceSalesman> provinceSalesmans;
        String key = "getProvinceSalesmanList";
        if (StringUtils.isNotBlank(beginDate)) {
            key += "-b" + beginDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
            key += "-e" + endDate;
        }
        List<Object> list = JedisUtils.getObjectList(key);
        if (list != null && list.size() > 0) {
            provinceSalesmans = new ArrayList<ProvinceSalesman>();
            ProvinceSalesman provinceSalesman;
            for (Object obj : list) {
                provinceSalesman = (ProvinceSalesman) obj;
                provinceSalesmans.add(provinceSalesman);
            }

        } else {
            provinceSalesmans = salesmanBaseDao.getProvinceSalesmanList(beginDate, endDate);
            if (provinceSalesmans != null && provinceSalesmans.size() > 0
                    && DateUtils.pastHour(DateUtils.parseDate(endDate)) > lastHour) {
                list = new ArrayList<Object>();
                for (ProvinceSalesman provinceSalesman : provinceSalesmans) {
                    list.add(provinceSalesman);
                }
                JedisUtils.setObjectList(key, list, expTime);
            }
        }
        return provinceSalesmans;
    }

    /**
     * 营销员列表
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.health.Salesman> getSalesmanList(String qudao,
            String province, String city, String organization) {
        return salesmanBaseDao.getSalesmanList(qudao, province, city, organization);
    }

    /**
     * 市
     *
     * @return
     */
    public CitySalesman getSumCitySalesmanList(String beginDate, String endDate, String province) {

        CitySalesman citySalesman;
        String key = "getSumCitySalesmanList";
        if (StringUtils.isNotBlank(province)) {
            key += "-p" + province;
        }
        if (StringUtils.isNotBlank(beginDate)) {
            key += "-b" + beginDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
            key += "-e" + endDate;
        }
        Object obj = JedisUtils.getObject(key);
        if (obj != null) {
            citySalesman = (CitySalesman) obj;
        } else {
            citySalesman = salesmanBaseDao.getSumCitySalesmanList(beginDate, endDate, province);
            if (citySalesman != null && DateUtils.pastHour(DateUtils.parseDate(endDate)) > lastHour) {
                JedisUtils.setObject(key, citySalesman, expTime);
            }
        }
        return citySalesman;

    }

    /**
     * 四级机构
     *
     * @return
     */
    public OrganizationSalesman getSumOrganizationSalesmanList(String beginDate, String endDate,
            String province, String city) {

        OrganizationSalesman organizationSalesman;
        String key = "getSumOrganizationSalesmanList";
        if (StringUtils.isNotBlank(province)) {
            key += "-p" + province;
        }
        if (StringUtils.isNotBlank(city)) {
            key += "-c" + city;
        }
        if (StringUtils.isNotBlank(beginDate)) {
            key += "-b" + beginDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
            key += "-e" + endDate;
        }
        Object obj = JedisUtils.getObject(key);
        if (obj != null) {
            organizationSalesman = (OrganizationSalesman) obj;
        } else {
            organizationSalesman = salesmanBaseDao.getSumOrganizationSalesmanList(beginDate,
                    endDate, province, city);
            if (organizationSalesman != null
                    && DateUtils.pastHour(DateUtils.parseDate(endDate)) > lastHour) {
                JedisUtils.setObject(key, organizationSalesman, expTime);
            }
        }
        return organizationSalesman;
    }

    /**
     * 省
     *
     * @return
     */
    public ProvinceSalesman getSumProvinceSalesmanList(String beginDate, String endDate) {

        ProvinceSalesman provinceSalesman;
        String key = "getSumProvinceSalesmanList";
        if (StringUtils.isNotBlank(beginDate)) {
            key += "-b" + beginDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
            key += "-e" + endDate;
        }
        Object obj = JedisUtils.getObject(key);
        if (obj != null) {
            provinceSalesman = (ProvinceSalesman) obj;
        } else {
            provinceSalesman = salesmanBaseDao.getSumProvinceSalesmanList(beginDate, endDate);
            if (provinceSalesman != null
                    && DateUtils.pastHour(DateUtils.parseDate(endDate)) > lastHour) {
                JedisUtils.setObject(key, provinceSalesman, expTime);
            }
        }
        return provinceSalesman;
    }

    /**
     * 客户列表
     *
     * @return
     */
    public List<com.thinkgem.jeesite.wx.entity.excel.health.User> getUserList(String province,
            String city, String organization) {
        return salesmanBaseDao.getUserList(province, city, organization);
    }

}
