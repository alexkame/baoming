package com.thinkgem.jeesite.wx.task;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.service.LotteryService;
import com.thinkgem.jeesite.wx.untils.HttpUtil;

@Service
@Lazy(false)
public class TaskJob {
    /**
     * 日志对象
     */
    protected Logger       logger = LoggerFactory.getLogger(TaskJob.class);
    @Autowired
    private LotteryService lotteryService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void job1() {

        if (!"1".equals(ConStant.TASK_JOB)) {
            return;
        }

        String url = "http://card.orientalwisdom.com/udp/xhbx/queryCardStatus?token=i8GXryxr2wwlAODq4RlUQA==";
        String str = HttpUtil.get(url);
        // String str =
        // "[{\"mch_billno\":\"4096865062201511191001787158\"},{\"mch_billno\":\"4096865062201511191001773220\"},{\"mch_billno\":\"4096865062201511191001787233\"},{\"mch_billno\":\"4096865062201511191001614191\"},{\"mch_billno\":\"4096865062201511191001794702\"}]";
        logger.info("请求流量领取接口：" + str);
        if (StringUtils.isNotBlank(str)) {
            JSONArray array = JSONArray.parseArray(str);
            if (array != null && array.size() > 0) {
                JSONObject jsonObject;
                String mch_billno;
                String lotteryStr;
                long lotteryId;
                for (Object object : array) {
                    if (object != null) {
                        jsonObject = (JSONObject) object;
                        mch_billno = jsonObject.getString("mch_billno");
                        if (StringUtils.isNotBlank(mch_billno) && mch_billno.length() == 28) {
                            lotteryStr = mch_billno.substring(18);
                            lotteryId = Long.parseLong(lotteryStr) - 1000000000;
                            lotteryService.updateStatus(lotteryId, 1, null);
                        }
                    }
                }
            }
        }

    }
}