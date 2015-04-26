package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;

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
				
			} else {
				
				DaoGroups dg = new DaoGroups(Bdd.getCo());
				Group group = dg.select(scope);  
				if(group == null){
					// -- Incorrect data, the group is missing.
					p.setWarning(true);
					p.setWarningMessage("Ce groupe n'exite pas... page bientôt remplcé par une liste de groupe");
					p.setContent("home.jsp");
					
					request.setAttribute("pages", p);
				} else {
					dg.completeMemebers(group);
					p.setContent("group/group_body.jsp");
					p.setCss("group.css");
					
					request.setAttribute("pages", p);
					request.setAttribute("group", group);
				}
				
				dg.close();
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
