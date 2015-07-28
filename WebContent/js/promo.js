/**
 * 
 */

// --------------------------------------------------------------------------------
// -- INITIALISATION
// --------------------------------------------------------------------------------

CANVAS_COLOR = [{'propety': 'chart-blue', 'hexa': '#246482', 'rank': 0},
                {'propety': 'chart-red', 'hexa': '#8E2A3A', 'rank': 1},
                {'propety': 'chart-yellow', 'hexa': '#EAC173', 'rank': 2},
                {'propety': 'chart-grey', 'hexa': '#BDBDBD', 'rank': 3},
                {'propety': 'chart-green', 'hexa': '#45B29D', 'rank': 4},
                {'propety': 'chart-orange', 'hexa': '#E27A3F', 'rank': 5}];

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




var MissPerClass = function(clsName){
	var paint = [];
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
				    color: CANVAS_COLOR[idx]["hexa"],
				    highlight: "#E1EBF5",
				    label: 	grp.group
				};
			paint.push(node);
			idx++;
		});
	});
	return paint;
};


var data = [
            {color: "#246482",highlight: "#E1EBF5",label: "G5D",value: 16},
            {color: "#8E2A3A",highlight: "#E1EBF5",label: "G5C",value: 13},
            {color: "#EAC173",highlight: "#E1EBF5",label: "G5A",value: 10},
            {color: "#BDBDBD",highlight: "#E1EBF5",label: "G5B",value: 10}
        ];





// --------------------------------------------------------------------------------
// -- DRAW CHART
// --------------------------------------------------------------------------------
Chart.defaults.global.animation = false;
Chart.defaults.global.responsive = true;


var jChats = $('canvas[id^="missing-chart-"]');
var allCtx = jChats.map(function(){
	return document.getElementById($(this).attr("id")).getContext("2d");
});

allCtx = $.makeArray(allCtx);
allCtx.forEach(function(ctx){
	var datum = MissPerClass("G5");
	console.log(datum);
	new Chart(ctx).Doughnut(datum, {});
});

//--------------------------------------------------------------------------------
//-- DRAW CHART
//--------------------------------------------------------------------------------
