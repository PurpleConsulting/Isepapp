package org.purple.bean;

public class SubSkill {
	private int id;
	private int id_skills;
	private String title;
	private int id_respo;
	private int note;
	//Date pour la creation ou modif ?
	
	public SubSkill(){
		
	}
	
	public SubSkill(int id, int id_skills, String title){
		this.setId(id);
		this.setId_skills(id_skills);
		this.setTitle(title);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_skills() {
		return id_skills;
	}
	public void setId_skills(int id_skills) {
		this.id_skills = id_skills;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId_respo() {
		return id_respo;
	}
	public void setId_respo(int id_respo) {
		this.id_respo = id_respo;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
}
