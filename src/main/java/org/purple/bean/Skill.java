package org.purple.bean;

import java.util.ArrayList;

public class Skill {

		private String title = "null";
		private String sub_title = "null";
		private int id;
		private ArrayList<Sub_skill> sub_skills;
		private String title = "null";

		
		public Skill(){
			this.sub_skills = new ArrayList<Sub_skill>();
		}
		
		public Skill(String title){
			this.setTitle(title);
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getSub_title() {
			return sub_title;
		}

		public void setSub_title(String sub_title) {
			this.sub_title = sub_title;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public ArrayList<Sub_skill> getSub_skills() {
			return sub_skills;
		}
		
		public void setSub_skills(Sub_skill sub_skills) {
			this.sub_skills.add(sub_skills);
		}
}
