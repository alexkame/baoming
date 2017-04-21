<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%><!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <title>中英让TA绽放·不一Young的未来</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <meta content="email=no" name="format-detection">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta http-equiv="Cache-Control" content="max-age=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
     <script type="text/javascript">
    var canshare = true;
    var myLinkUrl = "<%=basePath %>/wx/activity?salesManId=${nci_salesman.id}"; 
    var myDesc = "${nci_salesman.name}邀请您来体验不一Young的暑期，給孩子一个不一Young的未来"; 
    </script>
      <%@ include file="/WEB-INF/views/wx/common/js.jsp"%>
      
</head>
<body class="bg">
<div class="pop"><img src="${ctxStatic}/wx/images/share.png" /></div>
<div class="pop-bg"></div>
<img src="${ctxStatic}/wx/images/r-pic.png" class="pos w100"/>
<div class="btn-wrap">
    <a class="share-btn"><img src="${ctxStatic}/wx/images/share-btn.png"/></a>
    <a href="${pageContext.request.contextPath}/salesman/myuser"><img src="${ctxStatic}/wx/images/my-btn.png"/></a>
</div>
<img src="${ctxStatic}/wx/images/f-pic.png" class="footer"/>
</body>

 <script type="text/javascript">
 $(function(){
     $(".share-btn").click(function(){
         $(".pop-bg").show();
         $(".pop").show();
         setTimeout(function(){
             $(".pop-bg").hide();
             $(".pop").hide();
         },3000)
     });
 })
 </script>
</html>