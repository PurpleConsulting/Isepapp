package org.purple.isepapp;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.purple.bean.Mark;
import org.purple.bean.User;
import org.purple.model.Average;
import org.purple.model.Avg;
import org.purple.model.AvgBuilder;
public class TestAvgBuilder {

	private Random random = new Random();
	private User zozo = new User(7, "zkaneswa", "Zovena", "KANESWARAN", "student");
	private User dede = new User(10, "dchantha", "Delphine", "CHANTHAVONG", "student");
	private User billy  = new User(13, "nrasolom", "Narisely", "RASOLOMALALA",  "student");
	
	private String path = "/real/path/to/the/test/folder";
	
	private ArrayList<Mark> std = new ArrayList<Mark>();
	private ArrayList<Mark> stdCross = new ArrayList<Mark>();
	private ArrayList<Mark> stdBlank = new ArrayList<Mark>();
	
	private ArrayList<Mark> grp = new ArrayList<Mark>();
	private ArrayList<Mark> grpCross = new ArrayList<Mark>();
	private ArrayList<Mark> grpBlank = new ArrayList<Mark>();
	
	
	private String stdFile = "test_avg_student.csv";
	private String stdFileLimitCross = "test_avg_student_cross.csv";
	private String stdFileLimitBlank = "test_avg_student_uncompleted.csv";
	
	private String grpFile = "test_avg_group.csv";
	private String grpFileLimitCross = "test_avg_group_cross.csv";
	private String grpFileLimitBlank = "test_avg_group_uncompleted.csv";
	
	ArrayList<Mark> stdMark = new ArrayList<Mark>(); 
	
	@Before
	public void setUp() throws IOException{
		this.path = new File(".").getCanonicalPath() + "/src/test/";
		zozo.setGroup("G5A"); dede.setGroup("G5B");
		std = this.loadMarkFile(this.stdFile);
		
	}
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testAvgStudent(){
		Average a = AvgBuilder.studentAverage(this.std, this.dede, 5);
		Assert.assertEquals(this.std.get(random.nextInt(this.std.size())).getOwner(), a.getTitle());
		Assert.assertEquals(5, a.getGrid().size());
		Assert.assertEquals(13.52, a.compute(), 0.01);
	}

	public ArrayList<Mark> loadMarkFile(String csvFileName){
		BufferedReader br = null;
		String sep = ";"; String line = "";
		ArrayList<Mark> marks = new ArrayList<Mark>();
		try{
			br = new BufferedReader(new FileReader(this.path + csvFileName));
			while ((line = br.readLine()) != null) {
				String[] markLine = line.split(sep);
				Mark m = new Mark(markLine[0], Double.parseDouble(markLine[1]), markLine[2], 
						Integer.parseInt(markLine[3]), markLine[4], 
						Integer.parseInt(markLine[5]), markLine[6]);
				marks.add(m);
			}
		} catch (FileNotFoundException e){
			fail("Error the file " +csvFileName+ " is missing.");	
		} catch (IOException e) {
			fail("Error occures when parcing the file "+csvFileName+ ".");
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
