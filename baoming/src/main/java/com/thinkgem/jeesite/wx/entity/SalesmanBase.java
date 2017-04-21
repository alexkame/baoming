/**
 *
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;

/**
 * 营销员 基础数据
 */
public class SalesmanBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idNum;
    private String realName;
    private String jobNum;
    private String province;
    private String city;
    private String organization;
    private String provinceCode;
    private String cityCode;
    private String organizationCode;

    public String getCity() {
        return city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getJobNum() {
        return jobNum;
    }

    public String getOrganization() {
        return organization;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public String getProvince() {
        return province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}