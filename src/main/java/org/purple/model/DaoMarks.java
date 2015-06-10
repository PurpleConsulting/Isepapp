package org.purple.model;
import org.purple.bean.Mark;
import org.purple.constant.Bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoMarks extends Dao<Mark>{

	public DaoMarks(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Mark obj) {
		// TODO Auto-generated method stub
		boolean res = false;
		int cross = (obj.isCross()) ? 1 : 0;
		String q = "INSERT INTO Marks (id_student, id_value, id_sub_skill,`cross`, date, id_tutor)"
				+ " VALUES ((SELECT id FROM APPDB.Users WHERE pseudo = ?),"
				+ " ?, ?," + Integer.toString(cross) + ", CURDATE(), "
				+ "(SELECT Groups.id_tutor FROM Groups WHERE Groups.`name` = "
				+ "(SELECT Groups.`name` FROM Groups INNER JOIN Users ON Users.id_group = Groups.id WHERE Users.pseudo = ?))) "
				+ "ON DUPLICATE KEY UPDATE id_value=VALUES(id_value), date=CURDATE(), id_tutor = "
				+ "(SELECT Groups.id_tutor FROM Groups WHERE Groups.`name` = "
				+ "(SELECT Groups.`name` FROM Groups INNER JOIN Users ON Users.id_group = Groups.id WHERE Users.pseudo = ?));";
		try{
			String[] params = {
					obj.getOwner(),
					Double.toString(obj.getIdValue()),
					Integer.toString( obj.getIdSubSkill()),
					obj.getOwner(),
					obj.getOwner()};
			int affected = Bdd.preparePerform(this.connect, q, params);
			if(affected == 1 || affected == 2) res = true;
		}catch (NullPointerException e){
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean createMulti(Mark obj) {
		// -- Note Injection for a group
		boolean res = false;
		String q1 = "SELECT Users.pseudo FROM Users WHERE Users.id_group ="
				+ " (SELECT Groups.id FROM Groups WHERE Groups.`name` =  ?)";
		String[] params = {obj.getGroupOwner()};
		ResultSet currsor = Bdd.prepareExec(this.connect, q1, params);
		String[] pseudos = Bdd.rsToStringTab(currsor);
		if(pseudos.length > 0) res = true; 
		for(String student: pseudos){
			obj.setOwner(student);
			boolean inserted = this.create(obj);
			if(!inserted) return false;
		}
		return res;
	}
	
	public boolean createCrossMark(Mark marks) {
		// TODO Auto-generated method stub
		boolean res = false;
		int cross = (marks.isCross()) ? 1 : 0;
		String q = "INSERT INTO Marks (id_student, id_sub_skill, id_value,`cross`, date, group_mark)"
				+ " VALUES (?, ?, ?," + Integer.toString(cross) + ", NOW(), 0)";
		try{
			String[] params = {
					Integer.toString(marks.getIdOwner()),
					Integer.toString(marks.getIdSubSkill()),
					Double.toString(marks.getIdValue())};
			int affected = Bdd.preparePerform(this.connect, q, params);
			if(affected == 1 || affected == 2) res = true;
		}catch (NullPointerException e){
			// TODO Auto-generated catch block
			res = false;
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean delete(Mark obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Mark obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mark select(String id) {
		// TODO Auto-generated method stub
		Mark m = new Mark();
		String q = "SELECT Users.pseudo, `Values`.points, `Values`.title, Skills.title, Sub_skills.title FROM APPDB.Marks "
				+ "INNER JOIN APPDB.Users ON Users.id = Marks.id_student "
				+ "INNER JOIN APPDB.`Values` ON `Values`.id = Marks.id_value "
				+ "INNER JOIN APPDB.Sub_skills ON Sub_skills.id = Marks.id_sub_skill "
				+ "INNER JOIN APPDB.Skills ON Sub_skills.id_skill = Skills.id "
				+ "WHERE Marks.id = ?";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,id);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return m;
			m = new Mark(currsor.getString(1), currsor.getDouble(2), currsor.getString(3), currsor.getString(4), currsor.getString(5));
			prestmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			m = null;
			e.printStackTrace();
		}
		return m;
	}
	
	public ArrayList<Mark> selectByStudent(String id) {
		// TODO Auto-generated method stub
		ArrayList<Mark> allMarks = new ArrayList<Mark>();
		String q = "SELECT Users.pseudo, `Values`.points, `Values`.title, Skills.title, Sub_skills.title FROM APPDB.Marks "
				+ "INNER JOIN APPDB.Users ON Users.id = Marks.id_student "
				+ "INNER JOIN APPDB.`Values` ON `Values`.id = Marks.id_value "
				+ "INNER JOIN APPDB.Sub_skills ON Sub_skills.id = Marks.id_sub_skill "
				+ "INNER JOIN APPDB.Skills ON Sub_skills.id_skill = Skills.id "
				+ "WHERE Users.id = ?";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,id);
			ResultSet currsor = prestmt.executeQuery();
			while(currsor.next()){
				allMarks.add(new Mark(currsor.getString(1), currsor.getDouble(2), currsor.getString(3), currsor.getString(4), currsor.getString(5)));
			}
			prestmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			return allMarks;
		}
		return allMarks;
	}

}
