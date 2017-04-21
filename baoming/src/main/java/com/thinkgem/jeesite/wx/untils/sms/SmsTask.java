package com.thinkgem.jeesite.wx.untils.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsTask implements Runnable {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private String   phone;
    private String   content;
    private int      serialNo;

    public SmsTask(String phone, int serialNo, String content) {
        this.phone = phone;
        this.serialNo = serialNo;
        this.content = content;
    }

    @Override
    public void run() {
        int sendCode = SmsUtil.send(phone, serialNo, content);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("短信发布" + phone + "结果" + sendCode + "——正在执行："
                + SmsUtil.executor.getActiveCount() + "队列个数：" + SmsUtil.executor.getQueue().size());

    }
}
