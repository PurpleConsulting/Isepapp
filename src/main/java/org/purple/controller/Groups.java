package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMissings;


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
			p.setWarningMessage("La page sur laquelle vous tentez de vous rendre ne vous est pas accessible. "
					+ "Pour toute réclamation, prenez contact avec le responsable d'APP actuel.");
			p.setContent("home.jsp");
			
		} else {
			// -- We assume we are Admin or tutor or Respo, Now Does the student exist?
			// -- Which group is required
			String scope = request.getParameter("scope");
			if(scope == null || scope == "_all"){
				
				p.setContent("home.jsp");
			} else {
				
				DaoGroups dg = new DaoGroups(Bdd.getCo());
				DaoDeadline ddl = new DaoDeadline(Bdd.getCo());
				DaoMissings dm = new DaoMissings(Bdd.getCo());
				
				Group group = dg.select(scope);  
				if(group == null){
					
					// -- Incorrect data, the group is missing.
					p.setError(true);
					p.setErrorMessage("Le groupe demandé n'a pas été retrouvé dans la base.");
					p.setContent("home.jsp");
					request.setAttribute("pages", p);
					
				} else {
					
					dg.completeMemebers(group);
					
					// -- Retreve all missings
					Missing[] allmissings = dm.selectForGroup(group.getName());
					
					// -- Rereve all deadlines
					Deadline[] deadlines = ddl.selectByGroup(group.getName());
					
					
					p.setTitle("ISEP/APP - Group "+group.getName());
					p.setContent("users/group.jsp");
					p.setCss("group.css");
					p.setJs("group.js");
					
					request.setAttribute("pages", p);
					request.setAttribute("group", group);
					request.setAttribute("missings", allmissings);
					request.setAttribute("deadlines", deadlines);
				}
				
				ddl.close(); // -- close Deadline Dao
				dm.close();  // -- close Missings Dao
				dg.close();  // -- close Group Dao
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
