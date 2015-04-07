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

public class TestDaoUser extends TestCase{

	@Test
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
			System.out.print(e);
			fail();
		}
	}
	
	@Test
	public void testFind() {
		DaoUsers u = new DaoUsers(Bdd.getCo());
		int id = 8333;
		assertTrue(u.find(id));		
	}
	
	
	@Test
	public void testSelect() {
		int id = 8333;
		DaoUsers u = new DaoUsers(Bdd.getCo());
		User beanUser = u.select(id);
		
		assertEquals(beanUser.getFirstName(), "DVD");
		assertEquals(beanUser.getLastName(), "ldi");
	}

}
