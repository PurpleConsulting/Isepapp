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
import org.purple.bean.Mark;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.AvgBuilder;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoMissings;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class Promo
 */
@WebServlet("/Promo")
public class Promo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Promo() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doLoad(HttpServletRequest request, DaoGroups dg, DaoMarks dmk, DaoMissings dm){
    	// --------------------------------------------------------------------------------
    	// -- GET THE Class AND ALL GROUPS
    	// --------------------------------------------------------------------------------
		
    	String[] allClass = dg.selectAllClass();

		HashMap<String, ArrayList<Group>> prom = new HashMap<String, ArrayList<Group>>();
		HashMap<String, ArrayList<Mark>> marks = new HashMap<String, ArrayList<Mark>>();
		
		for(String c : allClass){
			ArrayList<Group> team = dg.selectGroupbyClass(c);
			for(Group t : team){ 
				dg.completeMemebers(t);
				dg.completeTutor(t);
			}
			prom.put(c, team);
		}
		
		// --------------------------------------------------------------------------------
		// -- GET THE MARKS
		// --------------------------------------------------------------------------------
		
		ArrayList<Group> groups = dg.selectAll();
		for(Group g : groups){
			// -- Fill the groups
			dg.completeMemebers(g);
			
			// -- Find average of each group
			marks.put(g.getName(), dmk.selectByGroup(g.getName()));
		}
		
		
		double valMax = DaoValues.fetchMax();
		Average avg = AvgBuilder.promAverage(marks, groups, valMax);
		
		// --------------------------------------------------------------------------------
		// -- GET THE MISSINGS
		// --------------------------------------------------------------------------------
				
		HashMap<String, Missing[]> grpMissings = new HashMap<String, Missing[]>();
		for(Group g : groups){
			grpMissings.put(g.getName(), dm.selectForGroup(g.getName()));
		}
		
		request.setAttribute("allClass", allClass);
		request.setAttribute("avg", avg);
		request.setAttribute("prom",prom);
		request.setAttribute("missings", grpMissings);
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		
		if(!Auth.isConnect(request)){
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("il semble que vous ayez un problème d'authentification. "
					+ "Essayez de vous reconnecter.");
		} else if(Auth.isStudent(request)){
			// --  the user is a student
			
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("la page que vous essayez d'atteindre n'est pas accessible aux étudiants.");
			

		} else {
			// -- the user acces to the database
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoMissings dm = new DaoMissings(bddServletCo);
			DaoMarks dmk = new DaoMarks(bddServletCo);
			
			p.setCss("promo.css");
			p.setContent("users/promo.jsp");
			p.setTitle("ISEP / APP - Promotion");
			p.setJs("chartjs/Chart.min.js", "promo.js");
			
			this.doLoad(request, dg, dmk, dm);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		JSONObject result = new JSONObject();
		Page p = new Page();
		
		/*$$$ PARAMS $$$*/
		String cls = request.getParameter("clsToCall");
		String grp = request.getParameter("...");
		
		//////////////////// OPS \\\\\\\\\\\\\\\\\\\\
		
		if(!Auth.isStudent(request)){
			
			Connection bddServletCo = Bdd.getCo();
			DaoMissings dm = new DaoMissings(bddServletCo);
			DaoGroups dg = new DaoGroups(bddServletCo);
			
			if(!Isep.nullOrEmpty(cls)){
				
				JSONObject clsMissing = new JSONObject();
				clsMissing.put("classSupport", cls);
				ArrayList<Group> groups = dg.selectGroupbyClass(cls);
				
				for(Group g : groups){
					JSONObject grpMissing = new JSONObject();
					grpMissing.put("group", g.getName());
					grpMissing.put("miss", dm.selectForGroup(g.getName()).length);
					
					clsMissing.append("class", grpMissing);
				}
				

				result.put("err", false);
				result.put("result", clsMissing);
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
				
			} else {
				
				System.out.print(cls);
				result.put("err", true);
				result.put("Error message", "Bad request.");
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
				
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
