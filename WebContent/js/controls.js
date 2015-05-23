/**
 * 
 */
$('#tabs').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
});

$("button.marker").attr("disabled", true);
$("select").prop("selectedIndex",0);

//Select group name
$("select").change(function () {
    var str = "";
    str=$("select option:selected").text();
   	$.post("/Isepapp/Controls", {string:str}, function(data, status) {
   		var result = data.result.groups.join(", ");
   		$("div#name_group").html(str+" : "+ result).show("slow");
	 });	    	
	 if(str != "Sélectionnez un groupe"){
		$("button.marker").attr("disabled", false);
		$("form.groupgrid").attr("data-target", str.trim());
	 } else {
		$("button.marker").attr("disabled", true);
		$("form.groupgrid").attr("data-target", "");
	 }
});


$(document).ready(function(){ //If page is ready

	$("form button.marker").click(function (e){ //When click on the button send
		e.preventDefault();
		
		var form = $(this).parent();
		var grp = form.attr("data-target");
		var mrk = [];
		form.find("div.line label input[type=radio]:checked").each(function(){
			mrk.push($(this).attr("name")+"&"+$(this).attr("value"));
		});
		//
		$.post("/Isepapp/Controls", {scope: "group", group:grp, marks:mrk.join(";")}, function(data,status){
			console.log(data);
		});
	});
});	


