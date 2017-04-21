package com.thinkgem.jeesite.wx.constant;

import java.util.Date;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.DateUtils;

/**
 * 常量类
 */
public class ConStant {

    /**
     * 奖品类型
     */
    public class PRIZE_TYPE {
        public static final int IWACTH = 3; // IWACTH
        public static final int LIULIANG = 2; // 流量包
        public static final int NCIMONEY = 1; // 新华币
    }

    /**
     * 短信通道信息
     */
    public static class SMS {
        public static final String SMSC_ADDR = Global.getConfig("sms.smscAddr"); // SSRPServer
                                                                                 // IP地址
        public static final String SMSC_ADDR2 = Global.getConfig("sms.smscAddr2"); // SSRPServer
        // IP地址
        public static final int PORT = Integer.parseInt(Global.getConfig("sms.port"));; // ssrpServer服务的端口号
                                                                                        // port取值范围[0,65535]
        public static final String USERNAME = Global.getConfig("sms.userName"); // ssrp连接的用户名
        public static final String PASSWORD = Global.getConfig("sms.password"); // ssrp连接的密码
        public static final int USERID = Integer.parseInt(Global.getConfig("sms.userID"));; // 用户账号（95555,95567等）
    }

    /**
     * 微信 session信息
     *
     * @author lei
     *
     */
    public class WEIXIN_SEESION {
        public static final String SALESMAN = "nci_salesman"; // 营销员
        public static final String SALESMAN_LOGIN = "nci_salesman_login"; // 营销员
        public static final String USER = "nci_user"; // 用户
        public static final String OPENID = "nci_openid"; // openid

        public static final String VOTE_SALESMANID = "nci_vote_salesmanId"; // 投票
        public static final String VOTE_REPEATUSERID = "nci_vote_repeatUserId"; // 转发投票

        public static final String SALESMAN_PHOTO = "nci_salesman_photo"; // 转发投票

    }

    /**
     * 中奖数
     */
    public static final int LOTTERY_NUM = Integer.parseInt(Global.getConfig("nci.lotteryNum"));

    /**
     * 活动结束时间
     */
    public static final Date LOTTERY_ENDDATE = DateUtils.parseDate(Global
            .getConfig("nci.lottery_endDate"));

    /**
     * 流量包 【运营商】【档次】
     */
    public static final int[][] MCH_NUM = { { 10, 30, 70, 500 }, { 20, 50, 100, 500 },
            { 10, 30, 100, 500 } };
    /**
     * mch_id
     */
    public static final String MCH_ID = Global.getConfig("mch.id");

    /**
     * mch_key
     */
    public static final String MCH_KEY = Global.getConfig("mch.key");

    /**
     * nci.activity.begin
     */
    public static final String ACTIVITY_BEGIN = Global.getConfig("nci.activity.begin");

    /**
     * 定时任务
     */
    public static final String TASK_JOB = Global.getConfig("nci.taskjob");

    public static final String LOGIN_OPENID = Global.getConfig("nci.login.openId");
    
    /**
     * 新华健康评测结果
     */
    public static final String[][] scoreResult = {{"99.9","good"}
    											 ,{"98.8","good"}
    											 ,{"95.6","good"}
    											 ,{"92.5","good"}
    											 ,{"88.9","good"}
    											 ,{"80.5","good"}
    											 ,{"77.8","bad"}
    											 ,{"66.7","bad"}
    											 ,{"55.5","bad"}
    											 ,{"44.4","bad"}
    											 ,{"33.3","bad"}
    											 ,{"22.2","bad"}
    											 ,{"11.1","bad"}
    											};
    /**
     * 新华癌症体质健康评测结果
     */
    public static final String[][] scoreResult2 = {{"99.9","good"}
    											 ,{"95.6","good"}
    											 ,{"92.3","good"}
    											 ,{"86.9","good"}
    											 ,{"80.8","good"}
    											 ,{"75.7","good"}
    											 ,{"67.6","normal"}
    											 ,{"55.5","normal"}
    											 ,{"41.4","normal"}
    											 ,{"33.3","normal"}
    											 ,{"25.5","normal"}
    											 ,{"16.7","normal"}
    											 ,{"13.6","littlebad"}
    											 ,{"12.5","littlebad"}
    											 ,{"11.3","littlebad"}
    											 ,{"9.7","littlebad"}
    											 ,{"7.4","littlebad"}
    											 ,{"5.7","littlebad"}
    											 ,{"3.3","bad"}
    											 ,{"2.2","bad"}
    											 ,{"1.1","bad"}
    											};


    /**
     * 新华健康当前的评测项目类型
     */
    public static final int HEALTH_TYPE = Integer.parseInt(Global.getConfig("nci.health.type"));
}
