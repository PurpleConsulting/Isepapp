package org.purple.model;
import org.purple.bean.Mark;

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
		return false;
	}

	@Override
	public boolean delete(Mark obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Mark obj, String where) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Mark select(String id) {
		// TODO Auto-generated method stub
		Mark m = null;
		String q = "SELECT Users.pseudo, `Values`.points, `Values`.title, Skills.title, Sub_skills.title FROM APPDB.Marks "
				+ "INNER JOIN APPDB.Users ON Users.id = Marks.id_student "
				+ "INNER JOIN APPDB.`Values` ON `Values`.id = Marks.id_value "
				+ "INNER JOIN APPDB.Sub_skills ON Sub_skills.id = Marks.id_sub_skill "
				+ "INNER JOIN APPDB.Skills ON Sub_skills.id_skills = Skills.id "
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
				+ "INNER JOIN APPDB.Skills ON Sub_skills.id_skills = Skills.id "
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
			allMarks = null;
			e.printStackTrace();
		}
		return allMarks;
	}

}
