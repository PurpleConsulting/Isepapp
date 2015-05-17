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

$("select").change(function () {
    var str = "";
    str=$("select option:selected").text();
   	$.post("/Isepapp/Controls", {string:str}, function(data, status) {
   		var result = data.result.groups;
   		$("div#name_group").html(str+" : "+data.result.groups);
	 })	    	
});

