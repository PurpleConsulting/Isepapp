/**
 * 
 */

$(document).ready(function(){
	
	function toDayIsTheDay(){
		var date = new Date();
		dateformat = date.getFullYear() +"-0"+ (date.getMonth()+1) +"-"+ date.getDate();
		$("input[name='day-skiped']").val(dateformat);
	}
	
	toDayIsTheDay();
	
	$("input.check_missing").prop("checked", false);
	
	$("input.check_missing").on("click", function(){
		var block = $(this).closest("div.form-group");
		if($(this).prop("checked")){
			block.children().filter("input").attr("disabled", false);
			block.find("button").attr("disabled", false);
			block.find("button").next().attr("disabled", false);
			block.find("input[type='hidden']").removeClass("no-submit");
		} else {
			block.children().filter("input").attr("disabled", true);
			block.find("button").attr("disabled", true);
			block.find("button").next().attr("disabled", true);
			block.find("input[type='hidden']").addClass("no-submit");
		}
	});
	
	$("button.late").click(function(e){
		e.preventDefault();
		$(this).toggleClass("btn-default");
		$(this).toggleClass("btn-primary");
		$(this).next().toggle('slow');
		$(this).next().val('');
	});
	
	$("span.master-clock").click(function(){
		var bodies = $("form .div ");
	});
	
	$("span.bold-clock").click(function(){
		var panel = $(this).closest("div.panel");
		console.log(panel);
		panel.children().filter("div.panel-body").toggle("show");
	});
	
	$("div#content form").on("submit", function(){
		$("input[type='hidden'].no-submit").remove();
	});
	
	$("input[name='day-skiped']").blur(function(){
		
		var scopeDate = $("select[name='planning'] option").map(function(opt){
			return $(this).val();
		});
		scopeDate = $.makeArray(scopeDate);
		
		if ( scopeDate.indexOf($(this).val()) != -1){
			$(this).parent().removeClass("has-warning");
			$("div.wrong-date").hide("slow");
		} else {
			$(this).parent().addClass("has-warning");
			$("div.wrong-date").show("slow");
			toDayIsTheDay();
		}
		console.log(scopeDate);
	});
});

