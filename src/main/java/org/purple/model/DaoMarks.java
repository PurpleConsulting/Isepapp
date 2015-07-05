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
		int groupMark = (obj.isGroupMark()) ? 1 : 0;
		String q = "INSERT INTO Marks (id_student, id_value, id_sub_skill,`cross`, date, group_mark, id_tutor)"
				+ " VALUES ((SELECT id FROM APPDB.Users WHERE pseudo = ?),"
				+ " ?, ?," + Integer.toString(cross) + ", NOW(), "+ Integer.toString(groupMark) +","
				+ "(SELECT Groups.id_tutor FROM Groups WHERE Groups.`name` = "
				+ "(SELECT Groups.`name` FROM Groups INNER JOIN Users ON Users.id_group = Groups.id WHERE Users.pseudo = ?))) "
				+ "ON DUPLICATE KEY UPDATE id_value=VALUES(id_value), date=NOW(), id_tutor = "
				+ "(SELECT Groups.id_tutor FROM Groups WHERE Groups.`name` = "
				+ "(SELECT Groups.`name` FROM Groups INNER JOIN Users ON Users.id_group = Groups.id WHERE Users.pseudo = ?)),"
				+ " group_mark = ?;";
		try{
			String[] params = {
					obj.getOwner(),
					Double.toString(obj.getIdValue()),
					Integer.toString( obj.getIdSubSkill()),
					obj.getOwner(),
					obj.getOwner(),
					Integer.toString(groupMark)};
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
		String[] params = {obj.getOwner()};
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
		String q = "INSERT INTO Marks (id_student, id_tutor, id_sub_skill, id_value,`cross`, date, group_mark)"
				+ " VALUES (?, ?, ?, ?," + Integer.toString(cross) + ", NOW(), 0)";
		try{
			String[] params = {
					Integer.toString(marks.getIdOwner()),
					Integer.toString(marks.getIdTutor()),
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
		// A utiliser?
		Mark m = new Mark();
		String[] params = {id};
		String q = "SELECT `Users`.pseudo, `Values`.points, `Values`.title, "
				+ " Skills.id, Skills.title, Sub_skills.id, Sub_skills.title, "
				+ " Marks.`cross`, Marks.group_mark, `Values`.id FROM Marks"
				+ " INNER JOIN `Users` ON Marks.id_student = `Users`.id"
				+ " INNER JOIN `Values` ON `Values`.id = Marks.id_value"
				+ " INNER JOIN Sub_skills ON Sub_skills.id = Marks.id_sub_skill"
				+ " INNER JOIN Skills ON Sub_skills.id_skill = Skills.id "
				+ " WHERE Marks.id = ? ;";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			if(!currsor.next()) return m;
			m = new Mark(currsor.getString(1), currsor.getDouble(2), currsor.getString(3), currsor.getInt(4), currsor.getString(5), currsor.getInt(6), currsor.getString(7));
		}catch (SQLException e){
			// TODO Auto-generated catch block
			m = null;
			e.printStackTrace();
		}
		return m;
	}
	
	public ArrayList<Mark> selectByGroup(String name) {
		// TODO Auto-generated method stub
		ArrayList<Mark> allMarks = new ArrayList<Mark>();
		String[] params = {name};
		String q = "SELECT `Users`.pseudo, `Values`.points, `Values`.title, "
				+ " Skills.id, Skills.title, Sub_skills.id, Sub_skills.title, "
				+ " Marks.`cross`, Marks.group_mark,`Values`.id FROM Marks"
				+ " INNER JOIN `Users` ON Marks.id_student = `Users`.id"
				+ " INNER JOIN `Values` ON `Values`.id = Marks.id_value"
				+ " INNER JOIN Sub_skills ON Sub_skills.id = Marks.id_sub_skill"
				+ " INNER JOIN Skills ON Sub_skills.id_skill = Skills.id "
				+ " WHERE Users.id_group = (SELECT id FROM Groups WHERE `name` = ? ) ORDER BY Users.pseudo ; ";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			while(currsor.next()){
				Mark m = new Mark(currsor.getString(1), currsor.getDouble(2),currsor.getString(3), 
						currsor.getInt(4), currsor.getString(5), currsor.getInt(6), currsor.getString(7));
				m.setCross(currsor.getBoolean(8));
				m.setGroupMark(currsor.getBoolean(9));
				m.setIdValue(currsor.getInt(10));
				allMarks.add(m);
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			allMarks = new ArrayList<Mark>();
			return allMarks;
		}
		return allMarks;
	}
	
	public ArrayList<Mark> selectByStudent(String pseudo) {
		// TODO Auto-generated method stub
		ArrayList<Mark> allMarks = new ArrayList<Mark>();
		String[] params = {pseudo};
		String q = "SELECT `Users`.pseudo, `Values`.points, `Values`.title, "
				+ " Skills.id, Skills.title, Sub_skills.id, Sub_skills.title, "
				+ " Marks.`cross`, Marks.group_mark,`Values`.id FROM Marks"
				+ " INNER JOIN `Users` ON Marks.id_student = `Users`.id"
				+ " INNER JOIN `Values` ON `Values`.id = Marks.id_value"
				+ " INNER JOIN Sub_skills ON Sub_skills.id = Marks.id_sub_skill"
				+ " INNER JOIN Skills ON Sub_skills.id_skill = Skills.id "
				+ " WHERE Users.pseudo = ? ORDER BY Skills.id ; ";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			while(currsor.next()){
				Mark m = new Mark(currsor.getString(1), currsor.getDouble(2),currsor.getString(3), 
						currsor.getInt(4), currsor.getString(5), currsor.getInt(6), currsor.getString(7));
				m.setCross(currsor.getBoolean(8));
				m.setGroupMark(currsor.getBoolean(9));
				m.setIdValue(currsor.getInt(10));
				allMarks.add(m);
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			allMarks = new ArrayList<Mark>();
			return allMarks;
		}
		return allMarks;
	}
	
	public ArrayList<Mark> selectCrossByStudent(String pseudo) {
		// TODO Auto-generated method stub
		ArrayList<Mark> allMarks = new ArrayList<Mark>();
		String[] params = {pseudo};
		String q = "SELECT `Users`.pseudo, `Values`.points, `Values`.title, "
				+ " Skills.id, Skills.title, Sub_skills.id, Sub_skills.title, "
				+ " Marks.`cross`, Marks.group_mark, `Values`.id FROM Marks"
				+ " INNER JOIN `Users` ON Marks.id_student = `Users`.id"
				+ " INNER JOIN `Values` ON `Values`.id = Marks.id_value"
				+ " INNER JOIN Sub_skills ON Sub_skills.id = Marks.id_sub_skill"
				+ " INNER JOIN Skills ON Sub_skills.id_skill = Skills.id "
				+ " WHERE Users.pseudo = ? && Marks.`cross` = 1";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			while(currsor.next()){
				Mark m = new Mark(currsor.getString(1), currsor.getDouble(2),currsor.getString(3), 
						currsor.getInt(4), currsor.getString(5), currsor.getInt(6), currsor.getString(7));
				m.setCross(currsor.getBoolean(8));
				m.setGroupMark(currsor.getBoolean(9));
				m.setIdValue(currsor.getInt(10));
				allMarks.add(m);
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			allMarks = new ArrayList<Mark>();
			return allMarks;
		}
		return allMarks;
	}
	
	public ArrayList<Mark> selectCrossByStudentAndMate(String pseudo, String mate) {
		// TODO Auto-generated method stub
		ArrayList<Mark> allMarks = new ArrayList<Mark>();
		String[] params = {pseudo, mate};
		String q = "SELECT `Users`.pseudo, `Values`.points, `Values`.title, "
				+ " Skills.id, Skills.title, Sub_skills.id, Sub_skills.title, "
				+ " Marks.`cross`, Marks.group_mark, `Values`.id FROM Marks"
				+ " INNER JOIN `Users` ON Marks.id_student = `Users`.id"
				+ " INNER JOIN `Values` ON `Values`.id = Marks.id_value"
				+ " INNER JOIN Sub_skills ON Sub_skills.id = Marks.id_sub_skill"
				+ " INNER JOIN Skills ON Sub_skills.id_skill = Skills.id "
				+ " WHERE Users.pseudo = ? && Marks.`cross` = 1 && "
				+ " Marks.id_tutor = (SELECT Users.`id` FROM Users WHERE Users.pseudo = ?)";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			while(currsor.next()){
				Mark m = new Mark(currsor.getString(1), currsor.getDouble(2),currsor.getString(3), 
						currsor.getInt(4), currsor.getString(5), currsor.getInt(6), currsor.getString(7));
				m.setCross(currsor.getBoolean(8));
				m.setGroupMark(currsor.getBoolean(9));
				m.setIdValue(currsor.getInt(10));
				allMarks.add(m);
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			allMarks = new ArrayList<Mark>();
			return allMarks;
		}
		return allMarks;
	}

	//Select group mark when group has already mark
	public Mark[] selectGroupMark(String idGroup){
		Mark[] m = new Mark[0];
		String q = "SELECT DISTINCT Marks.id_value, Marks.id_sub_skill FROM Marks "
				+ " INNER JOIN Users ON Marks.id_student = Users.id "
				+ " WHERE Users.id_group = ? AND Marks.group_mark = 1;";
		String[] params = {idGroup};
		ResultSet cursor = Bdd.prepareExec(this.connect, q, params);
		try {
			if (cursor.last()) {
				m = new Mark[cursor.getRow()];
				cursor.beforeFirst(); 
			}
			int i = 0;
			while(cursor.next()){
				m[i] = new Mark(cursor.getInt(1), cursor.getInt(2));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			m = new Mark[0];
			e.printStackTrace();
		}
		return m;
	}

	public ArrayList<Mark> selectByTutor(int id) {
		// TODO Auto-generated method stub
		ArrayList<Mark> allMarks = new ArrayList<Mark>();
		String[] params = {Integer.toString(id)};
		String q = "SELECT Marks.id_student, Marks.id_tutor, Marks.id_sub_skill,  Marks.id_value, "
				+  "Marks.`cross` FROM Marks WHERE Marks.id_tutor = ? ; ";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			while(currsor.next()){
				allMarks.add( new Mark(currsor.getInt(1),currsor.getInt(2), 
						currsor.getInt(3), currsor.getInt(4), currsor.getBoolean(5)));
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			allMarks = new ArrayList<Mark>();
			return allMarks;
		}
		return allMarks;

	}

}
