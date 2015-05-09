/**
 * 
 */
 $(document).ready(function(e) {

    $(window).scroll(function () {
        if ($(window).scrollTop() > '162') {
            $('#navmain').addClass('navbar-fixed-top');
            //$('#navmain').removeClass('navbar-static-top');                  
        } else {
            $('#navmain').removeClass('navbar-fixed-top');
            //$('#navmain').addClass('navbar-static-top');     
        }
    });         

});