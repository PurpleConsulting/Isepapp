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
import org.purple.bean.Calendar;
import org.purple.bean.Group;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoCalendars;
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

    
    protected void doLoad(HttpServletRequest request, User tutor, ArrayList<String> allDates, DaoGroups dg, DaoCalendars dc){
    	// --------------------------------------------------------------------------------
    	// -- FINDING THE GROUPS 
    	// --------------------------------------------------------------------------------
    	Group[] groups = dg.selectGroupbyTutor(tutor);
    	
    	// --------------------------------------------------------------------------------
    	// -- FINDING THE DATES 
    	// --------------------------------------------------------------------------------
    	for(Group g : groups){
			dg.completeMemebers(g);
			dg.completeTutor(g);
			
			Calendar c = dc.selectAllDate(Integer.toString(g.getId()));
			for(String date : c.getDateList()){
				if(!allDates.contains(date)) allDates.add(date);
			}
			
			
		}
    	
    	request.setAttribute("scopeDates", allDates);
		request.setAttribute("groups", groups);
		
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/*$$$ PARAMS $$$*/
		
		if(Auth.isRespo(request) || Auth.isTutor(request)){
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoCalendars dc = new DaoCalendars(bddServletCo);
			
			User tutor = (User)request.getSession().getAttribute("user");
			
			ArrayList<String> allDates = new ArrayList<String>();
			
			this.doLoad(request, tutor, allDates, dg, dc);
			
			p.setContent("deadline/rollcall.jsp");
			p.setTitle("ISEP / APP - L'appel");
			p.setCss("rollcall.css");
			p.setJs("rollcall.js");
			
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
			DaoCalendars dc =new DaoCalendars(bddServletCo);
			
			if(!Isep.nullOrEmpty(daySkiped)){
				boolean buisnessFlag = true;
				DateTime day = new DateTime();
				try{
					day = DateTime.parse(daySkiped, DateTimeFormat.forPattern("yyyy-MM-dd"));
					day = day.withZone(DateTimeZone.forID(Isep.LOCATION));
				} catch(IllegalArgumentException e){
					e.printStackTrace();
					buisnessFlag = false;
					p.setError(true);
					p.setErrorMessage("la date indiquée est incorrecte.");
				}
				if(buisnessFlag){
					ArrayList<Missing> allMissings = new ArrayList<Missing>();
					for(String std : students){
						boolean late = false;
						String support = request.getParameter(this.PATTERN_SUPPORT + std); 
						String delay = request.getParameter(this.PATTERN_DELAY + std);
						String missing = request.getParameter(this.PATTERN_MISSING + std);
						if(!Isep.nullOrEmpty(delay)){
							try{
								day = DateTime.parse(daySkiped+" "+delay+":00" , DateTimeFormat.forPattern(Isep.JODA_UTC));
								late = true;
							} catch(IllegalArgumentException e){
								buisnessFlag = false;
								late = false;
							}
						}
						Missing m = new Missing(std, support, day, late);
						allMissings.add(m);
					}
					boolean sqlFlag = true;
					if(buisnessFlag){
						for(Missing am : allMissings){
							sqlFlag = sqlFlag & dm.create(am);
						}
					}
					if(buisnessFlag && sqlFlag){
						p.setSuccess(true);
						p.setSuccessMessage("l'enssemble des absences à été inséré.");
					} else {
						p.setWarning(true);
						p.setWarningMessage("une erreur est survenue.");
					}
					
				}			
				
			} else {
				p.setWarning(true);
				p.setWarningMessage("votre requête n'a pas pu être correctement interprétée. Remplissez bien le champ date lié à l'absence.");
			}
			
			User tutor = (User)request.getSession().getAttribute("user");
			
			ArrayList<String> allDates = new ArrayList<String>();
			
			this.doLoad(request, tutor, allDates, dg, dc);
			
			p.setContent("/deadline/rollcall.jsp");
			p.setCss("rollcall.css");
			p.setJs("rollcall.js");
			p.setTitle("ISEP / APP - L'appel");
			
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
