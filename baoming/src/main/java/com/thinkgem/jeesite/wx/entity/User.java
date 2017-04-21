/**
 *
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 投票用户
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String realName;
    private String phone;
    private String sex;
    private int age;
    private String openid;

    private int type;

    private int luckDraw; // 抽奖次数

    private int useLuckDraw; // 使用抽奖次数

    private long salesManId;
    private Long repeatUserId;
    private long nciMoney;
    private Date createTime;
    private String hiddenRealName;
    private String hiddenPhone;

    private String childrenName;
    private Integer childrenClass;
    private String city;

    public int getAge() {
        return age;
    }

    public Integer getChildrenClass() {
        return childrenClass;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public String getCity() {
        return city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getHiddenPhone() {
        if (StringUtils.isBlank(phone)) {
            return "";
        } else {
            return phone.substring(0, 3) + "****" + phone.substring(7);
        }
    }

    public String getHiddenRealName() {
        if (StringUtils.isBlank(realName)) {
            return "";
        } else {
            return realName.substring(0, 1) + "**";
        }
    }

    public long getId() {
        return id;
    }

    public int getLuckDraw() {
        return luckDraw;
    }

    public long getNciMoney() {
        return nciMoney;
    }

    public String getOpenid() {
        return openid;
    }

    public String getPhone() {
        return phone;
    }

    public String getRealName() {
        return realName;
    }

    public Long getRepeatUserId() {
        return repeatUserId;
    }

    public long getSalesManId() {
        return salesManId;
    }

    public String getSex() {
        return sex;
    }

    public int getType() {
        return type;
    }

    public int getUseLuckDraw() {
        return useLuckDraw;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setChildrenClass(Integer childrenClass) {
        this.childrenClass = childrenClass;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLuckDraw(int luckDraw) {
        this.luckDraw = luckDraw;
    }

    public void setNciMoney(long nciMoney) {
        this.nciMoney = nciMoney;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setRepeatUserId(Long repeatUserId) {
        this.repeatUserId = repeatUserId;
    }

    public void setSalesManId(long salesManId) {
        this.salesManId = salesManId;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setUseLuckDraw(int useLuckDraw) {
        this.useLuckDraw = useLuckDraw;
    }

}