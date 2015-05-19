package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.DaoGroups;

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
		p.setCss("crossmark.css");
		// p.setTitle("");

		// Create instance Dao
		DaoGroups dgp = new DaoGroups(Bdd.getCo());

		// Display group name
		HttpSession s = request.getSession();
		User u = (User) s.getAttribute("user");

		String str = request.getParameter("string"); //"G5A";
		if (str != null) {
			DaoGroups dgroup = new DaoGroups(Bdd.getCo());

			Group g = new Group();

			String[] name = null;

			if (g != null) {
				str = str.trim();
				g = dgroup.select(str);
				dgroup.completeMemebers(g);
				name = new String[g.getMembers().size()];
				int i = 0;
				for (User us : g.getMembers()) {
					name[i] = us.getFirstName() + " ";
					i++;
				}
			}
			request.setAttribute("nom", name);
		}

		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp")
				.forward(request, response);
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
