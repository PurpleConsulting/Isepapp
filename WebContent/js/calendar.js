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
	
	
	displayCalendar('september','2015-09-01');
	displayCalendar('october','2015-10-01');
	displayCalendar('november','2015-11-01');
	displayCalendar('december','2015-12-01');
	displayCalendar('january','2016-01-01');
	
	displayCalendar('february','2016-02-01');
	displayCalendar('march','2016-03-01');
	displayCalendar('april','2016-04-01');
	displayCalendar('may','2016-05-01');
	displayCalendar('june','2016-06-01');
	
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
		var a = $("td.color-class");
		var hash = "";
		var del = "&";
		a.each(function(){
			hash = hash + del + $(this).attr("data-date");
		});
		hash = hash.substr(1);
		 $.post("/Isepapp/Calendars", {
		    	dates :hash,
		    	group:$("button.group_button.btn-primary").attr("data-group")
		    	}).done(function(data){
		    		});
	});
	
	
	$('.group_button').click(function(){
		$("td.fc-day").removeClass("color-class");
		$("td.fc-day-number").removeClass("color-class");
		 $.post("/Isepapp/Calendars", {
		    	groupClass:$(this).attr("data-group")
		    	}).done(function(data){
		    		
		    		data.result.dates.forEach(function(e){
		    			$("td.fc-day[data-date='"+ e +"']").toggleClass("color-class");
		    			$("td.fc-day-number[data-date='"+ e + "']").toggleClass("color-class");
		    		})
		    			
		    		
		    		});
	});
	
	
	
	});
