package org.purple.bean;

import java.util.ArrayList;

public class Group {
	
	private int id = 0;
	private String name = "null";
	private String _class = "null";
	private String tutor = "null";
	private ArrayList<User> members = new ArrayList<User>();

	
	public Group(){
		
	}

	public Group(int id, String name, String _class) {

		this.setId(id);
		this.setName(name);
		this.set_class(_class);
		this.setTutor(tutor);
	}
	
	public Group(String name, String tutor){
		this.setName(name);
		this.setTutor(tutor);
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String get_class() {
		return _class;
	}
	public void set_class(String _class) {
		this._class = _class;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public ArrayList<User> getMembers() {
		return members;
	}
	public void setMembers(User members) {
		this.members.add(members);
	}
	
}

