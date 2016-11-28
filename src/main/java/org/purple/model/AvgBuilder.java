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
	 * This function get the Average object standing for the average of ONE student.
	 * @param marks (ArrayList Mark ): owner pseudo, value, title, idSkill, skill title, id SubSkill, subSkill title
	 * need to be load. You will find all those things in DaoMarks.selectByStudent().
	 * @param student (User): Before pass the user to the method you need to add he's group
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
	
	/**
	 * This function return the Average standing for the average of a group (like G8B).
	 * @param marks (ArrayList Mark) owner pseudo, value, title, idSkill, skill title, id SubSkill, subSkill title
	 * need to be load and for all students. You will find all those things in DaoMarks.selectByGroup().
	 * @param group (Group): Before pass the group to the method you need to add the members with DaoGroup.completeMembers()
	 * @param valMax (double): maximum value of point in the database (try DaoValues.fetchMax())
	 * @return (Average): General Average of ONE Group like G5A.
	 */
	public static Average groupAverage(ArrayList<Mark> marks, Group group, double valMax){
		Average a = new Average(group.getName(), Isep.LANDMARK);
		for(User student : group.getMembers()){
			ArrayList<Mark> tab = (ArrayList<Mark>) marks.clone();
			Predicate<Mark> filter = new ShiftOtherStudentMarkPrd(student.getPseudo());
			tab.removeIf(filter);
			Average stdAverage = studentAverage(tab, student, valMax);
			if(stdAverage.status() == 1) a.push(stdAverage);
		}		
		return a;
	}
	
	/**
	 * This function return the Average object standing for the average all the prom.
	 * @param marks (HashMap Mark) ["G5A", ArrayList(Mark)] use DaoMark.selectByGroup to get the Arraylst
	 * @param groups (ArrayList Group) all groups in the array need to be complete with DaoGroup.completeMembers()
	 * @param valMax (double): maximum value of point in the database (try DaoValues.fetchMax()) 
	 * @return (Average): General Average of all the prom
	 */
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
				if (avg.getTitle().substring(0, 2).equals(c) && avg.status() == 1) avgClass.push(avg);
			}
			if(avgClass.status() == 1) a.push(avgClass);

		}
		return a;
	}
}
