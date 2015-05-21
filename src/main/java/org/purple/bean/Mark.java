package org.purple.bean;

import org.purple.model.Avg;

public class Mark extends Avg{
	
	private String title = "null";
	private String subSkill = "null";
	private String skill = "null";
	private String owner = "null";
	private String ownerPseudo = "null";
	private int idSubSkill;
	
	public Mark(){
		
	}
	
	public Mark(double value){
		this.setValue(value);
	}
	
	public Mark(String ownerPseudo, double value, String title, String skill, String subSkill){
		this.setValue(value);
		this.setTitle(title);
		this.setOwnerPseudo(ownerPseudo);
		this.setSkill(skill);
		this.setSubSkill(subSkill);
	}
	
	public void setValue(double value){
		this.value = value;
	}
	public double getValue(){
		return this.value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getSubSkill() {
		return subSkill;
	}
	public void setSubSkill(String subSkill) {
		this.subSkill = subSkill;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerPseudo() {
		return ownerPseudo;
	}
	public void setOwnerPseudo(String ownerPseudo) {
		this.ownerPseudo = ownerPseudo;
	}

	@Override
	public double compute() {
		// TODO Auto-generated method stub
		return this.value;
	}

	public int getIdSubSkill() {
		return idSubSkill;
	}

	public void setIdSubSkill(int idSubSkill) {
		this.idSubSkill = idSubSkill;
	}
	
}
