package org.purple.constant;

import java.sql.*;
public class Bdd {

	/**
	 * 
	 */
	
	public static final String BDDCLIENT = "com.mysql.jdbc.Driver";
	
	public static final String BDDUSER = "isep";
	public static final String BDDPASSWRD = "paris06";
	public static final String BDDHOST = "ns370799.ip-91-121-193.eu";
	public static final String BDDPORT = "6311";
	public static final String BDDSCHEMA = "APPDB";
	public static final String BDDURL = "jdbc:mysql://" + BDDHOST + ":"+ BDDPORT + "/" + BDDSCHEMA;
	
	public static final Connection getCo(){
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
	
}
