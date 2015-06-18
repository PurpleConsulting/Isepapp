/**
 * 
 */
$(function () {
	$('a[data-toggle="tooltip"]').tooltip();
});

var openUpload = function(link){
	$("div.home-fileinput, div#input-border").fadeOut();
	var inp = $('input[type="file"][data-role="exemple"]').clone();
	inp.attr("id", "delivery-upload");
	$("div#input-border").append(inp);
	plugInput(link.attr("data-info"), link.attr("data-target"));
	$("div.home-fileinput, div#input-border").fadeIn("slow");
//	$("div.home-fileinput").show("slow", function(){
//		$(this).find("div#input-border").fadeIn("slow");
//	});
}

var student = $("h1 small").attr("data-target");
$.post("/Isepapp/ServiceStudentHandler", {"depot-query": "true", "depot-delivery": student }, function(data, status){
	data.result.deadlines.forEach(function(element){
		if (element.completed && !element.cross){
			var tagList = $("ul.depots li[data-role='exemple']").clone();
			tagList.removeAttr("data-role");
			tagList.find("a").attr("href", element.path);
			tagList.find("a").text(element.description);
			tagList.find("a").attr("title", "Déposé le "+element.deveryDate);
			$("ul.depots").append(tagList);
		} else if(!element.completed && element.status){
			var tagList = $("ul.deadline li[data-role='exemple']").clone();
			tagList.removeAttr("data-role");
			tagList.find("a").text(element.description);
			tagList.find("a").attr("data-target",element.id);
			tagList.find("a").attr("data-info",element.description);
			$("ul.deadline").append(tagList);
			
		}
	});
	console.log(data);
	$(function () {
		$('a[data-toggle="tooltip"]').tooltip();
	});
	$("ul.deadline a.btn-primary").click(function(e){
		e.preventDefault();
		openUpload($(this));
	});
});
var plugInput = function(title, iddeadline){
	$("#delivery-upload").fileinput({
	    uploadUrl: "/Isepapp/FileHandler?deadline-number="+ iddeadline, // server upload action <div class="file-drop-zone-title" 
	    uploadAsync: false,
	    showCaption: false,
	    showCancel: true,
	    showRemove: true,
	    language: "fr",
	    dropZoneTitle: title,
	    allowedFileExtensions: ["pdf"],
	    maxFileCount: 1
	});
}

//$("#delivery-upload").fileinput({
//    uploadUrl: "/Isepapp/FileHandler?delivery-doc=", // server upload action <div class="file-drop-zone-title" 
//    uploadAsync: false,
//    showCaption: false,
//    showCancel: true,
//    showRemove: true,
//    language: "fr",
//    dropZoneTitle: "//////////////",
//    allowedFileExtensions: ["pdf"],
//    maxFileCount: 1
//});