/**
 * JS SCRIPT FOR THE STUDENT PAGE
 */

// -- THE REALY FIRST MOVE
/*$(document).ready(function(){
	$(".missing div").first().addClass("active");
})*/

$(document).ready(function(){
// Pagination for the missing part
	var missing = $(".missing");
	var missinNav = $(".missing nav");
	
	
	$(".missing nav ul li a").on("click", function(event){
		var pattern = $(this).attr("data-target");
		event.preventDefault();
		missing.children(".active").removeClass('active');
		missing.children("#blk" + pattern).addClass('active');
		$(".missing nav ul li.active").removeClass('active');
		if($(this).attr("aria-label") =="Previous"){
			$(".missing nav ul li").first().next().addClass('active');
		}else if($(this).attr("aria-label") == "Next"){
			$(".missing nav ul li").last().prev().addClass('active');
		} else{
			$(this).parent().addClass('active');
		}
	});


});


/*$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
});*/

$('button').tooltip({placement: 'bottom',trigger: 'manual'}).tooltip('show');
$('button').on('click',function(){$(this).tooltip('destroy');});