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
	private ArrayList<Mark> stdM = new ArrayList<Mark>();
	private String path = "/real/path/to/the/test/folder";
	private String stdMarkFile = "test_avg_student.csv";
	
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
		Average a = AvgBuilder.studentAverage(this.stdM, this.dede, 5);
		Assert.assertEquals(this.stdM.get(random.nextInt(this.stdM.size())).getOwner(), a.getTitle());
		Assert.assertEquals(13.52, a.compute(), 0.01);
		
	}

	public ArrayList<Mark> stdMarkFile(){
		BufferedReader br = null;
		String sep = ";"; String line = "";
		ArrayList<Mark> marks = new ArrayList<Mark>();
		try{
			br = new BufferedReader(new FileReader(this.path + this.stdMarkFile));
			while ((line = br.readLine()) != null) {
				String[] markLine = line.split(sep);
				Mark m = new Mark(markLine[0], Double.parseDouble(markLine[1]), markLine[2], 
						Integer.parseInt(markLine[3]), markLine[4], 
						Integer.parseInt(markLine[5]), markLine[6]);
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
