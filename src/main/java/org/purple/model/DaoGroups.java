package org.purple.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.purple.bean.Group;
import org.purple.bean.User;

public class DaoGroups extends Dao<Group> {

	public DaoGroups(Connection co) {
		super(co);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Group obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Group obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Group obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Group select(String name) {
		// TODO Auto-generated method stub
		Group g = null;
		String q = "SELECT Groups.id, Groups.`name`, Groups.class"
				+ " FROM APPDB.Groups" + " WHERE Groups.`name` =  ? ;";
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1, name);
			ResultSet currsor = prestmt.executeQuery();
			if (!currsor.next())
				return g;
			g = new Group(currsor.getInt(1), currsor.getString(2),
					currsor.getString(3));
			prestmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			g = null;
			e.printStackTrace();
		}
		return g;
	}

	// Select all group name
	public String[] selectAllName(String pseudo) {

		String[] gp_name = null;

		String q = "SELECT Groups.`name` FROM Groups INNER JOIN Users ON Users.id = Groups.id_tutor WHERE Users.pseudo = ? ;";
		try {
			PreparedStatement prestmt = this.connect.prepareStatement(q);
			prestmt.setString(1, pseudo);
			ResultSet cursor = prestmt.executeQuery();
			if (cursor.last()) {
				gp_name = new String[cursor.getRow()];
				cursor.beforeFirst();
			}
			int i = 0;
			while (cursor.next()) {
				gp_name[i] = new String(cursor.getString(1));
				i++;
			}
			cursor.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			gp_name = null;
			e.printStackTrace();
		}
		return gp_name;
	}

	public void completeMemebers(Group g) {
		String q = "SELECT Users.id, Users.pseudo, Users.first_name, Users.last_name, "
				+ " Users.tel, Users.mail "
				+ " FROM APPDB.Users INNER JOIN APPDB.Groups "
				+ " ON Groups.id = Users.id_group WHERE Groups.id = "
				+ Integer.toString(g.getId()) + "" + " AND Users.id_post = 4;";
		try {
			ResultSet currsor = this.connect.createStatement().executeQuery(q);
			while (currsor.next()) {
				User u = new User(currsor.getInt(1), currsor.getString(2),
						currsor.getString(3), currsor.getString(4), g.getName());
				u.setTel(currsor.getString(5));
				u.setMail(currsor.getString(6));
				g.setMembers(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// //Select the group of the student connect
	// public Group selectGroupName(int id) {
	// // TODO Auto-generated method stub
	// Group g = null;
	// String q = "SELECT Groups.id, Groups.`name`, Groups.class"
	// + "FROM APPDB.Groups"
	// + "WHERE Groups.`id` = ?";
	// try {
	// PreparedStatement prestmt = this.connect.prepareStatement(q);
	// prestmt.setInt(1, id);
	// ResultSet currsor = prestmt.executeQuery();
	// if (!currsor.next())
	// return g;
	//
	// while (currsor.next()){
	// g = new Group();
	// g.setId(currsor.getInt(1));
	// g.setName(currsor.getString(2));
	// g.set_class(currsor.getString(3));
	// }
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// g = null;
	// e.printStackTrace();
	// }
	// return g;
	// }

}
