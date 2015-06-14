package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;

/**
 * Servlet implementation class Calendars
 */
@WebServlet("/Calendars")
public class Calendars extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		Page p=new Page();
		p.setContent("/calendar/calendar.jsp");
		p.setCss("fullcalendar.css","fullcalendar.print.css","calendar.css");
		p.setJs("calendarFile/moment.min.js","calendarFile/jquery.min.js","calendarFile/fullcalendar.min.js","calendarFile/lang-all.js","calendar.js");
		p.setTitle("ISEP / APP - Calendar");

		
		request.setAttribute("pages", p);
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
