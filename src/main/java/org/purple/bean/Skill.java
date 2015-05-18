package org.purple.bean;

public class Skill {

		private String title = "null";
		
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
		
		
}
