$(document).ready(function(){
    $("a").on('click', function(event){
        if(this.hash !== ""){
            var hash = this.hash;
            var offset = $(hash).offset();
            if(offset == null)
                return;
            event.preventDefault();

            $('html, body').animate({
                scrollTop: offset.top
            }, 800, function(){
                window.location.hash = hash;
            });
        }
    });
});