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
import javax.servlet.http.HttpSession;

import org.purple.bean.Deadline;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
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
				DaoMissings dm = new DaoMissings(bddServletCo);
				DaoMarks dmk = new DaoMarks(bddServletCo);
				DaoDeadline ddl = new DaoDeadline(bddServletCo);
				DaoGroups dgrp = new DaoGroups(bddServletCo);
				User std = du.select(student);
				
				if(std != null && std.getPosition().equals("student")){
					
					// -- we get he/she from the data base
					du.addGroup(std);// -- we retrieve his group.
					
					// -- we deal with the skills
					double maxMark = DaoValues.fetchMax();
					Skill[] skills = DaoSkills.allSkill();// -- get all the skill for this session
					ArrayList<Mark> marks = dmk.selectByStudent(Integer.toString(std.getId()));// -- get all the mark for this student
					ArrayList<Average> sklAverage = new ArrayList<Average>();// -- 
					Average average = new Average("Moyenne: "+std.getPseudo(), Isep.LANDMARK);
					for(Skill s : skills){
						Average a = new Average(s.getTitle(), maxMark);
						if(s.getId() == 0) a.setCross(true);
						sklAverage.add(a);
						
					}
					for(Average av : sklAverage){
						for(Mark note : marks){
							if(av.getTitle().equals(note.getSkill())){
								av.push(note);
							}
						}
						average.push(av);
					}
					
					// -- we retreve all the deadline
					Deadline[] deadlines = ddl.selectByGroup(std.getGroup());
					
					request.setAttribute("average", average);
					request.setAttribute("skills", skills);
					
					// -- we get the missing of the student
					Missing[] missingGrid = dm.selectForStudent(Integer.toString(std.getId()));// -- we prepare the data format for the view
					if(missingGrid == null) missingGrid = new Missing[0];// -- He never skip class, he win an empty array
					
					
					// -- available group
					String[] grps = dgrp.allGroups();
					request.setAttribute("availableGroups", grps);
					
					
					p.setContent("users/student.jsp");
					p.setTitle("ISEP / APP - Etudiants");
					p.setCss("bootstrap-select.min.css", "student.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js","student.js","data_student.js");
					
					request.setAttribute("student", std);// -- we send the student
					request.setAttribute("missingGrid", missingGrid);// -- we send the his missing
					request.setAttribute("deadlines", deadlines);
					
					
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
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		
		// -- All type of request
		String scope = "";
		scope = request.getParameter("pseudo"); /** student, tutor, ... **/
		
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
				
			if(!Isep.nullOrEmpty(scope, stdFirstName, stdLastName, stdPseudo, stdEmail, stdNewGroup)){
				/**
				 *  HERE THE USER TRY TO UPDATE A STUDENT
				 */
				
				User subjectUser = du.select(scope);
				String redirectionPseudo = scope;
				if(subjectUser.getId() != 0){
					subjectUser.setFirstName(stdFirstName); subjectUser.setLastName(stdLastName);
					subjectUser.setPseudo(stdPseudo);
					subjectUser.setMail(stdEmail); subjectUser.setGroup(stdNewGroup);
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
					
					// -- end of modifications
					// -- redirection user
					
					User std = du.select(redirectionPseudo);
					// -- we get he/she from the data base
					du.addGroup(std);// -- we retrieve his group.
					
					// -- we deal with the skills
					double maxMark = DaoValues.fetchMax();
					Skill[] skills = DaoSkills.allSkill();// -- get all the skill for this session
					ArrayList<Mark> marks = dmk.selectByStudent(Integer.toString(std.getId()));// -- get all the mark for this student
					ArrayList<Average> sklAverage = new ArrayList<Average>();// -- 
					Average average = new Average("Moyenne: "+std.getPseudo(), Isep.LANDMARK);
					for(Skill s : skills){
						Average a = new Average(s.getTitle(), maxMark);
						if(s.getId() == 0) a.setCross(true);
						sklAverage.add(a);
						
					}
					for(Average av : sklAverage){
						for(Mark note : marks){
							if(av.getTitle().equals(note.getSkill())){
								av.push(note);
							}
						}
						average.push(av);
					}
					
					// -- we retreve all the deadline
					Deadline[] deadlines = ddl.selectByGroup(std.getGroup());
					
					request.setAttribute("average", average);
					request.setAttribute("skills", skills);
					
					// -- we get the missing of the student
					Missing[] missingGrid = dm.selectForStudent(Integer.toString(std.getId()));// -- we prepare the data format for the view
					if(missingGrid == null) missingGrid = new Missing[0];// -- He never skip class, he win an empty array
					
					
					// -- available group
					String[] grps = dgrp.allGroups();
					request.setAttribute("availableGroups", grps);
					
					
					p.setContent("users/student.jsp");
					p.setTitle("ISEP / APP - Etudiants");
					p.setCss("bootstrap-select.min.css", "student.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js", "student.js","data_student.js");
					
					request.setAttribute("student", std);// -- we send the student
					request.setAttribute("missingGrid", missingGrid);// -- we send the his missing
					request.setAttribute("deadlines", deadlines);
					
				} else {
					p.setWarning(true);
					p.setWarningMessage("le profil étudiant à modifier n'a pas été trouvé dans la base de données.");
				}
				
			} else {
				
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
