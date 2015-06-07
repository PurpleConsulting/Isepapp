
$(document).ready(function(){
	
	// -- slide class-mate
	
	$("button.prev").click(function(){
		var li = $("ul.group-ul li.active");
		var newLi = li.prev("li");
		if(newLi.length == 0) newLi = $("ul.group-ul li").last();
		li.removeClass("active");
		newLi.addClass("active");
		
		// -- 
		
		var form = $("div.marker form.active");
		var newForm = form.prev("form");
		if(newForm.length == 0) newForm = $("div.marker form").last();
		form.removeClass("active");
		newForm.addClass("active");
		
		// --
		
		if(newLi.is(':first-child')){
			$(this).attr("disabled", true);
		}
		$("button.nxt").attr("disabled", false);
	});
	
	$("button.nxt").click(function(){
		var li = $("ul.group-ul li.active");
		var newLi = li.next("li");
		if(newLi.length == 0) newLi = $("ul.group-ul li").first();
		li.removeClass("active");
		newLi.addClass("active");
		
		// -- 
		
		var form = $("div.marker form.active");
		var newForm = form.next("form");
		if(newForm.length == 0) newForm = $("div.marker form").first();
		form.removeClass("active");
		newForm.addClass("active");
		
		// --
		
		if(newLi.is(':last-child')){
			$(this).attr("disabled", true);
		}
		$("button.prev").attr("disabled", false);
	})
	
	
});