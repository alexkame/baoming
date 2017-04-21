/**
 *
 */
package com.thinkgem.jeesite.wx.entity.excel.health;

import java.io.Serializable;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class Salesman implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    private String qudao;
    private String province;
    private String city;
    private String organization;
    private String provinceCode;
    private String cityCode;
    private String organizationCode;
    private String realName;
    private String jobNum;
    private String phone;
    private long allUserCount;
    private long userCount;

    @ExcelField(title = "客户总数", align = 2, sort = 11)
    public long getAllUserCount() {
        return allUserCount;
    }

    @ExcelField(title = "中支", align = 2, sort = 3)
    public String getCity() {
        return city;
    }

    @ExcelField(title = "中支编号", align = 2, sort = 6)
    public String getCityCode() {
        return cityCode;
    }

    @ExcelField(title = "工号", align = 2, sort = 9)
    public String getJobNum() {
        return jobNum;
    }

    @ExcelField(title = "支公司", align = 2, sort = 4)
    public String getOrganization() {
        return organization;
    }

    @ExcelField(title = "支公司编号", align = 2, sort = 7)
    public String getOrganizationCode() {
        return organizationCode;
    }

    @ExcelField(title = "手机号", align = 2, sort = 10)
    public String getPhone() {
        return phone;
    }

    @ExcelField(title = "分公司", align = 2, sort = 2)
    public String getProvince() {
        return province;
    }

    @ExcelField(title = "分公司编号", align = 2, sort = 5)
    public String getProvinceCode() {
        return provinceCode;
    }

    @ExcelField(title = "渠道", align = 2, sort = 1)
    public String getQudao() {
        return qudao;
    }

    @ExcelField(title = "姓名", align = 2, sort = 8)
    public String getRealName() {
        return realName;
    }

    @ExcelField(title = "有效客户数", align = 2, sort = 12)
    public long getUserCount() {
        return userCount;
    }

    public void setAllUserCount(long allUserCount) {
        this.allUserCount = allUserCount;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setQudao(String qudao) {
        this.qudao = qudao;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

}