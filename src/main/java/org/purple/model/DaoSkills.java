package org.purple.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Skill;
import org.purple.constant.Bdd;

public class DaoSkills extends Dao<Skill>{

	public DaoSkills(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean create(Skill obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Skill obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Skill obj, String where) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Skill select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Skill[] allSkill(){
		Skill[] skills = new Skill[0];
		//Connection co = Bdd.getCo();
		Connection co = Bdd.getSecureCo();
		String q = "SELECT title FROM Skills";
		try {
			ResultSet currsor = co.createStatement().executeQuery(q);
			if (currsor.last()) {
				skills = new Skill[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				skills[i] = new Skill(currsor.getString(1));
				i++;
			}
			currsor.close();
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skills;
	}

}
