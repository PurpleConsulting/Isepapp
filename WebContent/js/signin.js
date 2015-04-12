$(document).ready(function(){
	block = $("div.alert-input");
	input = $("input").first();
	btn = $('button').first();
	
	
	input.blur(function(){
		$.post("/Isepapp/Signin", {Ajaxpseudo :$(this).val()}, function(data, status){
			var result = (data.result.find == "true") ? true : false; 
			if (result){
				input.parent().addClass('has-success');
				input.parent().removeClass('has-error');
				btn.removeAttr('disabled');
				block.addClass('alert-sucsses');
				block.css('display', 'inline-block');
			} else { 
				input.parent().addClass('has-error');
				input.parent().removeClass('has-success');
				btn.attr('disabled');
				block.css('display', 'inline-block');
				block.addClass('alert-danger');
			}
			
		})
	});
});