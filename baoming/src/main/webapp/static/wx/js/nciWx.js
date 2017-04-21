
function initWx() {
	var host = window.location.host;
	var shareTitle="中英让TA绽放·不一Young的未来";
	var shareDesc="";
	var linkUrl = location.href;
	var imgUrl = $("base").attr("href")+'/static/wx/images/share_logo.png';
	
	
   /***用户点击分享到微信圈后加载接口接口*******/
   $.post($("base").attr("href")+"/weixin/jsApi",{"url":encodeURIComponent(location.href.split('#')[0])},function(data){
           wx.config({
                 debug: false,
                 appId: data.appId,
                 timestamp:data.timestamp,
                 nonceStr:data.nonceStr,
                 signature:data.signature,
                 jsApiList: [
                 'checkJsApi',
                 'onMenuShareTimeline',
                 'onMenuShareAppMessage',
                 'showMenuItems',
                 'hideOptionMenu',
                 'hideAllNonBaseMenuItem'
                 ]
             });
           wx.ready(function(){
        	   
        	   if(typeof(canshare) != "undefined"&&canshare){
        		   
        		   wx.hideAllNonBaseMenuItem(
        				   { fail: function (res) {
        					   alert(JSON.stringify(res));
        				   }}); 
        		   if(typeof(myDesc) != "undefined"&&myDesc!=null&&myDesc!=""){
        			   shareDesc = myDesc;
        		   }
        		   if(typeof(myLinkUrl) != "undefined"&&myLinkUrl!=null&&myLinkUrl!=""){
        			   linkUrl = myLinkUrl;
        		   }
        		 /*  $.ajax({
        			         type: 'GET',
        			         async: false,
        			         url: $("base").attr("href")+"/weixin/shareurl",
        			         data: null,
        			         success: function(data){
        			            if(data != null&&data.shareurl!=null){
        			    	 		linkUrl = $("base").attr("href")+'/wx/newsalesman?'+ data.shareurl;
        			             }else{
        			            	linkUrl = $("base").attr("href")+'/wx/userLogin';
        			             }
        			          },
        			          error:function(){
        			        	  linkUrl = $("base").attr("href")+'/wx/userLogin';
        			          }
        			      });*/
        		   
        		   wx.showMenuItems({
        			    menuList: [
        			    'menuItem:share:appMessage',
        			    'menuItem:share:timeline'
        			    ] 
        			});
        		   
        	   }else{
        		   wx.hideAllNonBaseMenuItem(
        				   { fail: function (res) {
        					   alert(JSON.stringify(res));
        				   }}); 
        	   }
        	   
               wx.onMenuShareTimeline({
                   title: shareDesc, // 分享标题
                   link: linkUrl, // 分享链接
                   imgUrl: imgUrl, // 分享图标
         	       success: function (res) {
//         		    	share();
         		    }
               });
               
               wx.onMenuShareAppMessage({
                   title: shareTitle, // 分享标题
                   desc: shareDesc, // 分享描述
                   link: linkUrl, // 分享链接
                   imgUrl: imgUrl, // 分享图标
         	       success: function (res) {
//         		    	share();
         		    }
               });
              
           });
           
           wx.error(function (res) {
        		//  alert(res.errMsg);
        		});
   });
}

function share(){
	$.ajax({url:$("base").attr("href")+'/weixin/share',async:false});
}

initWx();