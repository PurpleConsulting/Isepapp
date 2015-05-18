package org.purple.constant;

import java.sql.*;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Resource(name="jdbc/APPDB")
public class Bdd {
/**
 * 
 */

//	public static final String BDDHOST = "172.16.179.5";
	
	public static final String BDDHOST = "ns370799.ip-91-121-193.eu";

	public static final String BDDCLIENT = "com.mysql.jdbc.Driver";
	public static final String BDDUSER = "isep";
	public static final String BDDPASSWRD = "paris06";
	public static final String BDDPORT = "6311";
	public static final String BDDSCHEMA = "APPDB";
	public static final String BDDURL = "jdbc:mysql://" + BDDHOST + ":"+ BDDPORT + "/" + BDDSCHEMA;
	
	public static final Connection getSecureCo(){
		Connection co = null;
		try {
			co = DriverManager.getConnection(Bdd.BDDURL, Bdd.BDDUSER, Bdd.BDDPASSWRD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return co;
	}
	
	public static final Connection getCo(){
		Connection co = null;
		DataSource ds = null;
		try {
			Context ctx;
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/APPDB");
			co = ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NamingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		return co;
	}
	
}
