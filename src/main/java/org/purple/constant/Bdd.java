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

	//public static final String BDDHOST = "192.168.56.15";
	
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
		} catch (NullPointerException | NamingException e3 ) {
			// TODO Auto-generated catch block
			//e3.printStackTrace();
			co = getSecureCo();
		}
		return co;
	}
	
	public static final ResultSet exec(Connection co, String query){
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = co.createStatement();
			rs = stmt.executeQuery(query);
			//stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static final ResultSet prepareExec(Connection co, String query, String[] params){
		ResultSet rs = null;
		PreparedStatement prestmt = null;
		try {
			prestmt = co.prepareStatement(query);
			int i = 0;
			for(String p : params){
				i++; prestmt.setString(i,p);
			}
			rs = prestmt.executeQuery();
			//prestmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return rs;
	}
	
	public static final int preparePerform(Connection co, String query, String[] params){
		PreparedStatement prestmt = null;
		int change = 0;
		try {
			prestmt = co.prepareStatement(query);
			int i = 0;
			for(String p : params){
				i++; prestmt.setString(i,p);
			}
			change = prestmt.executeUpdate();
			//prestmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return change;
	}
	
	public static final String[] rsToStringTab(ResultSet rs){
		String[] tab = {};
		String chain = "";
		try {
			while(rs.next()){
				chain = chain + "&" + rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chain = chain.substring(1);
		tab = chain.split("&");
		return tab;
	}
}
