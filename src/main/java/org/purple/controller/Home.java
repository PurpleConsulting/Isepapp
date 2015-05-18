package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		
		if(Auth.isConnect(request)){
			
			DaoUsers du = new DaoUsers(Bdd.getCo());
			
			if(Auth.isRespo(request)){
				User[] teachers = du.selectAllTutor();
				
				request.setAttribute("teachers", teachers);
				p.setJs("bootstrap-select.min.js","home_reso.js"); 
				p.setCss("bootstrap-select.min.css","home_respo.css"); p.setContent("home/respo.jsp");
				p.setTitle("ISEP / APP - Accueil");
			} else if(Auth.isTutor(request)){
				
			} else if(Auth.isAdmin(request)){
				
			} else if(Auth.isStudent(request)){
				
			}
			
			du.close();
		} else {
			
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
