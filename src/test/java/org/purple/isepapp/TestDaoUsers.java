package org.purple.isepapp;
/*** Junit import ***/
import java.sql.*;

/*** Junit import ***/
import junit.framework.TestCase;

import org.junit.Test;

/*** Purple import ***/
import org.purple.model.DaoUsers;
import org.purple.bean.User;
import org.purple.constant.Bdd;

public class TestDaoUsers extends TestCase{

	//@Test
	public void test() {
		//fail("Not yet implemented");
	}
	/**
	 * Place temporaire pour ce test.
	 */
	@Test
	public void testBddConnection(){
		try{
			Connection co = DriverManager.getConnection(Bdd.BDDURL, Bdd.BDDUSER, Bdd.BDDPASSWRD);
			co.close();
		} catch(SQLException e){
			e.printStackTrace();
			fail();
		} catch(NullPointerException e){
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testFind() {
		DaoUsers u = new DaoUsers(Bdd.getSecureCo());
		String id = "ldivad";
		assertTrue(u.find(id));
		u.close();
	}
	

	@Test
	public void testSelect() {
		String pseudo = "ldivad";
		DaoUsers u = new DaoUsers(Bdd.getSecureCo());
		User beanUser = u.select(pseudo);
		
		assertEquals(beanUser.getFirstName(), "DIVAD");
		assertEquals(beanUser.getLastName(), "Loïc");
		u.close();
	}


}
