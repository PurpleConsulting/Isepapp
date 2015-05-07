package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Skills;

public class DaoSkills extends Dao<Skills> {

	public DaoSkills(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Skills obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Skills obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Skills obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//Function which select all skills in table Skills
	public Skills[] selectAllValues (){
		Skills[] skills = null;
		String q = "SELECT id,title, points "
				+ "FROM `Values` "
				+ "ORDER BY points";
		
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			ResultSet cursor = prestmt.executeQuery();
			
			int i = 0;
			
			if(!cursor.next()) return skills;
			if (cursor.last()) {
				skills = new Skills[cursor.getRow()];
				cursor.beforeFirst(); 
			}
			
			while(cursor.next()){
				Skills v = new Skills();
				v.setId(cursor.getInt(1));
				v.setTitle(cursor.getString(2));
				v.setSubtitle(cursor.getString(3));
				skills[i] = v;
				i = i + 1;
			}
			
		
		}catch (SQLException e){
			// TODO Auto-generated catch block
			skills = null;
			e.printStackTrace();
		}
		return skills;
	}

	@Override
	public Skills select(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean find(String id) {
		// TODO Auto-generated method stub
		return false;
	}


	public int count(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
