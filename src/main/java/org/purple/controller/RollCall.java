package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;


/**
 * Servlet implementation class RollCall
 */
@WebServlet("/RollCall")
public class RollCall extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RollCall() {
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
	}

}
