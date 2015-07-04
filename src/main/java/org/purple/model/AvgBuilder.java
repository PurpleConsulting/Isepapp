package org.purple.model;

import java.util.ArrayList;

import org.purple.bean.Mark;
import org.purple.bean.User;
import org.purple.constant.Isep;

public class AvgBuilder {
	
	/**
	 * @param marks (Mark) owner pseudo, value, title, idSkill, skill title, id SubSkill, subSkill title
	 * need to be load. You find all those things in DaoMarks.selectByStudent().
	 * @param student (User): Before pass the user to the method you need to
	 * add he's group
	 * @param valMax (double): maximum value of point in the database (try DaoValues.fetchMax())
	 * @return (Average): General Average of ONE Student for this semester.
	 */
	public static Average studentAverage(ArrayList<Mark> marks, User student, double valMax){
		Average a = new Average(student.getPseudo(), Isep.LANDMARK);
		ArrayList<Avg> skillTab = new ArrayList<Avg>();
		Average aLeaf = new Average();
		int lastIdSkill = -1;
		for(Mark m : marks){
			if(m.getIdSkill() != lastIdSkill || lastIdSkill == -1){
				if(!aLeaf.getTitle().equals("null")) skillTab.add(aLeaf);
				lastIdSkill = m.getIdSkill();
				aLeaf = new Average(m.getSkill(), valMax);
			}
			aLeaf.push(m);
		}
		if(aLeaf.status() == 1)skillTab.add(aLeaf);
		a.setGrid(skillTab); 
		return a;
	}
}
