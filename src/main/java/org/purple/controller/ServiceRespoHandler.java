package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Group;
import org.purple.bean.Mark;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.Avg;
import org.purple.model.AvgBuilder;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class ServiceRespoHandler
 */
@WebServlet("/ServiceRespoHandler")
public class ServiceRespoHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceRespoHandler() {
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
		/** AJAX PARAMS **/
		String markPromParam = request.getParameter("mark-prom");
		String markGroupParam = request.getParameter("mark-group");
		
		////////////////////// OPS \\\\\\\\\\\\\\\\\\\\\\
		
		JSONObject result = new JSONObject();
		
		if(Auth.isRespo(request)){
			
			// -- Database access
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoMarks dm = new DaoMarks(bddServletCo);
			
			if(!Isep.nullOrEmpty(markPromParam)){
				// --------------------------------------------------------------------------------
				// -- JSON SOURCE - PROM RESULT TO BARCHART 
				// --------------------------------------------------------------------------------
				JSONObject prom = new JSONObject();
				ArrayList<Group> allGroups = dg.selectAll();
				HashMap<String, ArrayList<Mark>> marks = new HashMap<String, ArrayList<Mark>>();
				for(Group group : allGroups){ dg.completeMemebers(group); }
				for(String group : dg.allGroups()){ marks.put(group, dm.selectByGroup(group)); }
				
				Average promAverage = AvgBuilder.promAverage(marks, allGroups, DaoValues.fetchMax());

				for(Avg classAverage : promAverage.getGrid()){
					JSONObject _class = new JSONObject();
					prom.put(classAverage.getTitle(), classAverage.compute());
					
				}
				
				result.put("result", new JSONObject().put("prom", prom));
				
			}else if(!Isep.nullOrEmpty(markGroupParam)){	
				// --------------------------------------------------------------------------------
				// -- JSON SOURCE - GROUP RESULT TO LABEL 
				// --------------------------------------------------------------------------------
				ArrayList<JSONObject> groups = new ArrayList<JSONObject>();
				JSONObject group = new JSONObject();
				
				ArrayList<Group> allGrp = dg.selectGroupbyClass(markGroupParam);
				
				for(Group g : allGrp){
					
					dg.completeMemebers(g);
					ArrayList<Mark> marks = dm.selectByGroup(g.getName());
					Average avg = AvgBuilder.groupAverage(marks, g, DaoValues.fetchMax());
					
					group = new JSONObject();
					group.put("name", avg.getTitle());
					group.put("mark", avg.compute());
					groups.add(group);
				}
				
				result.put("result", new JSONObject().put("groups", groups));
				
				
			} else {
				
				// -- inproper request -- //
				result.put("err", true);
				result.put("err-message", "Requête incorrecte.");
				
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			// -- non-repo user -- //
			result.put("err", true);
			result.put("err-message", "L'utilisateur n'est pas responsable d'APP.");
			
		}
		
		response.setHeader("content-type", "application/json");
		response.getWriter().write(result.toString());
	}

}
