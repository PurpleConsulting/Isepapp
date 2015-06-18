/**
 * 
 */

var student = $("h1 small").attr("data-target");
console.log(student);
$.post("/Isepapp/ServiceStudentHandler", {"depot-query": "true", "depot-delivery": student }, function(data, status){
	data.result.deadlines.forEach(function(element){
		if (element.completed){
			var tagList = $("ul.depots li[data-role='exemple']").clone();
			tagList.removeAttr("data-role");
			tagList.find("a").attr("href", element.path);
			tagList.find("button").text(element.description);
			tagList.find("button").attr("title", "Déposé le "+element.deveryDate);
			$("ul.depots").append(tagList);
		} else {
			var tagList = $("ul.dealine li[data-role='exemple']").clone();
			
		}
	});
	console.log(data);
	$(function () {
		$('button[data-toggle="tooltip"]').tooltip();
	})
});

$(function () {
	$('button[data-toggle="tooltip"]').tooltip();
})