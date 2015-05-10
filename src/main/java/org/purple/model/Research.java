package org.purple.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.purple.constant.Bdd;

public class Research {

		public static final String[] pseudoResearch(){
			Connection co = Bdd.getCo();
			String[] res = null;
			String q = "SELECT pseudo FROM Users WHERE id_post = 4;";
			
			try {
				Statement stmt = co.createStatement();
				ResultSet currsor = stmt.executeQuery(q);
				if(!currsor.next()) return null;
				//String[] test = (String[])currsor.getArray(1).getArray();
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
		
		public static final String[] groupResearch(){
			Connection co = Bdd.getCo();
			String[] res = null;
			String q = "SELECT DISTINCT name FROM Groups ;";
			
			try {
				Statement stmt = co.createStatement();
				ResultSet currsor = stmt.executeQuery(q);
				if(!currsor.next()) return null;
				//String[] test = (String[])currsor.getArray(1).getArray();
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
