package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.purple.bean.SubSkill;
import org.purple.constant.Bdd;

public class DaoSubSkills extends Dao<SubSkill>{

	public DaoSubSkills(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(SubSkill obj) {
		// TODO Auto-generated method stub
		boolean res = true;
		String q = "INSERT INTO Sub_skills (id_skill, title, note, creation_date, id_respo) VALUE (?, ?, ?, CURDATE(),"
				+ " (SELECT Users.id FROM Users WHERE Users.id_post = 2));";
		String[] params = {Integer.toString(obj.getIdSkill()), obj.getTitle(), obj.getNote()};
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected != 1) res = false;
		return res;
	}

	@Override
	public boolean delete(SubSkill obj) {
		// TODO Auto-generated method stub
		boolean res = false;
		String[] params = { Integer.toString(obj.getId())};
		String q = "DELETE FROM Sub_skills WHERE Sub_skills.`id` = ?;" ;
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}

	@Override
	public SubSkill select(String id) {
		// TODO Auto-generated method stub
		SubSkill res = new SubSkill();
		String q = "SELECT Sub_skills.id, Sub_skills.id_skill, Sub_skills.title"
				+ " FROM Sub_skills WHERE Sub_skills.id = ? ;";
		String[] params = {id};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if(currsor.next()) res = new SubSkill(currsor.getInt(1), currsor.getInt(2), currsor.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public boolean update(SubSkill obj, String where) {
		// TODO Auto-generated method stub
		boolean res = false;
		String q = "UPDATE Sub_skills SET title = ?, modification_date = CURDATE() WHERE id = ?";
		String[] params = {obj.getTitle(), Integer.toString(obj.getId())};
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}
	
	public boolean updateMulti(ArrayList<SubSkill> list){
		boolean interRes = true;
		boolean res = true;
		for(SubSkill l : list){
			interRes = this.update(l, "");
			if(!interRes) res = false;
		}
		return res;
	}


}
