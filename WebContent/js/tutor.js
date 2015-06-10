
$(document).ready(function(){
	// -- form add tutor interactions
	
	// -- initialisation
	$("input[type=password]").attr("disabled", true);
	$("input.toggle-input[type=checkbox]").prop("checked", false);
	$("select").prop("selectedIndex",0);
	
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
	
	// -- selecting groups
	$("select").change(function(){
		var Cls = $(this).val();
		$.post("/Isepapp/Tutors", {class_name: Cls}, function(data, status){
			$("div.form-group.welcome-box div").hide("slow", function(){
				$("div.form-group.welcome-box div label").remove();
				data.result.groups.forEach(function(element){
					$("div.form-group.welcome-box div").append('<label><span class="fa fa-check-square"></span>'+ element.name + '</label>');
				});
				$("div.form-group.welcome-box div label input").prop("checked", true);
			})
			$("div.form-group.welcome-box div").show("slow");
		});
	});
	
	/**
	 *  BOOTBOX
	 */
	
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