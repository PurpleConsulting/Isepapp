package org.purple.isepapp;
/*** Junit import ***/
import static org.junit.Assert.*;

import org.junit.Test;

import org.purple.bean.User;
/*** Purple import ***/
import org.purple.model.Connection;

public class testConnection {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSingnIn() {
		Connection c = new Connection();
		String id = "abc";
		String pwd = "123";
		assertTrue(c.signIn(id, pwd));		
	}
	
	@Test
	public void testBuildUser() {
		Connection c = new Connection();
		String id = "abc";
		
		User u = c.buildUser(id);/*** the param should be replace by some infos from the LDAP ***/ 
		assertEquals(u.getFirstName(),"Sylvain");
		assertEquals(u.getLastName(),"LFVR");	
	}

}
