package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.constant.Isep;
import org.purple.model.Auth;

/**
 * Servlet implementation class Exemple
 */
@WebServlet("/Exemple")
public class Exemple extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exemple() {
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
		String paramA = request.getParameter("param-a");
		
		String paramB = request.getParameter("param-a");
		String paramC = request.getParameter("param-a");
		
		String paramD = request.getParameter("param-a");
		String paramE = request.getParameter("param-a");
		String paramF = request.getParameter("param-a");
		
		
		if(Auth.isRespo(request)){
			
			// -- Création de la connection et de la base de donnée
			
			if(!Isep.nullOrEmpty(paramA)){
				// -- actions
			} else if (!Isep.nullOrEmpty(paramB, paramC)){
				// -- actions
			} else if(!Isep.nullOrEmpty(paramD, paramE, paramF)){
				// -- actions
			} else {
				// -- nous avons pas compris votre reqête.
			}
			
			// -- redirection
	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
