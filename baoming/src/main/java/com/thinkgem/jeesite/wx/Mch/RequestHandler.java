package com.thinkgem.jeesite.wx.Mch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.thinkgem.jeesite.wx.untils.MD5Util;
import com.thinkgem.jeesite.wx.untils.XMLUtil;

/**
 * 流量包
 * 
 */
public class RequestHandler {

    private SortedMap<String, String> parameters;
    private String                    charset;
    private String                    sign;
    private String                    key;
    private String                    paramersStr;

    public RequestHandler() {
        charset = "UTF-8";
        parameters = new TreeMap<String, String>();
    }

    public void addParam(String key, String value) {
        parameters.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> doParse(String xmlContent) {
        try {
            parameters.clear();
            Map<String, Object> map = XMLUtil.doXMLParse(xmlContent);
            if (map != null) {
                // 设置参数
                Iterator<String> it = map.keySet().iterator();
                while (it.hasNext()) {
                    String k = it.next();
                    String v = (String) map.get(k);
                    setParameter(k, v);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCharset() {
        return charset;
    }

    public String getKey() {
        return key;
    }

    public String getParamersStr() {
        return paramersStr;
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public SortedMap<String, String> getParameters() {
        return parameters;
    }

    public String getSign() {
        return sign;
    }

    /**
     * 获得键值对字典排序字符串
     * 
     * @return
     */
    public String getSortKeyValueString() {
        if (parameters != null || parameters.size() > 0) {
            List<String> list = new ArrayList<String>();
            for (String key : parameters.keySet()) {
                String value = parameters.get(key);
                if (value == null || value.length() == 0) {
                    continue;
                }
                if (!"sign".equals(key)) {
                    list.add(key + "=" + value);
                }
            }
            if (list.size() > 0) {
                Collections.sort(list);
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    String kvStr = list.get(i);
                    if (i == 0) {
                        sb.append(kvStr);
                    } else {
                        sb.append("&" + kvStr);
                    }
                }
                return sb.toString();
            }
        }
        return null;
    }

    /**
     * 获得请求报文
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public String getXml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("<" + k + ">" + v + "</" + k + ">\n");
            }
        }
        sb.append("<sign>");
        sb.append("<![CDATA[");
        sb.append(sign);
        sb.append("]]>");
        sb.append("</sign>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 获得请求报文
     * 
     * @return
     */
    @SuppressWarnings("rawtypes")
    public String getXml(boolean isCDATA) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        if (isCDATA) {
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String k = (String) entry.getKey();
                String v = (String) entry.getValue();
                if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                    sb.append("<" + k + "><![CDATA[" + v + "]]></" + k + ">\n");
                }
            }
        } else {
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String k = (String) entry.getKey();
                String v = (String) entry.getValue();
                if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                    sb.append("<" + k + ">" + v + "</" + k + ">\n");
                }
            }
        }
        sb.append("<sign>");
        sb.append("<![CDATA[");
        sb.append(sign);
        sb.append("]]>");
        sb.append("</sign>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * md5签名
     * 
     * @return
     */
    public String Md5Sign() {
        if (paramersStr == null) {
            paramersStr = getSortKeyValueString();
        }
        if (paramersStr != null) {
            StringBuffer sb = new StringBuffer(paramersStr);
            sb.append("&key=");
            sb.append(key);
            System.out.println(sb.toString());
            sign = MD5Util.MD5Encode(sb.toString(), charset).toUpperCase();
        }
        return sign;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setParamersStr(String paramersStr) {
        this.paramersStr = paramersStr;
    }

    /**
     * 设置参数值
     * 
     * @param parameter
     *            参数名称
     * @param parameterValue
     *            参数值
     */
    public void setParameter(String parameter, String parameterValue) {
        String v = "";
        if (null != parameterValue) {
            v = parameterValue.trim();
        }
        parameters.put(parameter, v);
    }

    public void setParameters(SortedMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean validSign() {
        String sign = Md5Sign();
        String paramSign = getParameter("sign");
        if (sign == null) {
            return false;
        }
        if (paramSign == null) {
            return false;
        }
        return sign.equals(paramSign);
    }
}
