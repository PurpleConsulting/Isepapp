package org.purple.controller;
/*** java import ***/
import java.io.IOException;



import java.util.ArrayList;
import java.util.List;



/*** servlet import ***/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






/*** Purple import ***/
import org.purple.model.Connection;
import org.purple.model.Page;
import org.purple.model.User;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Ajouter tous les CSS et JS qu'on a besoin pour les afficher dans template.jsp
		ArrayList <String> css = new ArrayList<String>();
		css.add("maincss1.css");
		css.add("maincss2.css");
		
		ArrayList <String> js = new ArrayList<String>();
		js.add("mainjs1.js");
		js.add("mainjs2.js");
		
		
		
		//On cree le constructeur page qui va contenir tous les pages
		Page p = new Page();	
		
		//On ajout le css
		
		p.setCss(css);
		p.setJs(js);
		request.setAttribute("pages", p);
		
		if(request.getParameter("id").equals("") ||  request.getParameter("password").equals("")){
			this.getServletContext().getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
			
		} else {
			String url = "";
			Connection c = new Connection();
			String pwd = request.getParameter("password");
			String id = request.getParameter("id");
			
			if(c.signIn(id, pwd)){
				url = "/template.jsp";
				User user = c.buildUser(id);
				request.getSession(true).setAttribute("user", user);
				
			} else {
				url = "/jsp/signin.jsp";
			}
		
			this.getServletContext().getRequestDispatcher(url).forward(request, response);
			
		}
		
		
	}

}
