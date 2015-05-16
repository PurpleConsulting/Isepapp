/**
 * 
 */
$(document).ready(function(){
	 
	$('#modaladd').on('shown.bs.modal', function () {
		 $('#myInput').focus()
	});
	
	$("#add").click(function(){
		$("form#adduser").submit()
	});
	
});
