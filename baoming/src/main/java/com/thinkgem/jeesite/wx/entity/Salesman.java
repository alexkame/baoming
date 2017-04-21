/**
 *
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销员
 */
public class Salesman implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String idNum;
    private String phone;
    private byte[] photo;
    private String openid;
    private long votes;
    private Date createTime;
    private String filiale;
    private String channel;
    private String name;

    // private SalesmanBase baseInfo;

    public String getChannel() {
        return channel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getFiliale() {
        return filiale;
    }

    public long getId() {
        return id;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getName() {
        return name;
    }

    public String getOpenid() {
        return openid;
    }

    public String getPhone() {
        return phone;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public long getVotes() {
        return votes;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setFiliale(String filiale) {
        this.filiale = filiale;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

}