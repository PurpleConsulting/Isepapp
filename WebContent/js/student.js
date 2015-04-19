/**
 * JS SCRIPT FOR THE STUDENT PAGE
 */
$(document).ready(function(){
// Paginnation for the missing part
	var missing = $(".missing");
	var missinNav = $(".missing nav");
	
	$(".missing nav ul li a").on("click", function(event){
		var pattern = $(this).attr("data-target");
		event.preventDefault();
		missing.children(".active").removeClass('active');
		missing.children("#blk" + pattern).addClass('active');
		$(".missing nav ul li.active").removeClass('active');
		$(this).parent().addClass('active');
	});


});


$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
});

$('button').tooltip({placement: 'bottom',trigger: 'manual'}).tooltip('show');
$('button').on('click',function(){$(this).tooltip('destroy');});