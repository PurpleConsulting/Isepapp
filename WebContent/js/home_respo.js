/**
 * 
 */
var uploadIni = function(){
	$("div.progress, div.alert").hide();
	$("div.progress").hide();
	$("div.progress div.progress-bar").css("width", "0%");
	$("div.progress div.progress-bar").removeClass("progress-bar-success");
	$("div.progress div.progress-bar").removeClass("progress-bar-danger");
	$("div.semester div.alert").removeClass("alert-info");
	$("div.semester div.alert").removeClass("alert-warning");
	$("div.progress div.progress-bar").addClass("progress-bar-striped");
	$("div.progress div.progress-bar").addClass("active");
};
	 
// -- initialisation


function okBar(obj, j, res){
	if(j > 499){
		setTimeout(function(){
			obj.removeClass("progress-bar-striped"); obj.removeClass("active");
			var statusClass = res.success ? "success" : "danger";
			var statusMsg = res.success ? "info" : "warning";
			$("div.semester div.alert").addClass("alert-"+statusMsg);
			obj.addClass("progress-bar-" + statusClass);
			$("div.semester div.alert").html(res.message);
			$("div.semester div.alert").show("slow");
		}, 1500);
	}
	return false;
};
$("input.input-class").on("filebatchpreupload", function(){
	uploadIni();
});
// -- input subject
$("#input_subject").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["pdf"],
    showCancel: false,
    showRemove: false,
    uploadAsync: false
});
//-- input promo
$("#input_promo").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["csv"],
    showCancel: false,
    showRemove: false,
    uploadAsync: false
});
//-- input backup
$("#input_backup").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["zip"],
    showCancel: false,
    showRemove: false,
    uploadAsync: false
});
$("div.kv-upload-progress").remove();
$("#input_subject").on('filebatchuploadsuccess', function(event, data, previewId, index){
	var i = 0;
	var bar = $("form.subject_file div.progress-bar");
	bar.parent().show();
	for(j = 0; j< 500 || okBar(bar, j, data.response);  j++){
		i = i + 0.2; bar.css("width", i+"%");
	}
});

$("#input_promo").on('filelock', function(event, filestack, extraData){
	var bar = $("form.promo_file div.progress-bar");
	bar.parent().show();
	var i = 0;
	for(j = 0; j< 250;  j++){
		i = i + 0.2; bar.css("width", i+"%");
	}
	console.log("////////////////////");
});
$("#input_promo").on('filebatchuploadsuccess', function(event, data, previewId, index){
	var i = 50;
	var bar = $("form.promo_file div.progress-bar");
	for(j = 50; j< 500 || okBar(bar, j, data.response);  j++){
		i = i + 0.2; bar.css("width", i+"%");
	}
	console.log(data.response);
});


// with plugin options
//$("#input-id").fileinput({'showUplroad':false, 'previewFileType':'any'});


