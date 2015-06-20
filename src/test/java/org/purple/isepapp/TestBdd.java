package org.purple.isepapp;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.purple.constant.Bdd;
import static org.mockito.Matchers.anyInt;

public class TestBdd {

	private ResultSet rst;
	private Connection co;
	//@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
//	@Before
//	public void setUp() throws SQLException {
//	    MockitoAnnotations.initMocks(this); 
//	    this.rst = Mockito.mock(ResultSet.class);
//	    this.co = Mockito.mock(Connection.class);
//	    Mockito.when(this.rst.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
//	    Mockito.when(this.rst.getString(anyInt())).thenReturn("<3Sophie");
//	}
//
//	@Test
//	public void testBddConnection(){
//		try{
//			Connection co = DriverManager.getConnection(Bdd.BDDURL, Bdd.BDDUSER, Bdd.BDDPASSWRD);
//			co.close();
//		} catch(SQLException e){
//			e.printStackTrace();
//			fail();
//		} catch(NullPointerException e){
//			e.printStackTrace();
//			fail();
//		}
//	}
//	
//	@Test
//	public void testBddServletConnection(){
//		try{
//			Connection co = Bdd.getCo();
//			co.close();
//		} catch(SQLException e){
//			e.printStackTrace();
//			fail();
//		} catch(NullPointerException e){
//			e.printStackTrace();
//			fail();
//		}
//	}
//	
//	@Test
//	public void testExec(){
//		String q = "SELECT first_name FROM Users WHERE id_post = 4 and first_name LIKE '%i'";
//		ResultSet rs = Bdd.exec(Bdd.getCo(), q);
//		try {
//			if(rs.isClosed()) return;
//			while(rs.next()){
//				// -- > System.out.print(rs.getString(1) + "\n");
//			}
//		} catch (NullPointerException  | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace(); fail();
//		}
//		
//	}
//	
//	@Test
//	public void testPrepareExec(){
//		String q = "SELECT first_name FROM Users WHERE id_post = ? and first_name LIKE ?";
//		String[] params = {"4","%a"};
//		ResultSet rs = Bdd.prepareExec(Bdd.getCo(), q, params);
//		try {
//			if(rs.isClosed()) return;
//			while(rs.next()){
//				// -- > System.out.print(rs.getString(1) + "\n");
//			}
//		} catch (NullPointerException  | SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace(); fail();
//		}	
//	}
//	
//	@Test
//	public void testRsToStringTab(){
//		String[] sos = Bdd.rsToStringTab(this.rst);
//		if(sos.length != 3){
//			fail(Integer.toString(sos.length));
//		}
//		for(String s : sos){
//			assertEquals("<3Sophie", s);
//		}
//		
//	}
}
