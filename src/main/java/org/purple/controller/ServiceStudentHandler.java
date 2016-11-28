package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Deadline;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMissings;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class ServiceStudentHandler
 */
@WebServlet("/ServiceStudentHandler")
public class ServiceStudentHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceStudentHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/**
		 * AJAX QUERY
		 */
		// -- quey for depot
		String deliveryParam = request.getParameter("depot-query");
		String deliverySudent = request.getParameter("depot-delivery");
		// -- query for missing
		String missingParam = request.getParameter("missing-query");
		String missingStudent = request.getParameter("missing-std");
		// -- query for  
		
		JSONObject result = new JSONObject();
		
		if(Auth.isStudent(request)){
		
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoMissings dm = new DaoMissings(bddServletCo);
			DaoDeadline ddl = new DaoDeadline(bddServletCo);

			if(!Isep.nullOrEmpty(missingParam, missingStudent)){
				JSONObject jsonMissings = new JSONObject();
				User std = du.select(missingStudent);
				ArrayList<JSONObject> empty =  new ArrayList<JSONObject>();
				Missing[] missingGrid = dm.selectForStudent(Integer.toString(std.getId()));
				if(missingGrid.length == 0 ) jsonMissings.put("miss",empty);
				for(Missing m : missingGrid){
					JSONObject missing = new JSONObject();
					missing.put("late", m.getLate());
					missing.put("supporting", m.getSupporting());
					missing.put("date", m.printDate());
					
					jsonMissings.append("miss", missing);
				}
				result.put("result", jsonMissings);
				
			} else if(!Isep.nullOrEmpty(deliveryParam, deliverySudent)){
				JSONObject depots = new JSONObject();
				User std = du.select(deliverySudent);
				ArrayList<JSONObject> empty =  new ArrayList<JSONObject>();
				du.addGroup(std);
				Deadline[] deadlines = ddl.selectByGroup(std.getGroup());
				if(deadlines.length == 0 ) depots.put("deadlines",empty);
				for(Deadline d : deadlines){
					ddl.addPathToFile(d);
					JSONObject jsonDead = new JSONObject();
					jsonDead.put("id", d.getId());
					jsonDead.put("cross", d.getCross());
					jsonDead.put("completed", d.getCompleted());
					jsonDead.put("deveryDate", d.printDeliveryDate());
					jsonDead.put("dateLimit", d.printDateLimit());
					jsonDead.put("status", d.getStatus());
					jsonDead.put("description", d.getDescription());
					jsonDead.put("path", d.getDeliveryPath());
					depots.append("deadlines", jsonDead);
				}
				
				result.put("result", depots);
				
			} else {
				result.put("error", true);
				result.put("erromMessage", "Requête non reconnue.");
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			result.put("error", true);
			result.put("erromMessage", "l'utilisateur n'est pas connecté.");
		}
		response.setHeader("content-type", "application/json");
		response.getWriter().write(result.toString());
	}

}
