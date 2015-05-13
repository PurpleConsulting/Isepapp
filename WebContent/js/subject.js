/**
 * 
 */


$(document).ready(function(){
	var container = $("div#content.container-fluid.content");
	var pdf = $("embed");
	var pdfResize = function(){
		pdf.css("width",container.innerWidth() + "px" );
	};
	
	pdfResize();
	$( window ).resize(function(){
		pdfResize()
		console.log(container.innerWidth())
	}

	);
	
});
