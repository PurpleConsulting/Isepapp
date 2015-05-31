package org.purple.isepapp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.purple.bean.Skill;
import org.purple.model.DaoSkills;

public class TestDaoSkills {

	//@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	//@Test
	public void TestAllSkills(){
		Skill[] sk = DaoSkills.allSkill();
		if(sk.length != 6){
			System.out.print(sk[0].getTitle());
			fail(Integer.toString(sk.length));
		}
		
	}

}
