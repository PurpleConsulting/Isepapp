package org.purple.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Mark;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.DaoMarks;
import org.purple.model.DaoMissings;
import org.purple.model.DaoSkills;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
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
		if(!Auth.isRespo(request)){ 
			
			// -- Back to the home page with an warning message.
			p.setWarning(true);
			p.setWarningMessage("La page sur laquelle vous tentez de vous rendre ne vous est pas accessible. "
					+ "Pour toute réclamation, prenez contact avec le responsable d'APP actuel.");
			p.setContent("home.jsp");
		
		} else {
			
			// -- We assume we are Admin or tutor or Respo, Now Does the student exist?
			String student = request.getParameter("pseudo");
			if(student == null){
				// -- Incomplete data, the pseudo is missing.
				p.setWarning(true);
				p.setWarningMessage("Indication de l'étudiant manquante... page bientôt remplcé par une liste d'étudiant");
				p.setContent("home.jsp");
				
			} else {
				
				// -- Lets see if the pseudo is in the data base.
				DaoUsers du = new DaoUsers(Bdd.getCo());
				DaoMissings dm = new DaoMissings(Bdd.getCo());
				DaoMarks dmk = new DaoMarks(Bdd.getCo());
				User std = du.select(student);
				
				if(std != null && std.getPosition().equals("student")){
					
					// -- we get he/she from the data base
					du.addGroup(std); du.addTelMail(std);// -- we retrieve his group, his tel, and his mail
					
					// -- we deal with the skills
					Skill[] skills = DaoSkills.allSkill();// -- get all the skill for this session
					ArrayList<Mark> marks = dmk.selectByStudent(Integer.toString(std.getId()));// -- get all the mark for this student
					ArrayList<Average> sklAverage = new ArrayList<Average>();// -- 
					Average average = new Average("Moyenne: "+std.getPseudo(), Isep.LANDMARK);
					for(Skill s : skills){
						sklAverage.add(new Average(s.getTitle(), 3.0));
					}
					for(Average av : sklAverage){
						for(Mark note : marks){
							if(av.getTitle().equals(note.getSkill())){
								av.push(note);
							}
						}
						average.push(av);
					}
					
					
					request.setAttribute("average", average);
					request.setAttribute("skills", skills);
					
					// -- we get the missing of the student
					Missing[] missingGrid = dm.selectAll(Integer.toString(std.getId()));// -- we prepare the data format for the view
					if(missingGrid == null) missingGrid = new Missing[0];// -- He never skip class, he win an empty array
					
					p.setContent("student/student_body.jsp");
					p.setTitle("ISEP / APP - Etudiants");
					p.setCss("student.css");
					p.setJs("student.js");
					request.setAttribute("student", std);// -- we send the student
					request.setAttribute("missingGrid", missingGrid);// -- we send the his missing
					
					
				} else {
					
					// -- the pseudo isn't found
					p.setWarning(true);
					p.setWarningMessage("L'utilisateur que vous cherché n'a pas peu être trouvé "
							+ "ou ne fait pas parti des étudiants... page bientôt remplcé par une liste d'étudiant");
					p.setContent("home.jsp");
					
				}
				dmk.close();
				dm.close();
				du.close(); // -- we close the connection <-- THIS IS REALY IMPORTANT
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
