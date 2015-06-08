package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Dao;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;


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
			
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			
			
			p.setContent("users/tutor.jsp");
			p.setTitle("ISEP / APP - Les tuteurs");
			p.setJs("bootstrap-select.min.js", "bootbox.min.js", "tutor.js");
			p.setCss("bootstrap-select.min.css", "tutor.css");
			
			// -- we get the tutors
			User[] tutors = du.selectAllTutor();

			// -- we get the groups corresponding
			HashMap groups = new HashMap(); 
			for(User t : tutors){
				groups.put(t.getPseudo(),dg.selectAllName(Integer.toString(t.getId())));
			}
			// -- we get all class for the adding tutors form
			String[] allClass = dg.selectAllClass();

			// -- fill the request
			request.setAttribute("tutors", tutors);
			request.setAttribute("groups", groups);
			request.setAttribute("allClass", allClass);
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
