package org.purple.bean;

public class Skill {

		private String title;
		private String sub_title;
		private int id;
		
		public Skill(){
			
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
}
