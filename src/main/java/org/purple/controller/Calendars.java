package org.purple.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Calendar;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoCalendars;
import org.purple.model.DaoGroups;

/**
 * Servlet implementation class Calendars
 */
@WebServlet("/Calendars")
public class Calendars extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String skillValueDelimiter = "&";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calendars() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection bddServletCo = Bdd.getCo();
		DaoGroups dg = new DaoGroups(bddServletCo);
		
		Page p = new Page();
		p.setContent("/calendar/calendar.jsp");
		p.setCss("fullcalendar.css","calendar.css");
		p.setJs("calendarFile/moment.min.js","calendarFile/fullcalendar.min.js", "calendarFile/lang-all.js", "calendar.js");
		p.setTitle("ISEP / APP - Calendar");
		
		// -- get all the groups
		String[] groups = dg.selectAllClass();
		request.setAttribute("groups", groups);
		
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection bddServletCo = Bdd.getCo();
		DaoCalendars dc = new DaoCalendars(bddServletCo);
		DaoGroups dg = new DaoGroups(bddServletCo);
		
		String dates = request.getParameter("dates");
		String group = request.getParameter("group");
		String groupClass = request.getParameter("groupClass");
		
		if(Auth.isRespo(request)){
			JSONObject result = new JSONObject();
			if(!Isep.nullOrEmpty(group, dates)){
				String[] date = dates.split(this.skillValueDelimiter);
				
				Calendar cal= new Calendar();
				
				Group[] gr = dg.selectGroupbyClass(group);
			
				 for(int j=0; j < gr.length; j++){
					 //Selectionner calendrier d'un group
					 cal= dc.select(Integer.toString(gr[j].getId()));
					
					 dc.deleteDateGroup(Integer.toString(cal.getId_calendar()));
						
					 for(int i=0; i<date.length; i++){
							//Les dates sélectionner
							cal.getDateList().add(date[i]);
						}
					dc.createDate(cal);
				 }
			}
			else if(!Isep.nullOrEmpty(groupClass)){
				Calendar c= new Calendar();
				Group[] gr = dg.selectGroupbyClass(groupClass);
				c = dc.selectAllDate(Integer.toString(gr[0].getId()));
				
				JSONObject jsonCalendar = new JSONObject();
				
				jsonCalendar.put("class", groupClass);
				jsonCalendar.put("dates", c.getDateList());
				result.put("result", jsonCalendar);
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
				
			}
			else{
			}
				//Mettre message erreur
			}
		
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		 
	
	}

