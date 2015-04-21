package org.purple.model;

/*** SQL import ***/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.User;
import org.purple.constant.Bdd;
/*** Purple import ***/

public class DaoUsers extends Dao<User> {
	
	public DaoUsers(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}
	
	public void close() {
		try {
			this.connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * A minimalist function for connection. Should be replace by the LDAP protocol.
	 * NEED TO CLEAN UP THIS FUNCTION!!!
	 * @param id
	 * @return
	 */
	public boolean find(String id){/* FAKE FUNCTION, FIXING BUG */
		boolean res = false;
		String q = "SELECT COUNT(*) FROM Users WHERE pseudo = ?;";
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,id);
			ResultSet currsor = prestmt.executeQuery();
			currsor.next();
			int set = currsor.getInt(1);
			if (set == 1) res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
			return res;
	}
	

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public User select(String pseudo) {/* FAKE FUNCTION, FIXING BUG */
		// TODO Auto-generated method stub
		User u = null;
		String q = "SELECT Users.id,"
				+ "Users.last_name, Users.first_name,"
				+ " Positions.title, Users.pseudo"
				+ " FROM Users INNER JOIN Positions"
				+ " on Users.id_post = Positions.id "
				+ "WHERE Users.pseudo = ? ";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,pseudo);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return u;
			u = new User();
			u.setId(currsor.getInt(1));
			u.setFirstName(currsor.getString(2));
			u.setLastName(currsor.getString(3));
			u.setPosition(currsor.getString(4));
			u.setPseudo(currsor.getString(5));
		}catch (SQLException e){
			// TODO Auto-generated catch block
			u = null;
			e.printStackTrace();
		}
		return u;
	}
	
	public User[] selectBy(String field, String value) {
		return null;
	}

	
	public int count(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public void addGroup(User u){
		String q = "SELECT Groups.name FROM Groups INNER JOIN Users "
				+ "ON Groups.id = Users.id_group "
				+ "WHERE Users.id = " + Integer.toString(u.getId());
		try {
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			currsor.next();
			u.setGroup(currsor.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				u.setGroup("");
		}
	}
	
	public void addTelMail(User u){
		Connection c = Bdd.getCo();
		String q = "SELECT tel, mail FROM Users WHERE Users.id = " + Integer.toString(u.getId());
		try {
			ResultSet currsor = c.createStatement().executeQuery(q);
			currsor.next();
			u.setTel(currsor.getString(1));
			u.setMail(currsor.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				u.setTel("");
				u.setMail("");
		}
	}
}
