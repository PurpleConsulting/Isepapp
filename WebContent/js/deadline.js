
$(document).ready(function(){

	for(var i=0; i<=$("#taille").val(); i++){
		if($("#status"+i).val()== "false"){
			$("#status"+i).parent().removeClass("alert-info");
			$("#status"+i).parent().addClass("alert-danger");
			$("#datelim"+i).css("color", "#a94442");
			$("#timelim"+i).css("color", "#a94442");
			$("#del"+i).css("color", "#a94442");
			
		};
	}
	
//	pour interdire la modification des inputs de la date
	$(".dateDisabled").attr("disabled",true);
	
	$("#valid").hide();
	
	$("a.modify, a.cancel").on("click",function(event){
	    event.preventDefault();
	    $("form.form-inline").toggleClass("off");
	    if($(this).closest("form").hasClass("off")){
	    	location.reload();
	    	$("#valid").hide();
	      $("div.cascade-warning").removeAttr('style');
	      $("div.values").attr("disabled",true);
	      $("a.remove, a.reset").children("span").switchClass("safe","danger",0);
	      $("a.remove, a.reset").children("span").switchClass("fa-undo","fa-times",0);
	      $("a.remove, a.reset").switchClass("reset", "remove",0);
	      $("div.has-error").removeClass("has-error");
	      $(this).children("span").eq(0).switchClass("fa-undo", "fa-pencil", 0);
	      $(this).children("span").eq(1).text("Modifier");
	    } else {
	    	$("#valid").show();
	      $("form input").attr("disabled",false);
	      $(this).children("span").eq(1).text("Annuler");
	      $(this).children("span").eq(0).switchClass("fa-pencil", "fa-undo",0);
	    }
	  });
		  
		 

	
});