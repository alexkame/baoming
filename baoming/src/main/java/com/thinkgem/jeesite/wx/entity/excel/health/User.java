/**
 *
 */
package com.thinkgem.jeesite.wx.entity.excel.health;

import java.io.Serializable;
import java.util.Date;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class User implements Serializable {

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
    private String realName;
    private String sex;
    private String phone;
    private int age;
    private int score;
    private Date createTime;
    private String createTimeStr;

    private String salesMan;

    @ExcelField(title = "年龄", align = 2, sort = 4, fieldType = Integer.class)
    public int getAge() {
        return age;
    }

    @ExcelField(title = "中支", align = 2, sort = 9)
    public String getCity() {
        return city;
    }

    @ExcelField(title = "中支编号", align = 2, sort = 13)
    public String getCityCode() {
        return cityCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @ExcelField(title = "加入时间", align = 2, sort = 6)
    public String getCreateTimeStr() {
        return DateUtils.formatDateTime(createTime);
    }

    @ExcelField(title = "支公司", align = 2, sort = 10)
    public String getOrganization() {
        return organization;
    }

    @ExcelField(title = "支公司编号", align = 2, sort = 12)
    public String getOrganizationCode() {
        return organizationCode;
    }

    @ExcelField(title = "手机号", align = 2, sort = 2)
    public String getPhone() {
        return phone;
    }

    @ExcelField(title = "分公司", align = 2, sort = 8)
    public String getProvince() {
        return province;
    }

    @ExcelField(title = "分公司编号", align = 2, sort = 11)
    public String getProvinceCode() {
        return provinceCode;
    }

    @ExcelField(title = "姓名", align = 2, sort = 1)
    public String getRealName() {
        return realName;
    }

    @ExcelField(title = "营销员", align = 2, sort = 7)
    public String getSalesMan() {
        return salesMan;
    }

    @ExcelField(title = "评测得分", align = 2, sort = 5, fieldType = Integer.class)
    public int getScore() {
        return score;
    }

    @ExcelField(title = "性别", align = 2, sort = 3)
    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}