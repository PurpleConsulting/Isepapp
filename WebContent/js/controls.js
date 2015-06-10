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



function isValid() {
	$("ul#tabs.nav.nav-tabs li a").each(function(el){
   		$(this).children().remove();
   	});
   	$('form.groupgrid').each(function(){
   		if ($(this).find('div.line').length == $(this).find('input[type=radio]:checked').length){
   			var tab = $(this).parent();
   			var flag = "#" + tab.attr("id");
   			$('a[href="' + flag + '"]').append("<span class='fa fa-check-circle'></span>");
   			$(this).find("h4").append('<span class="fa fa-check-circle" data-toggle="tooltip" data-placement="right" title="Evaluation complète"></span>');
   			$('span[data-toggle="tooltip"]').tooltip();
   		}
   	})
}

//Select group name
$("select").change(function () {
    var str = "";
    str=$("select option:selected").text();
   	$.post("/Isepapp/Controls", {string:str}, function(data, status) {
   		$("input[type=radio]").prop("checked", false);
   		var result = data.result.groups.join(", ");
   		$("div#name_group").html("<strong>"+str+"</strong> : "+ result).hide().delay(20).show("slow");
   		data.result.marks.forEach(function(element){
   			var line = $("input[name='"+ element.subSkill +"']");
   			line.filter("[value="+ element.value +"]").prop("checked", true);
   		});
	 });
   	setTimeout(function(){isValid();}, 1000); 
   	
   	if(str != "Sélectionnez un groupe"){
		$("button.marker, button.adder").attr("disabled", false);
		$("form.groupgrid").attr("data-target", str.trim());
	 } else {
		$("button.marker, button.adder").attr("disabled", true);
		$("form.groupgrid").attr("data-target", "");
		$("div#name_group").html("<strong>"+str+"</strong> : "+ result).hide();
	 };
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
						"Félicitation, l'ajout de nouvelles notes a bien été effectué. Votre dernière notation se trouve maintenant " +
						"dans le formutaire. Vous pouvez dès maintenant consulter la page du groupe que vous " +
						"venez de noter: <a href=\"/Isepapp/Groups?scope="+ data.result.target +"\">"+ data.result.target +"</a></p>" );
				$("#confirmation_box").show("slow");
				setTimeout(function(){isValid();}, 1000); 
			}
		});
	});
	
	
	// -- bof bof
	$("form button.adder").click(function(e){
		e.preventDefault();
		$.get("jsp/mark/modal_control_addpersonal.jsp", {}, function(data, status){
			var node = $( data );
			$("form.groupgrid").each(function(){
				node.find("ul").append("<li><label><input type=\"checkbox\" value=\"\"/></label>"+ $(this).find("h4").attr("data-naming") +"</li>");
			});	    	
			bootbox.dialog({
			title: 'Ajout de notes personnalisées.',
			message: node.prop('outerHTML'),
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Démarrer",
		                className: "btn-primary",
		                callback: function () { }
					}
				}
			});
		});
		$.post("/Isepapp/Controls", {string: $("select.select-group option:selected").text()}, function(data, status) {
	   		var res = data.result.groups;
	   		res.forEach(function(element){
	   			$("select.select-modal").append("<option value=\"\">"+ element +"</option>");
	   		});
		});
	});
});	


