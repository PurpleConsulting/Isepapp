

$(document).ready(function(){

  $("form.form-inline input").attr("disabled", true);

  $('#modaladd').on('shown.bs.modal', function () {
    $('#newtitle').focus();
  });
 
  $('#modifyValue').click(function(){
	   $("form input").attr("disabled",false);
  });
  
  $("a.modify, a.cancel").on("click",function(event){
    event.preventDefault();
    $("form.form-inline").toggleClass("off");
    if($(this).closest("form").hasClass("off")){
    	location.reload();
    	
      $("div.cascade-warning").removeAttr('style');
      $("div.values").attr("disabled",true);
      $("a.remove, a.reset").children("span").switchClass("safe","danger",0);
      $("a.remove, a.reset").children("span").switchClass("fa-undo","fa-times",0);
      $("a.remove, a.reset").switchClass("reset", "remove",0);
      $("div.has-error").removeClass("has-error");
      $(this).children("span").eq(0).switchClass("fa-undo", "fa-pencil", 0);
      $(this).children("span").eq(1).text("Modifier");
    } else {
      $("form input").attr("disabled",false);
      $(this).children("span").eq(1).text("Annuler");
      $(this).children("span").eq(0).switchClass("fa-pencil", "fa-undo",0);
    }
  });
  
 
   $("a.remove, a.reset").on("click",function(event){
    event.preventDefault();
    //-- definition
    $("div.suppression").css("display", "inline-block");
    var line = $(this).closest("div.line");
    var icon = $(this).children("span");

    if($(this).hasClass("remove")){
      var idValue = line.attr("id");
      line.addClass("has-error");
      line.children("div").each(function(i){
        $(this).children("input[type=text]").attr("disabled",true);
        $(this).children("input[type=checkbox]").prop('checked', true);
      });

      icon.switchClass("danger","safe",0);
      icon.switchClass("fa-times","fa-undo",0);
      $(this).switchClass("remove", "reset",0);

    }else{

      var idValue = line.attr("id");
      line.removeClass("has-error");
      line.children("div").each(function(i){
        $(this).children("input").attr("disabled",false);
        $(this).children("input[type=checkbox]").prop('checked', false);
      });

      icon.switchClass("safe","danger",0);
      icon.switchClass("fa-undo","fa-times",0);
      $(this).switchClass("reset", "remove",0);
    }
 })
 
 
 $("#add").on('click', function(){
	 $.post("/Isepapp/Values", {
	    	newtitle :$("#newtitle").val(),
	    	newpoints:$("#newpoints").val(),
	    	number:$("#number").val(),
	    	modify:"2"
	    	}).done(function(data){
	    		if(data=="0"){
	    			  $("div.warning").css("display", "none");
	    			  location.reload();
				}else{
					 $("div.warningA").css("display", "inline-block");
					 $("div.warningA").html('<p><strong>La valeur n\'est pas correct, veuillez recommencer </strong> </p>')
					 $("div.warningA").css('display', 'block');
				}
	    	});
	    	
});
   
   
   $('#add').attr('disabled', true);
  
   //Verifier nombre input pour modifier
   $(".value").blur(function() {
	   
	   var number= isNaN($(this).val());
	   var line= $(this).parent();
	   if(number==true || $(this).val()==""){ 
		   $(this).val(0);
		   $(this).parent().addClass('has-warning');
		   $(this).parent().removeClass('has-success');
		   line.parent().children().children("div.warning_modify").css("display", "inline-block");
		line.parent().children().children("div.warning_modify").css('display', 'block');
		   $("div.warning_modify").html('<p><strong>Attention:</strong> Veuillez saisir un nombre valide </p>')
		   $('#modifyValue').attr('disabled', false);
		 
	   }else{
		   
		  $(this).parent().removeClass('has-warning');
		   $("div.warning_modify").css("display", "none");
		   $('#modifyValue').attr('disabled', false);
	   }
	   
	   });
   
   //Verifier nombre input pour ajouter
 $("#newpoints").blur(function() {
	   
	   var number= isNaN($(this).val());
	  
	   if(number==true || $(this).val()==""){ 
		   $(this).val(0);
		   $(this).parent().addClass('has-warning');
		   $(this).parent().removeClass('has-success');
		   $("div.warningadd").css("display", "inline-block");
		   $("div.warningadd").html('<p class="bg-warning"><strong>Attention: </strong>Veuillez saisir un nombre valide </p>')
			$("div.warningadd").css('display', 'inline-block');
		   $('#add').attr('disabled', false);
		 
	   }else{
		   $(this).parent().removeClass('has-warning');
		   $("div.warningadd").css("display", "none");
		   $('#add').attr('disabled', false);
	   }
	   
	   });
 
 
  
});


