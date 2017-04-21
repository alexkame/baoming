package com.thinkgem.jeesite.wx.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.wx.service.SalesmanBaseService;

public class Statistics implements Runnable {

    /**
     * 日志对象
     */
    protected Logger            logger = LoggerFactory.getLogger(getClass());

    private SalesmanBaseService salesmanBaseService;

    private int                 method;
    private int                 type;
    private String              beginDate;
    private String              endDate;
    private String              province;
    private String              city;

    public Statistics(int method, int type, String province, String city, String beginDate,
            String endDate, SalesmanBaseService salesmanBaseService) {
        this.salesmanBaseService = salesmanBaseService;
        this.method = method;
        this.type = type;
        this.province = province;
        this.city = city;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    @Override
    public void run() {
        switch (method) {
        case 0:
            if (type == 0) {
                salesmanBaseService.getProvinceSalesmanList(beginDate, endDate);
            } else {
                salesmanBaseService.getSumProvinceSalesmanList(beginDate, endDate);
            }
            break;
        case 1:
            if (type == 0) {
                salesmanBaseService.getCitySalesmanList(beginDate, endDate, province);
            } else {
                salesmanBaseService.getSumCitySalesmanList(beginDate, endDate, province);
            }
            break;
        case 2:
            if (type == 0) {
                salesmanBaseService.getOrganizationSalesmanList(beginDate, endDate, province, city);
            } else {
                salesmanBaseService.getSumOrganizationSalesmanList(beginDate, endDate, province,
                        city);
            }
            break;
        default:
            break;
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("统计正在执行：" + SalesmanBaseService.executor.getActiveCount() + "队列个数："
                + SalesmanBaseService.executor.getQueue().size());

    }
}
