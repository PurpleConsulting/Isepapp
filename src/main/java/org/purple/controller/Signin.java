package org.purple.controller;

/*** java import ***/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



/*** servlet import ***/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
/*** Purple import ***/
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class Signin
 */
@WebServlet("/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/jsp/signin.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// On cree le constructeur page qui va contenir tous les pages
		Page p = new Page();
		// On ajout le css
		p.setCss("maincss1.css", "maincss2.css");
		p.setJs("mainjs1.js", "mainjs2.js");
		p.setContent("testContent.jsp");
		request.setAttribute("pages", p);
		

		if (request.getParameter("Ajaxpseudo") != null){
			Boolean res = false;
			DaoUsers u = new DaoUsers(Bdd.getCo());
			String param = request.getParameter("Ajaxpseudo");
			if(u.find(param)) res = true;
			response.setHeader("content-type", "application/json");
			response.getWriter().write("{\"result\": {\"find\": \"" + res.toString() +"\" }}");
			
		} else if (request.getParameter("pseudo") == null){
			this.getServletContext().getRequestDispatcher("/jsp/signin.jsp")
					.forward(request, response);

		} else {
			String url = "";
			DaoUsers u = new DaoUsers(Bdd.getCo());
			String pseudo = request.getParameter("pseudo");
			User user = u.select(pseudo);
			if(user != null){
				url = "/template.jsp";
				request.getSession(true).setAttribute("user", user);

			} else {
				url = "/jsp/signin.jsp";
			}
	
			u.close();
			this.getServletContext().getRequestDispatcher(url)
					.forward(request, response);
		}

	}

}
