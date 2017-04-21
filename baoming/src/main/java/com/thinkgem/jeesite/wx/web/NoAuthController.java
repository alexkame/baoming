/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.wx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.common.web.BaseController;

/**
 */
@Controller
@RequestMapping(value = "noAuth")
public class NoAuthController extends BaseController {


    @RequestMapping("")
    public String noAuth() {
        return "wx/noAuth";
    }


}
