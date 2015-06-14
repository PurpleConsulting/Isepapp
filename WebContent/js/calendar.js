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
				$(this).toggleClass("color-class"); 
			},
			businessHours: true, // display business hours
			editable: true,
			contentHeight: 355
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
	
	$("div.fc-row.fc-week.fc-widget-content").last().remove();

	
	});
