/**
 * 
 */

$(document).ready(function(){
	$("input.check_missing").prop("checked", false);
	
	$("input.check_missing").on("click", function(){
		var block = $(this).closest("div.form-group");
		if($(this).prop("checked")){
			block.children().filter("input").attr("disabled", false);
			block.find("button").attr("disabled", false);
		} else {
			block.children().filter("input").attr("disabled", true);
			block.find("button").attr("disabled", true);
		}
	});
	$("button.late").click(function(e){
		e.preventDefault();
		$(this).toggleClass("btn-default");
		$(this).toggleClass("btn-primary");
		$(this).next().toggle('slow');
	});
	$("span.master-clock").click(function(){
		var bodies = $("");
	});
	$("span.bold-clock").click(function(){
		var panel = $(this).closest("div.panel");
		console.log(panel);
		panel.children().filter("div.panel-body").toggle("show");
	});
	
});

