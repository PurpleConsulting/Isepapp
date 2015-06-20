package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMissings;
import org.purple.model.DaoUsers;


/**
 * Servlet implementation class RollCall
 */
@WebServlet("/RollCall")
public class RollCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PATTERN_PSEUDO = "pseudo_";
	private static final String PATTERN_DELAY = "delay_";
	private static final String PATTERN_SUPPORT = "support_";
	private static final String PATTERN_MISSING = "miss_";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RollCall() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void doRegular(){
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/**
		 * PARAMS
		 */
		
		if(Auth.isRespo(request) || Auth.isTutor(request)){
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			
			p.setContent("deadline/rollcall.jsp");
			p.setTitle("ISEP / APP - L'appel");
			p.setCss("rollcall.css");
			p.setJs("rollcall.js");
			
			User tutor = (User)request.getSession().getAttribute("user");
			Group[] groups = dg.selectGroupbyTutor(tutor);
			for(Group g : groups){
				dg.completeMemebers(g);
				dg.completeTutor(g);
			}
			
			request.setAttribute("groups", groups);
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("Cette page n'est accéssible que par les tuteurs d'APP.");
			
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		String daySkiped = request.getParameter("day-skiped");
		ArrayList<String> _params = Collections.list(request.getParameterNames());
		ArrayList<String> students = new ArrayList<String>();
		for (String _p : _params) {
			if (_p.indexOf(this.PATTERN_PSEUDO) != -1) {
				students.add(_p.substring(this.PATTERN_PSEUDO.length()));
				// -- Arraylist of de pseudo isep ldivad, zkaneswa ...
			}

		}
		
		if(Auth.isRespo(request) || Auth.isTutor(request)){
			
			Connection bddServletCo = Bdd.getCo();
			DaoMissings dm = new DaoMissings(bddServletCo);
			DaoGroups dg = new DaoGroups(bddServletCo);
			if(!Isep.nullOrEmpty(daySkiped)){
				boolean buisnessFlag = true;
				
				try{
					DateTime day = DateTime.parse(daySkiped, DateTimeFormat.forPattern("yyyy-MM-dd"));
					day = day.withZone(DateTimeZone.forID(Isep.LOCATION));
				} catch(IllegalArgumentException e){
					e.printStackTrace();
					buisnessFlag = false;
					p.setError(true);
					p.setErrorMessage("la date indiquée est incorrecte.");
				}
				if(buisnessFlag){
					for(String std : students){
						String support = request.getParameter(this.PATTERN_SUPPORT + std); 
						String delay = request.getParameter(this.PATTERN_DELAY + std);
						String missing = request.getParameter(this.PATTERN_MISSING + std); 
						System.out.print(std + "\n");
						System.out.print(support +" :  "+ delay +" :  "+ missing +" \n");
					}
				}
				
				
			} else {
				p.setWarning(true);
				p.setWarningMessage("votre requête n'a pas pu être correctement interprétée. Remplissez bien le champ date lié à l'absence.");
			}
			
			p.setContent("/deadline/rollcall.jsp");
			p.setCss("rollcall.css");
			p.setJs("rollcall.js");
			
			User tutor = (User)request.getSession().getAttribute("user");
			Group[] groups = dg.selectGroupbyTutor(tutor);
			for(Group g : groups){
				dg.completeMemebers(g);
				dg.completeTutor(g);
			}
			
			request.setAttribute("groups", groups);
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("seul les tuteurs sont autorisés à inquer les absences.");
			
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		}
		
	}

}
