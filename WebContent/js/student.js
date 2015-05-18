/**
 * JS SCRIPT FOR THE STUDENT PAGE
 */

$(document).ready(function(){
// -- Display the delivery	
$('button').tooltip({placement: 'bottom',trigger: 'manual'}).tooltip('show');

//  -- Marks: Global averages
	$("div.progress-bar").each(function(i){
		max = $(this).attr("aria-valuemax");
		mark = $(this).attr("aria-valuenow");
		
		per = (mark * 100) / max;
		
		$(this).css("width", per+"%");
		
	});
	
	// -- Pagination for the missing part
	var missing = $("#blk-missing");
	var missinNav = $("#blk-missing nav");
	
	
	$(".missing nav ul li a").on("click", function(event){
		event.preventDefault();
		var pattern = $(this).attr("data-target");
		var targets = [];
		for(var i = (3 * (pattern - 1)) + 1,j = 0; j < 3; i++,j++){

			targets.push(i);
		}

		missing.children(".active").removeClass('active');
		targets.forEach(function(t){
			missing.children("#blk" + t).addClass('active');
		})
		
		$(".missing nav ul li.active").removeClass('active');
		if($(this).attr("aria-label") =="Previous"){
			$(".missing nav ul li").first().next().addClass('active');
		}else if($(this).attr("aria-label") == "Next"){
			$(".missing nav ul li").last().prev().addClass('active');
		} else{
			$(this).parent().addClass('active');
		}
	});

	// -- User modification
	$("a.link-dialog-std").on("click", function(e) {
		var student = $(this).attr("data-delete");
		$.get("jsp/editor/modal_edit_student.jsp", {}, function(data, status){
			var node = $( data );
			// -- get the right dialog content
			
			node.find( "em.todel" ).text(student);
			node.attr("action", "/Isepapp/AlterGroups?delete-grp=" + student);
			// -- complete it with the student properties
			
			// -- we send it
			bootbox.dialog({
			title: 'Modification de l\'étudiant(e) ' + student,
			message: "...", //node.prop('outerHTML')
			buttons: {
					failure:{ 
						label: "Annuler",
		                className: "btn-default",
		                callback: function () {}
					},sucess:{
						label: "Modifier",
		                className: "btn-primary btn-target",
		                callback: function () { $("form#delete-grp").submit(); }
					}
				}
			});
			
			$(".btn-danger.btn-target").attr("disabled", true);
			$("input.delete-std").on("keyup", function(){
				if($(this).val() == student) {
					$(".btn-danger.btn-target").attr("disabled", false);
				} else {
					$(".btn-danger.btn-target").attr("disabled", true);
				}
			});
			
			var blink = function(){}
			setInterval('$(".fa-exclamation-triangle").fadeOut(400).delay(300).fadeIn(400)' ,400);
			
		});
	});

});

