package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Skill;
import org.purple.bean.SubSkill;
import org.purple.constant.Bdd;

public class DaoSkills extends Dao<Skill>{

	public DaoSkills(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean create(Skill obj) {
		// TODO Auto-generated method stub
		boolean res = true;
		String q = "INSERT INTO Skills (title, sub_title, creation_date, id_respo) VALUE (? , ?, CURDATE(),"
				+ " (SELECT Users.id FROM Users WHERE Users.id_post = 2));";
		String[] params = {obj.getTitle(), obj.getSubtitle()};
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected != 1) res = false;
		return res;
	}

	@Override
	public boolean delete(Skill obj) {
		// TODO Auto-generated method stub
		boolean res = false;
		String[] params = { Integer.toString(obj.getId())};
		String q = "DELETE FROM Skills WHERE Skills.`id` = ?;" ;
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}

	@Override
	public boolean update(Skill obj) {
		// TODO Auto-generated method stub
		boolean res = true;
		String q = "UPDATE Skills SET title = ?, id_respo = (SELECT Users.id FROM Users WHERE Users.id_post = 2),"
				 + " modification_date = CURDATE(), sub_title = ? WHERE id = ?";
		String[] params = {obj.getTitle(), obj.getSubtitle(), Integer.toString(obj.getId())};
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected != 1) res = false;
		return res;
	}

	@Override
	public Skill select(String id) {
		// TODO Auto-generated method stub
		Skill s = new Skill();
		String q = "SELECT Skills.title, Skills.title FROM Skills  WHERE Skills.id = ? ;";
		String [] params = {id};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if(currsor.next()) s = new Skill(Integer.parseInt(id), currsor.getString(1), currsor.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public static Skill[] allSkill(){
		Skill[] skills = new Skill[0];
		//Connection co = Bdd.getCo();
		Connection co = Bdd.getSecureCo();
		String q = "SELECT id, title, sub_title FROM Skills";
		try {
			ResultSet currsor = co.createStatement().executeQuery(q);
			if (currsor.last()) {
				skills = new Skill[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				skills[i] = new Skill(currsor.getInt(1), currsor.getString(2), currsor.getString(3));
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



	public boolean find(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Skill[] selectAllSkills(){
		Skill[] skills = null;
		String q = "SELECT title, sub_title, id FROM Skills WHERE id != 0 ORDER BY id";
		
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
				s.setSubtitle(cursor.getString(2));
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
	}
	
	//Requete Cross-Skills
	public Skill selectCrossSkills(){
		Skill skill = new Skill();
		String q = "SELECT title, sub_title, id FROM Skills WHERE id = 0 ORDER BY id";
		
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			ResultSet cursor = prestmt.executeQuery();
			if (cursor.next()) { }
			skill = new Skill();
			skill.setTitle(cursor.getString(1));
			skill.setSubtitle(cursor.getString(2));
			skill.setId(cursor.getInt(3));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return skill;
	}



	public int count(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void completeSub_skills(Skill s){
        String q = "SELECT Sub_skills.id, Sub_skills.id_skill, Sub_skills.title"
        + " FROM APPDB.Sub_skills INNER JOIN APPDB.Skills "
        + " ON Skills.id = Sub_skills.id_skill WHERE Skills.id = "+ Integer.toString(s.getId()) + ";";
        try{
            ResultSet cursor = this.connect.createStatement().executeQuery(q);
            while(cursor.next()){
                SubSkill ss = new SubSkill(cursor.getInt(1), cursor.getInt(2), cursor.getString(3));
                s.setSubSkills(ss);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
