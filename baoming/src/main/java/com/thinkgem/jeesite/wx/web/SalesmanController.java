/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.User;
import com.thinkgem.jeesite.wx.service.CacheService;
import com.thinkgem.jeesite.wx.service.LotteryService;
import com.thinkgem.jeesite.wx.service.SalesmanService;

/**
 */
@Controller
@RequestMapping(value = "salesman")
public class SalesmanController extends BaseController {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private LotteryService lotteryService;
    @Autowired
    private CacheService cacheService;

    /**
     * 营销员主页
     */
    @RequestMapping("/main")
    public String main() {
        return "wx/salesman/main";
    }

    /**
     * 我的客户信息
     */
    @RequestMapping("/myuser")
    public String myUser(String name, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Salesman salesmanSeesion = (Salesman) session
                .getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);
        if (salesmanSeesion != null) {
            long salesmanId = salesmanSeesion.getId();
            List<User> users = cacheService.getUserListBySalmesManId(salesmanId);
            model.addAttribute("users", users.size() > 10 ? users.subList(0, 10) : users);
            model.addAttribute("usersCount", users.size());
        }
        model.addAttribute("name", name);
        return "wx/salesman/myuser";
    }

    /**
     * 我的客户信息
     */
    @RequestMapping("/myuserMore")
    public String myuserMore(String name, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Salesman salesmanSeesion = (Salesman) session
                .getAttribute(ConStant.WEIXIN_SEESION.SALESMAN);
        if (salesmanSeesion != null) {
            long salesmanId = salesmanSeesion.getId();
            List<User> users = cacheService.getUserListBySalmesManId(salesmanId);
            model.addAttribute("users", users);
            model.addAttribute("usersCount", users.size());
        }
        return "wx/salesman/myuserMore";
    }

    @RequestMapping("/userResult")
    public String userResult(int score, HttpServletRequest request, Model model) {
        String[] result;
        switch (ConStant.HEALTH_TYPE) {
        case 1:
            result = ConStant.scoreResult[score];
            break;
        case 2:
            result = ConStant.scoreResult2[score];
            break;
        default:
            result = ConStant.scoreResult[score];
            break;
        }
        model.addAttribute("result", result[0]);
        return "wx/salesman/userResult/" + ConStant.HEALTH_TYPE + "_" + result[1];
    }
}
