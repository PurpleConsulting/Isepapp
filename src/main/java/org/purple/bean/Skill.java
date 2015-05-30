package org.purple.bean;

import java.util.ArrayList;

public class Skill {

		private int id;
		private String title = "null";
		private ArrayList<SubSkill> sub_skills = new ArrayList<SubSkill>();
		private String subtitle = "null";
		private String type = "null";
		private int id_respo = 0;
		private int coefficient = 0;
		
		
		public Skill(){
			
		}
		
		public Skill(String title){
			this.setTitle(title);
		}
		
		public Skill(String title, String subtitle){
			this.setTitle(title);
			this.setSubtitle(subtitle);
		}
		
		public Skill(int id, String title, String subtitle){
			this.setId(id);
			this.setTitle(title);
			this.setSubtitle(subtitle);
		}
		
		public String getSubtitle() {
			return subtitle;
		}

		public void setSubtitle(String subtitle) {
			this.subtitle = subtitle;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getId_respo() {
			return id_respo;
		}

		public void setId_respo(int id_respo) {
			this.id_respo = id_respo;
		}

		public int getCoefficient() {
			return coefficient;
		}

		public void setCoefficient(int coefficient) {
			this.coefficient = coefficient;
		}

		public void setSub_skills(ArrayList<SubSkill> sub_skills) {
			this.sub_skills = sub_skills;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public ArrayList<SubSkill> getSub_skills() {
			return sub_skills;
		}
		
		public void setSub_skills(SubSkill sub_skills) {
			this.sub_skills.add(sub_skills);
		}
}
