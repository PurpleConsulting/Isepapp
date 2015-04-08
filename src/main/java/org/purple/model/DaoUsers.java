package org.purple.model;

/*** SQL import ***/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.purple.bean.User;
/*** Purple import ***/

public class DaoUsers extends Dao<User> {
	
	public DaoUsers(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * A minimalist function for connection. Should be replace by the LDAP protocol.
	 * NEED TO CLEAN UP THIS FUNCTION!!!
	 * @param id
	 * @return
	 */
	public boolean find(String id){/* FAKE FUNCTION, FIXING BUG */
		boolean res = false;
		String q = "SELECT COUNT(*) FROM Utilisateurs WHERE id = ?;";
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,id);
			ResultSet currsor = prestmt.executeQuery();
			currsor.next();
			int set = currsor.getInt(1);
			if (set == 1) res = true;
			this.connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return res;
	}
	

	@Override
	public boolean create(User obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User select(String id) {/* FAKE FUNCTION, FIXING BUG */
		// TODO Auto-generated method stub
		User u = new User();
		String q = "SELECT Utilisateurs.id,"
				+ "nom, prenom,"
				+ " Postes.intitule"
				+ " FROM Utilisateurs INNER JOIN Postes"
				+ " on Utilisateurs.id_poste = Postes.id "
				+ "WHERE Utilisateurs.id = ?;";
		try{
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1,id);
			ResultSet currsor = prestmt.executeQuery();
			currsor.next();
			u.setId(currsor.getString(1));
			u.setFirstName(currsor.getString(2));
			u.setLastName(currsor.getString(3));
			u.setPosition(currsor.getString(4));
		}catch (SQLException e){
			// TODO Auto-generated catch block
			u = null;
			e.printStackTrace();
		}
		/*u.setFirstName("Billy");
		u.setLastName("RASLOLO");
		u.setPosition("student");*/
		return u;
	}
	
	public User[] selectBy(String field, String value) {
		return null;
	}

	@Override
	public int count(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
