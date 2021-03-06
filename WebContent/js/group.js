/**
 * 
 */

$('a').tooltip({placement: 'bottom',trigger: 'manual'}).tooltip('show');


//-- Pagination for the missing part
var missing = $("#blk-missing");
var missinNav = $("#blk-missing nav");


$(".missing nav ul li a").on("click", function(event){
	event.preventDefault();
	var pattern = $(this).attr("data-target");
	var targets = [];
	for(var i = (3 * (pattern - 1)) + 1,j = 0; j < 3; i++,j++){

		targets.push(i);
	}

	missing.children(".active").removeClass('active');
	targets.forEach(function(t){
		missing.children("#blk" + t).addClass('active');
	})
	
	$(".missing nav ul li.active").removeClass('active');
	if($(this).attr("aria-label") =="Previous"){
		$(".missing nav ul li").first().next().addClass('active');
	}else if($(this).attr("aria-label") == "Next"){
		$(".missing nav ul li").last().prev().addClass('active');
	} else{
		$(this).parent().addClass('active');
	}
});