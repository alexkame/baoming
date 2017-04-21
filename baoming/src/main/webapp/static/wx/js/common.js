 			function lingqu(){
    	            if(!isCNName($("#name").val())){
    	                nciAlert("姓名有误，请重新输入");
    	                return false;
    	            }
    	            var phonestatus = validateMobile($("#phone").val());
    	            if(phonestatus!=3){
    	                nciAlert("手机号有误，请重新输入");
    	                return false;
    	            }
    	            load();
    	            $.ajax({
    	                type:'get',
    	                async: false,
    	                url:$("base").attr("href")+'/weixin/userLogining',
    	                data:{ "name":encodeURIComponent($("#name").val()),"phone":$("#phone").val()},
    	                success:function(data){
    	                	load_hide();
    	                    if(data!=null){
    	                        if(data.code<0){
    	                            nciAlert(data.msg); 
    	                            return false;
    	                        }else{
    	                       	 return true;
    	                        }
    	                    }else{
    	                        nciAlert("登录失败,请重试");
    	                        return false;
    	                    }
    	                },
    	                error:function(XmlHttpRequest,textStatus,errorThrown){
    	                	load_hide();
    	                    nciAlert("登录异常,请重试");
    	                    return false;
    	                }
    	            });
    	        }
    	 
    	 function validateMobile(mobile) {//校验电话号码
    	     if (mobile.length == 0) {
    	         return 1;
    	     }
    	     if (mobile.length != 11) {
    	         return 2;
    	     }
    	     var myReg = /^((13[0-9])|(14[5,7])|(15[^4,\D])|(17[0,6,7,8])|(18[0-9]))(\d{8})$/;
    	     if (myReg.test(mobile)) {//格式正确
    	         return 3;
    	     } else {
    	         return 2;
    	     }
    	 }
    	 
    	 //验证中文姓名
    	 function isCNName(name){
    		 return (/^([\u4e00-\u9fa5]|[.]|[·]){2,15}$/).test(name);
    	 }
