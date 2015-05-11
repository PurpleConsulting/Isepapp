/**
 * 
 */

 $(document).ready(function(e) {
	 
	// -- fixed nav bar
    $(window).scroll(function () {
        if ($(window).scrollTop() > '160') {
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
    	
    	$.post("Search", {
			query : "autocomplete",
			}, function(data, status){
				
				var pseudos = new Bloodhound({
				  datumTokenizer: Bloodhound.tokenizers.whitespace,
				  queryTokenizer: Bloodhound.tokenizers.whitespace,
				  local: data.result.pseudo
				});
				
				var groups = new Bloodhound({
					  datumTokenizer: Bloodhound.tokenizers.whitespace,
					  queryTokenizer: Bloodhound.tokenizers.whitespace,
					  local: data.result.group
					});
				
				$('.typeahead').typeahead({
			    	  minLength: 1,
			    	  highlight: true
			        },{
			    	  name: 'Groups',
			    	  source: groups,
			    	  limit: 2,
			    	  templates: {
			    	  header: '<div class="tt-suggestion tt-suggestion-header"><em>Groupes</em></div>'
			    	  }
			        },{
			    	  name: 'Students',
			    	  source: pseudos,
			    	  templates: {
			    	    header: '<div class="tt-suggestion tt-suggestion-header"><em>Etudiants</em></div>'
			    	  }
				    });
				
			});
    	
    	$(this).removeClass("search-virgin");
    	$(".typeahead").focus();
    });

});