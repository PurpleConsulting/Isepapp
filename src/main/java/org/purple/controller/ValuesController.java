package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.bean.Values;
import org.purple.constant.Bdd;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class ValuesController
 */
@WebServlet("/ValuesController")
public class ValuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValuesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Page p = new Page();
		// On ajout le css
		p.setCss("marks.css");
		p.setJs("marks.js");
		p.setContent("/mark/values_level.jsp");
		request.setAttribute("pages", p);
		
		DaoValues v = new DaoValues(Bdd.getCo());
	
		//Afficher les values
		Values[] value= v.selectAllValues();
		request.setAttribute("valeur", value);
		int i= value.length;//a essayer
		request.setAttribute("i", i);
		
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
