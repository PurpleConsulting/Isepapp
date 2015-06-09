package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Group;
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
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		
		/**
		 * AJAX POST REQUEST PARAMS
		 */
		// --  fetch the group for the insert tutor
		String classParam = request.getParameter("class_name");
		
		/**
		 * POST REQUEST PARAMS
		 */
		// -- add tutor request
		String newFirstName = request.getParameter("new_first_name");
		String newLastName = request.getParameter("new_last_name");
		String newEmail = request.getParameter("new_email");
		String newClass = request.getParameter("new_class");
		String newPass = request.getParameter("new_password");
		
		
		if(Auth.isRespo(request)){
			/*  the use have access to the data base */
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			
			if(!Isep.nullOrEmpty(classParam)){
				/**
				 * HERE THE USER ASK FOR SOME GROUP FOR THE NEW TUTOR
				 */

				Group[] groups = dg.selectGroupbyClass(classParam);
				JSONObject result = new JSONObject();
				ArrayList<JSONObject> jsGrooups = new ArrayList<JSONObject>();
				for(Group g : groups){
					JSONObject current = new JSONObject();
					current.put("id", g.getId());
					current.put("name", g.getName());
					jsGrooups.add(current);
				}
				result.put("groups", jsGrooups);
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(new JSONObject().put("result", result).toString());
				
			} else if(!Isep.nullOrEmpty(newFirstName, newLastName, newEmail, newClass)){
				
				
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {
			p.setWarning(true);
			p.setWarningMessage("cette page est accécible uniquement au responsable d'APP.");
			Isep.bagPackHome(p, request.getSession());
			
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			
		}
	}

}
