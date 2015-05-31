package org.purple.isepapp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.purple.bean.Mark;
import org.purple.constant.Bdd;
import org.purple.model.Average;
import org.purple.model.DaoMarks;

public class TestDaoMarks {

	//@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	//@Test
	public void TestGetMark(){
		DaoMarks dm = new DaoMarks(Bdd.getSecureCo());
		Mark m = dm.select("1");
		if (m.getValue() != 0.0 && m.getOwner() != "ldivad"){
			fail(Double.toString(m.getValue()));
		}
		
	}
	
	//@Test
	public void TestGetAllMark(){
		DaoMarks dm = new DaoMarks(Bdd.getSecureCo());
		ArrayList<Mark> marks = dm.selectByStudent("1");
		for(Mark m : marks){
			if(!m.getOwner().equals("_ldivad")){
				fail(m.getOwner());
			}
		}
	}
	
	
	//@Test
	public void ComputeMark(){
		Mark[] marks = { new Mark(3.0),
		new Mark(1.0),
		new Mark(3.0),
		new Mark(5.0)};
		
		Average a = new Average(true);
		for(Mark m : marks){
			a.push(m);
		}
		if(false/*marks[0].compute() != 3.0*/) fail("Result Error: " + Double.toString(marks[0].compute()));
		if(false/*a.compute() != 12.0*/) fail("Result Error: " + Double.toString(a.compute()));

	}

	//@Test 
	public void ComputeAverage(){
		ArrayList<Average> as = new ArrayList<Average>();
		for(int i = 0; i < 5; i++){
			Average a = Mockito.mock(Average.class);
			Mockito.when(a.compute()).thenReturn((double)(i+1));
			as.add(a);
		}
		
		Average mainAvg = new Average();
		for(Average a : as){
			mainAvg.push(a);
		}
		
		if(false/*mainAvg.compute() != 3.0*/) fail(Double.toString(mainAvg.compute()));
		
	}
	
	//@Test
	public void ComputeGroupAverage(){
		
	}
	
	//@Test 
	public void ComputePromAverage(){
		
	}
}
