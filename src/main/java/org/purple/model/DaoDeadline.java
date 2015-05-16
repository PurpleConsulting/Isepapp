package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Deadline;
import org.purple.bean.Missing;
import org.purple.constant.Isep;


public class DaoDeadline extends Dao<Deadline>{

	public DaoDeadline(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Deadline obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Deadline obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Deadline obj, String where) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Deadline select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Deadline[] selectByGroup(String name) {
		// TODO Auto-generated method stub
		Deadline[] dls = null;
		String q = "SELECT Deadlines.id, Deadlines.description, DATE_FORMAT(Deadlines.date_limit, '"+ Isep.MYSQL_UTC +"'), Deadlines.`status`, Groups.`name`"
				+ " FROM Deadlines INNER JOIN Groups ON Deadlines.id_group = Groups.id"
				+ " WHERE Groups.`name` = ? ;";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,name);
			ResultSet currsor = prestmt.executeQuery();
			if(!currsor.next()) return dls;
			if (currsor.last()) {
				dls = new Deadline[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				Deadline dl = new Deadline();
				dl.setId(currsor.getInt(1));
				dl.setDescription(currsor.getString(2)); dl.setDateLimit(currsor.getString(3));
				dl.setStatus(currsor.getBoolean(4)); dl.setGroup(currsor.getString(5));
				dls[i] = dl;
				i++;
			}
			for(Deadline d : dls){
				this.checkOut(d);
			}
			currsor.close();
			prestmt.close();
		} catch(SQLException e) {
			
		}
		
		return dls;
	}
	
	public void checkOut(Deadline dl){
		
		String q = "SELECT COUNT(*), DATE_FORMAT(Delivery.`date`, '"+ Isep.MYSQL_UTC +"')"
				+ " FROM Delivery INNER JOIN Groups ON Delivery.`id_owner_group` = Groups.id "
				+ " WHERE Groups.`name` = '" + dl.getGroup() + "'"
				+ " AND Delivery.id_deadline = '" + Integer.toString(dl.getId()) + "'";

		try{
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			currsor.next();
			if(currsor.getInt(1) != 0){
				dl.setCompleted(true);
				dl.setDeliveryDate(currsor.getString(2));
			} else {
				
			}
			currsor.close();
		} catch(SQLException e){
			System.out.print(e);
		}
		
	}

}
