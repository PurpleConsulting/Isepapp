package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.SubSkill;

public class DaoSubSkills extends Dao<SubSkill>{

	public DaoSubSkills(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(SubSkill obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(SubSkill obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SubSkill select(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(SubSkill obj, String where) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*public Sub_skill[] selectAllSub_skillsBySkillsId(int id_skills){
		Sub_skill[] sub_skills = null;

		String q = "SELECT id, id_skills, title FROM Sub_skills WHERE id_skills = ? ORDER BY id";
		
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			ResultSet cursor = prestmt.executeQuery();
			if (cursor.last()) {
				sub_skills = new Sub_skill[cursor.getRow()];
				cursor.beforeFirst(); 
			}
			int i = 0;
			while(cursor.next()){
				Sub_skill s = new Sub_skill(cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
				sub_skills[i] = s;
				i++;
			}
			cursor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			sub_skills = null;
			e.printStackTrace();
		}
		return sub_skills;
	}*/

}
