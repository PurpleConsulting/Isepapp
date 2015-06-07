
$(document).ready(function(){

	for(var i=0; i<=$("#taille").val(); i++){
		if($("#status"+i).val()== "false"){
			$("#status"+i).parent().removeClass("alert-info");
			$("#status"+i).parent().addClass("alert-danger");
			$("#datelim"+i).css("color", "#a94442");
			$("#timelim"+i).css("color", "#a94442");
			//$("#del"+i).css("color", "#a94442");
			
		};
	}
	
//	pour interdire la modification des inputs de la date
	$(".dateDisabled").attr("disabled",true);
	
	$("#valid").hide();
	
	$("a.modify, a.cancel").on("click",function(event){
	    event.preventDefault();
	    $("form.deadlineDate").toggleClass("off");
	    if($(this).closest("form").hasClass("off")){
	    	//location.reload();  <<<<<<---- Mais c'est quoi ce machin truc zozo ????
	      $("#valid").hide();
	      $("div.cascade-warning").removeAttr('style');
	      $("form.deadlineDate input").attr("disabled",true);
	      $(this).children("span").eq(0).switchClass("fa-undo", "fa-pencil", 0);
	      $(this).children("span").eq(1).text("Modifier");
	      $(this).switchClass("modify", "cancel",0);
	    } else {
	      $("#valid").show();
	      $("form.deadlineDate input").attr("disabled",false);
	      $(this).children("span").eq(1).text("Annuler");
	      $(this).children("span").eq(0).switchClass("fa-pencil", "fa-undo",0);
	      $(this).switchClass("cancel", "modify",0);
	    }
	  });
	
	
	  $("span.fa-trash-o").click(function(e){
		  e.preventDefault();
			var idDeadline = $(this).attr("data-target");
			var group = $(this).parent().attr("data-group");
			var title = $(this).parent().attr("data-subject");
			$.get("jsp/deadline/confim_deadline.jsp", {}, function(data, status){
				var node = $( data );
				// -- get the right dialog content
				node.find( "em.dd-title" ).text(title);
				node.find( "em.dd-group" ).text(group);
				
				// -- we send it
				bootbox.dialog({
				title: 'Suppression d\'une deadline.',
				message: node.prop('outerHTML'),
				buttons: {
						failure:{ 
							label: "Annuler",
			                className: "btn-default",
			                callback: function () {}
						},sucess:{
							label: "Supprimer",
			                className: "btn-danger btn-target",
			                callback: function () { $("form.form-del").submit(); }
						}
					}
				});
				$("input#modal-input-del").val("delete");
				$("input#modal-input-id").val(idDeadline);
			}); 
	  });
	  
});