package org.purple.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import org.purple.bean.Group;
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
	
	public static Average groupAverage(ArrayList<Mark> marks, Group group, double valMax){
		Average a = new Average(group.getName(), Isep.LANDMARK);
		for(User student : group.getMembers()){
			ArrayList<Mark> tab = (ArrayList<Mark>) marks.clone();
			Predicate<Mark> filter = new ShiftOtherStudentMarkPrd(student.getPseudo());
			tab.removeIf(filter);
			//System.out.print(student.getPseudo() +" : "+ tab.size() + "\n\n");
			a.push(studentAverage(tab, student, valMax));
		}		
		return a;
	}
	
	public static Average promAverage(HashMap<String, ArrayList<Mark>> marks, ArrayList<Group> groups, double valMax){
		Average a = new Average("Promo", Isep.LANDMARK);
		ArrayList<Average> groupsAVG = new ArrayList<Average>();
		ArrayList<String> _class = new ArrayList<String>();
		for(Group g : groups){
			String current = g.getName().substring(0, 2);
			if(!_class.contains(current)) _class.add(current);
			groupsAVG.add(AvgBuilder.groupAverage(marks.get(g.getName()), g, valMax));
		}
		for(String c : _class ){
			Average avgClass = new Average(c, Isep.LANDMARK);
			for(Average avg : groupsAVG){
				if (avg.getTitle().substring(0, 2).equals(c)) avgClass.push(avg);
			}
			a.push(avgClass);
		}
		return a;
	}
}
