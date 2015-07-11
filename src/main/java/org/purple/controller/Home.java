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
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
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
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		
		if(Auth.isConnect(request)){
			
			Connection bddServletCo = Bdd.getCo();
			DaoUsers du = new DaoUsers(bddServletCo);
			HttpSession s = request.getSession();
			
			if(Auth.isRespo(request)){
				User[] teachers = du.selectAllTutor();
				request.setAttribute("teachers", teachers); // à rajouter dans le JS !!!
			} else if(Auth.isTutor(request)){
				
			} else if(Auth.isAdmin(request)){
				
			} else if(Auth.isStudent(request)){
				
			}
			
			Isep.bagPackHome(p, s);
			p.setContent("home/common.jsp");
			p.setTitle("ISEP / APP - Accueil");
			
			request.setAttribute("pages", p);
			request.getRequestDispatcher("/template.jsp").forward(request, response);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			response.sendRedirect("/Isepapp/Signin");
		}
		
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("/Isepapp/Home");
	}

}
