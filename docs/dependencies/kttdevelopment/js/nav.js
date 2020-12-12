$(document).ready(function () {
    $(".navbar-toggler").click(delay);
    $(window).scroll(nav);
    $(window).resize(nav);
});

$("nav").ready(nav);

function delay(){
    setTimeout(nav, 150);
}

function nav(){
    height = $("#main").innerHeight();
    y = $(window).scrollTop();
    nh = $("nav").innerHeight();

    if(y > height - nh - 20){
        $("nav").addClass("active shadow")
    }else{
        $("nav").removeClass("active shadow")
    }
}