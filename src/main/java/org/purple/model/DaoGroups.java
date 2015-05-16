package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Group;
import org.purple.bean.Missing;
import org.purple.bean.User;
import org.purple.constant.Bdd;



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
	public boolean update(Group group, String where) {
		// TODO Auto-generated method stub
		String _class = "";
		try {
			_class = group.getName().substring(0, 2);
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
		
		String q = "UPDATE Groups SET Groups.`name` = ? , Groups.class = ?, "
				+ " id_tutor = (SELECT Users.id FROM Users WHERE Users.pseudo = ?)"
				+ " WHERE Groups.`name` = ?";
		String[] params = {group.getName(), _class, group.getTutor(), where};
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Group select(String name) {
		// TODO Auto-generated method stub
		Group g = null;
		String q = "SELECT Groups.id, Groups.`name`, Groups.class"
				+ " FROM APPDB.Groups"
				+ " WHERE Groups.`name` =  ? ;";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,name);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return g;
			g = new Group(currsor.getInt(1), currsor.getString(2), currsor.getString(3));
			prestmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			g = null;
			e.printStackTrace();
		}
		return g;
	}
	
	public static int returnTutor(String group) {
		// TODO Auto-generated method stub
		Connection co = Bdd.getSecureCo();
		int res = 0;
		String q = "SELECT Users.id FROM Users INNER JOIN Groups"
				+ " ON Users.id = Groups.id_tutor WHERE Groups.`name` = ?";
		try{
			ResultSet currsor = Bdd.prepareExec(co, q, new String[]{group});
			currsor.next();
			res = currsor.getInt(1);
			currsor.close();
			co.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public Group[] selectAll() {
		// TODO Auto-generated method stub
		Group[] gs = null;
		String q = "SELECT Groups.id, Groups.`name`, Groups.class"
				+ " FROM APPDB.Groups;";
		try{
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			if (currsor.last()) {
				gs = new Group[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				gs[i] = new Group(currsor.getInt(1), currsor.getString(2), currsor.getString(3));
				i++;
			}
			currsor.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			gs = null;
			e.printStackTrace();
		}
		return gs;
	}
	
	public void completeTutor(Group g){
		String q = "SELECT Users.pseudo FROM Users INNER JOIN Groups"
				+ " ON Groups.id_tutor = Users.id"
				+ " WHERE Groups.`name` =  \""+ g.getName() +"\" AND (Users.id_post = 3 OR Users.id_post = 2); ";
		try{
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			if(currsor.next()){
				g.setTutor(currsor.getString(1));
			} else{
				g.setTutor("(Vide)");
			}
			currsor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	
	public void completeMemebers(Group g){
		String q = "SELECT Users.id, Users.pseudo, Users.first_name, Users.last_name, "
				+ " Users.tel, Users.mail "
				+ " FROM APPDB.Users INNER JOIN APPDB.Groups "
				+ " ON Groups.id = Users.id_group WHERE Groups.id = "+ Integer.toString(g.getId()) +""
				+ " AND Users.id_post = 4;";
		try{
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			while(currsor.next()){
				User u = new User(currsor.getInt(1), currsor.getString(2), currsor.getString(3), currsor.getString(4), Auth.student);
				u.setGroup(g.getName()); u.setTel(currsor.getString(5)); u.setMail(currsor.getString(6));
				g.setMembers(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

}
