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
        <script>
        $(function(){
            //公公司
            $("#city-btn").click(function(){
                var h = $(".drop").height()/2*(-1);
                $(".drop").css({
                    'margin-top':h + 'px'
                });
                $(".pop-bg").show();
                $("#city").show();
            })
            $(".city-box li").click(function(){
                $("#city-btn").val($(this).text());
                $(".pop-bg").hide();
                $("#city").hide();
            })

            //渠道
            $("#canals-btn").click(function(){
                var h = $(".drop").height()/2*(-1);
                $(".drop").css({
                    'margin-top':h + 'px'
                });
                $(".pop-bg").show();
                $("#canals").show();
            });
            $(".canals-box li").click(function(){
                $("#canals-btn").val($(this).text());
                $(".pop-bg").hide();
                $("#canals").hide();
            })

       })
    </script>
</head>
<body class="bg">
<div class="drop" id="city">
    <div class="city-box">
        <ul>
            <li>北京</li>
            <li>上海</li>
            <li>河南</li>
            <li>辽宁</li>
            <li>黑龙江</li>
            <li>河北</li>
            <li>山东</li>
            <li>福建</li>
            <li>江苏</li>
            <li>广东</li>
            <li>四川</li>
            <li>湖北</li>
            <li>湖南</li>
        </ul>
    </div>
</div>

<div class="drop" id="canals">
    <ul class="canals-box">
        <li>个险</li>
        <li>经代</li>
        <li>银保</li>
        <li>电销</li>
        <li>职场营销</li>
    </ul>
</div>

<img src="${ctxStatic}/wx/images/1.png" class="pos w100"/>
<div class="info">
    <ul class="formDiv" style="padding-top: 14rem">
        <li><em><img src="${ctxStatic}/wx/images/f-text-7.png" /></em><input type="text" id="city-btn" value="${nci_salesman.filiale}" readonly /></li>
        <li><em><img src="${ctxStatic}/wx/images/f-text-8.png" /></em><input type="text" id="canals-btn" value="${nci_salesman.channel}" readonly /></li>
        <li><em><img src="${ctxStatic}/wx/images/f-text-9.png" /></em><input type="text" id="jobNum" value="${nci_salesman.idNum}" <c:if test="${!empty nci_salesman}"> disabled="disabled" </c:if>/></li>
        <li><em><img src="${ctxStatic}/wx/images/f-text-2.png" /></em><input type="text" id="name" value="${nci_salesman.name}"/></li>
        <li><em><img src="${ctxStatic}/wx/images/f-text-10.png" /></em><input type="text" id="phone" value="${nci_salesman.phone}"/></li>
    </ul>
    <p style="padding: 1rem 5rem 0 5rem"><img src="${ctxStatic}/wx/images/text-i.png" class="w100"/></p>
     <div class="btn-wrap" style="bottom: 1.5rem">
        <a onclick="addSalesman();"><img src="${ctxStatic}/wx/images/submit.png"/></a>
    </div>
</div>

<img src="${ctxStatic}/wx/images/f-pic.png" class="footer"/>
</body>

 <%@ include file="/WEB-INF/views/wx/common/alert.jsp"%>
 
 <script type="text/javascript">
 function addSalesman(){
 
            if($("#city-btn").val()==""){
                nciAlert("分公司不能为空，请输入");
                return false;
            }
            if($("#canals-btn").val()==""){
                nciAlert("渠道不能为空，请输入");
                return false;
            }
            if(!isCNName($("#name").val())){
                nciAlert("姓名有误，请重新输入");
                return false;
            }
            if($("#jobNum").val()==""){
                nciAlert("工号不能为空，请输入");
                return false;
            }
            var phonestatus = validateMobile($("#phone").val());
            if(phonestatus!=3){
                nciAlert("手机号有误，请重新输入");
                return false;
            }
             $.ajax({
                 type:'get',
                 url:'${pageContext.request.contextPath}/weixin/addSalesman',
                 data: { "name":encodeURIComponent($("#name").val()),"jobNum":$("#jobNum").val(),"phone":$("#phone").val(),"filiale":$("#city-btn").val(),"channel":$("#canals-btn").val()},
                 success:function(data){
                     if(data!=null){
                         if(data.code<0){
                             nciAlert(data.msg); 
                         }else{
                             window.location.href="${pageContext.request.contextPath}/wx/newsalesman?salesManId="+data.msg; 
                         }
                     }else{
                         nciAlert("提交失败,请重试");
                     }
                 },
                 error:function(XmlHttpRequest,textStatus,errorThrown){
                     nciAlert("提交异常,请重试");
                 }
             });
        }       
</script>
</html>