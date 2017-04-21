package com.thinkgem.jeesite.wx.untils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {

    @SuppressWarnings("rawtypes")
    public static Map doXMLParse(InputStream in) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            List list = root.elements();
            Iterator it = list.iterator();
            Map m = new HashMap();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String k = e.getName();
                String v = "";
                List children = e.elements();
                if (children.isEmpty()) {
                    // v = e.getTextNormalize();
                    v = e.getTextTrim();
                } else {
                    v = XMLUtil.getChildrenText(children);
                }

                m.put(k, v);
            }
            return m;
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, Object> doXMLParse(InputStream in, boolean deep) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            List list = root.elements();
            Iterator it = list.iterator();
            Map<String, Object> m = new HashMap<String, Object>();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String k = e.getName();
                Object v = "";
                List children = e.elements();
                if (children.isEmpty()) {
                    // v = e.getTextNormalize();
                    v = e.getTextTrim();
                } else {
                    if (deep) {
                        v = XMLUtil.parseChild(children);
                    } else {
                        v = XMLUtil.getChildrenText(children);
                    }
                }

                m.put(k, v);
            }
            return m;
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Map doXMLParse(String strxml) {
        return doXMLParse(strxml, "UTF-8");
    }

    public static Map doXMLParse(String strxml, boolean deep) {
        if (deep) {
            return doXMLParse(strxml, "UTF-8", true);
        } else {
            return doXMLParse(strxml, "UTF-8");
        }
    }

    public static Map doXMLParse(String strxml, String enc) {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        byte[] bs = null;
        if (enc == null || "".equals(enc)) {
            bs = strxml.getBytes();
        } else {
            try {
                bs = strxml.getBytes(enc);
            } catch (UnsupportedEncodingException e) {
                bs = strxml.getBytes();
            }
        }
        InputStream in = new ByteArrayInputStream(bs);
        return doXMLParse(in);
    }

    public static Map<String, Object> doXMLParse(String strxml, String enc, boolean deep) {

        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        byte[] bs = null;
        if (enc == null || "".equals(enc)) {
            bs = strxml.getBytes();
        } else {
            try {
                bs = strxml.getBytes(enc);
            } catch (UnsupportedEncodingException e) {
                bs = strxml.getBytes();
            }
        }
        InputStream in = new ByteArrayInputStream(bs);
        return doXMLParse(in, deep);
    }

    /**
     * 获取子结点的xml
     * 
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextTrim();
                List list = e.elements();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(XMLUtil.getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    private static Map<String, Object> parseChild(List list) {
        Iterator it = list.iterator();
        Map<String, Object> m = new HashMap<String, Object>();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            Object v = "";
            List children = e.elements();
            if (children.isEmpty()) {
                // v = e.getTextNormalize();
                v = e.getTextTrim();
            } else {
                v = XMLUtil.parseChild(children);
            }
            m.put(k, v);
        }
        return m;
    }
}
