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
	$("button.subskill_adder").on("click", function(e){
		e.preventDefault();
		$.get("jsp/skills/modal_subskill_add.jsp", {}, function(data, status){
			var node = $( data );
			// -- we send it
			bootbox.dialog({
			title: 'Ajout d\'une sous-compétence',
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
	$("span.fa-times-circle").on("click", function(e){
		var skill = $(this).prev().val();
		$.get("jsp/skills/modal_skill_delete.jsp", {}, function(data, status){
			var node = $( data );
			node.find("em.todel").text(skill);
			// -- we send it
			bootbox.dialog({
			title: 'Suppression d\'une compétence.',
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Supprimer",
		                className: "btn-danger btn-target",
		                callback: function () {  }
					}
				}
			});
			$("div.modal-header").addClass("modal-header-danger");
			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			$(".btn-danger.btn-target").attr("disabled", true);
			$("input.delete-std").on("keyup", function(){
				if($(this).val() == skill) {
					$(".btn-danger.btn-target").attr("disabled", false);
				} else {
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});
		});
	});
	$("span.fa-times-circle-o").on("click", function(e){
		var skill = $(this).prev().val();
		$.get("jsp/skills/modal_subskill_delete.jsp", {}, function(data, status){
			var node = $( data );
			node.find("em.todel").text(skill);
			// -- we send it
			bootbox.dialog({
			title: 'Suppression d\'une sous-compétence.',
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Supprimer",
		                className: "btn-danger btn-target",
		                callback: function () {  }
					}
				}
			});
			$("div.modal-header").addClass("modal-header-danger");
			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			$(".btn-danger.btn-target").attr("disabled", true);
			$("input.delete-std").on("keyup", function(){
				if($(this).val() == skill) {
					$(".btn-danger.btn-target").attr("disabled", false);
				} else {
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});
		});
	});
});
