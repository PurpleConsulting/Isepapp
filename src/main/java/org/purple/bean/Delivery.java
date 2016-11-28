package org.purple.bean;

import org.joda.time.DateTime;

public class Delivery {
	
	private int id = 0;
	private int idOwner = 0;
	private int idDeadline = 0;
	private String path = "null"; 
	private DateTime date = new DateTime(2000, 01,01, 0,0,0);
	
	public Delivery(){
		
	}
	
	public Delivery(int id, String path){
		this.setId(id);
		this.setPath(path);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdOwner() {
		return idOwner;
	}
	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}
	public int getIdDeadline() {
		return idDeadline;
	}
	public void setIdDeadline(int idDeadline) {
		this.idDeadline = idDeadline;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	
	
}
