package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Predicate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Mark;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.SubSkill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoSkills;
import org.purple.model.DaoSubSkills;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;
import org.purple.model.MyOwnClassMate;

/**
 * Servlet implementation class Crossmark
 */

@WebServlet("/CrossControls")
public class CrossControls extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String pattern = "sub_skill_";
	private final int patternLimit = this.pattern.length();

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

		if (Auth.isStudent(request)) {
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
			User u = (User) s.getAttribute("user");
			dusr.addGroup(u);
			String grp = u.getGroup();

			Deadline deadline = dl.fetchCrossDeadline(grp);

			if (deadline.getStatus()) {
				/**
				 * HERE THE CROSS MARK SESSION IS OPEN
				 */
				// -- We get the group
				Group g = dgp.select(grp);
				dgp.completeMemebers(g);
				Predicate<User> filter = new MyOwnClassMate(u.getPseudo());
				g.getMembers().removeIf(filter);

				p.setTitle("ISEP / APP - Evaluation croisée");
				p.setContent("mark/cross_controls.jsp");
				p.setCss("bootstrap-select.min.css", "cross_controls.css");
				p.setJs("cross_controls.js");

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

		// Close Dao
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
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();

		// Retrieve group name selected (global)
		String str = request.getParameter("string");
		ArrayList<String> _params = Collections.list(request
				.getParameterNames());
		ArrayList<String> submitSubSkills = new ArrayList<String>();
		HashMap<String, String> submitValues = new HashMap<String, String>();
		for (String _p : _params) {
			if (_p.indexOf(this.pattern) != -1) {
				submitSubSkills.add(_p.substring(this.patternLimit));
			}

		}

		// -- add serie of marks for a student on a skill
		String pseudo = request.getParameter("student");
		String idStudent = request.getParameter("id_student");

		if (Auth.isStudent(request)) {
			// -- Now the user is allowed to perfom queries on the database
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dgroup = new DaoGroups(bddServletCo);
			DaoMarks dmrk = new DaoMarks(bddServletCo);
			DaoSubSkills dssk = new DaoSubSkills(bddServletCo);
			DaoUsers dusr = new DaoUsers(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			DaoDeadline dl = new DaoDeadline(bddServletCo);

			HttpSession s = request.getSession();
			User u = (User) s.getAttribute("user");
			dusr.addGroup(u);

			// Display values in radio btn
			Value[] val = dv.selectAllValues();
//			HashMap<String, Value> mapV = new HashMap<String, Value>();
//			for (Value v : val) {
//				mapV.put(Integer.toString(v.getId()), v);
//			}

			if (!Isep.nullOrEmpty(pseudo, idStudent)) {
				for (String sbs : submitSubSkills) {
					submitValues.put(sbs,
							request.getParameter(this.pattern + sbs));
				}
				// -- Find the group
				Group g = new Group();
				String[] name = null; // Store group members
				User targetUser = dusr.select(pseudo);
				dusr.addGroup(targetUser);

				if (u.getGroup().equals(targetUser.getGroup())) {
					ArrayList<SubSkill> subSkills = new ArrayList<SubSkill>();
					for (String sskill : submitSubSkills) {
						subSkills.add(dssk.select(sskill));
					}

					Mark mark = new Mark();

					for (SubSkill ss : subSkills) {
						int valId = Integer.parseInt(submitValues.get(Integer.toString(ss.getId())));
						mark = new Mark(targetUser.getPseudo(), ss.getId(), valId,
								true);

						submitValues.get(this.pattern
								+ Integer.toString(ss.getId()));

						// /////////////
						System.out.println(mark.getIdValue());						
						// ////////////
					}
				} else {
					p.setError(true);
					p.setErrorMessage("Une erreur s'est produite lors de la notation de l'étudiant."
							+ "Vérifier que l'étudiant noté soit ben dans votre groupe."
							+ "En cas de problème contacter un responsable");

				}

				// -- redirection après avoir submit
				String grp = u.getGroup();
				Deadline deadline = dl.fetchCrossDeadline(grp);

				p.setTitle("ISEP / APP - Evaluation croisée");
				p.setContent("mark/cross_controls.jsp");
				p.setCss("bootstrap-select.min.css", "cross_controls.css");
				p.setJs("cross_controls.js");

				// -- We get the group
				Group gr = dgroup.select(grp);
				dgroup.completeMemebers(gr);
				Predicate<User> filter = new MyOwnClassMate(u.getPseudo());
				gr.getMembers().removeIf(filter);

				// Display skills in tab
				Skill skill = ds.selectCrossSkills();
				ds.completeSub_skills(skill); // Add sub_skills into skills

				request.setAttribute("group", gr);
				request.setAttribute("skills", skill);
				request.setAttribute("values", val);
				request.setAttribute("deadline", deadline);
				p.setContent("mark/cross_controls.jsp");
				p.setCss("bootstrap-select.min.css", "cross_controls.css");
				p.setJs("cross_controls.js");
				request.setAttribute("pages", p);
				this.getServletContext().getRequestDispatcher("/template.jsp")
						.forward(request, response);
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
