package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.purple.bean.Mark;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoSkills;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class PersoControls
 */
@WebServlet("/PersoControls")
public class PersoControls extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String pattern = "sub_skill_";
	private final int patternLimit = this.pattern.length();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersoControls() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doRegular(HttpServletRequest request, DaoUsers du, DaoSkills ds, DaoValues dv, Page p){
    	//Display student info to know who we are giving individual marks 
		String pseudo = request.getParameter("pseudo");
		User u = du.select(pseudo);
		du.addGroup(u);
		
		//If student exists
		if (u.getId() != 0){
			
			request.setAttribute("student", u);
			
			//Display skills in tab
			Skill[] skills = ds.selectAllSkills();
			if (skills != null){
				for (int i=0;i<=skills.length-1;i++){
					ds.completeSub_skills(skills[i]); //Add sub_skills into skills
				}
			}
				
			request.setAttribute("skills", skills);
			
			//Display values in radio btn
			Value[] v = dv.selectAllValues("0");
			request.setAttribute("values", v);
			
			p.setJs("bootbox.min.js", "bootstrap-select.min.js","perso_controls.js");
			p.setCss("bootstrap-select.min.css", "controls.css");
			p.setTitle("ISEP / APP - Evaluations");
			p.setContent("mark/perso_controls.jsp");
		}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		
		// -- global 
		String student = request.getParameter("pseudo");
		
		if(Auth.isStudentTutor(request, student) || Auth.isRespo(request)){
			
			//Create instance Dao
			Connection bddServletCo = Bdd.getCo();
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			DaoMarks dm = new DaoMarks(bddServletCo);
			
			//Display student info to know who we are giving individual marks 
			User u = du.select(student);
			du.addGroup(u);
			
			//If student exists
			if (u.getId() != 0){
				
				request.setAttribute("student", u);
				
				//Display skills in tab
				Skill[] skills = ds.selectAllSkills();
				if (skills != null){
					for (int i=0;i<=skills.length-1;i++){
						ds.completeSub_skills(skills[i]); //Add sub_skills into skills
					}
				}
					
				request.setAttribute("skills", skills);
				
				//Display values in radio btn
				Value[] v = dv.selectAllValues("0");
				request.setAttribute("values", v);
				
				//Display checked btn when there are already marks for the student
				ArrayList<JSONObject> mark = new ArrayList<JSONObject>();
				ArrayList<Mark> tabMark = dm.selectByStudent(Integer.toString(u.getId()));
				
				JSONObject current = new JSONObject();
				for (Mark m : tabMark){
					current = new JSONObject();
					current.put("subSkill", m.getIdSubSkill());
					current.put("value", m.getIdValue());
					mark.add(current);
				}
				
				JSONObject result = new JSONObject();
				JSONObject list = new JSONObject();
				list.put("marks", mark);
				result.put("result", list);
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
				
				p.setJs("bootbox.min.js", "bootstrap-select.min.js","perso_controls.js");
				p.setCss("bootstrap-select.min.css", "controls.css");
				p.setTitle("ISEP / APP - Evaluations");
				p.setContent("mark/perso_controls.jsp");
				request.setAttribute("pages", p);
			
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
			}else{
				p.setTitle("ISEP / APP - Accueil");
				p.setContent("home/common.jsp"); //A revoir
				p.setWarning(true);
				p.setWarningMessage("l'élève que vous souhaitez noter n'existe pas. Veuillez consulter la liste des groupes pour trouver un élève existant.");
				request.setAttribute("pages", p);
				
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
			}
			
			//Close Dao
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			p.setTitle("ISEP / APP - Accueil");
			p.setContent("home/common.jsp");
			p.setWarning(true);
			p.setWarningMessage("la page que vous essayez d'atteindre n'est accessible que par les tuteurs d'APP.");
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
		// -- global
		
		/**
		 * AJAX QUERY PARAMS
		 */
		String markedStudent = request.getParameter("marked");
		/**
		 * POST PARAMETERS
		 */
		String student = request.getParameter("student");
		// -- Add marks query
		
		if(Auth.isTutor(request) || Auth.isRespo(request)){
			
			Connection bddServletCo = Bdd.getCo();
			//Create instance Dao
			DaoMarks dm = new DaoMarks(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			
			
			if(!Isep.nullOrEmpty(markedStudent)){
				
				User markedUser = du.select(markedStudent);
				ArrayList<Mark> allMarks = dm.selectByStudent(Integer.toString(markedUser.getId()));
				JSONObject result = new JSONObject();
				JSONObject grid = new JSONObject();
				for(Mark m : allMarks){
					if(!m.isCross()){
						JSONObject jsonMark = new JSONObject();
						jsonMark.put("value", m.getIdValue());
						jsonMark.put("subSkill", m.getIdSubSkill());
						jsonMark.put("groupMark", m.isGroupMark());
						grid.append("grid", jsonMark);
					}
				}
				if(grid.isNull("grid")) { 
					String[] tab = new String[0];
					grid.putOnce("grid", tab);
				};
				
				result.put("result", grid);
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
					
			} else if(!Isep.nullOrEmpty(student)){
				ArrayList<String> _params = Collections.list(request.getParameterNames());
				ArrayList<String> submitSubSkills = new ArrayList<String>();
				for (String _p : _params) {
					if (_p.indexOf(this.pattern) != -1) {
						submitSubSkills.add(_p.substring(this.patternLimit));
					}
				}
				HashMap<String, String> submitValues = new HashMap<String, String>();
				for (String s : submitSubSkills){
					submitValues.put(s,request.getParameter(this.pattern + s));
				}
				boolean querySuccess = true;
				Iterator it = submitValues.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					Mark m = new Mark(Integer.parseInt((String)pair.getValue()), Integer.parseInt((String)pair.getKey()));
					m.setOwner(student);
					m.setGroupMark(false);
					querySuccess = querySuccess & dm.create(m);
				}
				
				if(querySuccess){
					p.setSuccess(true);
					p.setSuccessMessage("évaluation réussie. Si une évaluation de groupe a déjà été "
							+ " effectuée, les notes de " +student+ " ont été mises à jour.");
				} else {
					p.setTitle("ISEP / APP - Accueil");
					p.setContent("home/common.jsp"); //A revoir
					p.setWarning(true);
					p.setWarningMessage("l'élève que vous souhaitez noter n'existe pas. Veuillez "
							+ "consulter la liste des groupes pour trouver un élève existant.");
					request.setAttribute("pages", p);
					
				}

				this.doRegular(request, du, ds, dv, p);
				request.setAttribute("pages", p);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
					
			}
			
			
			//Close Dao
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("seuls les tuteurs des étudiants ont le droit d'accéder aux pages de notation.");
		}
	}
}
