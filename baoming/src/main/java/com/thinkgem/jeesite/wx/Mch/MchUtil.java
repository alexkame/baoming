package com.thinkgem.jeesite.wx.Mch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.wx.constant.ConStant;
import com.thinkgem.jeesite.wx.entity.Lottery;
import com.thinkgem.jeesite.wx.untils.WeiXinUtil;

public class MchUtil {

    public static String getMch(Lottery lottery, String openId, String ip) {

        URLConnection urlConnection = null;
        HttpURLConnection httpUrlConnection = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("http://card.orientalwisdom.com/udp/mch/createOrder");
            urlConnection = url.openConnection();
            httpUrlConnection = (HttpURLConnection) urlConnection;
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setRequestProperty("POST", "/violations HTTP/1.1");
            httpUrlConnection.setRequestMethod("POST");
            httpUrlConnection.connect();
            RequestHandler reqHandler = new RequestHandler();
            reqHandler.setParameter("nonce_str", UUID.randomUUID().toString().replace("-", ""));
            reqHandler.setParameter("timestamp", System.currentTimeMillis() / 1000 + "");
            long seqnum = lottery.getId() + 1000000000;
            reqHandler.setParameter("mch_billno",
                    ConStant.MCH_ID + DateUtils.formatDate(new Date(), "yyyyMMdd") + seqnum);
            reqHandler.setParameter("mch_id", ConStant.MCH_ID);
            reqHandler.setParameter("wxappid", WeiXinUtil.APPID);
            reqHandler.setParameter("flow_code", lottery.getFlowCode());
            reqHandler.setParameter("client_ip", ip);
            reqHandler.setParameter("result_type", "url");
            reqHandler.setParameter("openid", openId);
            reqHandler.setKey(ConStant.MCH_KEY);
            reqHandler.Md5Sign();
            String outputStr = reqHandler.getXml(true);
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConnection.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            InputStream inputStream = httpUrlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
        return null;
    }
}
