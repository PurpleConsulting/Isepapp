package org.purple.isepapp;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.purple.bean.Group;
import org.purple.bean.Mark;
import org.purple.bean.User;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.Avg;
import org.purple.model.AvgBuilder;

public class TestAvgBuilder {

	private Random random = new Random();
	private User zozo = new User(7, "zkaneswa", "Zovena", "KANESWARAN", "student");
	private User dede = new User(10, "dchantha", "Delphine", "CHANTHAVONG", "student");
	private User billy  = new User(13, "nrasolom", "Narisely", "RASOLOMALALA",  "student");
	private User loic  = new User(16, "ldivad", "Loïc", "DIVAD",  "student");
	private Group g9z = new Group(9, "G9Z", "G9");
	
	private String path = "/real/path/to/the/test/folder";
	
	private ArrayList<Mark> std = new ArrayList<Mark>();
	private ArrayList<Mark> stdCross = new ArrayList<Mark>();
	private ArrayList<Mark> stdBlank = new ArrayList<Mark>();
	
	private ArrayList<Mark> grp = new ArrayList<Mark>();
	private ArrayList<Mark> grpCross = new ArrayList<Mark>();
	private ArrayList<Mark> grpBlank = new ArrayList<Mark>();
	
	private HashMap<String, ArrayList<Mark>> prm = new HashMap<String, ArrayList<Mark>>();
	
	
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
		this.g9z.setMembers(zozo);this.g9z.setMembers(dede); this.g9z.setMembers(billy);this.g9z.setMembers(loic);
		zozo.setGroup("G5A"); dede.setGroup("G5B"); billy.setGroup("G6C"); loic.setGroup("G09");
		
		std = this.loadMarkFile(this.stdFile);
		stdCross = this.loadMarkFile(this.stdFileLimitCross);
		stdBlank = this.loadMarkFile(this.stdFileLimitBlank);
		
		grp = this.loadMarkFile(this.grpFile);
		grpCross = this.loadMarkFile(this.grpFileLimitCross);
		grpBlank = this.loadMarkFile(this.grpFileLimitBlank);
		
		
	}
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testAvgStudent(){
		// -- Normal case
		Average a = AvgBuilder.studentAverage(this.std, this.dede, 5.0);
		Assert.assertEquals(this.std.get(random.nextInt(this.std.size())).getOwner(), a.getTitle()); // Identity of the student for a random skill 
		Assert.assertEquals(5, a.getGrid().size());		 // test number of skill evaluated
		Assert.assertEquals(13.52, a.compute(), 0.01);	// test the result of average
		
		// -- Case limit || average with cross mark
		Average b = AvgBuilder.studentAverage(this.stdCross, this.billy, 5.0);
		Assert.assertEquals(this.stdCross.get(random.nextInt(this.stdCross.size())).getOwner(), b.getTitle()); // Identity of the student for a random skill
		Assert.assertEquals(6, b.getGrid().size());		// test number of skill evaluated
		Assert.assertEquals(11.71, b.compute(), 0.01); // test the result of average
		
		// -- Case limit || uncomplete evaluation
		Average c = AvgBuilder.studentAverage(this.stdBlank, this.zozo, 5.0);
		Assert.assertEquals(this.stdBlank.get(random.nextInt(this.stdBlank.size())).getOwner(), c.getTitle()); // Identity of the student for a random skill
		Assert.assertEquals(3, c.getGrid().size());		// test number of skill evaluated
		Assert.assertEquals(17.73, c.compute(), 0.01); // test the result of average
	}
	
	
	@Test
	public void testAvgGroup(){
		// -- Normal case
		Average a = AvgBuilder.groupAverage(this.grp, this.g9z, 5.0);
		assertEquals("G9Z", a.getTitle());		// -- test the average belong to the group
		assertEquals(4, a.getGrid().size());		// -- test the number in the group
		assertEquals(15.71, a.compute(), 0.1);	// -- test the computation for the group
		assertEquals(17.68, a.byTitle("zkaneswa").compute(), 0.01); // -- test the computation for one student
		/**  group: 15.71, zozo: 17.68, dede: 14.17, billy: 14.92, loic: 16.08  **/
		
		// -- Case limit || half completed cross mark
		Average b = AvgBuilder.groupAverage(this.grpCross, this.g9z, 5.0);
		assertEquals(139, this.grpCross.size());
		assertEquals("G9Z", b.getTitle());		// -- test the average belong to the group
		assertEquals(4, b.getGrid().size());		// -- test the number in the group
		assertEquals(15.6, b.compute(), 0.1);	// -- test the computation for the group
		assertEquals(14.21, b.byTitle("dchantha").compute(), 0.01); // -- test the computation for one student
		/** group: 15.6, zozo: 17.8, dede: 14.21, billy: 14.64, loic: 15.73 **/
		
		// -- Case limit || incomplete evaluation no cross mark
		Average c = AvgBuilder.groupAverage(this.grpBlank, this.g9z, 5);
		assertEquals(139, this.grpCross.size());
		assertEquals("G9Z", c.getTitle());		// -- test the average belong to the group
		assertEquals(4, c.getGrid().size());		// -- test the number in the group
		assertEquals(16.17, c.compute(), 0.1);	// -- test the computation for the group 16.17 vs 12.13
		assertEquals(15.0, c.byTitle("nrasolom").compute(), 0.01); // -- test the computation for one student
		/** group: 16.17, zozo: 17.68, dede: 15.82, billy: 15.0, loic:0.0 **/
	}
	

	@Test
	public void testAvgProm(){
		ArrayList<Group>allGrp = this.loadPromoFile();
		Average a = AvgBuilder.promAverage(this.prm, allGrp, 5.0);
		assertEquals(4, a.getGrid().size());
		assertNotEquals(0.0, a.compute());
		
		ArrayList<Group> p = this.loadPromoFile();
		

		
	}
	
	
	
	
	// --------------------------------------------------------------------------------
	// -- UTILITIES FUNCTION - OBJECT LOADER FOR TEST
	// --------------------------------------------------------------------------------
	
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
	
	public ArrayList<Group> loadPromoFile(){
		BufferedReader br = null;
		String sep = ";"; String line = ""; String lastGrp = "";
		ArrayList<Group> prom = new ArrayList<Group>();
		try{
			br = new BufferedReader(new FileReader(this.path + "test_promo.csv"));
			Group group = new Group();
			while ((line = br.readLine()) != null) {
				String[] stdLine = line.split(sep);
				if(!lastGrp.equals(stdLine[0]) && !lastGrp.equals("")){
					group.setName(lastGrp); prom.add(group);
					group = new Group(); 
				}
				lastGrp = stdLine[0];
				User student = new User(stdLine[1], stdLine[2], stdLine[3], stdLine[4], Auth.student);
				student.setGroup(stdLine[0]);
				group.setMembers(student);
			}
			group.setName(lastGrp); prom.add(group);
		} catch (FileNotFoundException e){
			fail("Error the file test_promo.csv is missing.");	
		} catch (IOException e) {
			fail("Error occures when parcing the file test_promo.csv.");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

				}
			}
		}
		return prom;
	}
}
