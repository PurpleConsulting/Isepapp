package org.purple.model;

/*** SQL import ***/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Group;
import org.purple.bean.User;
import org.purple.constant.Bdd;

/*** Purple import ***/

public class DaoUsers extends Dao<User> {
	
	public DaoUsers(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
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
			prestmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return res;
	}
	

	@Override
	public boolean create(User u) {
		// TODO Auto-generated method stub
		boolean res = false;
		String[] params = { u.getPseudo(),
							u.getFirstName(), u.getLastName(),
							u.getMail(), u.getPosition(), u.getGroup()};
		String q = "INSERT INTO Users (pseudo, first_name, last_name, mail, add_date, id_post, id_group)"
				+ " VALUES (?, ?, ?, ?, CURDATE(),"
				+ " (SELECT Positions.id FROM Positions WHERE Positions.title = ?),"
				+ " (SELECT Groups.id FROM Groups WHERE Groups.`name` = ?))";
		
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}


	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(User obj, String where) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public User select(String pseudo) {/* FAKE FUNCTION, FIXING BUG */
		// TODO Auto-generated method stub
		User u = null;
		String q = "SELECT Users.id,"
				+ " Users.first_name, Users.last_name,"
				+ " Positions.title, Users.pseudo,"
				+ " Users.tel, Users.mail"
				+ " FROM Users INNER JOIN Positions"
				+ " on Users.id_post = Positions.id"
				+ " WHERE Users.pseudo = ? ";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,pseudo);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return u;
			u = new User(currsor.getInt(1), currsor.getString(5), currsor.getString(2), currsor.getString(3), currsor.getString(4));
			u.setTel(currsor.getString(6));
			u.setMail(currsor.getString(7));
			prestmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			u = null;
			e.printStackTrace();
		}
		return u;
	}
	
	public User[] selectAllTutor() {
		User[] us = null; 
		String q = "SELECT Users.id,"
				+ " Users.first_name, Users.last_name,"
				+ " Positions.title, Users.pseudo,"
				+ " Users.tel, Users.mail"
				+ " FROM Users INNER JOIN Positions"
				+ " on Users.id_post = Positions.id"
				+ " WHERE (Users.id_post = 3 OR Users.id_post = 2) ";
		ResultSet currsor = Bdd.exec(this.connect, q);
		try {
			if(!currsor.next()) return new User[0];
			if (currsor.last()) {
				us = new User[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				User u = new User(currsor.getInt(1), currsor.getString(5), currsor.getString(2), currsor.getString(3), currsor.getString(4));
				us[i] = u;
				i++;
			}
			currsor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
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

}
