package org.purple.bean;

public class SubSkill {
	private int id = 0;
	private int idSkill = 0;
	private String title = "null";
	private int idRespo = 0;
	private String note = "null";
	//Date pour la creation ou modif ?
	
	public SubSkill(){
		
	}
	
	public SubSkill(String title, String  note){
		this.setTitle(title);
		this.setNote(note);
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
	public int getIdSkill() {
		return idSkill;
	}
	public void setId_skills(int idSkill) {
		this.idSkill = idSkill;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIdRespo() {
		return idRespo;
	}
	public void setIdRespo(int id_respo) {
		this.idRespo = id_respo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
