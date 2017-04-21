/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.web;

import java.io.IOException;
import java.util.Date;
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

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.wx.entity.excel.CitySalesman;
import com.thinkgem.jeesite.wx.entity.excel.OrganizationSalesman;
import com.thinkgem.jeesite.wx.entity.excel.ProvinceSalesman;
import com.thinkgem.jeesite.wx.service.CacheService;
import com.thinkgem.jeesite.wx.service.SalesmanBaseService;
import com.thinkgem.jeesite.wx.service.SalesmanService;

/**
 */
@Controller
@RequestMapping(value = "statistics")
public class OldStatisticsController extends BaseController {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private SalesmanBaseService salesmanBaseService;
    @Autowired
    private CacheService    cacheService;


   

    /**
     * 首页
     * @throws IOException 
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
    	 return "wx/statistics/old";
    }
  
}
