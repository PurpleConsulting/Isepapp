package org.purple.bean;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.purple.constant.Isep;

public class Missing {
	
	private String id = "";
	private DateTime date = new DateTime(2000, 01,01, 0,0,0);
	// -- initialise at 1st Jan 2000 ... we never know;
	private Boolean late = false;
	private String supporting = "null";
	private String student = "null";
	
	public Missing(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public DateTime getDate() {
		return date;
	}
	public String printDate() {
		return date.toString("d MMM Y");
	}
	public String printLate() {
		return date.toString("HH:mm");
	}
	public void setDate(String date) {
		this.date = DateTime.parse(date, DateTimeFormat.forPattern(Isep.JODA_UTC));
		this.date = this.date.withZone(DateTimeZone.forID(Isep.LOCATION));
	}
	public Boolean getLate() {
		return late;
	}
	public void setLate(Boolean late) {
		this.late = late;
	}
	public String getSupporting() {
		return supporting;
	}
	public void setSupporting(String supporting) {
		this.supporting = supporting;
	}

	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	
	
}
