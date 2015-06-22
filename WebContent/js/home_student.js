/**
 * 
 */
$(function () {
	$('a[data-toggle="tooltip"]').tooltip();
});

var plugInput = function(title, iddeadline){
	$("#delivery-upload").fileinput({
	    uploadUrl: "/Isepapp/FileHandler?deadline-number="+ iddeadline, // server upload action <div class="file-drop-zone-title" 
	    uploadAsync: false,
	    showCaption: false,
	    showCancel: true,
	    showRemove: true,
	    language: "fr",
	    dropZoneTitle: title,
	    allowedFileExtensions: ["pdf"],
	    maxFileCount: 1
	});
	

}

var openUpload = function(link){
	$("div.home-fileinput, div#input-border").fadeOut();
	$("div#input-border").empty();
	var inp = $('input[type="file"][data-role="exemple"]').clone();
	inp.attr("id", "delivery-upload");
	$("div#input-border").append(inp);
	plugInput(link.attr("data-info"), link.attr("data-target"));
	$("div.home-fileinput, div#input-border").fadeIn("slow");
//	$("div.home-fileinput").show("slow", function(){
//		$(this).find("div#input-border").fadeIn("slow");
//	});
}
// -- absences
var student = $("h1 small").attr("data-target");
$.post("/Isepapp/ServiceStudentHandler", {"missing-query": true, "missing-std": student}, function(data, status){
	var i = 0; var j = 0;
	data.result.miss.forEach(function(element){
		i++;
		var tag = $("div.home-missing #blk[data-role='exemple']").clone();
		tag.removeAttr("data-role");
		tag.removeAttr("id"); tag.attr("id", "blk" + i);
		tag.find("span.when").text(element.supporting);
		tag.find("span.why").text(element.date);
		if(i <= 3){
			tag.addClass("active")
		}
		if(element.late){
			tag.addClass("alert-info");
			tag.find("strong").text("Retard");
		} else {
			tag.addClass("alert-warning");
			tag.find("strong").text("Absence");
		}
		
		$("#blok-missing").append(tag);
		if(i%3 == 1){
			j++; var active = (j == 1) ? "active" : "";
			$("nav.missing-nav ul.pagination li").last().before("<li class=\""+active+"\"><a data-target="+j+" href=\"#\">"+j+"</a></li>");
		}
		
		var missing = $("#blok-missing");
		var missinNav = $("#blok-missing nav");
		$(".home-missing nav ul li a").on("click", function(event){
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
			$(".home-missing nav ul li.active").removeClass('active');
			if($(this).attr("aria-label") =="Previous"){
				$(".home-missing nav ul li").first().next().addClass('active');
			}else if($(this).attr("aria-label") == "Next"){
				$(".home-missing nav ul li").last().prev().addClass('active');
			} else{
				$(this).parent().addClass('active');
			}
		});
	})
	
	$(".home-missing nav ul li a[aria-label='Next']").attr("data-target", j);
	
	if(data.result.miss.length == 0){
		$("#blok-missing").empty();
		$("nav.missing-nav").remove();
		$("#blok-missing").append('<div class="col-xs-6 col-xs-offset-3">' +
				'<img src="img/empty/missing.svg" alt="" class="app-empty-img\">' +
				'</div>');
	}
});
// -- deadline
$.post("/Isepapp/ServiceStudentHandler", {"depot-query": true, "depot-delivery": student }, function(data, status){
	data.result.deadlines.forEach(function(element){
		if (element.completed && !element.cross){
			var tagList = $("ul.depots li[data-role='exemple']").clone();
			tagList.removeAttr("data-role");
			tagList.find("a").attr("href", element.path);
			tagList.find("a").text(element.description);
			tagList.find("a").attr("title", "Déposé le "+element.deveryDate);
			$("ul.depots").append(tagList);
			
		} else if(!element.completed && element.status){
			var tagList = $("ul.deadline li[data-role='exemple']").clone();
			tagList.removeAttr("data-role");
			tagList.find("a").text(element.description);
			tagList.find("a").attr("data-target",element.id);
			tagList.find("a").attr("data-info",element.description);
			tagList.find("a").append(" <span class=\"badge\">" + element.dateLimit + "</span>");
			tagList.find("a").append(" <span class=\"fa fa-exclamation-triangle\"></span>");
			$("ul.deadline").append(tagList);
			
		} else if(!element.completed && !element.status){
			var tagList = $("ul.missing-depots li[data-role='exemple']").clone();
			tagList.removeAttr("data-role");
			tagList.find("a").text(element.description);
			tagList.find("a").append(" <span class=\"fa fa fa-bomb\"></span>");
			$("ul.missing-depots").append(tagList);
		}
	});
	$(function () {
		$('a[data-toggle="tooltip"]').tooltip();
	});
	$("ul.deadline a.btn-primary").click(function(e){
		e.preventDefault();
		openUpload($(this));
	});
});

