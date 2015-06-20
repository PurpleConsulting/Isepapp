package org.purple.constant;
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


public class IsepCsvParser {
	
	private String path = "";
	private String file = "";
	private String sep = "";

	
	private BufferedReader br = null;
	private String line = "";
	public ArrayList<ArrayList<String>> promotion;
	
	
	public IsepCsvParser(String path, String file, String sep){
		this.setPath(path);
		this.setFile(file);
		this.setSep(sep);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	public String getSep() {
		return sep;
	}
	public void setSep(String sep) {
		this.sep = sep;
	}
	
	public boolean read(){
		boolean flag = true;
		try { 
			this.br = new BufferedReader(new FileReader(this.path + this.file));
			this.promotion = new ArrayList<ArrayList<String>>();
			while ((line = br.readLine()) != null) {
				ArrayList<String> stdline = new ArrayList<String>(Arrays.asList(line.split(this.sep)));
				this.promotion.add(stdline);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			flag = false;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}
	
	public boolean insertGroup(){
		boolean res = true;
		ArrayList<String> groups = new ArrayList<String>();
		for(ArrayList<String> line : this.promotion){
			if(!groups.contains(line.get(2))){
				groups.add(line.get(2));
			}
		}
		
		Connection co = Bdd.getCo();
		String q = "INSERT INTO `APPDB`.`Groups` (`name`, `class`) VALUES (?, ?);";
		
		for(String g : groups){
			String[] params = {g, g.substring(0,2)};
			int affected = Bdd.preparePerform(co, q, params);
			if(affected < 1) res = false;
		}
		
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	public boolean insertStudent(ArrayList<String> line){
		boolean res = true;
		Connection co = Bdd.getSecureCo();
		
		String q = "INSERT INTO `APPDB`.`Users` "
				+ "(`last_name`, `first_name`, `id_group`, "
				+ "`pseudo`, `isep_no`, `mail`,"
				+ "`add_date`, `id_post`)"
				+ "  VALUES (\""+ line.get(0) +"\","
				+ " \""+ line.get(1) +"\","
			    + " (SELECT Groups.`id` From Groups WHERE Groups.`name` = \""+ line.get(2) +"\" ),"
			    + " \""+ line.get(3) +"\","
			    + " \""+ line.get(4) +"\","
			    + " \""+ line.get(5) +"\","
			    + " CURDATE(),"
			    + " 4); ";
 
		res = res & Bdd.perform(co, q);;
		
		try {
			co.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
