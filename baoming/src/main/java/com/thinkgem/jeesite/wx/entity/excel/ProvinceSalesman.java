/**
 * 
 */
package com.thinkgem.jeesite.wx.entity.excel;

import java.io.Serializable;
import java.math.BigDecimal;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 省机构
 */
public class ProvinceSalesman implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer           rowNo;
    private String            province;
    private long              salesmanBaseCount;              // 在册人力
    private long              salesmanCount;                  // 微名片生成人力
    private String            salesmanCountPercentStr;        // 微名片人力占比
    private long              votes;                          // 累积总票数
    private double            avgVotes;                       // 累积人均票数
    private long              voteSalesmanCount;              // 累积参与人力
    private String            voteSalesmanCountPercentStr;    // 累积参与人力占比
    private long              userCount;                      // 累计投票客户
    private long              user0Count;                     // 一级客户
    private long              user1Count;                     // 二级客户
    private long              timeVotes;                      // 阶段票数
    private double            avgTimeVotes;                   // 人均票数
    private long              timeVoteSalesmanCount;          // 参与人力
    private String            timeVoteSalesmanCountPercentStr; // 参与人力占比
    private long              timeUserCount;                  // 阶段投票客户

    @ExcelField(title = "人均票数", align = 2, sort = 16)
    public double getAvgTimeVotes() {
        return new BigDecimal(timeVotes).divide(new BigDecimal(salesmanBaseCount), 2,
                BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @ExcelField(title = "累计人均票数", align = 2, sort = 9)
    public double getAvgVotes() {
        return new BigDecimal(votes).divide(new BigDecimal(salesmanBaseCount), 2,
                BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @ExcelField(title = "分公司", align = 2, sort = 2)
    public String getProvince() {
        return province;
    }

    @ExcelField(title = "排名", align = 2, sort = 1)
    public Integer getRowNo() {
        return rowNo;
    }

    @ExcelField(title = "在册人力", align = 2, sort = 5)
    public long getSalesmanBaseCount() {
        return salesmanBaseCount;
    }

    @ExcelField(title = "微名片生成人力", align = 2, sort = 6)
    public long getSalesmanCount() {
        return salesmanCount;
    }

    @ExcelField(title = "微名片人力占比", align = 2, sort = 7)
    public String getSalesmanCountPercentStr() {
        return new BigDecimal(salesmanCount * 100).divide(new BigDecimal(salesmanBaseCount), 1,
                BigDecimal.ROUND_HALF_UP).doubleValue()
                + "%";
    }

    @ExcelField(title = "阶段投票客户", align = 2, sort = 19)
    public long getTimeUserCount() {
        return timeUserCount;
    }

    @ExcelField(title = "阶段票数", align = 2, sort = 15)
    public long getTimeVotes() {
        return timeVotes;
    }

    @ExcelField(title = "参与人力", align = 2, sort = 17)
    public long getTimeVoteSalesmanCount() {
        return timeVoteSalesmanCount;
    }

    @ExcelField(title = "参与人力占比", align = 2, sort = 18)
    public String getTimeVoteSalesmanCountPercentStr() {
        return new BigDecimal(timeVoteSalesmanCount * 100).divide(
                new BigDecimal(salesmanBaseCount), 1, BigDecimal.ROUND_HALF_UP).doubleValue()
                + "%";
    }

    @ExcelField(title = "一级客户", align = 2, sort = 13)
    public long getUser0Count() {
        return user0Count;
    }

    @ExcelField(title = "二级客户", align = 2, sort = 14)
    public long getUser1Count() {
        return userCount - user0Count;
    }

    @ExcelField(title = "累计投票客户", align = 2, sort = 12)
    public long getUserCount() {
        return userCount;
    }

    @ExcelField(title = "累计总票数", align = 2, sort = 8)
    public long getVotes() {
        return votes;
    }

    @ExcelField(title = "累计参与人力", align = 2, sort = 10)
    public long getVoteSalesmanCount() {
        return voteSalesmanCount;
    }

    @ExcelField(title = "累计参与人力占比", align = 2, sort = 11)
    public String getVoteSalesmanCountPercentStr() {
        return new BigDecimal(voteSalesmanCount * 100).divide(new BigDecimal(salesmanBaseCount), 1,
                BigDecimal.ROUND_HALF_UP).doubleValue()
                + "%";
    }

    public void setAvgTimeVotes(double avgTimeVotes) {
        this.avgTimeVotes = avgTimeVotes;
    }

    public void setAvgVotes(double avgVotes) {
        this.avgVotes = avgVotes;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public void setSalesmanBaseCount(long salesmanBaseCount) {
        this.salesmanBaseCount = salesmanBaseCount;
    }

    public void setSalesmanCount(long salesmanCount) {
        this.salesmanCount = salesmanCount;
    }

    public void setSalesmanCountPercentStr(String salesmanCountPercentStr) {
        this.salesmanCountPercentStr = salesmanCountPercentStr;
    }

    public void setTimeUserCount(long timeUserCount) {
        this.timeUserCount = timeUserCount;
    }

    public void setTimeVotes(long timeVotes) {
        this.timeVotes = timeVotes;
    }

    public void setTimeVoteSalesmanCount(long timeVoteSalesmanCount) {
        this.timeVoteSalesmanCount = timeVoteSalesmanCount;
    }

    public void setTimeVoteSalesmanCountPercentStr(String timeVoteSalesmanCountPercentStr) {
        this.timeVoteSalesmanCountPercentStr = timeVoteSalesmanCountPercentStr;
    }

    public void setUser0Count(long user0Count) {
        this.user0Count = user0Count;
    }

    public void setUser1Count(int user1Count) {
        this.user1Count = user1Count;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public void setVoteSalesmanCount(long voteSalesmanCount) {
        this.voteSalesmanCount = voteSalesmanCount;
    }

    public void setVoteSalesmanCountPercentStr(String voteSalesmanCountPercentStr) {
        this.voteSalesmanCountPercentStr = voteSalesmanCountPercentStr;
    }

}