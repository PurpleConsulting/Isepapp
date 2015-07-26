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
                color:"#F7464A",
                highlight: "#FF5A5E",
                label: "Red"
            },
            {
                value: 50,
                color: "#46BFBD",
                highlight: "#5AD3D1",
                label: "Green"
            },
            {
                value: 100,
                color: "#FDB45C",
                highlight: "#FFC870",
                label: "Yellow"
            }
        ]


allCtx.forEach(function(ctx){
	new Chart(ctx).Doughnut(data, {});
})

