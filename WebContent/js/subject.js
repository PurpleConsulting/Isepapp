/**
 * 
 */


$(document).ready(function(){
	var container = $("div#content.container-fluid.content");
	var pdf = $("embed");
	var pdfResize = function(){
		pdf.css("width",container.innerWidth() + "px" );
		pdf.css("height", $(window).height() - 50 + "px" );
	};
	
	pdfResize();
	$( window ).resize(function(){
		pdfResize();
	});
	
});
$("div#content").css('background-image', 'url("img/empty/subject.svg")');
$("footer").remove();
