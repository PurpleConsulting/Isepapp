package org.purple.isepapp;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.purple.bean.Mark;
import org.purple.bean.User;
import org.purple.model.AvgBuilder;
public class TestAvgBuilder {

	User zozo = new User(7, "zkaneswa", "Zovena", "KANESWARAN", "student");
	User dede = new User(10, "dchantha", "Delphine", "CHANTHAVONG", "student");
	ArrayList<Mark> stdM = new ArrayList<Mark>();
	String path = "/real/path/to/the/test/folder";
	String stdMarkFile = "test_avg_student.csv";
	
	ArrayList<Mark> stdMark = new ArrayList<Mark>(); 
	
	@Before
	public void setUp() throws IOException{
		this.path = new File(".").getCanonicalPath() + "/src/test/";
		zozo.setGroup("G5A"); dede.setGroup("G5B");
		stdM = this.stdMarkFile();
	}
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAvgStudent(){
		
		AvgBuilder.studentAverage(this.stdM, this.zozo);
		
	}

	public ArrayList<Mark> stdMarkFile(){
		BufferedReader br = null;
		String sep = ";"; String line = "";
		ArrayList<Mark> marks = new ArrayList<Mark>();
		try{
			br = new BufferedReader(new FileReader(this.path + this.stdMarkFile));
			while ((line = br.readLine()) != null) {
				String[] markLine = line.split(sep);
				Mark m = new Mark(markLine[0], Double.parseDouble(markLine[1]), markLine[2], markLine[3], markLine[4]);
				marks.add(m);
			}
		} catch (FileNotFoundException e){
			fail("Error the file " +this.stdMarkFile+ " is missing.");	
		} catch (IOException e) {
			fail("Error occures when parcing the file "+this.stdMarkFile+ ".");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

				}
			}
		}
		return marks;
	}
}
