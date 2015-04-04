package org.purple.model;

public class User {

		private String firstName;
		private String lastName;
		private int status;
		private String position;
		
		public User(){
			
		}
		
		public User(String id){
			this.firstName = "Sylvain";
			this.lastName = "LFVR";
			this.position = "";
			this.status = 1;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		
		
}
