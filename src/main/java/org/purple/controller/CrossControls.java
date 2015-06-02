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

import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
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
		Page p = new Page();
		p.setContent("mark/cross_controls.jsp");
		p.setCss("bootstrap-select.min.css","crossmark.css");
		// p.setTitle("");

		// Create instance Dao
		Connection bddServletCo = Bdd.getCo();
		DaoGroups dgp = new DaoGroups(bddServletCo);
		DaoUsers dusr = new DaoUsers(bddServletCo);
		DaoSkills ds = new DaoSkills(bddServletCo);
		DaoValues dv = new DaoValues(bddServletCo);

		// Display group name
		HttpSession s = request.getSession();
		User u = (User) s.getAttribute("user");
		dusr.addGroup(u);
		String str = u.getGroup();
		Group g = dgp.select(str);
		dgp.completeMemebers(g);

		// Display skills in tab
		Skill skill = ds.selectCrossSkills();
		ds.completeSub_skills(skill); // Add sub_skills into skills
		
		// Display values in radio btn
		Value[] v = dv.selectAllValues();
		System.out.print(skill.getSubSkills().size());
		request.setAttribute("group", g);
		request.setAttribute("pages", p);
		request.setAttribute("skills", skill);
		request.setAttribute("values", v);
		
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
