package org.purple.model;

import org.purple.bean.User;

public class Connection {

	private String testId = "abc";
	private String testPwd = "123";
	
	public Connection(){
		
	}
	
	/**
	 * A minimalist function for connection. Should be replace by the LDAP protocol.
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean signIn(String id, String pwd){
		boolean res = false;
		if(id.equals(this.testId) && pwd.equals(this.testPwd)){
			res = true;
		}
		return res;
	}
	
	public User buildUser(String id){
		User u = new User(id);
		u.setPosition("etudiant");
		return u;
	}
}
