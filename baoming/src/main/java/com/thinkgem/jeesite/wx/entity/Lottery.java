/**
 * 
 */
package com.thinkgem.jeesite.wx.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户获得奖品
 */
public class Lottery implements Serializable {

    private static final long serialVersionUID = 1L;

    private long              id;
    private long              userId;
    private int               type;
    private int               num;
    private int               status;               // 状态 0未发 1发送成功2发送失败3领取中
    private String            remark;
    private Date              createTime;
    /**
     * flow_code: 10001 //移动10M/联通20M/电信10M 10002 //移动30M/联通50M/电信30M
     * 10003移动70M/联通100M/电信100M 10004 //移动500M/联通500M/电信500M
     */
    private String            flowCode;

    private User              user;
    private String            typeName;
    private String            unit;                  // 单位

    public Date getCreateTime() {
        return createTime;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public long getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getRemark() {
        return remark;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        String name = null;
        switch (type) {
        case 3:
            name = "苹果iwatch一部";
            break;
        case 2:
            name = "流量包";
            break;
        case 1:
            name = "新华币";
            break;
        default:
            break;
        }
        return name;
    }

    public String getUnit() {
        String name = null;
        switch (type) {
        case 3:
            name = "部";
            break;
        case 2:
            name = "兆";
            break;
        case 1:
            name = "枚";
            break;
        default:
            break;
        }
        return name;
    }

    public User getUser() {
        return user;
    }

    public long getUserId() {
        return userId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}