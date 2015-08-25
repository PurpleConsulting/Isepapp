package org.purple.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.purple.bean.Group;
import org.purple.bean.User;
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
		
		public static final String[] exactResearch(String field, String table, String filter){
			Connection co = Bdd.getCo();
			String[] res = new String[0];
			String q = "SELECT " + field + " FROM " + table +" "+ filter +" ;";
			try {
				Statement stmt = co.createStatement();
				ResultSet currsor = stmt.executeQuery(q);
				if(!currsor.next()) return new String[0];
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
		
		
		public static final HashMap<String, User> fuzzyStudent(String keyword) throws SQLException{
			Connection co = Bdd.getCo();
			String[] keys = keyword.split(" ");
			HashMap<String, User> u = new HashMap<String, User>();
			String q = "SELECT Users.id, Users.first_name, Users.last_name,"
					+ " Groups.`name` FROM Users INNER JOIN Groups ON Users.id_group = Groups.`id` INNER JOIN Positions "
					+ " ON Positions.`id` = Users.id_post WHERE Positions.title = '" + Auth.student + "'"
					+ " AND (Users.first_name LIKE ? OR Users.last_name LIKE ? )";
			for(String k : keys){
				String[] param = {"%"+k+"%", "%"+k+"%"};
				ResultSet currsor = Bdd.prepareExec(co, q, param);
				while(currsor.next()){
					User s = new User(currsor.getInt(1), currsor.getString(2), currsor.getString(3),currsor.getString(4));
					u.put(Integer.toString(s.getId()), s);				
				}
			}			
			co.close();
			return u;
		}
		
		public static final HashMap<String, Group> fuzzyGroup(String keyword) throws SQLException {
			Connection co = Bdd.getCo();
			String[] keys = keyword.split(" ");
			HashMap<String, Group> g = new HashMap<String, Group>();
			String q = "SELECT Groups.`id` as gid, "
					+ " Groups.`name` as grp, Users.`pseudo`, "
					+ "(SELECT COUNT(*) FROM Users WHERE Users.id_group = gid) "
					+ " FROM Groups LEFT JOIN Users ON Groups.id_tutor = Users.id "
					+ " WHERE Groups.`name` LIKE ?";
			if(keys.length == 1){
				keys[0] = "%" + keys[0] + "%";
				ResultSet cursor = Bdd.prepareExec(co, q, keys);
					while(cursor.next()){
						Group grp = new Group(cursor.getString(2), cursor.getString(3));
						g.put(cursor.getString(2)+"@"+cursor.getString(4), grp);
					}
			}
			co.close();
			return g;
		}
		
		public static final String nameToSpeudo(String name){
			Connection co = Bdd.getCo();
			String res = "";
			String q = "SELECT pseudo FROM Users WHERE CONCAT(first_name, ' ', last_name) = ?";
			String[] args = {name};
			ResultSet currsor  = Bdd.prepareExec(co, q, args);
			try {
				if(currsor.next()){
					res = currsor.getString(1);
				} else {
					res = "";
				}
				currsor.close();
				co.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				res = "";
			}
			return res;
		}
	
}
