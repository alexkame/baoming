<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
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
    
    <%@ include file="/WEB-INF/views/wx/common/js.jsp"%>
</head>

<body class="bg">

<div class="info">
    <img src="${ctxStatic}/wx/images/info.png" class="info-title"/>
    <div style="padding: 1rem">
        <div style="border: .1rem dashed #fff; color: #a8afba; padding: 1rem; font-size: 1.2rem; text-align: left; margin-bottom: 1rem">客户信息总数：${usersCount}人</div>
         <div style="height: 24rem; overflow: auto">
        <table class="table">
            <thead>
                <tr>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>手机号</td>
                    <td>城市</td>
                    <td>小孩姓名</td>
                    <td>小孩年龄</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.realName}</td>
                     <td>${user.sex}</td>
                    <td>${user.phone}</td>
                    <td>${user.city}</td>
                    <td>${user.childrenName}</td>
                    <td>${user.childrenClass}</td>
                </tr>
                 </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>
<div class="btn-wrap">
    <a class="share-btn" href="${pageContext.request.contextPath}/salesman/myuserMore"><img src="${ctxStatic}/wx/images/more-btn.png"/></a>
</div>
<img src="${ctxStatic}/wx/images/f-pic.png" class="footer"/>

</body>

</html>