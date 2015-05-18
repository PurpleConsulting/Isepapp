package org.purple.controller;

/*** java import ***/
import java.io.IOException;




/*** servlet import ***/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
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
		Page p = new Page();
		p.setContent("signin.jsp");
		p.setTitle("ISEP / APP - Connection");
		request.setAttribute("pages", p);
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

		if (request.getParameter("Ajaxpseudo") != null){
			Boolean res = false;
			DaoUsers u = new DaoUsers(Bdd.getCo());
			String param = request.getParameter("Ajaxpseudo");
			if(u.find(param)) res = true;
			
			response.setHeader("content-type", "application/json");
			JSONObject result = new JSONObject();
			JSONObject js = new JSONObject();
			js.put("find", res.toString());
			result.put("result", js);
			response.getWriter().write(result.toString());
			
		} else if (request.getParameter("pseudo") == null){
			Page p = new Page();
			p.setWarning(true);
			p.setWarningMessage("Vos identifiants n'ont pas été correctement récupérés. Veuillez vous connecter à nouveau.");
			p.setTitle("ISEP / APP - Connection");
			this.getServletContext().getRequestDispatcher("/jsp/signin.jsp")
					.forward(request, response);

		} else {
			String url = "";
			DaoUsers u = new DaoUsers(Bdd.getCo());
			String pseudo = request.getParameter("pseudo");
			User user = u.select(pseudo);
			
			if(user.getId() !=  0){
				Page p = new Page();
				p.setTitle("ISEP / APP - Home");
				p.setContent("home.jsp");
				url = "/template.jsp";
				request.getSession(true).setAttribute("user", user);
				request.setAttribute("pages", p);
			} else {
				Page p = new Page();
				p.setTitle("ISEP / APP - Connection");
				p.setError(true);
				p.setErrorMessage("Un problème est survenu lors de l'établissement de la connection. "
						+ "Pour toute récupération de mot de passe veuillez vous rapprocher de l'administration de l'ISEP.");
				
				url = "/jsp/signin.jsp";
				request.setAttribute("pages", p);
			}
	
			u.close();
			
			this.getServletContext().getRequestDispatcher(url)
					.forward(request, response);
		}
	}
}
