package org.purple.bean;

import org.purple.model.Avg;

public class Mark extends Avg {

	private String title = "null";
	private String subSkill = "null";
	private String skill = "null";
	private String owner = "null";
	private int idValue = 0;
	private int idSkill = -1;
	private int idSubSkill = 0;
	private int idTutor = 0;
	private boolean cross = false;
	private boolean groupMark = true;
	private int idOwner = 0;

	public Mark() {

	}

	public Mark(double value) {
		this.setValue(value);
	}
	
	public Mark(int idValue, int idSubSkill){
		// -- This constructor only prepare mark for INSERTION of a Group mark
		this.setIdValue(idValue);
		this.setIdSubSkill(idSubSkill);
	}
	
	public Mark(int idOwner, int idTutor, int idSubSkill, int idValue, boolean cross) {
		// -- This constructor only prepare mark for INSERTION
		this.setIdOwner(idOwner);
		this.setIdSubSkill(idSubSkill);
		this.setIdValue(idValue);
		this.setCross(cross);
	}

	public Mark(String owner, double value, String title, int idSkill, String skill, int idSubSkill, String subSkill) {
		// -- This constructor only prepare mark for the display and computation
		this.setValue(value);
		this.setTitle(title);
		this.setOwner(owner);
		this.setIdSkill(idSkill);
		this.setSkill(skill);
		this.setIdSubSkill(idSubSkill);
		this.setSubSkill(subSkill);
	}
	
	public int getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
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


	public int getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
	}

	public boolean isCross() {
		return cross;
	}

	public void setCross(boolean cross) {
		this.cross = cross;
	}
	
	

	public int getIdValue() {
		return idValue;
	}

	public void setIdValue(int idValue) {
		this.idValue = idValue;
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

	public boolean isGroupMark() {
		return groupMark;
	}

	public void setGroupMark(boolean groupMark) {
		this.groupMark = groupMark;
	}
	

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	@Override
	public int status() {
		// TODO Auto-generated method stub
		return 1;
	}

}
