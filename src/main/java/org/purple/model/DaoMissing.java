package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Missing; 
import org.purple.bean.User;

public class DaoMissing extends Dao<Missing>{

	public DaoMissing(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Missing obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Missing obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Missing obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Missing select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Missing[] selectAll(String idStudent){
		Missing[] ms = null;
		String q = "SELECT id, date, late, supporting"
				+ " FROM Missing WHERE id_student = ? ";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,idStudent);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return ms;
			if (currsor.last()) {
				ms = new Missing[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				Missing m = new Missing();
				m.setId(currsor.getString(1));
				m.setDate(currsor.getString(2));
				m.setLate(currsor.getBoolean(3));
				m.setSupporting(currsor.getString(4));
				ms[i] = m;
				i = i + 1;
			}
				
			prestmt.close();
		}catch (SQLException e){
			// TODO Auto-generated catch block
			ms = null;
			e.printStackTrace();
		}
		return ms;
	}

	public void close() {
		try {
			this.connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
