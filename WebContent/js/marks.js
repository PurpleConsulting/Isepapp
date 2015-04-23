//Permet de griser les input
$(document).ready(function() {
	$("input").prop('disabled', true);
	$("#valider").hide();
	$("#ajouter").hide();
	$("#newtitle").prop('disabled', false);
	$("#newpoints").prop('disabled', false);
	$("#add").prop('disabled', false);
	$("#modify").prop('disabled', false);
	
});

$('#ModifierNotM').click(function() {
	$("input").prop('disabled', false);
	$("#valider").show();
	$("#modifier").hide();
});

$('#AjouterB').click(function() {
	$("#ajouter").dialog({
		height : 300,
		width : 350
	});
});
