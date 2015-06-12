/**
 * 
 */
// -- input subject
$("#input_subject").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["pdf"],
    uploadAsync: false
});

$("#input_subject").on('fileuploaded', function(event, data, previewId, index){
	console.log("------------ response ------------ ");
	console.log(data.response);
});
//-- input promo
$("#input_promo").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["csv"]
});
//-- input backup
$("#input_backup").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["zip"]
});

// with plugin options
//$("#input-id").fileinput({'showUplroad':false, 'previewFileType':'any'});