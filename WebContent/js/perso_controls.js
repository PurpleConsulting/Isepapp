/**
 * 
 */
// Display checked btn when there are already marks for the student
$(document).ready(function(){
	data.result.marks.forEach(function(element){
	   			var line = $("input[name='"+ element.subSkill +"']");
	   			line.filter("[value="+ element.value +"]").prop("checked", true);
	})
});