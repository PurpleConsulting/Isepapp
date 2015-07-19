$(document).ready(function() {
	var displayCalendar = function(cMonth, cDate){
		
		$('div#' + cMonth ).fullCalendar({	
			defaultDate: cDate,
			lang: 'fr',
			weekends: false,
			header: {
				left: '',
				center: 'title',
				right: ''
			},
			dayClick: function(event, jsEvent, view) {
				//var block = $(this).closest(".fc-bg");
				$("td.fc-day-number[data-date='"+ $(this).attr("data-date") + "']").toggleClass("color-class");
				$(this).toggleClass("color-class"); 
			},
			businessHours: true, // display business hours
			editable: true,
			contentHeight: 300
			});			 
};	
	var today = new Date();
	
	if(parseInt(today.getMonth()) > 5){
		firstOffset = 0;
		secoundOffset = 1;
	} else {
		firstOffset = 0;
		secoundOffset = 0;
	}
	
	firtTerm = parseInt(today.getFullYear()) + firstOffset;
	secoundTerm = parseInt(today.getFullYear()) + secoundOffset;
	
	displayCalendar('september', firtTerm + '-09-01');
	displayCalendar('october', firtTerm + '-10-01');
	displayCalendar('november', firtTerm + '-11-01');
	displayCalendar('december', firtTerm + '-12-01');
	displayCalendar('january', firtTerm + '-01-01');
	
	displayCalendar('february', secoundTerm + '-02-01');
	displayCalendar('march', secoundTerm + '-03-01');
	displayCalendar('april', secoundTerm + '-04-01');
	displayCalendar('may', secoundTerm + '-05-01');
	displayCalendar('june', secoundTerm + '-06-01');
	
	//$("div.fc-row.fc-week.fc-widget-content").last().remove();

	$("#deuxiemeSemester").hide();
	$(".btn-primary").prop("disabled",true);
	
	$('#semester2').click(function(){ 
		$(this).removeClass("btn-default");
		$(this).addClass("btn-primary");
		$('#semester1').removeClass("btn-primary");
		$('#semester1').addClass("btn-default");
		$(".btn-primary").prop("disabled",true);
		$(".btn-default").prop("disabled",false);
		$("#premierSemester").hide();
		$("#deuxiemeSemester").show();
		});
	
	$('#semester1').click(function(){ 
		$(this).removeClass("btn-default");
		$(this).addClass("btn-primary");
		$('#semester2').removeClass("btn-primary");
		$('#semester2').addClass("btn-default");
		$(".btn-primary").prop("disabled",true);
		$(".btn-default").prop("disabled",false);
		$("#deuxiemeSemester").hide();
		$("#premierSemester").show();
		});
	
	
	$('.group_button').click(function(){
		$(".group_button").removeClass("btn-primary");	
		$(".group_button").addClass("btn-default");
		$(this).removeClass("btn-default");
		$(this).addClass("btn-primary");
	});
	
	
	$('#validerForm').click(function(){
		var waiting = $("div.waiting");
		var spin = $("div[data-role='exemple'] div.fa-spinner-box").clone();
		
		waiting.empty()
		waiting.append(spin);
		
		
		var a = $("td.color-class");
		var hash = "";
		var del = "&";
		a.each(function(){
			hash = hash + del + $(this).attr("data-date");
		});
		hash = hash.substr(1);
		 $.post("/Isepapp/Calendars", { dates :hash, group:$("button.group_button.btn-primary").attr("data-group") }, 
				 function(data, status){
					 var waiting = $("div.waiting");
					 var alert = $("div[data-role='exemple'] div.alert-dismissible").clone();
						
					 waiting.empty()
					 waiting.append(alert);
			 
		 });
	});
	
	
	$('.group_button').click(function(){
		$("td.fc-day").removeClass("color-class");
		$("td.fc-day-number").removeClass("color-class");
		 $.post("/Isepapp/Calendars", { groupClass: $(this).attr("data-group") }).done(function(data){		
    		data.result.dates.forEach(function(e){
    			$("td.fc-day[data-date='"+ e +"']").toggleClass("color-class");
    			$("td.fc-day-number[data-date='"+ e + "']").toggleClass("color-class");
    		})
		 });
	});
	
	
});
