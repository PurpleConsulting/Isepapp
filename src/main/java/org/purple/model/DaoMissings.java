package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Missing; 
import org.purple.constant.Bdd;
import org.purple.constant.Isep;

public class DaoMissings extends Dao<Missing>{

	public DaoMissings(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Missing obj) {
		// TODO Auto-generated method stub
		boolean res = true;
		int l = (obj.getLate()) ? 1 : 0;
		String[] params = {obj.getStudent(), obj.getDate().toString(Isep.JODA_UTC), 
				Integer.toString(l), obj.getSupporting()};
		String q = "INSERT INTO Missing (id_student, date, late, supporting)"
				+ " VALUES ((SELECT Users.id FROM Users WHERE Users.pseudo = ?),"
				+ " ?, ?, ?)";
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected != 1) res = false;
		return res;
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
	
	public Missing[] selectForStudent(String idStudent){
		Missing[] ms = new Missing[0];
		String q = "SELECT id, DATE_FORMAT(date, '"+ Isep.MYSQL_UTC +"') , late, supporting"
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
			ms = new Missing[0];
			e.printStackTrace();
		}
		return ms;
	}
	
	public Missing[] selectForGroup(String NameGroup){
		Missing[] ms = new Missing[0];
		String[] params = {NameGroup};
		String q = "SELECT Users.pseudo, Missing.late FROM Missing"
				+ " INNER JOIN  Users on Missing.id_student = Users.id"
				+ " WHERE Users.id_group ="
				+ " (SELECT Groups.id  FROM Groups WHERE Groups.`name` = ?);";
		try{
			ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
			if(!currsor.next()) return ms;
			if (currsor.last()) {
				ms = new Missing[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				Missing m = new Missing();
				m.setStudent(currsor.getString(1));
				m.setLate(currsor.getBoolean(2));
				ms[i] = m; i = i + 1;
			}
		}catch (SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ms;
	}

}
