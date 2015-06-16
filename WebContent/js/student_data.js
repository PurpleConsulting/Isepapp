/**
 * 
 */
RadarChart.defaultConfig.color = function() {};
RadarChart.defaultConfig.radius = 4;
RadarChart.defaultConfig.w = 500;
RadarChart.defaultConfig.h = 500;

var data = [{'axes':[]}];
data[0].className = "ldivad";
$("span.badge").each(function(){
	data[0].axes.push({
		'axis': $(this).attr("data-vis"),
		'value': parseInt($(this).text())
		})
});

console.log(data);

var chart = RadarChart.chart();
var cfg = chart.config(); // retrieve default config
RadarChart.defaultConfig.w = 300;
RadarChart.defaultConfig.h = 300;

var svg = d3.select('div.d3-target').append('svg')
  .attr('width', cfg.w + cfg.w + 50)
  .attr('height', cfg.h + cfg.h / 4);
svg.append('g').classed('single', 1).datum(data).call(chart);

