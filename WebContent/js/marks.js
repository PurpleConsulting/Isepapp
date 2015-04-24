//Permet de griser les input
$(document).ready(function() {
	$(".display").prop('disabled', true);
	$("#valider").hide();
	$("#ajouter").hide();
	
});

$('#ModifierNotM').click(function() {
	$(".display").prop('disabled', false);
	$("#valider").show();
	$("#modifier").hide();
});

$('#AjouterB').click(function() {
	$("#ajouter").dialog({
		height : 300,
		width : 350
	});
});
