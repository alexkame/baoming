<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html lang="ch" manifest="/appcache/act/1797?_t=1409643350">
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Expires" CONTENT="-1">
    <title>中英让TA绽放·不一Young的未来</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/wx/activity/css/main.css" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="js/html5.js"></script>
    <![endif]-->
    <script type="text/javascript">
    var canshare = true;
    var myLinkUrl = "<%=basePath %>/wx/activity?salesManId=${salesman.id}"; 
    var myDesc = "竟然有这么不一Young的暑期活动！我已报名，你也快来！"; 
    </script>
    
    <script type="text/javascript">
        var phoneWidth =  parseInt(window.screen.width);
        var phoneScale = phoneWidth/640;
        var ua = navigator.userAgent;
        if (/Android (\d+\.\d+)/.test(ua)){
            var version = parseFloat(RegExp.$1);
            if(version>2.3){
                document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
            }else{
                document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
            }
        } else {
            document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
        }
    </script>
    <script type="text/javascript" src="${ctxStatic}/wx/activity/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/wx/activity/js/my.js"></script>
    <script src="${ctxStatic}/wx/js/jweixin-1.0.0.js" type="text/javascript"></script>
    <script src="${ctxStatic}/wx/js/nciWx.js" type="text/javascript"></script>
     <script src="${ctxStatic}/wx/js/common.js" type="text/javascript"></script>
     
    <script>
        $(function(){
            $("input[name=radio1]").attr("disabled","disabled");
            //$("input[name=radio2]").attr("disabled","disabled");

            $("#bm").click(function(){

                $(".drop").show();
                $(".pop-bg").show();

                $("input[name=radio]").change(function() {
                    $("#bm").val($(this).parent().next().eq(0).attr("city"));
                    $("input[name=radio1]").removeAttr('checked');
                    //$("input[name=radio2]").removeAttr('checked');
                    $(this).parent().next().next().next().find("input").removeAttr("disabled");
                    $("#childrenName").val("");
                    $("#childrenClass").val("");
                    $("#childrenName").addClass("grg");
                    $("#childrenClass").addClass("grg");
                    $("#childrenName").attr("readonly","readonly");
                    $("#childrenClass").attr("readonly","readonly");
                });
                $("input[name=radio1]").click(function() {
                	 var chidren = $('input[name="radio1"]:checked ').length;
                     if(chidren>0){
                    	 $("#childrenName").removeClass("grg");
                    	 $("#childrenClass").removeClass("grg");
                    	 $("#childrenName").removeAttr("readonly");
                    	 $("#childrenClass").removeAttr("readonly");
                     }else{
                    	 $("#childrenName").val("");
                    	 $("#childrenClass").val("");
                    	 $("#childrenName").addClass("grg");
                    	 $("#childrenClass").addClass("grg");
                    	 $("#childrenName").attr("readonly","readonly");
                    	 $("#childrenClass").attr("readonly","readonly");
                     }
                });
                /*
                 $("input[name=radio1]").change(function() {
                 $("#bm").val($(this).parent().parent().find("img")[0].title + $(this).next()[0].title);
                 });
                 $("input[name=radio2]").change(function() {
                 $("#bm").val($(this).parent().parent().find("img")[0].title + $(this).next()[0].title);
                 });
                 */
            });
            $(".sub").click(function() {
                $(".drop").hide();
                $(".pop-bg").hide();
            })
	       $("#sex").click(function(){

				var h = $(".sex").height()/2*(-1);
				$(".sex").css({
					'margin-top': h + 'px'
				});

				$(".sex").show();
				$(".pop-bg").show();
			})
			$(".sex li").click(function(){
				$(".pop-bg").hide();
				$(".sex").hide();
				$("#sex").val($(this).text())
			})

        })
    </script>
    
        <style>
        .yun {
            position: absolute; left: 0; top: 5rem; width: 100%; text-align: center; z-index: 2;
            animation:myfirst 1s linear 2s infinite alternate;
            -webkit-animation:myfirst 1s linear 1s infinite alternate;
        }

        @-webkit-keyframes myfirst /* Safari and Chrome */
        {
            0%   {margin-left:0 }
            100%  { margin-left:2rem}
        }

        .page1 {
            position: absolute; left: 3%; top: 17%; width: 40%;
            -webkit-animation:line 1s infinite;
            -webkit-transform-origin:top center;
        }

        .page2-1 {
            position: absolute; right: -10%; top: 7%; width: 40%;
            -webkit-animation:line 1s infinite;
            -webkit-transform-origin:bottom center;
        }
        .page2-2 {
            position: absolute; left: 3%; bottom:44%; width: 20%;
            -webkit-animation:line 1s infinite;
            -webkit-transform-origin:bottom center;
        }
        .page2-3 {
            position: absolute; left: 3%; bottom: 20%; width: 18%;
            -webkit-animation:line 1s infinite;
            -webkit-transform-origin:bottom center;
        }

        @-webkit-keyframes line{
            0%{-webkit-transform:rotate(-20deg)}
            50%{-webkit-transform:rotate(20deg)}
            100%{-webkit-transform:rotate(-20deg)}
        }

        .page3-1 {
            position: absolute; left: 0; top: 0; width: 100%; z-index: 1;
            animation:myfirst 1s linear 2s infinite alternate;
            -webkit-animation:myfirst 1s linear 1s infinite alternate;}

        .page3-img img {
            animation:op 1s linear 2s infinite alternate;
            -webkit-animation:op 1s linear 1s infinite alternate;
        }
        @-webkit-keyframes op{
            0%{opacity: 1}
            100%{opacity: .2}
        }

        .page5-img {
            position: absolute; left: 15%; bottom: 32%; width: 70%;
            animation: left-f 2s;
            -webkit-animation:left-f 2s;
        }

        @-webkit-keyframes left-f{
            0%{left:-55%}
            100%{left: 15%}
        }

        .page7-3{
            position: absolute; left: -5%; bottom: 22%; width: 60%; z-index: 2;
            animation: left-1 2s;
            -webkit-animation:left-1 2s;
        }
        @-webkit-keyframes left-1{
            0%{left:-55%}
            100%{left: -5%}
        }
        .page7-4 {
            position: absolute; right: -5%; bottom: 22%; width: 60%; z-index: 2
            animation: right-1 2s;
            -webkit-animation:right-1 2s;
        }
        @-webkit-keyframes right-1{
            0%{right:-55%}
            100%{right: -5%}
        }
        .page7-5 {
            position: absolute; left: 15%; bottom: 0; width: 80%; z-index: 1;
            animation: bottom-1 2s;
            -webkit-animation:bottom-1 2s;
        }
        @-webkit-keyframes bottom-1{
            0%{bottom:-55%}
            100%{bottom:0}
        }
        .page8-4 {
            position:absolute; right: 1rem; top: 4rem; width: 40%; z-index: 4;
            animation: right-2 2s;
            -webkit-animation:right-2 2s;
        }
        @-webkit-keyframes right-2{
            0%{right:-10rem}
            100%{right: 1rem}
        }
    </style>
</head>

<body>
<section class="p-index">
        <section data-page="1" class="m-page m-page1" data-type='info_pic2'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page1.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/1.png" class="page1"/>
        </div>
    </section>
    <section data-page="2" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page2.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/2-1.png" class="page2-1"/>
            <img src="${ctxStatic}/wx/activity/images/2-2.png" class="page2-2"/>
            <img src="${ctxStatic}/wx/activity/images/2-3.png" class="page2-3"/>
        </div>
    </section>
    <section data-page="3" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page3.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/3-1.png" class="page3-1"/>
            <img src="${ctxStatic}/wx/activity/images/3-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
            <div class="yun">
                <img src="${ctxStatic}/wx/activity/images/yun.png" style="width: 45%"/>
            </div>
            <div class="page3-img">
                <img src="${ctxStatic}/wx/activity/images/dian-1.png" style="position: absolute; left: 0; top: 25%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-2.png" style="position: absolute; left: 0; top: 32%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-1.png" style="position: absolute; left: 0; top: 38%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-2.png" style="position: absolute; left: 0; top: 44%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-1.png" style="position: absolute; left: 0; top: 51%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-2.png" style="position: absolute; left: 0; top: 57%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-1.png" style="position: absolute; left: 0; top: 63.5%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-2.png" style="position: absolute; left: 0; top: 70%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-1.png" style="position: absolute; left: 0; top: 76%; width: 100%;"/>
                <img src="${ctxStatic}/wx/activity/images/dian-2.png" style="position: absolute; left: 0; top: 83%; width: 100%;"/>
            </div>
        </div>
    </section>
    <section data-page="4" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page4.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/4-1.png" style="position: absolute; left: 30%; top: 2%; width: 20%; z-index: 1;-webkit-animation:line 3s infinite;
            -webkit-transform-origin:bottom center;"/>
            <img src="${ctxStatic}/wx/activity/images/4-2.png" style="position: absolute; right:15%; top: 2%; width: 30%; z-index: 1;-webkit-animation:line 3s infinite;
            -webkit-transform-origin:bottom left;"/>
            <img src="${ctxStatic}/wx/activity/images/4-3.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
            <div class="yun">
                <img src="${ctxStatic}/wx/activity/images/yun.png" style="width: 45%"/>
            </div>
        </div>
    </section>
    <section data-page="5" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page5.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/5-1.png" class="page5-img"/>
        </div>
    </section>
    <section data-page="6" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page6.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/6-1.png" style="position: absolute; left: 5%; top: 0; width: 100%; z-index: 1"/>
            <img src="${ctxStatic}/wx/activity/images/6-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
            <div class="yun">
                <img src="${ctxStatic}/wx/activity/images/yun.png" style="width: 45%"/>
            </div>
            <img src="${ctxStatic}/wx/activity/images/6-3.png" style="position: absolute; left: 15%; bottom: 0; width: 70%;animation: left-f 2s;-webkit-animation:left-f 2s;"/>
            <img src="${ctxStatic}/wx/activity/images/f-pic.png" style="position: absolute; left: 0; bottom: 0; width: 100%"/>
        </div>
    </section>
    <section data-page="7" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/page7.jpg' style="background:center no-repeat; background-size:cover;">
            <img src="${ctxStatic}/wx/activity/images/7-1.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 1"/>
            <img src="${ctxStatic}/wx/activity/images/7-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
            <div class="yun">
                <img src="${ctxStatic}/wx/activity/images/yun.png" style="width: 45%"/>
            </div>
            <img src="${ctxStatic}/wx/activity/images/7-3.png" class="page7-3" />
            <img src="${ctxStatic}/wx/activity/images/7-4.png" class="page7-4"/>
            <img src="${ctxStatic}/wx/activity/images/7-5.png" class="page7-5"/>
        </div>
    </section>
    <section data-page="8" class="m-page m-page2 hide" data-type='info_nomore'>
        <div class="m-img m-img01 lazy-bk" data-bk='${ctxStatic}/wx/activity/images/bg.jpg' style="background:center no-repeat; background-size:cover;">
            <div class="box-bg more" id="cancel-box">
                <a class="close"></a>
                <p>如果您想了解更多活动详情或咨询报名事项，欢迎来电！（联系人：${salesman.name}，电话：${salesman.phone}）</p>
            </div>
            <div class="box-bg box" id="suss-box">
                <a class="close"></a>
                <p>恭喜您报名成功！（工作人员：${salesman.name}，电话：${salesman.phone}）将尽快与您联系送上专属邀请函，请您耐心等待。</p>
            </div>
            <div class="drop">
                <div style="padding: 1rem;">
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_1_0" name="radio" />
                            <label for="squaredTwo_1_0"></label>
                        </div><img src="${ctxStatic}/wx/activity/images/city-1.png" city="哈尔宾" title="哈尔宾7月19日" class="aa"/>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_2_0" name="radio" />
                            <label for="squaredTwo_2_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-2.png" city="济南" title="济南7月19日" class="aa"/>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_3_0" name="radio"  />
                            <label for="squaredTwo_3_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-3.png" city="淄博" title="淄博7月10日" class="aa"/>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_4_0" name="radio"  />
                            <label for="squaredTwo_4_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-4.png" city="武汉" title="武汉7月16日" class="aa"/>
                        <div class="clearfix"></div>
                        <div class="bg-or">
                            <div class="radio-box m20">
                                <input type="radio" id="squaredTwo_4_1" name="radio1" />
                                <label for="squaredTwo_4_1"></label>
                            </div>
                            <img src="${ctxStatic}/wx/activity/images/city-text.png" title="同时参加当天儿童活动" class="bb" />
                        </div>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_5_0" name="radio"  />
                            <label for="squaredTwo_5_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-5.png" city="长沙" title="长沙7月16日" class="aa"/>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_6_0" name="radio"  />
                            <label for="squaredTwo_6_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-6.png" city="广州" title="广州8月6日" class="aa"/>
                        <div class="clearfix"></div>
                        <div class="bg-or">
                            <div class="radio-box m20">
                                <input type="radio" id="squaredTwo_6_1" name="radio1" />
                                <label for="squaredTwo_6_1"></label>
                            </div>
                            <img src="${ctxStatic}/wx/activity/images/city-text.png" title="同时参加当天儿童活动" class="bb" />
                        </div>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_7_0" name="radio"  />
                            <label for="squaredTwo_7_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-7.png" city="北京" title="北京8月6日" class="aa"/>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_8_0" name="radio"  />
                            <label for="squaredTwo_8_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-8.png" city="福州" title="福州8月7日" class="aa"/>
                        <div class="clearfix"></div>
                        <div class="bg-or">
                            <div class="radio-box m20">
                                <input type="radio" id="squaredTwo_8_1" name="radio1" />
                                <label for="squaredTwo_8_1"></label>
                            </div>
                            <img src="${ctxStatic}/wx/activity/images/city-text.png" title="同时参加当天儿童活动" class="bb" />
                        </div>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_9_0" name="radio"  />
                            <label for="squaredTwo_9_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-9.png" city="石家庄" title="石家庄8月13日" class="aa"/>
                        <div class="clearfix"></div>
                        <div class="bg-or">
                            <div class="radio-box m20">
                                <input type="radio" id="squaredTwo_9_1" name="radio1" />
                                <label for="squaredTwo_9_1"></label>
                            </div>
                            <img src="${ctxStatic}/wx/activity/images/city-text.png" title="同时参加当天儿童活动" class="bb" />
                        </div>
                    </div>
                    <div class="city-list">
                        <div class="radio-box">
                            <input type="radio" id="squaredTwo_10_0" name="radio"  />
                            <label for="squaredTwo_10_0"></label>
                        </div>
                        <img src="${ctxStatic}/wx/activity/images/city-10.png" city="上海" title="上海8月20日" class="aa"/>
                        <div class="clearfix"></div>
                        <div class="bg-or">
                            <div class="radio-box m20">
                                <input type="radio" id="squaredTwo_10_1" name="radio1" />
                                <label for="squaredTwo_10_1"></label>
                            </div>
                            <img src="${ctxStatic}/wx/activity/images/city-text.png" title="同时参加当天儿童活动" class="bb" />
                        </div>
                    </div>
                </div>
                <a class="sub"><img src="${ctxStatic}/wx/activity/images/sub.png" /></a>
            </div>
            <div class="sex">
				<ul>
					<li>男</li>
					<li>女</li>
				</ul>
			</div>
            <div class="pop-bg"></div>
            <div class="info">
                <img src="${ctxStatic}/wx/activity/images/8-1.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 1"/>
                <img src="${ctxStatic}/wx/activity/images/8-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
                <div class="yun">
                    <img src="${ctxStatic}/wx/activity/images/yun.png" style="width: 45%"/>
                </div>

                <img src="${ctxStatic}/wx/activity/images/text.png" class="page8-4"/>
                <ul class="formDiv" style="margin-top: 16rem">
                    <li><em><img src="${ctxStatic}/wx/activity/images/f-text-1.png" /></em><input type="text" readonly id="bm" /></li>
                    <li><em><img src="${ctxStatic}/wx/activity/images/f-text-2.png" /></em><input type="text" id="name"/></li>
                    <li><em><img src="${ctxStatic}/wx/activity/images/f-text-3.png" /></em><input type="text" readonly id="sex" /></li>
                    <li><em><img src="${ctxStatic}/wx/activity/images/f-text-4.png" /></em><input type="text" id="phone"/></li>
                    <li><em><img src="${ctxStatic}/wx/activity/images/f-text-5.png" /></em><input class="grg" readonly type="text" id="childrenName"/></li>
                    <li><em><img src="${ctxStatic}/wx/activity/images/f-text-6.png" /></em><input class="grg" readonly type="text" id="childrenClass"/></li>
                </ul>
                <div class="btn-wrap">
                    <a onclick="addUser();"><img src="${ctxStatic}/wx/activity/images/sub-btn.png"/></a>
                    <a><img src="${ctxStatic}/wx/activity/images/cancel-btn.png" id="cancel-btn"/></a>
                </div>
            </div>
            <img src="${ctxStatic}/wx/activity/images/star.png" class="pos w100"/>
            <img src="${ctxStatic}/wx/activity/images/f-pic.png" class="footer"/>
        </div>
    </section>
    <section class='u-arrow'><img src="${ctxStatic}/wx/activity/images/btn01_arrow.png" /></section>
</section>
<script type="text/javascript" src="${ctxStatic}/wx/activity/js/html5.js"></script>
<script type="text/javascript" src="${ctxStatic}/wx/activity/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/wx/activity/js/main.js"></script>
 <%@ include file="/WEB-INF/views/wx/common/alert.jsp"%>
</body>

<script type="text/javascript">
 function addUser(){
 
            if($("#bm").val()==""){
                nciAlert("活动城市不能为空，请选择");
                return false;
            }
            if(!isCNName($("#name").val())){
                nciAlert("姓名有误，请重新输入");
                return false;
            }
            if($("#sex").val()==""){
                nciAlert("性别不能为空，请输入");
                return false;
            }
            var phonestatus = validateMobile($("#phone").val());
            if(phonestatus!=3){
                nciAlert("手机号有误，请重新输入");
                return false;
            }
            var chidren = $('input[name="radio1"]:checked ').length;
            if(chidren>0){
            	if(!isCNName($("#childrenName").val())){
                    nciAlert("小孩姓名有误，请重新输入");
                    return false;
                }
            	 var age = $("#childrenClass").val();
                 if(!(age>0&&age<20)){
                     nciAlert("小孩年龄有误，请重新输入");
                     return false;
                 }
            }
            
             $.ajax({
                 type:'get',
                 url:'${pageContext.request.contextPath}/weixin/userTest',
                 data: { "realName":encodeURIComponent($("#name").val()),"childrenName":encodeURIComponent($("#childrenName").val()),"childrenClass":$("#childrenClass").val(),"phone":$("#phone").val(),"sex":$("#sex").val(),"city":$("#bm").val(),"salesManId":<c:if test="${!empty salesman}"> ${salesman.id}</c:if><c:if test="${empty salesman}">0</c:if>},
                 success:function(data){
                     if(data!=null){
                         if(data.code<0){
                             nciAlert(data.msg); 
                         }else{
                        	 $("#suss-box").show();
                             $(".pop-bg").show();
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
