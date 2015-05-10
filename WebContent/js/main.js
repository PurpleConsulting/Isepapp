/**
 * 
 */

 $(document).ready(function(e) {
	 
	// -- fixed nav bar
    $(window).scroll(function () {
        if ($(window).scrollTop() > '162') {
            $('#navmain').addClass('navbar-fixed-top');
            //$('#navmain').removeClass('navbar-static-top');                  
        } else {
            $('#navmain').removeClass('navbar-fixed-top');
            //$('#navmain').addClass('navbar-static-top');     
        }
    });
    

    $(document).on("mouseenter mouseleave", "div.tt-suggestion.tt-selectable",function(){
    	$(".tt-cursor").removeClass("tt-cursor");
    	$(this).toggleClass("tt-cursor");
    });
    
    $(".typeahead.search-virgin").one("focus", function(){
    	console.log("///////////////////////////////////");
    	$.post("Search", {
			query : "autocomplete",
			}, function(data, status){
				console.log(data);
				var pseudo = new Bloodhound({
				  datumTokenizer: Bloodhound.tokenizers.whitespace,
				  queryTokenizer: Bloodhound.tokenizers.whitespace,
				  local: data.result.pseudo
				});
				
				$('.typeahead').typeahead({
			    	  minLength: 1,
			    	  highlight: true
			        },{
			    	  name: 'Students',
			    	  source: pseudo,
			    	  templates: {
			    	    header: '<div class="tt-suggestion tt-suggestion-header"><em>Etudiants</em></div>'
			    	  }
			        });
				
			});
    	$(this).removeClass("search-virgin");
    });
    /* autocomplete search input
        $('.typeahead').typeahead({
    	  minLength: 1,
    	  highlight: true
        },{
    	  name: 'cars',
    	  source: cars,
    	  templates: {
    	    header: '<div class="tt-suggestion tt-suggestion-header"><em>Cars</em></div>'
    	  }
        },{
    	  name: 'girls',
    	  source: girls,
    	  templates: {
    	    header: '<div class="tt-suggestion tt-suggestion-header"><em>Girs</em></div>'
    	  }
        },{
		  name: 'boys',
		  source: boys,
		  templates: {
		    header: '<div class="tt-suggestion tt-suggestion-header"><em>Boys</em></div>'
		  }
		});*/

});