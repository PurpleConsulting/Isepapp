package org.purple.bean;

import org.purple.model.Avg;

public class Mark extends Avg {

	private String title = "null";
	private String subSkill = "null";
	private String skill = "null";
	private String owner = "null";
	private String groupOwner = "null";
	private int idValue = 0;
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

	public Mark(String groupOwner, int idSubSkill, int idValue) {
		// -- This constructor only prepare mark for INSERTION
		this.setGroupOwner(groupOwner);
		this.setIdSubSkill(idSubSkill);
		this.setIdValue(idValue);
	}
	
	public Mark(int idOwner, int idTutor, int idSubSkill, int idValue, boolean cross) {
		// -- This constructor only prepare mark for INSERTION
		this.setIdOwner(idOwner);
		this.setIdSubSkill(idSubSkill);
		this.setIdValue(idValue);
		this.setCross(cross);
	}

	public Mark(String owner, double value, String title, String skill, String subSkill) {
		// -- This constructor only prepare mark for the display and computation
		this.setValue(value);
		this.setTitle(title);
		this.setOwner(owner);
		this.setSkill(skill);
		this.setSubSkill(subSkill);
	}
	
	public Mark(int idValue, int idSubSkill){
		// -- This constructor is for selecting group mark
		this.setIdValue(idValue);
		this.setIdSubSkill(idSubSkill);
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

	public String getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(String groupOwner) {
		this.groupOwner = groupOwner;
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
