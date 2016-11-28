package org.purple.bean;

import java.util.ArrayList;

public class Calendar {
	
	private int idGroup = 0; 
	private ArrayList<String> dateList = new ArrayList();
	private int id_calendar=0;
	
	
	public Calendar(){
	}
	
	public Calendar(int idCalendar, int idGroup){
		this.setId_calendar(idCalendar);
		this.setId_group(idGroup);
	}
	
	public Calendar(int idGroup , ArrayList<String> date){
		this.setId_group(idGroup);
		this.setDateList(date);
	}
	
	
	public ArrayList<String> getDateList() {
		return dateList;
	}
	public void setDateList(ArrayList<String> dateList) {
		this.dateList = dateList;
	}
	public int getIdroup() {
		return idGroup;
	}
	
	public void setId_group(int id_group) {
		this.idGroup = idGroup;
	}
	
	public int getId_calendar() {
		return id_calendar;
	}
	
	public void setId_calendar(int id_calendar) {
		this.id_calendar = id_calendar;
	}
	
}
