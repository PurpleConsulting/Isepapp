
$(document).ready(function(){
	// -- form add tutor interactions
	
	// -- initialisation
	$("input[type=password]").attr("disabled", true);
	$("input.toggle-input[type=checkbox]").prop("checked", false);
	
	// -- add external tutor
	$("a.form-show").click(function(e){
		e.preventDefault();
		$(this).hide("slow", function(){
			$(this).next("form").show("slow");
		});
	})
	
	$("input.toggle-input[type=checkbox]").change(function(){
		$("input[type=password]").attr("disabled", !$(this).prop('checked') );
	});
	
	/**
	 *  BOOTBOX
	 */
	
//	$("a.link-dialog-std").on("click", function(e) {
//		e.preventDefault();
//		var std = $(this).attr("data-delete");
//		$.get("jsp/editor/modal_delete_user.jsp", {}, function(data, status){
//			var node = $( data );
//			// -- get the right dialog content
//			
//			node.find( "em.pop" ).text(std);
//			node.attr("action", "/Isepapp/AlterGroups?delete-std=" + std);
//			// -- complete it with the student properties
//			
//			// -- we send it
//			bootbox.dialog({
//			title: 'Suppression d\'un étudiant.',
//			message: node.prop('outerHTML'),
//			buttons: {
//					failure:{ 
//						label: "Annuler",
//		                className: "btn-default",
//		                callback: function () {}
//					},sucess:{
//						label: "Supprimer",
//		                className: "btn-danger btn-target",
//		                callback: function () { $("form#delete-std").submit(); }
//					}
//				}
//			});
//			
//			$(".btn-danger.btn-target").attr("disabled", true);
//			$("input.delete-std").on("keyup", function(){
//				if($(this).val() == std) {
//					$(".btn-danger.btn-target").attr("disabled", false);
//				} else {
//					$(".btn-danger.btn-target").attr("disabled", true);
//				}
//			});
//			
//			var blink = function(){}
//			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
//			
//		});
//	});
	
	// -- delete all
	$("span.fa.fa-trash-o.master").click(function(){
		$.get("jsp/editor/modal_delete_all_tutors.jsp", {}, function(data, status){
			var node = $( data );
			bootbox.dialog({
				title: 'Suppression de tous les tuteurs.',
				message: node.prop('outerHTML'),
				buttons: {
						failure:{ 
							label: "Annuler",
			                className: "btn-default",
			                callback: function () {}
						},sucess:{
							label: "Confirmer",
			                className: "btn-danger btn-target",
			                callback: function () { $("form#delete-all-tutors").submit(); }
						}
					}
				});
			$("div.modal-header").addClass("danger-header");
		});
	});
	// -- delete one
	$("span.fa.fa-trash-o.slave").click(function(){
		var tutor = $(this).attr("data-target");
		$.get("jsp/editor/modal_delete_tutor.jsp", {}, function(data, status){
			var node = $( data );
			
			bootbox.dialog({
				title: 'Suppression du tuteur ' + tutor + '.',
				message: node.prop('outerHTML'),
				buttons: {
						failure:{ 
							label: "Annuler",
			                className: "btn-default",
			                callback: function () {}
						},sucess:{
							label: "Supprimer",
			                className: "btn-danger btn-target",
			                callback: function () { $("form#delete-tutor").submit(); }
						}
					}
				});
			$(".btn-danger.btn-target").attr("disabled", true);
			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			$("div.modal-header").addClass("danger-header");
			$("strong.st-target").text(tutor);
			$("input[name=del-tutor]").on("keyup", function(){
				if($(this).val() == tutor) {
					$(".btn-danger.btn-target").attr("disabled", false);
				} else {
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});
		});
	});
	// -- update one
	$("span.fa.fa-pencil").click(function(){
		var tutor = $(this).attr("data-target");
		$.get("jsp/editor/modal_alter_tutor.jsp", {}, function(data, status){
			var node = $( data );
			
			bootbox.dialog({
				title: 'Modification du tuteur ' + tutor + '.',
				message: node.prop('outerHTML'),
				buttons: {
						failure:{ 
							label: "Annuler",
			                className: "btn-default",
			                callback: function () {}
						},sucess:{
							label: "Modifier",
			                className: "btn-primary btn-target",
			                callback: function () { $("form#alter-tutor").submit(); }
						}
					}
				});
			$("div.modal-header").addClass("primary-header");
			$("u.u-target").text(tutor);
		});
	});
});