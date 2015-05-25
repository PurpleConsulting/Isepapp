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
