$(function(){
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