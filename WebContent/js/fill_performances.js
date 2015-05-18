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

$("#btn-test").on("click",function () {
	var i;
	var tab = document.getElementsByName('${sub_skill.getTitle().replaceAll("[^\\w]","")}');
	for (i=0;i<tab.length;i++)
	{
		if(tab[i].checked)
		{
			var s = tab[i].value;
			break;
		}
	}
});
