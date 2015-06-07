/**
 * 
 */

$(document).ready(function(){
	// -- form add tutor interactions
	
	// -- initialisation
	$("input[type=password]").attr("disabled", true);
	$("input.toggle-input[type=checkbox]").prop("checked", false);
	
	// -- add external tutor
	$("a.form-show").click(function(){
		$(this).next("form").show("slow");
	})
	
	$("input.toggle-input[type=checkbox]").change(function(){
		$("input[type=password]").attr("disabled", !$(this).prop('checked') );
	});
});