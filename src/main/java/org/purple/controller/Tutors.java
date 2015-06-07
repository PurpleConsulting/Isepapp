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
 * Servlet implementation class Tutors
 */
@WebServlet("/Tutors")
public class Tutors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tutors() {
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
		// -- param
		
		if(Auth.isRespo(request)){
			
			p.setContent("users/tutor.jsp");
			p.setTitle("ISEP / APP - Les tuteurs");
			p.setJs("bootstrap-select.min.js", "tutor.js");
			p.setCss("bootstrap-select.min.css", "tutor.css");
			
		} else {
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("la page demandé n'est accessible qu'au responsable d'APP");
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
