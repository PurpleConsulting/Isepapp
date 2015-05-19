package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public boolean update(Skill obj) {
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
	public Skill select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Skill[] allSkill(){
		Skill[] skills = null;
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
			skills = null;
			e.printStackTrace();
		}
		return skills;



	public boolean find(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Skill[] selectAllSkills(){
		Skill[] skills = null;
		String q = "SELECT title, sub_title, id FROM Skills ORDER BY id";
		
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			ResultSet cursor = prestmt.executeQuery();
			if (cursor.last()) {
				skills = new Skill[cursor.getRow()];
				cursor.beforeFirst(); 
			}
			int i = 0;
			while(cursor.next()){
				Skill s = new Skill();
				s.setTitle(cursor.getString(1));
				s.setSub_title(cursor.getString(2));
				s.setId(cursor.getInt(3));
				skills[i] = s;
				i++;
			}
			cursor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			skills = null;
			e.printStackTrace();
		}
		return skills;


	public int count(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
