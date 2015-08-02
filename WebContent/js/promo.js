/**
 * 
 */

// --------------------------------------------------------------------------------
// -- INITIALISATION
// --------------------------------------------------------------------------------

CANVAS_COLOR = [{'propety': 'chart-blue', 'hexa': '#246482', 'rank': 0, 'highlight': '#40B2E8'},
                {'propety': 'chart-red', 'hexa': '#8E2A3A', 'rank': 1, 'highlight': '#F54864'},
                {'propety': 'chart-yellow', 'hexa': '#EAC173', 'rank': 2, 'highlight': '#FFDB85'},
                {'propety': 'chart-grey', 'hexa': '#BDBDBD', 'rank': 3, 'highlight': '#7D7D7D'},
                {'propety': 'chart-green', 'hexa': '#45B29D', 'rank': 4, 'highlight': '#63FFE1'},
                {'propety': 'chart-orange', 'hexa': '#E27A3F', 'rank': 5, 'highlight': '#FF8A47'}];

$('body').scrollspy({
    target: '.bs-docs-sidebar',
    offset: 62
});
$("#sidebar").affix({
    offset: {
      top: 160
    }
});

$("a.list-group-item").on("click", function(e){
	e.preventDefault();
});

var MissingCharts = function(ctx){
	var datum = [];
	var jCanvas = $(ctx.canvas);
	var clsName = jCanvas.attr('data-target');
	
	$.post("Promo", {clsToCall: clsName}, function(data, status){
		data.result['class'].sort(function(a,b){
			if(a.miss > b.miss){
				return -1;
			} else if(a.miss < b.miss) {
				return 1;
			}
			return 0;
		});
		
		var idx = 0;
		
		data.result['class'].forEach(function(grp){
			var node = {
					value: grp.miss,
					colorName: CANVAS_COLOR[idx]["propety"],
				    color: CANVAS_COLOR[idx]["hexa"],
				    highlight: CANVAS_COLOR[idx]["highlight"],
				    label: 	grp.group
				};
			datum.push(node);
			idx++;
		});
		var  total = datum
		.map(function(dat){return dat.value})
		.reduce(function(d, t){return d + t});
		
		$("div.medal[data-miss='"+ clsName +"']").append('<span class="badge">'+total+'</span>');
				
		datum.forEach(function(element){
			jCanvas.nextAll(".ledgend").eq(1).append('<span class="badge '
					+element.colorName+'">'+element.label+'</span> \n');
		});
		
		new Chart(ctx).Doughnut(datum, {});
		console.log(datum.length);
		if(total == 0){
			console.log("run");
			var blk = jCanvas.parent();
			blk.empty();
		}
	});
};

//--------------------------------------------------------------------------------
//-- DRAW CHART
//--------------------------------------------------------------------------------

Chart.defaults.global.animation = false;
Chart.defaults.global.responsive = true;

var jChats = $('canvas[id^="missing-chart-"]');
var allCtx = jChats.map(function(){
	return document.getElementById($(this).attr("id")).getContext("2d");
});

allCtx = $.makeArray(allCtx);

allCtx.forEach(function(ctx){
	var cls = ctx.canvas.getAttribute('data-target');
	MissingCharts(ctx);
});

//--------------------------------------------------------------------------------
//-- DRAW CHART
//--------------------------------------------------------------------------------