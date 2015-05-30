package org.purple.bean;

public class User {
	
		private int id = 0;
		private int isepNo = 0;
		private String pseudo = "null";
		private String firstName = "null";
		private String lastName = "null";
		private String position = "null";
		private String group = "null";
		private String mail = "null";
		private String tel = "null";
		
		public User(){
			
		}
		
		public User(int id, String pseudo, String firtstName, String lastName, String position){
			this.setId(id);
			this.setPseudo(pseudo);
			this.setFirstName(firtstName);
			this.setLastName(lastName);
			this.setPosition(position);
		}
		public User(int id, String firtstName, String lastName, String group){
			this.setId(id);
			this.setFirstName(firtstName);
			this.setLastName(lastName);
			this.setGroup(group);
		}
		
		public User(String firtstName, String lastName, String pseudo, String email,  String position){
			this.setPseudo(pseudo);
			this.setFirstName(firtstName);
			this.setLastName(lastName);
			this.setPosition(position);
			this.setMail(email);
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
		public int getIsepNo() {
			return isepNo;
		}
		public void setIsepNo(int isepNo) {
			this.isepNo = isepNo;
		}
		
		
}
