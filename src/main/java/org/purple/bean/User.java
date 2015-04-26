package org.purple.bean;

public class User {
	
		private int id;
		private String pseudo;
		private String firstName;
		private String lastName;
		private int status;
		private String position;
		private String group;
		private String mail;
		private String tel;
		
		public User(){
			
		}
		
		/**
		 * Constructor with all the user parameters 
		 * (V1.0 only 3 parameters firstName, lastName, position)
		 */
		public User(int id){
			
		}
		
		public User(int id, String pseudo, String firtstName, String lastName, String position){
			this.setId(id);
			this.setPseudo(pseudo);
			this.setFirstName(lastName);
			this.setLastName(lastName);
			this.setPosition(position);
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPseudo() {
			return pseudo;
		}
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
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
		public String getGroup() {
			return group;
		}
		public void setGroup(String group) {
			this.group = group;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		
		
}
