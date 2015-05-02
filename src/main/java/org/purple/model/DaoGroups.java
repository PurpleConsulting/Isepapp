package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Group;
import org.purple.bean.User;



public class DaoGroups extends Dao<Group>{

	public DaoGroups(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Group obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Group obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Group obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Group select(String name) {
		// TODO Auto-generated method stub
		Group g = null;
		String q = "SELECT Groups.id, Groups.`name`, Groups.class, Users.pseudo "
				+ "FROM APPDB.Groups INNER JOIN APPDB.Users ON Groups.id_tutor = Users.id "
				+ "WHERE Groups.`name` = ?";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,name);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return g;
			g = new Group(currsor.getInt(1), currsor.getString(2), currsor.getString(3), currsor.getString(4));
			prestmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			g = null;
			e.printStackTrace();
		}
		return g;
	}
	
	public void completeMemebers(Group g){
		String q = "SELECT Users.id, Users.pseudo, Users.first_name, Users.last_name, "
				+ "Users.tel, Users.mail "
				+ "FROM APPDB.Users INNER JOIN APPDB.Groups "
				+ "ON Groups.id = Users.id_group WHERE Groups.id = "+ Integer.toString(g.getId());
		try{
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			while(currsor.next()){
				User u = new User(currsor.getInt(1), currsor.getString(2), currsor.getString(3), currsor.getString(4), g.getName());
				u.setTel(currsor.getString(5));
				u.setMail(currsor.getString(6));
				g.setMembers(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
