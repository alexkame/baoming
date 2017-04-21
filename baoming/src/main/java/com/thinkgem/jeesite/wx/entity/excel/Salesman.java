/**
 * 
 */
package com.thinkgem.jeesite.wx.entity.excel;

import java.io.Serializable;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 营销员
 */
public class Salesman implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer rowNo;
    private String            province;
    private String            city;
    private String            organization;
    private String            realName;
    private String            jobNum;
    private long              votes;
    private long              timeVotes;            // 阶段票数
    private long              userCount;            // 客户数
    private long              user0Count;           // 一级客户数
    private long              user1Count;           // 二级客户数

    @ExcelField(title = "排名", align = 2,sort=1)
    public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	@ExcelField(title = "中支", align = 2,sort = 3)
    public String getCity() {
        return city;
    }

    @ExcelField(title = "工号", align = 2,sort = 6)
    public String getJobNum() {
        return jobNum;
    }

    @ExcelField(title = "支公司", align = 2,sort = 4)
    public String getOrganization() {
        return organization;
    }

    @ExcelField(title = "分公司", align = 2,sort = 2)
    public String getProvince() {
        return province;
    }

    @ExcelField(title = "姓名", align = 2,sort = 5)
    public String getRealName() {
        return realName;
    }

    @ExcelField(title = "阶段票数", align = 2,sort=8)
    public long getTimeVotes() {
        return timeVotes;
    }

    @ExcelField(title = "一级客户", align = 2,sort = 10)
    public long getUser0Count() {
        return user0Count;
    }

    @ExcelField(title = "二级客户", align = 2,sort = 11)
    public long getUser1Count() {
        return userCount - user0Count;
    }

    @ExcelField(title = "累积客户", align = 2,sort = 9)
    public long getUserCount() {
        return userCount;
    }

    @ExcelField(title = "总票数", align = 2,sort = 7)
    public long getVotes() {
        return votes;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setTimeVotes(long timeVotes) {
        this.timeVotes = timeVotes;
    }

    public void setUser0Count(long user0Count) {
        this.user0Count = user0Count;
    }

    public void setUser1Count(long user1Count) {
        this.user1Count = user1Count;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

}