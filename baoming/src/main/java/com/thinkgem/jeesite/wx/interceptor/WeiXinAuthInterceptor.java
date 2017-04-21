package com.thinkgem.jeesite.wx.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.wx.constant.ConStant.WEIXIN_SEESION;
import com.thinkgem.jeesite.wx.entity.Salesman;
import com.thinkgem.jeesite.wx.entity.User;
import com.thinkgem.jeesite.wx.service.SalesmanService;
import com.thinkgem.jeesite.wx.service.UserService;
import com.thinkgem.jeesite.wx.untils.WeiXinUtil;

/**
 * 微信授权拦截器 通过微信网页授权获取openid，并加载相应用户信息到session中， 数据库中还未有当前微信用户信息，应先保存用户信息到数据中
 *
 */
public class WeiXinAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private SalesmanService salesmanService;
    @Autowired
    private UserService userService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        HttpSession session = request.getSession();

        // session.setAttribute(WEIXIN_SEESION.OPENID, "123");
        System.out.println("进入拦截器");
        String openid = (String) session.getAttribute(WEIXIN_SEESION.OPENID);
        if (openid == null) {
            // 判断是否从微信浏览器访问的
            String user_agent = request.getHeader("user-agent");
            int index = user_agent.indexOf("MicroMessenger");
            if (index > -1) {
                // 从微信访问过来的,判断是否授权的域名
                String url = request.getRequestURL().toString();
                if (url.indexOf(WeiXinUtil.OAURH_HOST) > -1) {
                    // 微信code
                    String code = request.getParameter("code");
                    if (StringUtils.isNotBlank(code)) {
                        // 取得微信code 获取openId
                        String jsonStr = WeiXinUtil.getAccessToken(code);
                        System.out.println("微信获取OPENID:" + jsonStr);
                        if (StringUtils.isNotBlank(jsonStr)) {
                            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                            if (jsonObject != null && jsonObject.getString("openid") != null) {
                                String openidStr = jsonObject.getString("openid");
                                if (StringUtils.isNotBlank(openidStr)) {
                                    session.removeAttribute(WEIXIN_SEESION.OPENID);
                                    session.setAttribute(WEIXIN_SEESION.OPENID, openidStr);
                                    Salesman salesman = salesmanService
                                            .getWithBaseByOpenid(openidStr);
                                    if (salesman != null) {
                                        session.removeAttribute(WEIXIN_SEESION.SALESMAN);
                                        session.setAttribute(WEIXIN_SEESION.SALESMAN, salesman);

                                    }
                                    User user = userService.getByOpenid(openidStr);
                                    if (user != null) {
                                        session.removeAttribute(WEIXIN_SEESION.USER);
                                        session.setAttribute(WEIXIN_SEESION.USER, user);
                                    }

                                    String redUrl = request.getRequestURL().toString();
                                    String params = "?";
                                    Map<String, String[]> map = request.getParameterMap();
                                    Set<Entry<String, String[]>> set = map.entrySet();
                                    Iterator<Entry<String, String[]>> it = set.iterator();
                                    while (it.hasNext()) {
                                        Entry<String, String[]> entry = it.next();
                                        for (String value : entry.getValue()) {
                                            String key = entry.getKey();
                                            if ("code".equals(key) || "state".equals(key)) {
                                                continue;
                                            }
                                            if (params.length() > 1) {
                                                params += "&";
                                            }
                                            params += key + "=" + value;
                                        }
                                    }
                                    System.out.println("---重定向:"+redUrl + params);
                                    response.sendRedirect(redUrl + params);
                                    return false;
                                }
                            }
                        }
                    } else {
                        // 添加url参数
                        if (StringUtils.isNotBlank(request.getQueryString())) {
                            url += "?" + request.getQueryString();
                        }
                        String redirectUrl = WeiXinUtil.redirectWeiXinURL(url);
                        System.out.println("===微信重定向:" + redirectUrl);
                        response.sendRedirect(redirectUrl);
                        return false;
                    }
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/noAuth");
                return false;
            }
        }
        return true;
    }

}
