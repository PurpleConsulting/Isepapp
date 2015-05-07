package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.DaoGroups;

/**
 * Servlet implementation class Skills
 */
@WebServlet("/Controls")
public class Controls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		
		//p.setCss(".css");
		p.setJs("fill_performances.js");
		p.setContent("/views-performances/fill_performances.jsp");
		request.setAttribute("pages", p);
		
		//Create instance DaoGroups
		DaoGroups dgp = new DaoGroups(Bdd.getCo());
		
		//Display group name
		HttpSession s= request.getSession();
		User u = (User)s.getAttribute("user");

		String[] gp_name = dgp.selectAllName(u.getPseudo());
		
		request.setAttribute("group_names", gp_name);
		
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
