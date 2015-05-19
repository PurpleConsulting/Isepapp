import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.purple.constant.Bdd;


public class CsvReader {
	
	public static final String PATH = "/Users/LoicMDIVAD/Documents/workspace/java/Isepapp/WebContent/assets/";
	public static final String FILE = "CURRENTGROUPLIST.csv";
	public static final String SEP = ";";
	
	public static final String isepSubstring(String lastname){
		if(lastname.length() > 7){
			return lastname.substring(0,7);
		} else {
			return lastname.substring(0,lastname.length());
		}
	}
	
	private BufferedReader br = null;
	private String line = "";
	private ArrayList<ArrayList<String>> promotion;
	
	
	public CsvReader(){
		
	}
	
	public void read(){
		try {
			 
			this.br = new BufferedReader(new FileReader(CsvReader.PATH + CsvReader.FILE));
			this.promotion = new ArrayList<ArrayList<String>>();
			while ((line = br.readLine()) != null) {
				ArrayList<String> stdline = new ArrayList<String>(Arrays.asList(line.split(CsvReader.SEP)));
				stdline.add(stdline.get(1).substring(0, 1).toLowerCase() + isepSubstring(stdline.get(0)).toLowerCase());
				stdline.add(stdline.get(1).substring(0, 1).toLowerCase() + stdline.get(0).toLowerCase() +"@isep.fr");
				for(String s : stdline){
					System.out.print(s +" ");
				}
				System.out.print("\n");	
				this.promotion.add(stdline);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			
	}
	
	public void insertGroup(){
		
		ArrayList<String> groups = new ArrayList<String>();
		for(ArrayList<String> line : this.promotion){
			if(!groups.contains(line.get(3))){
				groups.add(line.get(3));
			}
		}
		
		Connection co = Bdd.getSecureCo();
		String q = "INSERT INTO `APPDB`.`Groups` (`name`, `id_tutor`, `class`)"
				+ " VALUES (?, ?, ?);";
		for(String g : groups){
			try {
				PreparedStatement prestmt = co.prepareStatement(q);
				prestmt.setString(1,g);
				prestmt.setInt(2,0);
				prestmt.setString(3,g.substring(0,2));
				prestmt.executeUpdate();
				//prestmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void insertStudent(ArrayList<String> line){
		Connection co = Bdd.getSecureCo();
		String gr = "G0D";
		String qget = "SELECT id FROM Groups WHERE `name` = ? ;";
		try {
			PreparedStatement prestmt = co.prepareStatement(qget);
			prestmt.setString(1, line.get(3));
			ResultSet currsor = prestmt.executeQuery();
			currsor.next();
			gr = currsor.getString(1);
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		Connection connect = Bdd.getSecureCo();
		String q = "INSERT INTO `APPDB`.`Users` (`isep_no`, `pseudo`, `id_group`, `promo`,"
				+ " `last_name`, `firts_name`, `mail`,"
				+ " `password`, `add_date`, `id_respo`,`id_post`, `tel)"
				+ "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ";
		
		String q2 = "INSERT INTO `APPDB`.`Users` (`isep_no`, `pseudo`, `id_group`, `promo`,"
				+ " `last_name`, `first_name`, `mail`,"
				+ " `password`, `add_date`, `id_post`, `tel`)"
				+ "  VALUES ("+ line.get(2) +","
				+ " \""+ line.get(4) +"\","
			    + " "+ gr +","
			    + " 2016,"
			    + " \""+ line.get(0) +"\","
			    + " \""+ line.get(1)+"\","
			    + " \""+ line.get(5) +"\","
			    + " \"password\","
			    + " CURDATE(),"
			    + " 4,"
			    + " \"7778880690\"); ";
		try {
			PreparedStatement pstmt = connect.prepareStatement(q2);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			CsvReader cr = new CsvReader();
			cr.read();
			cr.insertGroup();
			for(ArrayList<String> line : cr.promotion){
				cr.insertStudent(line);
			}
	}

}
