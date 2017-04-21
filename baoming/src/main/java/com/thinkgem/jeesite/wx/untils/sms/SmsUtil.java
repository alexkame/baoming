package com.thinkgem.jeesite.wx.untils.sms;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.untils.RegexUtils;
import com.wisdom.sms.ssrp.SMReceiver;
import com.wisdom.sms.ssrp.SSRP;
import com.wisdom.sms.ssrp.StatusIndicator;

public class SmsUtil {

    public static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 200,
                                                            TimeUnit.MILLISECONDS,
                                                            new LinkedBlockingQueue<Runnable>());

    public static int send(String phone, int serialNo, String content) {

        int code = -1;
        int operator_id = 0;
        String smsc_name = "SMCS";
        String smsc_addr = ConStant.SMS.SMSC_ADDR2;// SSRPServer端的地址
        if (RegexUtils.getMobileComByPhone(phone) == 1) {
            smsc_addr = ConStant.SMS.SMSC_ADDR;// SSRPServer端的地址
        }
        int port = ConStant.SMS.PORT;// SSRPServer的端口号
        String user_name = ConStant.SMS.USERNAME;// 连接用户名
        String password = ConStant.SMS.PASSWORD;// 连接密码
        int user_id = ConStant.SMS.USERID;// 用户id

        SMReceiver sm_receiver = new MyReceiverImpl();
        StatusIndicator status_indicator = new MyStatusIndicatorImpl();
        SSRP ssrp = new SSRP();
        if (ssrp.start(operator_id, smsc_name, smsc_addr, port, user_name, password, user_id,
                sm_receiver, status_indicator)) {

            if (ssrp.send(null, phone, "SMCS", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"),
                    DateUtils.formatDate(DateUtils.addDays(new Date(), 1), "yyyyMMddHHmmss"),
                    serialNo, content, "xx|日期(yyyyMMdd)|xx|xx|xx|xx", 10)) {
                code = 0;
            } else {
                code = -1;
            }
            // return ssrp.send(phone, serialNo, content);
        } else {
            code = -1;
        }
        ssrp.stop();
        return code;

    }
}
