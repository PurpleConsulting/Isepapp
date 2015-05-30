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

import org.purple.bean.Deadline;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class DeadlinesTuteur
 */
@WebServlet("/DeadlinesTuteur")
public class DeadlinesTuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeadlinesTuteur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page p = new Page();
		if(Auth.isTutor(request)){
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoDeadline dl = new DaoDeadline(bddServletCo);
			DaoUsers u = new DaoUsers(bddServletCo);
			
			p.setCss("deadline.css");
			p.setJs("deadline.js");
			p.setContent("/deadline/deadlineTuteur.jsp");
			p.setTitle("ISEP / APP - Deadline");
			request.setAttribute("pages", p);
			
			HttpSession session = request.getSession();
			User userSession = (User)session.getAttribute("user");
			request.setAttribute("usession", userSession);
	
			
			// -- get all the groups
			String[] groups = dg.selectAllClass();
			request.setAttribute("groups", groups);
			
			// -- get all the tutors
			User[] user=u.selectTutorbyGroup();
			request.setAttribute("user", user);
			
			// get all the deadlines
			Deadline[] deadline = dl.selectAllDeadlines();
			request.setAttribute("deadline", deadline);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		this.getServletContext().getRequestDispatcher("/template.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
