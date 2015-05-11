package org.purple.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.purple.constant.Bdd;

public class Research {

		public static final boolean isRealVal(String table, String field, String value){
			Connection co = Bdd.getCo();
			boolean res = false; int total = 0;	
			String q = "SELECT COUNT(*) FROM " + table + " WHERE " + field + " = ? ;";
			String[] args = {value};
			ResultSet currsor  = Bdd.prepareExec(co, q, args);
			try {
				if(currsor.next()){
					total = currsor.getInt(1);
					System.out.print(total);
				}
				currsor.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(total > 0) res = true;
			return res;
		}
		
		public static final String[] pseudoResearch(String field, String table, String filter){
			Connection co = Bdd.getCo();
			String[] res = null;
			String q = "SELECT " + field + " FROM " + table +" "+ filter +" ;";
			String[] test = null;
			try {
				Statement stmt = co.createStatement();
				ResultSet currsor = stmt.executeQuery(q);
				if(!currsor.next()) return null;
				//test = (String[])currsor.getArray("Student").getArray();
				if (currsor.last()) {
					res = new String[currsor.getRow()];
					currsor.beforeFirst(); 
				}
				int i = 0;
				while(currsor.next()){
					res[i] = currsor.getString(1);
					i = i + 1;
				}
				currsor.close();
				stmt.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
		}
	
}
