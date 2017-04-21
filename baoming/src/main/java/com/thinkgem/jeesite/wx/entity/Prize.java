/**
 * 
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * 奖品池
 */
public class Prize implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private int               type;
    private long              num;
    private long              putOutNum;
    private Date              createDate;
    private Time              createTime;

    public Date getCreateDate() {
        return createDate;
    }

    public Time getCreateTime() {
        return createTime;
    }

    public long getId() {
        return id;
    }

    public long getNum() {
        return num;
    }

    public long getPutOutNum() {
        return putOutNum;
    }

    public int getType() {
        return type;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setPutOutNum(long putOutNum) {
        this.putOutNum = putOutNum;
    }

    public void setType(int type) {
        this.type = type;
    }

}