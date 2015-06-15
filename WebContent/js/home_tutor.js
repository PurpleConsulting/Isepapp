/**
 * 
 */

$(document).ready(function(){
	// -- init
	
	// --
	$("a.change-pass").click(function(e){
		e.preventDefault();
		$("#alert-passwd-form").toggle("slow");
		//.show("slow");
	});
	
	$(".fa-lock, .fa-unlock").click(function(){
		var input = $(this).prev();
		var status = input.attr("type") == "password" ? "text" : "password";
		$(this).toggleClass("fa-lock");
		$(this).toggleClass("fa-unlock");
		input.attr("type", status);
	});
	
	(function () {
		var pseudo = $("#alert-passwd").attr("data-target");
		$.post("/Isepapp/SeviceTuteurHandler", { isExternal: pseudo }, function(data, status){
			if(data.result.isExt){
				$("#alert-passwd").css("display", "block");
			}
			
			var template = $("#line-grp-template");
			data.result.groups.forEach(function(element){
				var line = template.clone();
				line.removeAttr("id"); line.addClass("alert-grp");
				// -- name
				line.find("strong.grp a").text(element.name);
				line.find("strong.grp a").attr("href", "Groups?scope="+element.name);
				// -- abs
				line.find('a[data-info="missing"]').attr("href", "Groups?scope="+element.name+"#missing-div");
				line.find("strong.abs").text(element.missings);
				// -- delivery
				line.find('a[data-info="delivery"]').attr("href", "Groups?scope="+element.name+"#delivery-div");
				line.find("strong.del").text(element.deliveries);
				$("div.group").append(line);
			});
			var showup = function(tag){
				tag.show(300, function(){
					showup($(this).next());
				});
			};
			showup($("div.group div.alert-grp").first());
		});
	})();
	
	
	$("div.group form button.btn").click(function(){
		var oldPwd = $('input[name="old-pwd"]').val();
		var newPwd = $('input[name="new-pwd"]').val();
		$.post("/Isepapp/SeviceTuteurHandler", { "old-pwd": oldPwd , "new-pwd": newPwd  }, function(data, status){
			if(data.result.insert){
				$("div.form-group input").val('');
				$("div.form-group").addClass("has-success");
				$(".alert-pwd").removeClass("alert-warning");
				$(".alert-pwd").addClass("alert-success");
				$(".alert-pwd").append("<strong>ok:</strong>, mot de passe modifié.")
				$(".alert-pwd").show("slow");
				
			}
			console.log(data);
		});
	});
	 
});