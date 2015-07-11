
// -- utils functions

var uploadIni = function(){
	$("div.progress, div.alert").hide();
	$("div.progress").hide();
	$("div.progress div.progress-bar").css("width", "0%");
	$("div.progress div.progress-bar").removeClass("progress-bar-success");
	$("div.progress div.progress-bar").removeClass("progress-bar-danger");
	$("div.semester div.alert").removeClass("alert-info");
	$("div.semester div.alert").removeClass("alert-warning");
	$("div.progress div.progress-bar").addClass("progress-bar-striped");
	$("div.progress div.progress-bar").addClass("active");
};

var okBar = function(obj, res){
	setTimeout(function(){
		obj.removeClass("progress-bar-striped"); obj.removeClass("active");
		var statusClass = res.success ? "success" : "danger";
		var statusMsg = res.success ? "info" : "warning";
		$("div.semester div.alert").addClass("alert-"+statusMsg);
		obj.addClass("progress-bar-" + statusClass);
		$("div.semester div.alert").html(res.message);
		$("div.semester div.alert").show("slow");
	}, 1500);
	return false;
};

// -- initialisation

Chart.defaults.global.responsive = true;
(function () {
	/**  LOAD GROUPS OF THE RESPO IF ANY **/
	
	var pseudo = $("h1 small").attr("data-target");
	$.post("/Isepapp/SeviceTuteurHandler", { isExternal: pseudo }, function(data, status){
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
			$("div.respo-group").append(line);
		});
		var showup = function(tag){
			tag.show(300, function(){
				showup($(this).next());
			});
		};
		showup($("div.respo-group div.alert-grp").first());
		if(data.result.groups.length == 0){
			var img = $("div.group div[data-role='more-groups']").before('<br/><div class="col-xs-4 col-xs-offset-4" class="app-empty-img">' +
					'<img src="img/empty/group.svg" alt="" class="app-empty-img" />' +
					'</div>');
			$("div.app-empty-img").show("slow");
		}
	});
})();

(function () {
	/** LOAD THE MARK OF THE PROMOTION **/
	$.post("/Isepapp/ServiceRespoHandler", { "mark-service": "true"}, function(data, status){
		console.log(data);
		var ctx = document.getElementById("barchart-canvas").getContext("2d");
		//var lab = data.result.prom.map(function(element){return element.name});
		var lab = Object.keys(data.result.prom);
		var datum = {
				labels: lab,
				datasets: [
	               {
	                   label: "Moyenne de la Promotion",
	                   fillColor: "rgba(220,220,220,0.5)",
	                   strokeColor: "rgba(220,220,220,0.8)",
	                   highlightFill: "rgba(220,220,220,0.75)",
	                   highlightStroke: "rgba(220,220,220,1)",
	                   data: lab.map(function(group){return data.result.prom[group]})
	               }
               ]
           };
		var canvas = $("#barchart-canvas");
		var barchart = new Chart(ctx).Bar(datum);
		canvas.click(function(evt){
		    var activeBars = barchart.getBarsAtEvent(evt);
		    console.log(activeBars);
		});
	});
})();



$("input.input-class").on("filebatchpreupload", function(){
	uploadIni();
});

// -- input subject
$("#input_subject").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["pdf"],
    showCancel: false,
    showRemove: false,
    uploadAsync: false
});
//-- input promo
$("#input_promo").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["csv"],
    showCancel: false,
    showRemove: false,
    uploadAsync: false
});
//-- input backup
$("#input_backup").fileinput({
    language: "fr",
    uploadUrl: "FileHandler",
    allowedFileExtensions: ["zip"],
    showCancel: false,
    showRemove: false,
    uploadAsync: false
});
$("div.kv-upload-progress").remove();
$("#input_subject").on('filebatchuploadsuccess', function(event, data, previewId, index){
	var bar = $("form.subject_file div.progress-bar");	
	bar.parent().show();setTimeout(function(){}, 500);
	var i = Math.floor((Math.random() * (75 - 45) + 45));
	var temp = Math.floor((Math.random() * 2000) + 500);
	bar.css("width", i+"%");
	setTimeout(function(){
		bar.css("width", "100%");
		okBar(bar, data.response);
	 }, temp);
});

$("#input_promo").on('filelock', function(event, filestack, extraData){
	var e = $("form.promo_file div.progress");
	var bar = e.find(".progress-bar"); 
	var threshold = Math.floor((Math.random() * (85 - 65) + 65));
	var inter = window.setInterval(function(){
		e.show();
		var barPos = Math.floor((Math.random() * 5) + 1);
		var barNeg = Math.floor((Math.random() * 9) + 2);
		var freez = Math.floor((Math.random() * 15000) + 10000);
		var i = Math.round(100 * (bar.width() / e.width()));
		if(i < threshold){
			i = i + barPos;
			bar.css("width", i+"%");
			setTimeout(function(){}, freez);
			i = i + barNeg;
			bar.css("width", i+"%");
		}
		},
		3000
	);
	
	$("#input_promo").on('filebatchuploadsuccess', function(event, data, previewId, index){
		clearInterval(inter);
		bar.css("width", "87%");
		setTimeout(function(){}, 1000);
		bar.css("width", "100%");
		okBar(bar, data.response);
	});
	
});


var waiting = function(){
	var e = $("div.progress.test");
	var bar = e.find(".progress-bar"); 
	var threshold = Math.floor((Math.random() * (85 - 65) + 65));
	console.log(threshold);
	e.show();
	window.setInterval(function(){
		var barPos = Math.floor((Math.random() * 5) + 1);
		var barNeg = Math.floor((Math.random() * 9) + 2);
		var freez = Math.floor((Math.random() * 15000) + 10000);
		var i = Math.round(100 * (bar.width() / e.width()));
		if(i < threshold){
			i = i + barPos;
			bar.css("width", i+"%");
			setTimeout(function(){}, freez);
			i = i + barNeg;
			bar.css("width", i+"%");
		}
		},
		3000
	);
} 


$("a#csv-reminder").on("click", function(e) {
	e.preventDefault();
	$.get("jsp/home/modal_csv_reminder.jsp", {}, function(data, status){
		var node = $( data );
		bootbox.dialog({
		title: 'Disposition attendu pour le fichier .csv',
		message: node.prop('outerHTML'),
		buttons: {
				failure:{ 
					label: "Ok",
	                className: "btn-primary",
	                callback: function () {}
				}
			}
		});
		$("div.modal-header").addClass("primary-header");
	});
});



$("a#backup-reminder").on("click", function(e) {
	e.preventDefault();
	$.get("jsp/home/modal_backup_reminder.jsp", {}, function(data, status){
		var node = $( data );
		bootbox.dialog({
		title: 'Contnue du .zip de sauvegarde.',
		message: node.prop('outerHTML'),
		buttons: {
				failure:{ 
					label: "Ok",
	                className: "btn-default",
	                callback: function () {}
				}
			}
		});
		$("div.modal-header").addClass("primary-header");
	});
});





