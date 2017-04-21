/**
 * 
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;

/**
 * 投票关系表
 */
public class UserSalesmanVote implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private long              userId;
    private long              salesManId;
    private int               num;
    private User              user;

    public long getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public long getSalesManId() {
        return salesManId;
    }

    public User getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setSalesManId(long salesManId) {
        this.salesManId = salesManId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}