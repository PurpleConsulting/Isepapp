package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoSkills;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class PersoControls
 */
@WebServlet("/PersoControls")
public class PersoControls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersoControls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		if(Auth.isTutor(request) || Auth.isRespo(request)){
			
			//Create instance Dao
			Connection bddServletCo = Bdd.getCo();
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			
			//Display student info to know who we are giving individual marks 
			String pseudo = request.getParameter("pseudo");
			User u = du.select(pseudo);
			
			//If student exists
			if (u.getId() != 0){
				
				request.setAttribute("student", u);
				
				//Display skills in tab
				Skill[] skills = ds.selectAllSkills();
				if (skills != null){
					for (int i=0;i<=skills.length-1;i++){
						ds.completeSub_skills(skills[i]); //Add sub_skills into skills
					}
				}
					
				request.setAttribute("skills", skills);
				
				//Display values in radio btn
				Value[] v = dv.selectAllValues();
				request.setAttribute("values", v);
				
				p.setJs("bootbox.min.js", "bootstrap-select.min.js","perso_controls.js");
				p.setCss("bootstrap-select.min.css");
				p.setTitle("ISEP / APP - Evaluations");
				p.setContent("mark/perso_controls.jsp");
				request.setAttribute("pages", p);
			
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
				//Close Dao
				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				p.setTitle("ISEP / APP - Home");
				p.setContent("home/student.jsp"); //A revoir
				p.setWarning(true);
				p.setWarningMessage("L'élève que vous souhaitez noter n'existe pas. Veuillez consulter la liste des groupes pour trouver un élève existant.");
				request.setAttribute("pages", p);
				
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
				//Close Dao
				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} else {
			p.setTitle("ISEP / APP - Home");
			p.setContent("home/student.jsp");
			p.setWarning(true);
			p.setWarningMessage("La page que vous essayez d'atteindre n'est accessible que par les tuteurs d'APP.");
			request.setAttribute("pages", p);
			
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
