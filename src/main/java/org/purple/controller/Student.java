package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoMissing;
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
				User s = du.select(student);
				
				if(s != null && s.getPosition().equals("student")){
					
					// -- we get he/she from the data base
					du.addGroup(s); du.addTelMail(s);// -- we retrive his group, his tel, and his mail
					
					// -- we get the missing of the student
					DaoMissing dm = new DaoMissing(Bdd.getCo());
					Missing[] missings = dm.selectAll(Integer.toString(s.getId()));// -- we prepare the data format for the view
					int tabnum = (int)Math.ceil(missings.length / 3.0);
					Missing[][] missingGrid = new Missing[3][tabnum];
					int i = 0, j = 0;
					for(Missing m : missings){
						missingGrid[i][j] = m;
						j++;
						if(j % 3 == 0){
							j = 0; i = i + 1;
						}
					}
					
					p.setContent("student/student_body.jsp");
					p.setTitle("ISEP / APP - Etudiants");
					p.setCss("student.css");
					p.setJs("student.js");
					request.setAttribute("student", s);// -- we send the student
					request.setAttribute("missingGrid", missingGrid);// -- we send the his missing
					request.setAttribute("missingCount", missings.length);
					
					
				} else {
					
					// -- the pseudo isn't found
					p.setWarning(true);
					p.setWarningMessage("L'utilisateur que vous cherché n'a pas peu être trouvé "
							+ "ou ne fait pas parti des étudiants... page bientôt remplcé par une liste d'étudiant");
					p.setContent("home.jsp");
					
				}
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
