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
 * Servlet implementation class Promo
 */
@WebServlet("/Promo")
public class Promo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Promo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		
		if(!Auth.isRespo(request) && !Auth.isTutor(request) && !Auth.isAdmin(request)){
			// --  the user is a student
			
			p.setContent("home.jsp");
			p.setTitle("ISEP / APP - Home");
			p.setWarning(true);
			p.setWarningMessage("La page que vous essayez d'atteindre n'est pas accécible aux étudiants");
			
			
			
		} else {
			// -- the user acces to the prom page
			p.setCss("promo.css"); p.setJs("promo.js");
			p.setContent("users/promo.jsp");
			p.setTitle("ISEP / APP - Pomotion");
			
			DaoGroups dg = new DaoGroups(Bdd.getCo());
			
			// -- get all the groups
			Group[] groups = dg.selectAll();
			String[] allClass = {"G5","G6","G7","G8"};
			for(Group g : groups){
				dg.completeTutor(g);
				dg.completeMemebers(g);
			}
			request.setAttribute("groups", groups);
			request.setAttribute("allClass", allClass);
			
			dg.close();
		}
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
