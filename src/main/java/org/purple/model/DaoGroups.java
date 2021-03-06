package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.purple.bean.Group;
import org.purple.bean.User;
import org.purple.constant.Bdd;



public class DaoGroups extends Dao<Group>{
    
    public DaoGroups(Connection co) {
        super(co);
        // TODO Auto-generated constructor stub
    }


    //Select all group name
    public String[] selectAllName(String pseudo){

        String[] gpName = new String[0];

        String q = "SELECT Groups.`name` FROM Groups INNER JOIN Users ON Users.id = Groups.id_tutor WHERE Users.id = ? AND Groups.id != 0 ORDER BY Groups.name;";
        try {
            PreparedStatement prestmt = this.connect.prepareStatement(q);
            prestmt.setString(1,pseudo);
            ResultSet cursor = prestmt.executeQuery();
            if (cursor.last()) {
                gpName = new String[cursor.getRow()];
                cursor.beforeFirst();
            }
            int i = 0;
            while(cursor.next()){
                gpName[i] = new String(cursor.getString(1));
                i++;
            }
            cursor.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpName;
    }

	@Override
	public boolean create(Group group) {
		// TODO Auto-generated method stub
		String _class = "";
		String q = "";
		String[] params;
		try {
			_class = group.getName().substring(0, 2);
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
		if(group.getTutor().equals("null")){
			q = "INSERT INTO Groups (`name`, class) VALUE (? , ?)";
			params = new String[2];
			params[0] = group.getName(); params[1] =  _class;
		} else {
			q = "INSERT INTO Groups (`name`, class, id_tutor) VALUE (? , ?, "
					+ " (SELECT Users.id FROM Users WHERE Users.pseudo = ?))";
			params = new String[3];
			params[0] = group.getName(); params[1] =  _class; params[2] =  group.getTutor();
		}
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Group group) {
		// TODO Auto-generated method stub
		boolean res = false;
		String[] params = { Integer.toString(group.getId())};
		String q = "DELETE FROM Groups WHERE Groups.`id` = ?;" ;
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}

	@Override
	public boolean update(Group group) {
		// TODO Auto-generated method stub
		String _class = "";
		try {
			_class = group.getName().substring(0, 2);
		} catch(IndexOutOfBoundsException e) {
			return false;
		}
		
		String q = "UPDATE Groups SET Groups.`name` = ? , Groups.class = ?, "
				+ " id_tutor = (SELECT Users.id FROM Users WHERE Users.pseudo = ?)"
				+ " WHERE Groups.`id` = ?";
		String[] params = {group.getName(), _class, group.getTutor(), Integer.toString(group.getId())};
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
		Group g = new Group();
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
			e.printStackTrace();
			g = new Group();
		}
		return g;
	}
	
	public static int returnTutor(String group) {
		// TODO Auto-generated method stub
		Connection co = Bdd.getCo();
		int res = 0;
		String q = "SELECT Users.id FROM Users INNER JOIN Groups"
				+ " ON Users.id = Groups.id_tutor WHERE Groups.`name` = ?";
		try{
			ResultSet currsor = Bdd.prepareExec(co, q, new String[]{group});
			currsor.next();
			res = currsor.getInt(1);
			co.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<Group> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<Group> gs = new ArrayList<Group>();

		String q = "SELECT Groups.id, Groups.`name`, Groups.class "
				+ " FROM Groups WHERE Groups.`id` != 0;";
		try{
			ResultSet currsor = Bdd.exec(this.connect, q);
			while(currsor.next()){
				gs.add(new Group(currsor.getInt(1), currsor.getString(2), currsor.getString(3)));
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			gs = new ArrayList<Group>();
			e.printStackTrace();
		}
		return gs;
	}
	
	public Group[] selectGroupbyTutor(User t){
		Group[] g = new Group[0];
		String[] params = {Integer.toString(t.getId())};
		String q = "SELECT Groups.`id`, Groups.`name`, Groups.`class`"
				+ " FROM Groups WHERE Groups.id_tutor = ? ;";
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if(currsor.last()){
				g = new Group[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){ 
				g[i] = new Group(currsor.getInt(1), currsor.getString(2), currsor.getString(3));
				i++;
			}
		} catch (SQLException e) {
			g = new Group[0];
		}
		return g;
	}
	
	public ArrayList<Group> selectGroupbyClass(String classe) {
		// TODO Auto-generated method stub
		ArrayList<Group> gs = new ArrayList<Group>();
		String q = "SELECT Groups.id, Groups.`name`, Groups.class"
				+ " FROM APPDB.Groups WHERE Groups.class= ? AND Groups.id != 0;";
		String[] params = {classe};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			while(currsor.next()){
				gs.add(new Group(currsor.getInt(1), currsor.getString(2), currsor.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gs = new ArrayList<Group>();
		}
		return gs;
	}
	
	
	
	public String[] selectAllClass() {
		// TODO Auto-generated method stub
		String[] gs = new String[0];
		String q = "SELECT distinct Groups.`class` FROM Groups WHERE Groups.`id` != 0;";
		ResultSet currsor = Bdd.exec(this.connect, q);
		gs = Bdd.rsToStringTab(currsor);
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
				g.setTutor("Auncun");
			}
			currsor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	
	public void completeMemebers(Group g){
		String q = "SELECT Users.id, Users.pseudo, Users.first_name, "
				+ " Users.last_name, Users.mail "
				+ " FROM APPDB.Users INNER JOIN APPDB.Groups "
				+ " ON Groups.id = Users.id_group WHERE Groups.id = "+ Integer.toString(g.getId()) +""
				+ " AND Users.id_post = 4;";
		try{
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			while(currsor.next()){
				User u = new User(currsor.getInt(1), currsor.getString(2), currsor.getString(3), currsor.getString(4), Auth.student);
				u.setGroup(g.getName()); u.setMail(currsor.getString(5));
				g.setMembers(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	

	public String[] allGroups(){
		String [] res = new String[0];
		String q = "SELECT Groups.`name` FROM Groups ;";
		ResultSet currsor = Bdd.exec(this.connect, q);
		res = Bdd.rsToStringTab(currsor);
		return res;
	}
	
	public boolean declareTutor(User u, String _class){
		boolean res = true;
		String q = "UPDATE Groups SET id_tutor = (SELECT Users.id FROM Users WHERE Users.pseudo = ?)"
				+ " WHERE class = ?;";
		String[] params = { u.getPseudo(), _class };
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected < 1) res = false;
		return res;
	}
	
	public boolean retireTutor(User u){
		boolean res = true;
		String q = "UPDATE Groups SET id_tutor = null "
				+ " WHERE id_tutor = (SELECT Users.id FROM Users WHERE Users.pseudo = ?) ;";
		String[] params = { u.getPseudo()};
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected < 0) res = false;
		return res;
	}
	
	public void dropAll(){
		String q = "DELETE FROM Groups WHERE id != 0 ;";
		String [] params = {};
		int affected = Bdd.preparePerform(this.connect, q, params);
	}
	

}
