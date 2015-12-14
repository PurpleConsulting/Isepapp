package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.purple.model.DaoUsers;

/**
 * THIS SERVLET GIVE ACCESS TO THE SIGNIN PAGE, PROVIDE A CHECK OF YOUR LOGIN IN AJAX
 * ADD AN USER OBJECT TO THE SESSION AFTER A SUCESS FULL AUTHETICATION
 * 
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		p.setTitle("ISEP / APP - Connexion");
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/*$$$ AJAX PARAMS $$$*/
		String ajPseudo = request.getParameter("Ajaxpseudo"); // -- trigged by the blur input
		
		/*$$$ FORM PARAMS $$$*/
		String pseudo = "apprespo";//request.getParameter("pseudo");
		String pwd = "apprespo"; //request.getParameter("password");
		
		////////////////////// OPS \\\\\\\\\\\\\\\\\\\\\\
		
		Connection bddServletCo = Bdd.getCo();
		DaoUsers du = new DaoUsers(bddServletCo);
		
		if (!Isep.nullOrEmpty(ajPseudo)){
			
			// -- Check Login helper -- //
			Boolean res = false;
			if(du.find(ajPseudo)) res = true;
			response.setHeader("content-type", "application/json");
			JSONObject result = new JSONObject();
			JSONObject js = new JSONObject();
			js.put("find", res.toString());
			result.put("result", js);
			response.getWriter().write(result.toString());

		} else if (Isep.nullOrEmpty(pseudo, pwd)){
			
			// -- BAD attempt for sign in -- //
			p.setWarning(true);
			p.setWarningMessage("vos identifiants n'ont pas été correctement récupérés. Veuillez vous connecter à nouveau.");
			p.setTitle("ISEP / APP - Connexion");
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);

		} else {
			// -- GOOD attempt for sign in -- //
			User user = du.select(pseudo);
			user.setPassword(pwd);
			user = du.comparePwd(user);
			if(user.getId() !=  0){
				
				// -- Identification Success
				request.getSession(true).setAttribute("user", user);
				response.sendRedirect("/Isepapp/Home");
				
			} else {
				
				// -- Identification Failure
				p.setTitle("ISEP / APP - Connexion");
				p.setError(true);
				p.setErrorMessage("un problème est survenu lors de l'établissement de la connexion. "
						+ "Pour toute récupération de mot de passe, veuillez vous rapprocher de l'administration de l'ISEP.");
				
				request.setAttribute("pages", p);
				this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
		
			}
		}
	
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
