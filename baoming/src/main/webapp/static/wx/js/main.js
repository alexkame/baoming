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


})