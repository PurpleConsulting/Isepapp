package org.purple.bean;

public class Value {

	public Value(){
		
	}
	
	private int id = 0;
	private int points = 0;
	private String title = "null";

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
}
