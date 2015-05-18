/**
 * 
 */


$(document).ready(function(){
	var container = $("div#content.container-fluid.content");
	var pdf = $("embed");
	var pdfResize = function(){
		pdf.css("width",container.innerWidth() + "px" );
		pdf.css("height", $(window).height() - 71 + "px" );
	};
	
	pdfResize();
	$( window ).resize(function(){
		pdfResize();
	});
	
});
