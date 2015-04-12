package org.purple.bean;

public class User {
	
		private int id;
		private String pseudo;
		private String firstName;
		private String lastName;
		private int status;
		private String position;
		
		public User(){
			
		}
		
		/**
		 * Constructor with all the user parameters 
		 * (V1.0 only 3 parameters firstName, lastName, position)
		 */
		public User(int id){
			
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
