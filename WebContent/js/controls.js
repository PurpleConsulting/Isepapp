/**
 * 
 */
$('#tabs').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
});

$("button.marker, button.adder ").attr("disabled", true);
$("select").prop("selectedIndex",0);
$("input[type=radio]").attr("checked", false);

//Select group name
$("select").change(function () {
    var str = "";
    str=$("select option:selected").text();
   	$.post("/Isepapp/Controls", {string:str}, function(data, status) {
   		var result = data.result.groups.join(", ");
   		$("div#name_group").html("<strong>"+str+"</strong> : "+ result).hide().delay(20).show("slow");
	 });	    	
	 if(str != "Sélectionnez un groupe"){
		$("button.marker, button.adder").attr("disabled", false);
		$("form.groupgrid").attr("data-target", str.trim());
	 } else {
		$("button.marker, button.adder").attr("disabled", true);
		$("form.groupgrid").attr("data-target", "");
		$("div#name_group").html("<strong>"+str+"</strong> : "+ result).hide();
	 }
});


$(document).ready(function(){ //If page is ready

	var reInitConfboc = function(){
		$("button.close").parent().fadeOut("slow", function(){
			$("button.close").parent().removeClass("alert-success");
			$("button.close").parent().children("p").remove();
		});
	}
	$("button.close").click(function(){reInitConfboc()});
	$("form button.marker").click(function (e){ //When click on the button send
		e.preventDefault();
		reInitConfboc();
		$(this).children("span").removeClass("fa-crosshairs").addClass("fa-cog fa-spin");
		$("button.marker").toggleClass("working");
		var form = $(this).parent();
		var grp = form.attr("data-target");
		var mrk = [];
		form.find("div.line label input[type=radio]:checked").each(function(){
			mrk.push($(this).attr("name")+"&"+$(this).attr("value"));
		});
		$.post("/Isepapp/Controls", {scope: "group", group:grp, marks:mrk.join(";")}, function(data,status){
			$("button.marker span").removeClass("fa-cog fa-spin").addClass("fa-crosshairs");
			$("button.marker").toggleClass("working");
			if(data.result.status == true){
				// SURTOUT PAS CETTE LIGNE.
				//$('html, body').animate( { scrollTop: $("#name_group").offset().top - 50 }, 800 );
				$("#confirmation_box").toggleClass("alert-success");
				$("#confirmation_box").append("<p><span class=\"fa fa-check-circle\" style=\"color:#3E753F;\"></span>   " +
						"Félicitation, l'ajout de nouvelles notes à bien été effectué. Votre dernière notation se trouve maintenant" +
						"dans le formutaire. Vous pouvez également dès maitenant consulter la page du group que vous " +
						"venez de noter: <a href=\"/Isepapp/Groups?scope="+ data.result.target +"\">"+ data.result.target +"</a></p>" );
				$("#confirmation_box").show("slow");
			}
		});
	});
});	


