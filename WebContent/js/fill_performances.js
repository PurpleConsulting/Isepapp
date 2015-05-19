/**
 * 
 */
// Tabs in JQuery
/*$(function() {
  $( "#tabs" ).tabs();
});*/

$('#tabs').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
});

//Select group name
$("select").change(function () {
    var str = "";
    str=$("select option:selected").text();
   	$.post("/Isepapp/Controls", {string:str}, function(data, status) {
   		var result = data.result.groups;
   		$("div#name_group").html(str+" : "+data.result.groups);
	 })	    	
});

$(document).ready(function(){ //If page is ready
	$(".btn-test").click(function (){ //When click on the button send
		
		var a= $(this).val();
		alert($("#form"+a).serialize());
		$.ajax({ 
		  method: "POST",
		  url: "/Isepapp/Controls",
		  data: $("#form"+a).serialize() //Retrieve all info in form
		})
		  
	});
});	

/*$(".btn-test").on("click",function () {
	var i;
	var str = $("form").serialize();
	var tab = $(this).val();
	var a= $('input[type=radio][name=tab]:checked').attr('value');
	alert(a);
	for (i=0;i<tab.length;i++)
	{
		if(tab[i].checked)
		{
			var s = tab[i].value;
			alert(s);
			break;
		}
	}
});*/
