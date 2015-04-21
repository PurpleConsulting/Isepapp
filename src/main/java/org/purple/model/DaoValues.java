package org.purple.model;

/*** SQL import ***/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;








import org.purple.bean.User;
import org.purple.bean.Values;
/*** Purple import ***/

public class DaoValues extends Dao<Values> {

	
	
	public DaoValues(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Values obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Values obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Values obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Values select(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Values[] selectAllValues (){
		Values[] val=null;
		String q = "SELECT id,title, points "
				+ "FROM `Values` ";
		
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			ResultSet currsor = prestmt.executeQuery();
			
			int i = 0;
			
			if(!currsor.next()) return val;
			if (currsor.last()) {
				val = new Values[currsor.getRow()];
				currsor.beforeFirst(); 
			}
			
			while(currsor.next()){
				Values v = new Values();
				v.setId(currsor.getInt(1));
				v.setTitle(currsor.getString(2));
				v.setPoints(currsor.getInt(3));
				val[i] = v;
				i = i + 1;
			}
			
		
		}catch (SQLException e){
			// TODO Auto-generated catch block
			val = null;
			e.printStackTrace();
		}
		return val;
	}
	
}
