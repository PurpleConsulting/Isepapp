
$(document).ready(function(){
	
	// -- ini
	$('input[type="radio"]').prop("checked", false);
	var inkStatus = $("ul.group-ul").attr("data-status");
	var ink = $('li[data-target="'+inkStatus+'"]');
	var numberOfclick = $("ul.group-ul li").index(ink);
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
	});
	
	for(var i = 0; i < numberOfclick; i++){
		$("button.nxt").trigger('click');
	}
	
	$('form.cross-form input[type="radio"]').on("click", function(){
		console.log($(this));
		var form = $(this).closest("form.cross-form");
		console.log(form);
		var mark = form.find('input[type="radio"]:checked');
		var skill = form.find('h4');
		if(mark.length == skill.length){
			form.find('button').attr("disabled", false);
			
		}
	});
	
	
	// -- disabled form.
	$.post("/Isepapp/CrossControls", {"flag-form": "true"}, function(data, satus){
		
		data.result.grid.forEach(function(element){
			var form = $('input[value="' + element.student + '"]').parent("form");
			var input = form.find('input[name="sub_skill_'+element.subSkillId+'"][value="'+element.value+'"]');
			input.prop("checked", true);
			input.parent("label").addClass("checked");
		});
		
		$("form.cross-form").each(function(){
			var mark = $(this).find('input[type="radio"]:checked');
			var skill = $(this).find('h4');
			if(mark.length == skill.length){
				$(this).addClass("completed");
				$(this).find('input:not(:checked), button[type="submit"]').attr("disabled", true);
			}
			
		});
	});
	
});