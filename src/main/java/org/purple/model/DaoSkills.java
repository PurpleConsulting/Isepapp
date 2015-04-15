package org.purple.model;

import java.sql.Connection;

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

	@Override
	public Skills select(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean find(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
