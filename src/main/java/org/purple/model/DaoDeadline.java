package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.purple.bean.Deadline;
import org.purple.bean.Delivery;
import org.purple.bean.Group;
import org.purple.bean.Missing;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;


public class DaoDeadline extends Dao<Deadline>{

	public DaoDeadline(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean create(Deadline dl) {
		boolean r=false;
		String[]params={dl.getDescription(),
				Integer.toString(dl.getIdGroup()),
				Integer.toString(dl.getResponsable()),
				"1",dl.getDateLimit().toString(Isep.JODA_UTC), Integer.toString(dl.getCross())};
		String q = "INSERT INTO `Deadlines`(description, id_group, id_createur, date_creation, Status, date_limit, `Deadlines`.cross)"
				+ "VALUES (?, ?, ?, CURDATE(),?,  ?, ?) ";	
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) r = true;
		return r;
	}

	@Override
	public boolean delete(Deadline obj) {
		// TODO Auto-generated method stub
		boolean res = false;
		String[] params = { Integer.toString(obj.getId())};
		String q = "DELETE FROM Deadlines WHERE Deadlines.`id` = ?;" ;
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) res = true;
		return res;
	}

	@Override
	public boolean update(Deadline obj) {
		boolean r=false;
		String[]params={obj.getDateLimit().toString(Isep.JODA_UTC), Integer.toString(obj.getId())};
		String q = "UPDATE `Deadlines` SET Deadlines.date_limit= ? "
				+ "WHERE Deadlines.id= ?";	
		int affected = Bdd.preparePerform(this.connect, q, params);
		if(affected == 1) r = true;
		return r;
	}

	@Override
	public Deadline select(String id) {
		// TODO Auto-generated method stub
		Deadline d = new Deadline();
		String q = "SELECT Deadlines.description, DATE_FORMAT(Deadlines.date_limit, '"+ Isep.MYSQL_UTC +"'), Deadlines.id_createur, Deadlines.`status`"
				+	"FROM Deadlines WHERE Deadlines.id = ? ;" ;
		String [] params = {id};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if(currsor.next()) d = new Deadline(Integer.parseInt(id), currsor.getString(1),currsor.getString(2), currsor.getInt(3), currsor.getBoolean(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public Deadline[] selectByGroup(String name) {
		// TODO Auto-generated method stub
		Deadline[] dls = new Deadline[0];
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
			e.printStackTrace();
		}
		
		return dls;
	}
	
	public Deadline[] selectGroupbyTutor(String idTutor) {
		// TODO Auto-generated method stub
		Deadline[] gs = new Deadline[0];
		String q = "SELECT Deadlines.id, Deadlines.description, DATE_FORMAT(Deadlines.date_limit, '"+ Isep.MYSQL_UTC +"'), Deadlines.`status`, Groups.`name`, Deadlines.id_createur"
				+ " FROM Deadlines INNER JOIN Groups ON Deadlines.id_group = Groups.id"
				+ " WHERE Groups.`id_tutor` = ? ;";
		String[] params = {idTutor};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if (currsor.last()) {
				gs = new Deadline[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				
				gs[i] = new Deadline(currsor.getInt(1), currsor.getString(2), currsor.getString(3), currsor.getInt(6), currsor.getBoolean(4), currsor.getString(5));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gs = new Deadline[0];
			e.printStackTrace();
		}
		
		return gs;
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

	public Deadline[] selectAllDeadlines(){
		Deadline[] dl = new Deadline[0];
		String q = "SELECT `Deadlines`.id, `Deadlines`.description,  DATE_FORMAT(Deadlines.date_limit, '"+ Isep.MYSQL_UTC +"'),"
				+"`Deadlines`.id_createur, `Deadlines`.Status, Groups.name "
				+ "FROM `Deadlines`INNER JOIN Groups ON  `Deadlines`.id_group = Groups.id";	
		ResultSet currsor = Bdd.exec(this.connect, q);
		try {
			if(!currsor.next()) return new Deadline[0];
			if (currsor.last()) {
				dl = new Deadline[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				Deadline d = new Deadline(currsor.getInt(1), currsor.getString(2), currsor.getString(3), currsor.getInt(4), currsor.getBoolean(5), currsor.getString(6));
				dl[i] = d;
				i++;
			}
			currsor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dl;
	}
	
	
	public Delivery[] depositPerGroups(Group g){
		Delivery[] res = new Delivery[0];
		String[] params = {Integer.toString(g.getId())};
		String q = "SELECT Delivery.`id`,  Delivery.`path` FROM Delivery"
				+ " WHERE Delivery.id_owner_group = ? ;";
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if (currsor.last()) {
				res = new Delivery[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				Delivery d = new Delivery(currsor.getInt(1), currsor.getString(2));
				res[i] = d;
				i++;
			}
		} catch (SQLException e) { res = new Delivery[0]; }
		return res;
	}
	
	public void addPathToFile(Deadline d){
		String[] params = {Integer.toString(d.getId())};
		String q = "SELECT Delivery.path FROM Delivery INNER JOIN Deadlines"
				+ " ON Deadlines.`id` = Delivery.id_deadline WHERE Deadlines.`id` = ?";
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			currsor.next();
			d.setDeliveryPath(currsor.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
