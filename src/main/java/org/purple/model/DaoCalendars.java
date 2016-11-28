package org.purple.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.purple.bean.Calendar;
import org.purple.bean.Deadline;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;

public class DaoCalendars  extends Dao<Calendar> {

	public DaoCalendars(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Calendar obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Calendar obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Calendar obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Calendar select(String id) {
		// TODO Auto-generated method stub
		Calendar c = new Calendar();
		String q = "SELECT Calendars.id, Calendars.id_group"
				+  " FROM Calendars WHERE Calendars.id_group = ? ;" ;
		String [] params = {id};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if(currsor.next()) c = new Calendar(currsor.getInt(1),Integer.parseInt(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public boolean createDate(Calendar cal){
		boolean r = true;
		String q = "INSERT INTO `Dates`(id_calendar, date)"
				+ "VALUES (?, ?) ";	
		for(String d : cal.getDateList()){
			String[]params={Integer.toString(cal.getId_calendar()), d};
			int affected = Bdd.preparePerform(this.connect, q, params);
			if(affected != 1) r = false;
		}
		return r;
	}
	
	public Calendar selectAllDate(String idGroup){
		Calendar c = new Calendar();
		String q = "SELECT DISTINCT Dates.date"
				+  " FROM Dates INNER JOIN Calendars ON Calendars.id = Dates.id_calendar"
				+  " WHERE Calendars.id_group=?;" ;
		String [] params = {idGroup};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			c.setId_group(Integer.parseInt(idGroup));
			while(currsor.next()){
				c.getDateList().add(currsor.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	public boolean deleteDateGroup(String idCalendar){
		boolean res = false;
		String[] params = {idCalendar };
		String q = "DELETE FROM Dates WHERE Dates.id_calendar = ?;" ;
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}

	
	

}
