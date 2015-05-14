package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.model.Auth;

/**
 * Servlet implementation class AlterGroups
 */
@WebServlet("/AlterGroups")
public class AlterGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterGroups() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		
		if(Auth.isTutor(request) || Auth.isRespo(request)){
			p.setCss("bootstrap-select.min.css", "edit_group.css"); 
			p.setJs("bootstrap-select.min.js", "edit_group.js");
			p.setTitle("ISEP / APP - Edition de group");
			p.setContent("editor/edit_group.jsp");
			
			
			
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
