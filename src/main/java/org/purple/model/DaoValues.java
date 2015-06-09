package org.purple.model;

/*** SQL import ***/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;













import org.purple.bean.Deadline;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;

/*** Purple import ***/

public class DaoValues extends Dao<Value> {

	
	
	public DaoValues(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Value val) {
		boolean r=false;
		String q = "INSERT INTO `Values`(title, points)"
				+ "VALUES (?, ?) ";	
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1, val.getTitle());
			prestmt.setInt(2, val.getPoints());
			
			prestmt.execute();
			
			r=true;
			

		}catch (SQLException e){
			// TODO Auto-generated catch block
			r = false;
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public boolean delete(Value val) {
		return false;
		
	}

	@Override
	public boolean update(Value obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Value select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Value[] selectAllValues(String cross) {
		// TODO Auto-generated method stub
		Value[] val = new Value[0];
		String q = "SELECT id,title, points "
				+ "FROM `Values` WHERE `Values`.cross=?"
				+ "ORDER BY id;";		
		String[] params = {cross};
		ResultSet currsor = Bdd.prepareExec(this.connect, q, params);
		try {
			if (currsor.last()) {
				val = new Value[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			int i = 0;
			while(currsor.next()){
				
				val[i] = new Value(currsor.getInt(1), currsor.getInt(3), currsor.getString(2));
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			val = new Value[0];
			e.printStackTrace();
		}
		
		return val;
	}
	
	
	public boolean updateValues (Value[] v){
		boolean val=false;
		String q = "UPDATE `Values` "
				+ "SET title=? , points= ? "
				+ "WHERE id=?";		
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
					
									
			for(int i=0; i< v.length ;i++){
				prestmt.setString(1, v[i].getTitle());
				prestmt.setInt(2, v[i].getPoints());
				prestmt.setInt(3, v[i].getId());
				prestmt.execute();
			}
			val=true;
			

		}catch (SQLException e){
			// TODO Auto-generated catch block
			val = false;
			e.printStackTrace();
		}
		return val;
	}

	
	 public boolean deleteId(int id){
		 boolean r = false;
		 String q = "DELETE FROM `Values`"
					+ "WHERE id=?";	
			try{
				PreparedStatement prestmt = this.connect.prepareStatement(q);
				prestmt.setInt(1, id);
				prestmt.execute();
				
				r=true;
				
			}catch (SQLException e){
				// TODO Auto-generated catch block
				r = false;
				e.printStackTrace();
			}
			return r;
		}
	 
	 public static double fetchMax(){
		 Connection co = Bdd.getCo();
		 double res = 1.0;
		 String q = "SELECT MAX(`Values`.points) FROM `Values` ;";
		 ResultSet currsor = Bdd.exec(co, q);
		 try {
			if(currsor.next()){ res = currsor.getDouble(1);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	 }
 
 }