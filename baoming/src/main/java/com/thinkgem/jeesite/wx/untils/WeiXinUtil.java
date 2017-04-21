package com.thinkgem.jeesite.wx.untils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.impl.util.json.JSONTokener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.JedisUtils;

public class WeiXinUtil {

    /**
     * 日志对象
     */
    protected static Logger    logger       = LoggerFactory.getLogger(WeiXinUtil.class);

    // 公众号appid 、appsecret
    public static final String APPID        = Global.getConfig("weixin.appid");

    public static final String APPSECRET    = Global.getConfig("weixin.appsecret");

    public static final String OAURH_HOST   = Global.getConfig("weixin.oauth.host");

    // 授权链接
    public static final String OAUTH_URL    = "https://open.weixin.qq.com/connect/oauth2/authorize";
    // 通过code换取网页授权access_token 链接
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * Date类型转换为10位时间戳
     * 
     * @param time
     * @return
     * */
    public static Integer dateToTimestamp(Date time) {
        Timestamp ts = new Timestamp(time.getTime());
        return (int) ((ts.getTime()) / 1000);
    }

    /**
     * 获取 access_token
     * 
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        String access_token = "";
        Object act = JedisUtils.get("access_token");
        int expires_in;
        if (null == act) {

            synchronized (WeiXinUtil.class) {
                act = JedisUtils.get("access_token");
                if (act != null) {
                    return (String) act;
                }
                URL url = new URL(
                        "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                                + APPID + "&secret=" + APPSECRET);
                JSONObject json = getConnection(url);
                logger.info("获取微信 access_token:" + json.toString());
                access_token = json.getString("access_token");
                expires_in = json.getInt("expires_in");
                if (access_token == null) {
                    return null;
                }
                JedisUtils.set("access_token", access_token, expires_in);
            }

        } else {
            access_token = (String) act;
        }
        logger.info("微信 access_token:" + access_token);
        return access_token;
        // 断开连接

    }

    /**
     * 通过code换取网页授权access_token
     * 
     * @param code
     * @return
     */
    public static String getAccessToken(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        String url = ACCESS_TOKEN + "?appid=" + APPID + "&secret=" + APPSECRET + "&code=" + code
                + "&grant_type=authorization_code";

        String msagge = HttpUtil.get(url);
        return msagge;
    }

    public static JSONObject getConnection(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        connection.connect();
        JSONObject jsono = new JSONObject(new JSONTokener(new InputStreamReader(
                connection.getInputStream())));
        connection.disconnect();
        return jsono;
    }

    public static String getWeiXinTicket() throws Exception {
        String access_token = "";
        String ticket = "";
        Object apiticket = JedisUtils.get("ticket");

        if (null == apiticket) {

            synchronized (WeiXinUtil.class) {

                // apiticket = EhCacheUtils.get("ticket");
                apiticket = JedisUtils.get("ticket");

                if (apiticket != null) {
                    return (String) apiticket;
                }
                // 获取 access_token
                access_token = getAccessToken();
                URL url1 = new URL(
                        "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                                + access_token + "&type=jsapi");
                JSONObject json = getConnection(url1);
                logger.debug("获取微信 ticket:" + json.toString());
                ticket = (String) json.get("ticket");
                if (ticket == null) {
                    return null;
                }
                JedisUtils.set("ticket", ticket, json.getInt("expires_in"));

            }

        } else {
            ticket = (String) apiticket;
        }
        logger.debug("微信 ticket:" + ticket);
        return ticket;
        // 断开连接

    }

    /**
     * 获取微信授权的链接———————— 应用授权作用域，snsapi_base
     * 
     * @param url
     *            需要授权的页面链接
     * @return
     */
    public static String redirectWeiXinURL(String url) {

        try {
            url = URLEncoder.encode(url, "UTF-8");
            return OAUTH_URL + "?appid=" + APPID + "&redirect_uri=" + url
                    + "&response_type=code&scope=snsapi_base#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
