package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
				Value[] v = dv.selectAllValues("1");

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/**
		 * FOR AJAX QUERIES
		 */
		String flagForm = request.getParameter("flag-form");
		
		
		/**
		 * FOR REGULAR POST QUERIES
		 */
		// -- params for cross evaluation
		String pseudo = request.getParameter("student");
		String idStudent = request.getParameter("id_student");
		/* the subskill evaluated by the student */
		ArrayList<String> _params = Collections.list(request.getParameterNames());
		ArrayList<String> submitSubSkills = new ArrayList<String>();
		for (String _p : _params) {
			if (_p.indexOf(this.pattern) != -1) {
				submitSubSkills.add(_p.substring(this.patternLimit));
				// -- Arraylist of id ex: 63, 64, 79
			}

		}

		if (Auth.isStudent(request)) {
			/**
			 * THE USER IS A AUTHORISED STUDENT
			 */
			// -- the user have access to the data base
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dgroup = new DaoGroups(bddServletCo);
			DaoMarks dmrk = new DaoMarks(bddServletCo);
			DaoSubSkills dssk = new DaoSubSkills(bddServletCo);
			DaoUsers dusr = new DaoUsers(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			DaoDeadline dl = new DaoDeadline(bddServletCo);

			// -- This is the user
			HttpSession s = request.getSession();
			User u = (User) s.getAttribute("user");
			dusr.addGroup(u);

			Value[] val = dv.selectAllValues("1");
			if(!Isep.nullOrEmpty(flagForm)){
				JSONObject result = new JSONObject();
				JSONObject grid = new JSONObject();
				ArrayList<Mark> allMarks = dmrk.selectByTutor(u.getId());
				for(Mark mark : allMarks){
					JSONObject control = new JSONObject();
					control.put("value", mark.getIdValue());
					control.put("student", mark.getIdOwner());
					control.put("subSkillId", mark.getIdSubSkill());
					grid.append("grid", control);
				}
				if(grid.isNull("grid")) { 
					String[] tab = new String[0];
					grid.putOnce("grid", tab);
				};
				result.put("result", grid);
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
				
				
			} else if(!Isep.nullOrEmpty(pseudo, idStudent)) {
				/**
				 * THIS IS AN EVALUATION REQUEST
				 */

				// -- This is the classmate evaluated
				User targetUser = dusr.select(pseudo);
				dusr.addGroup(targetUser);
				request.setAttribute("ink", targetUser.getPseudo());

				// -- Hashmap (id_sub_skill, id_value)
				HashMap<String, String> submitValues = new HashMap<String, String>();
				for (String sbs : submitSubSkills) {
					submitValues.put(sbs, request.getParameter(this.pattern + sbs));
				}

				// -- Hashmap (id_value, bean:value)
				HashMap<String, Value> mapV = new HashMap<String, Value>();
				for (Value v : val) {
					mapV.put(Integer.toString(v.getId()), v);
				}

				// -- The both user are in the same group
				// -- it's ok let's evaluate
				if (u.getGroup().equals(targetUser.getGroup())) {

					boolean businessFlague = true;
					boolean sqlFlag = true;
					ArrayList<Mark> marks = new ArrayList<Mark>();

					// -- Are those sub_skill real cross sub_skill ??
					ArrayList<SubSkill> subSkills = new ArrayList<SubSkill>();
					for (String sskill : submitSubSkills) {
						SubSkill in = dssk.select(sskill);
						if (in.getIdSkill() != 0 || in.getId() == 0){
							businessFlague = false;
						} else {
							subSkills.add(in);
						}
						
					}

					// -- Are those values real cross values ??
					Iterator it = submitValues.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry) it.next();
						String vv = (String) pair.getValue();
						if (!mapV.keySet().contains(vv)){
							businessFlague = false;
						}
					}

					//
					if (!businessFlague) {
						/* SOMETHING WRONG WHITH THE FORM SUBMITED */
						p.setError(true);
						p.setErrorMessage("nous avons remarqué un problème dans l'envoie du formulaire,"
								+ " veillez à bien remplire tous les champs du fomulaire pour un de vos collègues.");
					} else {
						for (SubSkill ss : subSkills) {
							int valId = Integer.parseInt(submitValues.get(Integer.toString(ss.getId())));
							marks.add(new Mark(targetUser.getId(),u.getId(), ss.getId(), valId, true));
						}
						for (Mark m : marks) {
							boolean querrysuccess = true;
							m.setIdTutor(u.getId());
							querrysuccess = dmrk.createCrossMark(m);
							if (!querrysuccess) sqlFlag = false;
						}

						if (sqlFlag){
							p.setSuccess(true);
							p.setSuccessMessage("Evaluation réussie !");
						}else{
							p.setWarning(true);
							p.setWarningMessage("Une erreur est survenue. Veuillez revérifier les formulaires de notation !");
						}
						
					}

				} else {
					p.setError(true);
					p.setErrorMessage("Une erreur s'est produite lors de la notation de l'étudiant."
							+ "Vérifiez que l'étudiant noté soit bien dans votre groupe."
							+ "En cas de problème contacter un tuteur ou le responsable d'APP.");

				}

				// -- redirection après avoir submit
				String grp = u.getGroup();
				Deadline deadline = dl.fetchCrossDeadline(grp);

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
				p.setTitle("ISEP / APP - Evaluation croisée");
				p.setContent("mark/cross_controls.jsp");
				p.setCss("bootstrap-select.min.css", "cross_controls.css");
				p.setJs("cross_controls.js");
				request.setAttribute("pages", p);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			} else {
				
				p.setWarning(true);
				p.setWarningMessage("une erreur est survenue, avez vous bien remplie"
						+ " tous les champs des formulaires à votre disposition? ");
				
				// -- redirection après avoir submit
				String grp = u.getGroup();
				Deadline deadline = dl.fetchCrossDeadline(grp);

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
				p.setTitle("ISEP / APP - Evaluation croisée");
				p.setContent("mark/cross_controls.jsp");
				p.setCss("bootstrap-select.min.css", "cross_controls.css");
				p.setJs("cross_controls.js");
				request.setAttribute("pages", p);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
			}

			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("seul les étudiants sont authorisés à compléter les évaluation croisés."
					+ " êtes vous bien connectés à l'application? ");
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			
		}
	}
}
