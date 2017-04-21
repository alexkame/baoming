<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE >
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <title>新华保险-新时代优秀营销员评选活动</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta content="email=no" name="format-detection">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta http-equiv="Cache-Control" content="max-age=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="${ctxStatic}/wx/css/main.css">
    <script src="${ctxStatic}/wx/js/jquery-1.9.0.min.js" type="text/javascript"></script>
     <script>
        $(function(){
            var h = $(".tips-box").height()/2*(-1);
            $(".tips-box").css({
                'margin-top':h + 'px'
            })
        })
    </script>
     <style>
        .tips-box { position: fixed; left:50%; top:50%; width: 80%; margin-left:-40%; text-align: center; color: #fff; font-size: 16px; line-height: 150%}
    </style>
</head>

<body style="background: url(${ctxStatic}/wx/images/tipsBg.jpg) 0 0 repeat">
<div class="tips-box">活动异常火爆！<br />正在处理前面客户需求。<br />请您稍等片刻哦！</div>
</body>
</html>
