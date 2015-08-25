/**
 * JS SCRIPT FOR THE STUDENT PAGE
 */

$(document).ready(function(){
	// -- Display the delivery	
	$('button').tooltip({placement: 'bottom',trigger: 'manual'}).tooltip('show');

	//  -- Marks: Global averages
	$("div.progress-bar").each(function(i){
		max = $(this).attr("aria-valuemax");
		mark = $(this).attr("aria-valuenow");
		
		per = (mark * 100) / max;
		
		$(this).css("width", per+"%");
		
	});
	
	
	$.post("/Isepapp/Students", {radar: true, radar_std: $("div.idcard").attr("data-target")}, function(data, status){
		
		console.log(data);
	});
	// -- chart
	Chart.defaults.global.responsive = true;
	//Chart.defaults.global.scaleShowLabels = false;
	var ctx = document.getElementById("radar-canvas").getContext("2d");
	
	var data = {
		    labels: ["Trav","Comm","Cond","Conc","Prof","Eval c."],
		    datasets: [
		        {
		            label: "My First dataset",
		            fillColor: "rgba(220,220,220,0.2)",
		            strokeColor: "rgba(220,220,220,1)",
		            pointColor: "rgba(220,220,220,1)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(220,220,220,1)",
		            data: [65, 59, 90, 81, 56, 55]
		        },
		        {
		            label: "My Second dataset",
		            fillColor: "rgba(36,100,130,0.2)",
		            strokeColor: "rgba(36,100,130,1)",
		            pointColor: "rgba(36,100,130,1)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(151,187,205,1)",
		            data: [28, 48, 40, 19, 96, 27]
		        }
		    ]
		};
	
	
	var Radar = new Chart(ctx).Radar(data, {scaleShowLabels: false});
	
	// -- Pagination for the missing part
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
	
});

