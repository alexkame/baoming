<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath %>">
    <meta name="mobileoptimized" content="0"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="screen-orientation" content="portrait">
    <meta name="browsermode" content="application">
    <title>中英让TA绽放·不一Young的未来</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/wx/activity2/css/wm.css" />
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/wx/activity2/css/main.css" />
    <script type="text/javascript">
    var canshare = true;
    var myLinkUrl = "<%=basePath %>/wx/activity?salesManId=${salesman.id}"; 
    var myDesc = "竟然有这么不一Young的暑期活动！我已报名，你也快来！"; 
    </script>
    <script type="text/javascript" src="${ctxStatic}/wx/activity2/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="${ctxStatic}/wx/activity2/js/my.js"></script>
    <script src="${ctxStatic}/wx/js/jweixin-1.0.0.js" type="text/javascript"></script>
    <script src="${ctxStatic}/wx/js/nciWx.js" type="text/javascript"></script>
     <script src="${ctxStatic}/wx/js/common.js" type="text/javascript"></script>
    
       <style>
        #layout { position:absolute; left:0; right:0; top:0; bottom:0; overflow:hidden; background:black;}
        .page, .page-init { position:absolute; left:0; right:0; top:0; bottom:0; overflow:hidden;}
        .page-init {z-index:10;}
        .lock {pointer-events:none;}
        .hide {display:none;}
    </style>
</head>

<body>
<div id="lanren">
    <div id="audio-btn" class="on" onclick="lanren.changeClass(this,'media')">
        <audio loop src="${ctxStatic}/wx/activity2/images/bg.mp3" id="media" preload="preload"></audio>
    </div>
</div>
<div class="page-loading">
    <ul class="border">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
<div id="layout">
    <div class="page page-0">
        <img src="${ctxStatic}/wx/activity2/images/bg-0-1.jpg" class="back-img">
        <img src="${ctxStatic}/wx/activity2/images/1.png" class="page1"/>
        <img src="${ctxStatic}/wx/activity2/images/1-2.png" class="page1-text2" />
        <img src="${ctxStatic}/wx/activity2/images/1-1.png" class="page1-text" />
        <div class="arrow"></div>
    </div>
    <div class="page page-1">
        <img src="${ctxStatic}/wx/activity2/images/bg-0-2.jpg" class="back-img">
        <img src="${ctxStatic}/wx/activity2/images/2-1.png" style=" position: absolute; left: 0; top: 0; width: 100%; margin: 5% 0 0 -5%; z-index: 2"/>
        <img src="${ctxStatic}/wx/activity2/images/2-2.png" class="page2-1"/>
        <img src="${ctxStatic}/wx/activity2/images/2-3.png" class="page2-2"/>
        <div class="arrow"></div>
    </div>
    <div class="page page-2">
        <img src="${ctxStatic}/wx/activity2/images/bg-0-3.jpg" class="back-img">

        <img src="${ctxStatic}/wx/activity2/images/3-1.png" class="page3-1"/>
        <img src="${ctxStatic}/wx/activity2/images/3-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
        <div class="yun">
            <img src="${ctxStatic}/wx/activity2/images/yun.png" style="width: 45%"/>
        </div>
        <img src="${ctxStatic}/wx/activity2/images/pic3-1.png" class="pic3 pic3-1"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-2.png" class="pic3 pic3-2"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-3.png" class="pic3 pic3-3"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-4.png" class="pic3 pic3-4"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-5.png" class="pic3 pic3-5"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-6.png" class="pic3 pic3-6"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-7.png" class="pic3 pic3-7"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-8.png" class="pic3 pic3-8"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-9.png" class="pic3 pic3-9"/>
        <img src="${ctxStatic}/wx/activity2/images/pic3-10.png" class="pic3 pic3-10"/>

        <div class="page3-img">
            <img src="${ctxStatic}/wx/activity2/images/dian-1.png" style="position: absolute; left: 0; top: 25%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-2.png" style="position: absolute; left: 0; top: 32%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-1.png" style="position: absolute; left: 0; top: 38%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-2.png" style="position: absolute; left: 0; top: 44%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-1.png" style="position: absolute; left: 0; top: 51%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-2.png" style="position: absolute; left: 0; top: 57%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-1.png" style="position: absolute; left: 0; top: 63.5%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-2.png" style="position: absolute; left: 0; top: 70%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-1.png" style="position: absolute; left: 0; top: 76%; width: 100%;"/>
            <img src="${ctxStatic}/wx/activity2/images/dian-2.png" style="position: absolute; left: 0; top: 83%; width: 100%;"/>
        </div>
        <div class="arrow"></div>
    </div>
    <div class="page page-3-5">
        <img src="${ctxStatic}/wx/activity2/images/bg.jpg" class="back-img" />
        <img src="${ctxStatic}/wx/activity2/images/41-1.png" style="position: absolute; left: 30%; top: 2%; width: 20%; z-index: 1;-webkit-animation:line 3s infinite;
            -webkit-transform-origin:bottom center;"/>
        <img src="${ctxStatic}/wx/activity2/images/41-2.png" style="position: absolute; right:15%; top: 2%; width: 30%; z-index: 1;-webkit-animation:line 3s infinite;-webkit-transform-origin:bottom left;"/>
        <img src="${ctxStatic}/wx/activity2/images/41-3.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
        <div class="yun">
            <img src="${ctxStatic}/wx/activity2/images/yun.png" style="width: 45%"/>
        </div>

        <div class="pic4">
            <img src="${ctxStatic}/wx/activity2/images/4-2.png" class="pic4-2"/>
            <img src="${ctxStatic}/wx/activity2/images/4-3.png" class="pic4-3"/>
            <img src="${ctxStatic}/wx/activity2/images/4-4.png" class="pic4-4"/>
            <img src="${ctxStatic}/wx/activity2/images/4-5.png" class="pic4-5"/>
            <img src="${ctxStatic}/wx/activity2/images/4-6.png" class="pic4-6"/>
            <img src="${ctxStatic}/wx/activity2/images/4-7.png" class="pic4-7"/>
            <img src="${ctxStatic}/wx/activity2/images/4-8.png" class="pic4-8"/>
        </div>

        <img src="${ctxStatic}/wx/activity2/images/4-1.png" class="pic4-9"/>
        <img src="${ctxStatic}/wx/activity2/images/f-pic.png" style="position: fixed; left: 0; bottom: 0; width: 100%;"/>
        <div class="arrow"></div>
    </div>
    <div class="page page-3">
        <img src="${ctxStatic}/wx/activity2/images/bg-0-5.jpg" class="back-img">
        <img src="${ctxStatic}/wx/activity2/images/5-1.png" class="pic5-1"/>
        <img src="${ctxStatic}/wx/activity2/images/5-2.png" class="pic5-2"/>
        <div class="arrow"></div>
    </div>
    <!--logo页面-->
    <div class="page page-4">
        <img src="${ctxStatic}/wx/activity2/images/bg-0-6.jpg" class="back-img">
        <img src="${ctxStatic}/wx/activity2/images/6-1.png" style="position: absolute; left: 5%; top: 0; width: 100%; z-index: 1"/>
        <img src="${ctxStatic}/wx/activity2/images/6-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
        <div class="yun">
            <img src="${ctxStatic}/wx/activity2/images/yun.png" style="width: 45%"/>
        </div>
        <img src="${ctxStatic}/wx/activity2/images/6-4.png" class="pic6-4"/>
        <img src="${ctxStatic}/wx/activity2/images/6-3.png" class="pic6-3"/>
        <img src="${ctxStatic}/wx/activity2/images/f-pic.png" style="position: absolute; left: 0; bottom: 0; width: 100%"/>
        <div class="arrow"></div>
    </div>
    <!--地图页面-->
    <div class="page page-5">
        <img src="${ctxStatic}/wx/activity2/images/bg-0-4.jpg" class="back-img">

        <img src="${ctxStatic}/wx/activity2/images/7-1.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 1"/>
        <img src="${ctxStatic}/wx/activity2/images/7-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
        <div class="yun">
            <img src="${ctxStatic}/wx/activity2/images/yun.png" style="width: 45%"/>
        </div>
        <img src="${ctxStatic}/wx/activity2/images/7-3.png" class="page7-3" />
        <img src="${ctxStatic}/wx/activity2/images/7-4.png" class="page7-4"/>
        <img src="${ctxStatic}/wx/activity2/images/7-5.png" class="page7-5"/>
        <img src="${ctxStatic}/wx/activity2/images/7-6.png" class="page7-6"/>
        <div class="arrow"></div>
    </div>
    <div class="page page-6">
        <img src="${ctxStatic}/wx/activity2/images/bg.jpg" class="back-img">

        <div class="box-bg more" id="cancel-box">
                <a class="close"></a>
                <p>如果您想了解更多活动详情或咨询报名事项，欢迎来电！（联系人：${salesman.name}，电话：${salesman.phone}）</p>
            </div>
            <div class="box-bg box" id="suss-box">
                <a class="close"></a>
                <p>恭喜您报名成功！（工作人员：${salesman.name}，电话：${salesman.phone}）将尽快与您联系送上专属邀请函，请您耐心等待。</p>
            </div>
        <div class="drop">
                <div style="padding: .5rem;">
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
                                <input type="radio" id="squaredTwo_6_1" name="radio2" />
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
                                <input type="radio" id="squaredTwo_8_1" name="radio3" />
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
                                <input type="radio" id="squaredTwo_9_1" name="radio4" />
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
                                <input type="radio" id="squaredTwo_10_1" name="radio5" />
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
            <img src="${ctxStatic}/wx/activity2/images/8-1.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 1"/>
            <img src="${ctxStatic}/wx/activity2/images/8-2.png" style="position: absolute; left: 0; top: 0; width: 100%; z-index: 3"/>
            <div class="yun">
                <img src="${ctxStatic}/wx/activity2/images/yun.png" style="width: 45%"/>
            </div>

            <img src="${ctxStatic}/wx/activity2/images/text.png" class="page8-4"/>
            <ul class="formDiv" style="margin-top: 40%">
                  <li><em><img src="${ctxStatic}/wx/activity2/images/f-text-1.png" /></em><input type="text" readonly id="bm" /></li>
                    <li><em><img src="${ctxStatic}/wx/activity2/images/f-text-2.png" /></em><input type="text" id="name"/></li>
                    <li><em><img src="${ctxStatic}/wx/activity2/images/f-text-3.png" /></em><input type="text" readonly id="sex" /></li>
                    <li><em><img src="${ctxStatic}/wx/activity2/images/f-text-4.png" /></em><input type="text" id="phone"/></li>
                    <li><em><img src="${ctxStatic}/wx/activity2/images/f-text-5.png" /></em><input class="grg" readonly type="text" id="childrenName"/></li>
                    <li><em><img src="${ctxStatic}/wx/activity2/images/f-text-6.png" /></em><input class="grg" readonly type="text" id="childrenClass"/></li>
            </ul>
            <div class="btn-wrap">
                <a onclick="addUser();"><img src="${ctxStatic}/wx/activity2/images/sub-btn.png"/></a>
                <a><img src="${ctxStatic}/wx/activity2/images/cancel-btn.png" id="cancel-btn"/></a>
            </div>
        </div>
        <img src="${ctxStatic}/wx/activity2/images/star.png" class="pos w100"/>
        <img src="${ctxStatic}/wx/activity2/images/f-pic.png" style="position: absolute; left: 0; bottom: 0; width: 100%"/>

    </div>
</div>

<div id="template" style="display:none"></div>
 <script src="${ctxStatic}/wx/activity2/js/zMobile.js"></script>
    <script src="${ctxStatic}/wx/activity2/js/wmLib.js"></script>
    <script src="${ctxStatic}/wx/activity2/js/wm.js"></script>
     
     <style>
    .text-pop {
        position: absolute; left: 50%; top: 50%; margin: -15rem 0 0 -40%; width: 80%;z-index: 5; background: #90cdef; border-radius: 1rem;
        -moz-box-shadow:.2rem .2rem .2rem rgba(0,0,0,.2) inset;
        -webkit-box-shadow:.2rem .2rem .2rem rgba(0,0,0,.2) inset;
        box-shadow:.2rem .2rem .2rem rgba(0,0,0,.2) inset;
        z-index: 15;
    }
    .text-pop p { color: #103665; padding: 1.5rem; font-size: 1.2rem}
    .text-pop a { display: block; width: 50%; margin: 0 auto 1.5rem auto}
    .text-pop a img { width: 100%}
</style>

<div class="text-pop" style="display: none;" id="nciAlert">
    <p></p>
    <a onclick="nciAlert_hide();"><img src="${ctxStatic}/wx/activity/images/sub.png"/> </a>
</div>

<script>
        $(function(){
             var h = $(".text-pop").height()/2*(-1);
             $(".text-pop").css({
                 'margin-top':h + 'px'
             });
        })
        
        function  nciAlert(msg){
                $("#nciAlert p").text(msg);
                $(".pop-bg").show();
                $("#nciAlert").show();
          }
        function  nciAlert_hide(){
            $(".pop-bg").hide();
            $("#nciAlert").hide();
        }
        
 </script>
 
</body>
<script>
    // 音乐代码控制
    var lanren = {
        changeClass: function(target, id) {
            var className = $(target).attr('class');
            var ids = document.getElementById(id);
            (className == 'on') ? $(target).removeClass('on').addClass('off'): $(target).removeClass('off').addClass('on');
            (className == 'on') ? ids.pause(): ids.play();
        },
        play: function() {
            document.getElementById('media').play();
    }
}
// 音乐代码结束
    $(function(){
        lanren.play();

    })

</script>

<script type="text/javascript">

$(function(){
	
	 init();
     function init() {
         $("input[name=radio1]").attr("disabled", "disabled");
         $("input[name=radio2]").attr("disabled", "disabled");
         $("input[name=radio3]").attr("disabled", "disabled");
         $("input[name=radio4]").attr("disabled", "disabled");
         $("input[name=radio5]").attr("disabled", "disabled");

         $("#childrenName").val("");
         $("#childrenClass").val("");
         $("#childrenName").addClass("grg");
         $("#childrenClass").addClass("grg");
         $("#childrenName").attr("readonly", "readonly");
         $("#childrenClass").attr("readonly", "readonly");
     }
     
    $("#bm").click(function(){

        $(".drop").show();
        $(".pop-bg").show();

        $("input[name=radio]").change(function() {
            $("#bm").val($(this).parent().next().eq(0).attr("city"));
            $("input[name=radio1]").removeAttr('checked');
            //$("input[name=radio2]").removeAttr('checked');
            $("input[name=radio2]").removeAttr('checked');
                $("input[name=radio3]").removeAttr('checked');
                $("input[name=radio4]").removeAttr('checked');
                $("input[name=radio5]").removeAttr('checked');
                init();
            $(this).parent().next().next().next().find("input").removeAttr("disabled");
            $("#childrenName").val("");
            $("#childrenClass").val("");
            $("#childrenName").addClass("grg");
            $("#childrenClass").addClass("grg");
            $("#childrenName").attr("readonly","readonly");
            $("#childrenClass").attr("readonly","readonly");
        });
        
        $(".bg-or input").click(function () {
            var chidren = $(this).attr('checked');
            if (chidren == "checked" ) {
                $("#childrenName").removeClass("grg");
                $("#childrenClass").removeClass("grg");
                $("#childrenName").removeAttr("readonly");
                $("#childrenClass").removeAttr("readonly");
            }
        });
      
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
     var h = $(window).height();
        var w = $(window).width();
        $(".bg").css({
            'width': w + 'px',
            'height': h + 'px'
        });
        $(window).resize(function(){
            var h = $(window).height();
            var w = $(window).width();
            $(".bg").css({
                'width': w + 'px',
                'height': h + 'px'
            });
        });

        $(".info").css({
            'height': h + 'px'
        });

        $("#sub-btn").click(function(){
            //alert(a)
            $("#suss-box").show();
            $(".pop-bg").show()
        })
        $("#cancel-btn").click(function(){
            $("#cancel-box").show();
            $(".pop-bg").show()
        })
        $(".close").click(function(){
            $("#suss-box").hide();
            $("#cancel-box").hide();
            $(".pop-bg").hide();
        })

})


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
            var chidren = $('.bg-or input:checked ').length;
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
