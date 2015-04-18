package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.model.Auth;

/**
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(!Auth.isConnect(request)){ request.getRequestDispatcher("/jsp/signin.jsp").forward(request, response);
		
		} else {
			Page p = new Page();
			String student = request.getParameter("pseudo");
			if(student != null){
				p.setContent("student/student_body.jsp");
				p.setTitle("ISEP / APP - Etudiants");
				p.setCss("student.css");
				p.setJs("student.js");
			} else {
				p.setContent("student/student_body.jsp");
				p.setTitle("ISEP / APP - Home");
				p.setCss("student.css");
				p.setJs("student.js");
				request.setAttribute("pages", p);
			}
			request.setAttribute("pages", p);
			request.getRequestDispatcher("/template.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
