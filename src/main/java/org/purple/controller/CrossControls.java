package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoSkills;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class Crossmark
 */
@WebServlet("/CrossControls")
public class CrossControls extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrossControls() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		Connection bddServletCo = Bdd.getCo();
		
		if(Auth.isStudent(request)){
			/**
			 * HERE THE USER IS A STUDENT
			 */
			DaoGroups dgp = new DaoGroups(bddServletCo);
			DaoUsers dusr = new DaoUsers(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			DaoDeadline dl = new DaoDeadline(bddServletCo);
			
			// -- We get the User 
			HttpSession s = request.getSession();
			User u = (User)s.getAttribute("user");
			dusr.addGroup(u);
			String grp = u.getGroup();
			
			Deadline deadline = dl.fetchCrossDeadline(grp);
			
			if(deadline.getStatus()){
				/**
				 *  HERE THE CROSS MARK SESSION IS OPEN
				 */
				// -- We get the group
				Group g = dgp.select(grp);
				dgp.completeMemebers(g);
		
				
				p.setContent("mark/cross_controls.jsp");
				p.setCss("bootstrap-select.min.css","cross_controls.css");
				

				// Display skills in tab
				Skill skill = ds.selectCrossSkills();
				ds.completeSub_skills(skill); // Add sub_skills into skills
				
				// Display values in radio btn
				Value[] v = dv.selectAllValues();

				request.setAttribute("group", g);
				request.setAttribute("skills", skill);
				request.setAttribute("values", v);
				request.setAttribute("deadline", deadline);
				
			} else {
				Isep.bagPackHome(p, s);
				p.setWarning(true);
				p.setWarningMessage("pas d'évaluation croisée");
			}
			
		} else {
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("Cette page n'est accessible qu'aux étudiants.");
		}
		
		
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp")
				.forward(request, response);
		
		//Close Dao
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
