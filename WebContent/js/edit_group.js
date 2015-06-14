/**
 * 
 */

$(document).ready(function(){
	 
	//--ini
	var noEnterForSubmit = function(element){
		$(element).bind("keypress", function (e) {
		    if (e.keyCode == 13) {
		        $("#btnSearch").attr('value');
		        //add more buttons here
		        return false;
		    }
		});
	}
	noEnterForSubmit("form");
	var addUserSubmit =  function(){ $("form#adduser").submit(); }
	
	$("span.link-dialog-grp").on("click", function(e) {
		var groupname = $(this).attr("data-delete");
		$.get("jsp/editor/modal_delete_group.jsp", {}, function(data, status){
			var node = $( data );
			// -- get the right dialog content
			
			node.find( "em.todel" ).text(groupname);
			node.attr("action", "/Isepapp/AlterGroups?delete-grp=" + groupname);
			// -- complete it with the student properties
			
			// -- we send it
			bootbox.dialog({
			title: 'Suppression du groupe ' + groupname,
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Supprimer",
		                className: "btn-danger btn-target",
		                callback: function () { $("form#delete-grp").submit(); }
					}
				}
			});
			$("form#delete-grp").addClass("no-enter");
			noEnterForSubmit("form.no-enter");
			$(".btn-danger.btn-target").attr("disabled", true);
			$("input.delete-std").on("keyup", function(){
				if($(this).val() == groupname) {
					$(".btn-danger.btn-target").attr("disabled", false);
					$("form#delete-grp").removeClass("no-enter");
				} else {
					$("form#delete-grp").addClass("no-enter");
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});

			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			
		});
	});
	 
	
	$("a.link-dialog-std").on("click", function(e) {
		e.preventDefault();
		var std = $(this).attr("data-delete");
		$.get("jsp/editor/modal_delete_user.jsp", {}, function(data, status){
			var node = $( data );
			// -- get the right dialog content
			
			node.find( "em.pop" ).text(std);
			node.attr("action", "/Isepapp/AlterGroups?delete-std=" + std);
			// -- complete it with the student properties
			
			// -- we send it
			bootbox.dialog({
			title: 'Suppression d\'un étudiant.',
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Supprimer",
		                className: "btn-danger btn-target",
		                callback: function () { $("form#delete-std").submit(); }
					}
				}
			});
			$("form#delete-std").addClass("no-enter");
			noEnterForSubmit("form.no-enter");
			$(".btn-danger.btn-target").attr("disabled", true);
			$("input.delete-std").on("keyup", function(){
				if($(this).val() == std) {
					$(".btn-danger.btn-target").attr("disabled", false);
					$("form#delete-std").removeClass("no-enter");
				} else {
					$("form#delete-std").addClass("no-enter");
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});
			
			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			
		});
	});
	
});
