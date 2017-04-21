<%@ page contentType="text/html;charset=UTF-8" %>

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

<div class="pop-bg" style="display: none;"></div>

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