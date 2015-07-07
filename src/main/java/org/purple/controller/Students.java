package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Mark;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.SubSkill;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.AvgBuilder;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoMissings;
import org.purple.model.DaoSkills;
import org.purple.model.DaoSubSkills;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Students")
public class Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Students() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doLoad(HttpServletRequest request, DaoGroups dgrp, DaoSkills ds, DaoMarks dmk, DaoDeadline ddl, DaoMissings dm, User std){
    	// --------------------------------------------------------------------------------
    	// -- BUILDING THE MARK
    	// --------------------------------------------------------------------------------
		double maxMark = DaoValues.fetchMax(); 						  // -- the maximum possible for one evaluation
		Skill[] skills = ds.allSkill();
		ArrayList<Mark> marks = dmk.selectByStudent(std.getPseudo()); // -- get all the marks for this student
		Average average = AvgBuilder.studentAverage(marks,std,maxMark);
		
		
		// --------------------------------------------------------------------------------
    	// -- BUILDING THE DEADLINE AND DELIVERIES
    	// --------------------------------------------------------------------------------
		Deadline[] deadlines = ddl.selectByGroup(std.getGroup());
		
				
		// --------------------------------------------------------------------------------
    	// -- BUILDING THE MISSINGS GRID
    	// --------------------------------------------------------------------------------
		Missing[] missingGrid = dm.selectForStudent(Integer.toString(std.getId()));

		
		// --------------------------------------------------------------------------------
		// -- BUILDING THE CROSSMAK (IF EXIST)
		// --------------------------------------------------------------------------------	
		ArrayList<User> crossMate = new ArrayList<User>();
		Group group = dgrp.select(std.getGroup()); /*for this group*/
		dgrp.completeMemebers(group);/*find all the students*/
		HashMap<String, ArrayList<Mark>> allCrossMarks = new HashMap<String, ArrayList<Mark>>();/* group all the mark by classmate*/
		/*["classmateA", [markA1,markA2 markA3 ... ]]*/
		/*["classmateB", [markB1,markB2 markB3 ... ]]*/
		/*["classmateC", [markC1,markC2 markC3 ... ]]*/
		
		Skill cross  = ds.select("0");/*the cross skill id is 0*/
		ds.completeSub_skills(cross); /*all the subskil belong to the skill n°0*/
		for(User m : group.getMembers()){// for each class mate ...
			allCrossMarks.put(m.getPseudo(), dmk.selectCrossByStudentAndMate(std.getPseudo(), m.getPseudo()));
			//get the mark given to this student ...
			if(allCrossMarks.get(m.getPseudo()).size() > 0){
				crossMate.add(m);// if more then 0 mark, put it in the average.
			}
		}
		
		
		// --------------------------------------------------------------------------------
		// -- FILLIN THE REQUEST
		// --------------------------------------------------------------------------------	
		String[] grps = dgrp.allGroups();
		request.setAttribute("availableGroups", grps);
		
		request.setAttribute("CSubSkills", cross.getSubSkills());
		request.setAttribute("crossmates", crossMate);
		request.setAttribute("crossmarks", allCrossMarks);
		request.setAttribute("skills", skills);
		request.setAttribute("average", average);	
		request.setAttribute("missingGrid", missingGrid);
		request.setAttribute("deadlines", deadlines);
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); Page p = new Page();
		/*$$$ GET PARAMS  $$$*/
		String student = request.getParameter("pseudo");
		
		
		// -- Authentication --
		if(Auth.isRespo(request) || Auth.isStudentTutor(request, student)){ 
			// -- We assume we are Admin or tutor or Respo, Now Does the student exist?
			
			if(Isep.nullOrEmpty(student)){
				// -- Incomplete data, the pseudo is missing.
				p.setWarning(true);
				Isep.bagPackHome(p, request.getSession());
				p.setWarningMessage("la page étudiante n'a pas été retrouvée.");
				p.setContent("home/common.jsp");
				
			} else {
				
				// -- Lets see if the pseudo is in the data base.
				Connection bddServletCo = Bdd.getCo();
				DaoUsers du = new DaoUsers(bddServletCo);
				
				User std = du.select(student);
				
				if(std != null && std.getPosition().equals("student")){
					
					DaoMissings dm = new DaoMissings(bddServletCo);
					DaoMarks dmk = new DaoMarks(bddServletCo);
					DaoDeadline ddl = new DaoDeadline(bddServletCo);
					DaoGroups dgrp = new DaoGroups(bddServletCo);
					DaoSkills ds = new DaoSkills(bddServletCo);
					DaoSubSkills dss = new DaoSubSkills(bddServletCo);
					
					// -- we get he/she from the data base
					du.addGroup(std);// -- we retrieve his group.
					
					this.doLoad(request, dgrp, ds, dmk, ddl, dm, std);
						
					p.setContent("users/student.jsp");
					p.setTitle("ISEP / APP - Etudiant");
					p.setCss("bootstrap-select.min.css", "student.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js","student.js","data_student.js");
					
					request.setAttribute("student", std);// -- we send the student
					
				} else {
					
					// -- the pseudo isn't found
					p.setWarning(true);
					p.setWarningMessage("l'utilisateur que vous cherchez n'a pas pu être trouvé "
							+ "ou ne fait pas parti des étudiants... page bientôt remplacée par une liste d'étudiant");
					p.setContent("home.jsp");
					
				}
				
				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else {
			// -- Back to the home page with an warning message.
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("la page sur laquelle vous tentez de vous rendre ne vous est pas accessible. "
					+ "Pour toutes réclamations, prenez contact avec le responsable d'APP actuel.");
			p.setContent("home/common.jsp");
		}
		
		request.setAttribute("pages", p);
		request.getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); Page p = new Page();
		
		/*$$$ POST PARAM $$$*/
		String scope = request.getParameter("pseudo");
		if(Isep.nullOrEmpty(scope)) scope = "";
		
		// -- Alter student request
		String stdFirstName = request.getParameter("std_first_name");
		String stdLastName = request.getParameter("std_last_name");
		String stdPseudo = request.getParameter("std_pseudo");
		String stdIsepNo = request.getParameter("std_no");
		String stdEmail = request.getParameter("std_email");
		String stdNewGroup = request.getParameter("std_new_group");
		
		
		if(Auth.isRespo(request) || Auth.isStudentTutor(request, scope)){
				// -- Since the user is connected he can access to the database
				Connection bddServletCo = Bdd.getCo();
				
				DaoUsers du = new DaoUsers(bddServletCo);
				DaoMissings dm = new DaoMissings(bddServletCo);
				DaoMarks dmk = new DaoMarks(bddServletCo);
				DaoDeadline ddl = new DaoDeadline(bddServletCo);
				DaoGroups dgrp = new DaoGroups(bddServletCo);
				DaoSkills ds = new DaoSkills(bddServletCo);
				
			if(!Isep.nullOrEmpty(scope, stdFirstName, stdLastName, stdPseudo, stdEmail, stdNewGroup)){
				// --------------------------------------------------------------------------------
				// -- HERE THE USER TRY TO UPDATE A STUDENT
				// --------------------------------------------------------------------------------		
				User subjectUser = du.select(scope);
				String redirectionPseudo = scope;
				if(subjectUser.getId() != 0){
					subjectUser.setFirstName(stdFirstName); subjectUser.setLastName(stdLastName);
					subjectUser.setPseudo(stdPseudo);subjectUser.setGroup(stdNewGroup);
					subjectUser.setMail(stdEmail); 
					
					try{ subjectUser.setIsepNo(Integer.parseInt(stdIsepNo));} 
					catch (NumberFormatException e){ }
					boolean querysuccess = du.update(subjectUser);
					
					if(querysuccess){
						p.setSuccess(true);
						p.setSuccessMessage("les modifications sur le profil ont bien été apportées.");
						redirectionPseudo = stdPseudo;
					} else {
						p.setError(true);
						p.setErrorMessage("une erreur est survenue lors de la modification du profil étudiant."
								+ " Les modifications demandées peuvent ne pas avoir été réalisées.");
					}
					
					// -- end of modifications, redirection
					
					User std = du.select(redirectionPseudo);
					// -- we get he/she from the data base
					du.addGroup(std);// -- we retrieve his group.
					
					this.doLoad(request, dgrp, ds, dmk, ddl, dm, std);
					
					p.setContent("users/student.jsp");
					p.setTitle("ISEP / APP - Etudiants");
					p.setCss("bootstrap-select.min.css", "student.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js", "student.js","data_student.js");
					
					request.setAttribute("student", std);// -- we send the student
					
				} else {
					p.setWarning(true);
					p.setWarningMessage("le profil étudiant à modifier n'a pas été trouvé dans la base de données.");
				}
				
			} else {
				// --------------------------------------------------------------------------------
				// -- ANY REQUEST UNDERSTOOD
				// --------------------------------------------------------------------------------
				p.setError(true);
				p.setErrorMessage("votre demande a mal été interprétée. Veillez à bien remplir les champs des formulaires proposés.");
				
			}
				
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			p.setWarning(true);
			p.setWarningMessage("la page sur laquelle vous tentez de vous rendre ne vous est pas accessible. "
					+ "Pour toutes réclamations, prenez contact avec le responsable d'APP actuel.");
			HttpSession s = request.getSession();
			Isep.bagPackHome(p, s);
			p.setContent("home/common.jsp");
			p.setTitle("ISEP / APP - Accueil");
		
		}
		
		request.setAttribute("pages", p);
		request.getRequestDispatcher("/template.jsp").forward(request, response);
					
	}

}
