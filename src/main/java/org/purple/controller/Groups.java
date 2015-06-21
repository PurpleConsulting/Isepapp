package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Mark;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoMissings;
import org.purple.model.DaoSkills;
import org.purple.model.DaoValues;


/**
 * Servlet implementation class Group
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Groups() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		// -- Authentication --
		if(!Auth.isRespo(request) && !Auth.isTutor(request) && !Auth.isAdmin(request)){ 
			
			// -- Back to the home page with an warning message.
			p.setWarning(true);
			p.setWarningMessage("la page sur laquelle vous tentez de vous rendre ne vous est pas accessible. "
					+ "Pour toutes réclamations, prenez contact avec le responsable d'APP actuel.");
			p.setContent("home.jsp");
			
		} else {
			// -- We assume we are Admin or tutor or Respo, Now Does the student exist?
			// -- Which group is required
			String scope = request.getParameter("scope");
			if(scope == null || scope == "_all"){
				
				p.setContent("home.jsp");
			} else {
				Connection bddServletCo = Bdd.getCo();
				DaoGroups dg = new DaoGroups(bddServletCo);
				DaoMarks dmk = new DaoMarks(bddServletCo);
				DaoDeadline ddl = new DaoDeadline(bddServletCo);
				DaoMissings dm = new DaoMissings(bddServletCo);
				
				Group group = dg.select(scope);  
				if(group == null){
					
					// -- Incorrect data, the group is missing.
					p.setWarning(true);
					p.setWarningMessage("le groupe demandé n'a pas été retrouvé dans la base.");
					p.setContent("home.jsp");
					request.setAttribute("pages", p);
					
				} else {
					
					dg.completeMemebers(group);
					dg.completeTutor(group);
					
					// -- Retrieve all missings
					Missing[] allmissings = dm.selectForGroup(group.getName());
					
					// -- Retrieve all deadlines
					Deadline[] deadlines = ddl.selectByGroup(group.getName());
					
					// -- Retrieve the group average
					double maxMark = DaoValues.fetchMax();
					Skill[] skills = DaoSkills.allSkill();// -- get all the skill for this session
					Average grpAverage = new Average("Moyenne: "+group.getName(), Isep.LANDMARK);
					for(User u : group.getMembers()){
						// -- get all the mark for this student
						Average stdAverage = new Average(u.getPseudo(), Isep.LANDMARK);
						ArrayList<Mark> marks = dmk.selectByStudent(Integer.toString(u.getId()));
						for(Skill skill : skills){
							Average skillAvg = new Average(skill.getTitle(), maxMark);
							for(Mark m : marks){
								if(skillAvg.getTitle().equals(m.getSkill())){
									skillAvg.push(m);
								}
							}
							stdAverage.push(skillAvg);
						}
						grpAverage.push(stdAverage);
					}
					
					
					p.setTitle("ISEP / APP - Groupes "+group.getName());
					p.setContent("users/group.jsp");
					p.setCss("group.css");
					p.setJs("group.js");
					
					request.setAttribute("pages", p);
					request.setAttribute("group", group);
					request.setAttribute("average", grpAverage);
					request.setAttribute("missings", allmissings);
					request.setAttribute("deadlines", deadlines);
				}
				
				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		request.setAttribute("pages", p);
		request.getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
