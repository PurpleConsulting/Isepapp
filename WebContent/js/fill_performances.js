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
    $("select option:selected").each(function () {
          str = $(this).text();
    });
});