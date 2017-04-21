/**
 * 
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享日志
 */
public class ShareLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private long              userId;
    private Long              salesManId;
    private Long              repeatUserId;
    private Date              createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public long getId() {
        return id;
    }

    public Long getRepeatUserId() {
        return repeatUserId;
    }

    public Long getSalesManId() {
        return salesManId;
    }

    public long getUserId() {
        return userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRepeatUserId(Long repeatUserId) {
        this.repeatUserId = repeatUserId;
    }

    public void setSalesManId(Long salesManId) {
        this.salesManId = salesManId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}