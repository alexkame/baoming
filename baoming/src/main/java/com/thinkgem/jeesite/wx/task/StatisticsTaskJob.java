package com.thinkgem.jeesite.wx.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.runnable.Statistics;
import com.thinkgem.jeesite.wx.service.SalesmanBaseService;

@Service
@Lazy(false)
public class StatisticsTaskJob {
    /**
     * 日志对象
     */
    protected Logger            logger = LoggerFactory.getLogger(StatisticsTaskJob.class);
    @Autowired
    private SalesmanBaseService salesmanBaseService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void job1() {

        if (!"1".equals(ConStant.TASK_JOB)) {
            return;
        }
        String beginDate = "2015-11-16";
        String endDate = DateUtils.formatDate(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");

        SalesmanBaseService.executor.execute(new Statistics(2, 0, null, null, beginDate, endDate,
                salesmanBaseService));
        SalesmanBaseService.executor.execute(new Statistics(2, 1, null, null, beginDate, endDate,
                salesmanBaseService));
        SalesmanBaseService.executor.execute(new Statistics(1, 0, null, null, beginDate, endDate,
                salesmanBaseService));
        SalesmanBaseService.executor.execute(new Statistics(1, 1, null, null, beginDate, endDate,
                salesmanBaseService));
        SalesmanBaseService.executor.execute(new Statistics(0, 0, null, null, beginDate, endDate,
                salesmanBaseService));
        SalesmanBaseService.executor.execute(new Statistics(0, 1, null, null, beginDate, endDate,
                salesmanBaseService));

        List<Map> provinces = salesmanBaseService.getProvinces();
        if (provinces != null && provinces.size() > 0) {
            Object proObject;
            Object citiesObj;
            String[] cities;
            for (Map provinceMap : provinces) {
                proObject = provinceMap.get("province");
                if (proObject != null) {

                    SalesmanBaseService.executor.execute(new Statistics(1, 0, provinceMap
                            .get("province") + "", null, beginDate, endDate, salesmanBaseService));
                    SalesmanBaseService.executor.execute(new Statistics(1, 1, provinceMap
                            .get("province") + "", null, beginDate, endDate, salesmanBaseService));

                    SalesmanBaseService.executor.execute(new Statistics(2, 0, provinceMap
                            .get("province") + "", null, beginDate, endDate, salesmanBaseService));
                    SalesmanBaseService.executor.execute(new Statistics(2, 1, provinceMap
                            .get("province") + "", null, beginDate, endDate, salesmanBaseService));

                    // citiesObj = provinceMap.get("cities");
                    // if (citiesObj != null) {
                    // cities = (citiesObj + "").split(",");
                    // if (cities != null && cities.length > 0) {
                    // for (String city : cities) {
                    // SalesmanBaseService.executor.execute(new Statistics(2, 0,
                    // provinceMap.get("province") + "", city, beginDate,
                    // endDate,
                    // salesmanBaseService));
                    // SalesmanBaseService.executor.execute(new Statistics(2, 1,
                    // provinceMap.get("province") + "", city, beginDate,
                    // endDate,
                    // salesmanBaseService));
                    // }
                    // }
                    // }
                }

            }
        }

    }
}