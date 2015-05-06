/*


$('span.fa').on('click', function(){
   alert("Voulez vous supprimer");
    $.post("/Isepapp/Value", {
    	idSupp :$(this).attr('id'),
    	modify:"3"
    	}, function(data, status){
    		window.open("Value");
    		}
    	)
});*/

$(document).ready(function(){

  $("form.form-inline input").attr("disabled", true);

  $('#modaladd').on('shown.bs.modal', function () {
    $('#myInput').focus()
  });


  $("a.modify, a.cancel").on("click",function(event){
    event.preventDefault();
    $("form.form-inline").toggleClass("off");
    if($(this).closest("form").hasClass("off")){
      $("form input").attr("disabled",true);
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
    $("div.cascade-warning").css("display", "inline-block")
    var line = $(this).closest("div.line");
    var icon = $(this).children("span");

    if($(this).hasClass("remove")){
    	alert("hel");
       // $("#delete1").prop(checked, true);
      var idValue = line.attr("id");
      line.addClass("has-error");
      line.children("div").each(function(i){
        $(this).children("input").attr("disabled",true);
      });

      icon.switchClass("danger","safe",0);
      icon.switchClass("fa-times","fa-undo",0);
      $(this).switchClass("remove", "reset",0);

    }else{

      var idValue = line.attr("id");
      line.removeClass("has-error");
      line.children("div").each(function(i){
        $(this).children("input").attr("disabled",false);
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
	    	})
	    	location.reload();
	});
  
  
 
  
});


