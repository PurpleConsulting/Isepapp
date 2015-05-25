/**
 * 
 */

$(document).ready(function(){
	$("select").prop("selectedIndex",0);
	$("select.selectpicker").on("change", function(){
		$("form[id^=form-skill]").removeClass("active");
		$("form[id^=form-skill"+ $(this).val() +"]").addClass("active");
	});
	
	$("div.skills-selector a.btn").on("click", function(e){
		e.preventDefault();
		$.get("jsp/skills/modal_skill_add.jsp", {}, function(data, status){
			var node = $( data );
			// -- we send it
			bootbox.dialog({
			title: 'Ajout d\'une compétence',
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Ajouter",
		                className: "btn-primary",
		                callback: function () {  }
					}
				}
			});
		});
	});
	
	$("div.input-group.subskill_adder").on("click", function(e){
		e.preventDefault();
		$.get("jsp/skills/modal_subskill_add.jsp", {}, function(data, status){
			var node = $( data );
			// -- we send it
			bootbox.dialog({
			title: 'Ajout d\'une compétence',
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Ajouter",
		                className: "btn-primary",
		                callback: function () {  }
					}
				}
			});
		});
	});
});
