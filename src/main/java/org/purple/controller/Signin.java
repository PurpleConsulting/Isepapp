package org.purple.controller;

/*** java import ***/
import java.io.IOException;




import java.sql.Connection;
import java.sql.SQLException;



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
import org.purple.constant.Isep;
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
		this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * AJAX HANDLER PART
		 */
		// -- trigged by the blur input
		String ajPseudo = request.getParameter("Ajaxpseudo");
		
		/**
		 * REGULAR POST PART
		 */
		
		String pseudo = request.getParameter("pseudo");
		String pwd = request.getParameter("password");
		/* OPS */
		
		/**
		 * AJAX HANDLER PART
		 */
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		Connection bddServletCo = Bdd.getCo();
		DaoUsers du = new DaoUsers(bddServletCo);
		
		if (!Isep.nullOrEmpty(ajPseudo)){
			
			Boolean res = false;
			
			if(du.find(ajPseudo)) res = true;
			response.setHeader("content-type", "application/json");
			JSONObject result = new JSONObject();
			JSONObject js = new JSONObject();
			js.put("find", res.toString());
			result.put("result", js);
			response.getWriter().write(result.toString());

		} else if (Isep.nullOrEmpty(pseudo, pwd)){
			p.setWarning(true);
			p.setWarningMessage("Vos identifiants n'ont pas été correctement récupérés. Veuillez vous connecter à nouveau.");
			p.setTitle("ISEP / APP - Connection");
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);

		} else {
			String url = "";
			User user = du.select(pseudo);
			user.setPassword(pwd);
			user = du.comparePwd(user);
			if(user.getId() !=  0){
				
				p.setTitle("ISEP / APP - Home");
				p.setContent("home.jsp");
				url = "/template.jsp";
				request.getSession(true).setAttribute("user", user);
				request.setAttribute("pages", p);
				response.sendRedirect("/Isepapp/Home");
			} else {
				p.setTitle("ISEP / APP - Connection");
				p.setError(true);
				p.setErrorMessage("Un problème est survenu lors de l'établissement de la connection. "
						+ "Pour toute récupération de mot de passe veuillez vous rapprocher de l'administration de l'ISEP.");
				
				url = "/jsp/signin.jsp";
				request.setAttribute("pages", p);
				this.getServletContext().getRequestDispatcher(url).forward(request, response);
			}
	
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
