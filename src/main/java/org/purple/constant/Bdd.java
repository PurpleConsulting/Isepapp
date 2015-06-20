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
	
    public static final String BDDHOST = System.getProperty("bddhost");
	public static final String BDDCLIENT = System.getProperty("bddclient");
	public static final String BDDUSER = System.getProperty("bdduser");
	public static final String BDDPASSWRD = System.getProperty("bddpasswrd");
	public static final String BDDPORT = System.getProperty("bddport");
	public static final String BDDSCHEMA = System.getProperty("bddschema");
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
		} catch (NamingException e3 ) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
			co = getSecureCo();
		} catch (NullPointerException e2) {
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
	
	public static final boolean perform(Connection co, String query){
		ResultSet rs = null;
		Statement stmt = null;
		boolean res = true;
		try {
			stmt = co.createStatement();
			stmt.execute(query);
			//prestmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		}	
		return res;
	}
	
	public static final int preparePerform(Connection co, String query, String[] params){
		PreparedStatement prestmt = null;
		int change = 0;
		try {int i = 0;
			prestmt = co.prepareStatement(query);
			for(String p : params){ i++; prestmt.setString(i,p); }
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
		if(chain.length() > 1){
			chain = chain.substring(1);
			tab = chain.split("&");
		}
		return tab;
	}
}
