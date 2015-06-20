/**
 * 
 */

 $(document).ready(function(e) {
	 
	// -- fixed nav bar
	 
    $(window).scroll(function () {
        if ($(window).scrollTop() > '160') {
            $('#navmain').addClass('navbar-fixed-top');
            $("#content").css("margin-top","55px");
            //$('#navmain').removeClass('navbar-static-top');                  
        } else {
            $('#navmain').removeClass('navbar-fixed-top');
            $("#content").css("margin-top","0px");
            //$('#navmain').addClass('navbar-static-top');     
        }
    });
    $(document).on("mouseenter mouseleave", "div.tt-suggestion.tt-selectable",function(){
    	$(".tt-cursor").removeClass("tt-cursor");
    	$(this).toggleClass("tt-cursor");
    });
    
    // -- Search Bar
 	
	$.post("Search", {
		query : "autocomplete",
		}, function(data, status){
			
			var pseudos = new Bloodhound({
			  datumTokenizer: Bloodhound.tokenizers.whitespace,
			  queryTokenizer: Bloodhound.tokenizers.whitespace,
			  local: data.result.student
			});
			
			var groups = new Bloodhound({
				  datumTokenizer: Bloodhound.tokenizers.whitespace,
				  queryTokenizer: Bloodhound.tokenizers.whitespace,
				  local: data.result.group
			});
			
			var classes = new Bloodhound({
				  datumTokenizer: Bloodhound.tokenizers.whitespace,
				  queryTokenizer: Bloodhound.tokenizers.whitespace,
				  local: data.result.classes
			});
			
			$('.typeahead').typeahead({
		    	  minLength: 1,
		    	  highlight: true,
		    	  hint: false
		        },{
		    	  name: 'Classes',
		    	  source: classes,
		    	  limit: 1,
		    	  templates: {
		    	    header: '<div class="tt-suggestion tt-suggestion-header"><em class="Bloodhound">Classes</em></div>'
		    	  }
			    },{
		    	  name: 'Groups',
		    	  source: groups,
		    	  limit: 3,
		    	  templates: {
		    	  header: '<div class="tt-suggestion tt-suggestion-header"><em class="Bloodhound">Groupes</em></div>'
		    	  }
		        },{
		    	  name: 'Students',
		    	  source: pseudos,
		    	  templates: {
		    	    header: '<div class="tt-suggestion tt-suggestion-header"><em class="Bloodhound">Etudiants</em></div>'
		    	  }
			    });
			
		});


});