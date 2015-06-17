/**
 * 
 */
// Display checked btn when there are already marks for the student
$(document).ready(function(){
	
	function isValid() {
		$("ul#tabs.nav.nav-tabs li a").each(function(el){
	   		$(this).children().remove();
	   	});
		$('form.groupgrid').each(function(el){
			$(this).find("span.fa-check-circle").remove();
		});
	   	$('form.groupgrid').each(function(){
	   		if ($(this).find('div.line').length == $(this).find('input[type=radio]:checked').length){
	   			var tab = $(this).parent();
	   			var flag = "#" + tab.attr("id");
	   			$('a[href="' + flag + '"]').append("<span class='fa fa-check-circle'></span>");
	   			$(this).find("h4").append('<span class="fa fa-check-circle" data-toggle="tooltip" data-placement="right" title="Evaluation complète"></span>');
	   			$('span[data-toggle="tooltip"]').tooltip();
	   		}
	   	});
	};
	var student =  $("div.grid").attr("data-target");
	
	$.post("/Isepapp/PersoControls", { marked: student }, function(data, status){
		data.result.grid.forEach(function(element){
			var markedInput = $("input[name='sub_skill_"+ element.subSkill +"'][value='"+ element.value +"']");
			markedInput.prop("checked", true);
			if(element.groupMark){
				labelClass = 'checked-grp'
				markedInput.prop("disabled", true);
			} else {
				labelClass = 'checked';
			}
			markedInput.parent("label").addClass(labelClass);
		});
		isValid();
	});
	
});