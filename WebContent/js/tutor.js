
$(document).ready(function(){
	// -- form add tutor interactions
	
	// -- initialisation
	$("input[type=password]").attr("disabled", true);
	$("input.toggle-input[type=checkbox]").prop("checked", false);
	$("select").prop("selectedIndex",0);
	var noEnterForSubmit = function(element){
		$(element).on("keypress", function (e) {
		    if (e.keyCode == 13) {
		        $("#btnSearch").attr('value');
		        //add more buttons here
		        return false;
		    }
		});
	}
	noEnterForSubmit("form");
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
			$("fomr#delete-all-tutors").addClass("no-enter");
			noEnterForSubmit("form.no-enter");
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
			$("fomr#delete-tutor").addClass("no-enter");
			noEnterForSubmit("form");
			
			$(".btn-danger.btn-target").attr("disabled", true);
			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			$("div.modal-header").addClass("danger-header");
			$("strong.st-target").text(tutor);
			$("input[name=del-tutor]").on("keyup", function(){
				if($(this).val() == tutor) {
					$(".btn-danger.btn-target").attr("disabled", false);
					$("fomr#delete-tutor").removeClass("no-enter");
				} else {
					$("fomr#delete-tutor").addClass("no-enter");
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});
		});
	});
	// -- delete respo
	$("span.fa.fa-trash.standalone").click(function(){
		//var tutor = $(this).attr("data-target");
		$.get("jsp/editor/modal_no_delete_respo.jsp", {}, function(data, status){
			var node = $( data );
			bootbox.dialog({
				title: 'Suppression du tuteur responsable d\'APP.',
				message: node.prop('outerHTML'),
				buttons: {
						failure:{ 
							label: "    OK    ",
			                className: "btn-default",
			                callback: function () {}
						}
					}
				});
			$("div.modal-header").addClass("danger-header");
		});
	});
	// -- update one
	$("span.fa.fa-pencil").click(function(){
		var tutor = $(this).attr("data-target");
		var existingClass = $("select.selectpicker option");
		var obj = {
				firstName: $(this).parent().parent().find('li[data-naming="first_name"] em').text(),
				lastName: $(this).parent().parent().find('li[data-naming="last_name"] em').text(),
				eMail: $(this).parent().parent().find('li[data-naming="email"] em').text(),
				pseudo: $(this).parent().parent().find('li[data-naming="pseudo"]').text(),
		};
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
			noEnterForSubmit("form");
			$("div.modal-header").addClass("primary-header");
			$("u.u-target").text(tutor);
			// -- prefill the form
			$('input[name="update_pseudo"]').val(obj.pseudo);
			$('input[name="update_first_nane"]').val(obj.firstName);
			$('input[name="update_last_name"]').val(obj.lastName)
			$('input[name="update_email"]').val(obj.eMail);
			
			$('input[name="has-pass"]').change(function(){
				$(this).prev().attr("disabled", !$(this).prop('checked') );
			});
			console.log(existingClass.length);
			existingClass.each(function(option){
				$(this).clone().appendTo('select[name="update_group"]');
			});
		});
	});
});