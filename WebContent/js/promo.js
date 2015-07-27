/**
 * 
 */

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

Chart.defaults.global.animation = false;
Chart.defaults.global.responsive = true;

var jChats = $('canvas[id^="missing-chart-"]');
var allCtx = jChats.map(function(){
	return document.getElementById($(this).attr("id")).getContext("2d");
});
allCtx = $.makeArray(allCtx);
console.log(allCtx);
var data = [
            {
                value: 300,
                color:"#246482",
                highlight: "#E1EBF5",
                label: "Red"
            },
            {
                value: 100,
                color: "#EAC173",
                highlight: "#E1EBF5",
                label: "Green"
            },
            {
                value: 150,
                color: "#8E2A3A",
                highlight: "#E1EBF5",
                label: "Yellow"
            },
            {
                value: 50,
                color: "#BDBDBD",
                highlight: "#E1EBF5",
                label: "other"
            }
        ]


allCtx.forEach(function(ctx){
	new Chart(ctx).Doughnut(data, {});
});

var MissPerClass = function(clsName){
	$.post("Promo", {clsToCall: clsName}, function(data, status){
		console.log(data);
	});
}

MissPerClass("G5");
