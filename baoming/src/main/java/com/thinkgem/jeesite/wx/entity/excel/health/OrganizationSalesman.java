/**
 *
 */
package com.thinkgem.jeesite.wx.entity.excel.health;

import java.io.Serializable;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 四级机构
 */
public class OrganizationSalesman implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    private String province;
    private String city;
    private String organization;
    private String provinceCode;
    private String cityCode;
    private String organizationCode;
    private long count;

    @ExcelField(title = "中支", align = 2, sort = 2)
    public String getCity() {
        return city;
    }

    @ExcelField(title = "中支编号", align = 2, sort = 5)
    public String getCityCode() {
        return cityCode;
    }

    @ExcelField(title = "参与人数", align = 2, sort = 7)
    public long getCount() {
        return count;
    }

    @ExcelField(title = "支公司", align = 2, sort = 3)
    public String getOrganization() {
        return organization;
    }

    @ExcelField(title = "支公司编号", align = 2, sort = 6)
    public String getOrganizationCode() {
        return organizationCode;
    }

    @ExcelField(title = "分公司", align = 2, sort = 1)
    public String getProvince() {
        return province;
    }

    @ExcelField(title = "分公司编号", align = 2, sort = 4)
    public String getProvinceCode() {
        return provinceCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCount(long count) {
        this.count = count;
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

}