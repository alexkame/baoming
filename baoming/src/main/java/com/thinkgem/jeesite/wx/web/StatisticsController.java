/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.wx.service.CacheService;
import com.thinkgem.jeesite.wx.service.SalesmanBaseService;
import com.thinkgem.jeesite.wx.service.SalesmanService;

/**
 */
@Controller
@RequestMapping(value = "newStatistics")
public class StatisticsController extends BaseController {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private SalesmanBaseService salesmanBaseService;
    @Autowired
    private CacheService cacheService;

    private int pageSize = 100;

    /**
     * 城市
     */
    @RequestMapping("/getCities")
    @ResponseBody
    public Object getCities(HttpServletRequest request, HttpServletResponse response) {
        List<Map> lists = cacheService.getCities();
        return lists;
    }

    /**
     * 省份
     */
    @RequestMapping("/getProvices")
    @ResponseBody
    public Object getProvices(HttpServletRequest request, HttpServletResponse response) {
        List<Map> lists = cacheService.getProvinces();
        return lists;
    }

    /**
     * 首页
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, Model model) {
        return "wx/statistics/index";
    }

    /**
     * 四级机构
     */
    @RequestMapping("/organization")
    public String organization(HttpServletRequest request, HttpServletResponse response, Model model) {
        String province = request.getParameter("province");
        String city = request.getParameter("city");

        if (StringUtils.isNotBlank(request.getParameter("export"))) {
            try {
                List<com.thinkgem.jeesite.wx.entity.excel.health.OrganizationSalesman> salesmans = salesmanBaseService
                        .getOrganizationSalesmanCountList(province, city, null);

                String fileName = province + city + "分公司及中支公司数据.xlsx";

                new ExportExcel("分公司及中支公司数据", null,
                        com.thinkgem.jeesite.wx.entity.excel.health.OrganizationSalesman.class)
                        .setDataList(salesmans).write(response, fileName).dispose();
                return null;
            } catch (Exception e) {
                logger.error("导出支公司数据异常：" + e.getMessage());
                e.printStackTrace();
                model.addAttribute("error", "系统异常，请稍后再试");
                return "wx/statistics/organization";
            }
        } else {
            return "wx/statistics/organization";
        }
    }

    /**
     * 营销员清单列表
     */
    @RequestMapping("/salesman")
    public String salesman(HttpServletRequest request, HttpServletResponse response, Model model) {
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String qudao = request.getParameter("qudao");

        if (StringUtils.isNotBlank(request.getParameter("export"))) {
            try {
                List<com.thinkgem.jeesite.wx.entity.excel.health.Salesman> salesmans = salesmanBaseService
                        .getSalesmanList(qudao, province, city, null);
                String fileName = province + city + "营销员清单.xlsx";

                new ExportExcel("营销员清单列表", null,
                        com.thinkgem.jeesite.wx.entity.excel.health.Salesman.class)
                        .setDataList(salesmans).write(response, fileName).dispose();
                return null;
            } catch (Exception e) {
                logger.error("导出营销员清单列表异常：" + e.getMessage());
                e.printStackTrace();
                model.addAttribute("error", "系统异常，请稍后再试");
                return "wx/statistics/salesman";
            }
        } else {
            return "wx/statistics/salesman";
        }

    }

    /**
     * 客户清单列表
     */
    @RequestMapping("/user")
    public String user(HttpServletRequest request, HttpServletResponse response, Model model) {
        String province = request.getParameter("province");
        String city = request.getParameter("city");

        if (StringUtils.isNotBlank(request.getParameter("export"))) {
            try {
                List<com.thinkgem.jeesite.wx.entity.excel.health.User> salesmans = salesmanBaseService
                        .getUserList(province, city, null);
                String fileName = province + city + "客户清单.xlsx";

                new ExportExcel("客户列表", null,
                        com.thinkgem.jeesite.wx.entity.excel.health.User.class)
                        .setDataList(salesmans).write(response, fileName).dispose();
                return null;
            } catch (Exception e) {
                logger.error("导出营销员清单列表异常：" + e.getMessage());
                e.printStackTrace();
                model.addAttribute("error", "系统异常，请稍后再试");
                return "wx/statistics/user";
            }
        } else {
            return "wx/statistics/user";
        }

    }
}
