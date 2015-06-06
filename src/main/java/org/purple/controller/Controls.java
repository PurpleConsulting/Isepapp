package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoSkills;
import org.purple.model.DaoSubSkills;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class Skills
 */
@WebServlet("/Controls")
public class Controls extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String markDelimiter = ";";
    private static final String skillValueDelimiter = "&";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controls() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		if(Auth.isTutor(request) || Auth.isRespo(request)){
			
			//Create instance Dao
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dgp = new DaoGroups(bddServletCo);
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoValues dv = new DaoValues(bddServletCo);
			
			//Display group name
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("user");

			String[] gp_name = dgp.selectAllName(Integer.toString(u.getId()));
			
			request.setAttribute("group_names", gp_name);
			
			//Display skills in tab
			Skill[] skills = ds.selectAllSkills();
			if (skills != null){
				for (int i=0;i<=skills.length-1;i++){
					ds.completeSub_skills(skills[i]); //Add sub_skills into skills
				}
			}
				
			request.setAttribute("skills", skills);
			
			//Display values in radio btn
			Value[] v= dv.selectAllValues();
			request.setAttribute("values", v);
			
			p.setJs("bootbox.min.js", "bootstrap-select.min.js", "controls.js","control_load.js");
			p.setCss("bootstrap-select.min.css","controls.css");
			p.setTitle("ISEP / APP - Evaluations");
			p.setContent("mark/controls.jsp");
			request.setAttribute("pages", p);
			
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);

			//Close Dao
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			p.setTitle("ISEP / APP - Home");
			p.setContent("home/student.jsp");
			p.setWarning(true);
			p.setWarningMessage("La page que vous essayez d'atteindre n'est accessible que par les tuteurs d'APP.");
			request.setAttribute("pages", p);
			
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Retrieve group name selected (global)
		String str = request.getParameter("string");
		
		// -- add serie of marks for a group on a skill
		String scope = request.getParameter("scope");
		String group = request.getParameter("group");
		String marks = request.getParameter("marks");
		
		if(Auth.isTutor(request) || Auth.isRespo(request)){
			// -- Now the user is allowed to perfom queries on the database
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dgroup = new DaoGroups(bddServletCo);
			DaoMarks dmrk = new DaoMarks(bddServletCo);
			DaoMarks dm = new DaoMarks(bddServletCo);
			
			if(!Isep.nullOrEmpty(str) && Auth.isTutor(request, str)){
				// -- Find the group
				Group g = new Group();
				String[] name = null; //Store group members
				
				if (g != null){
					g = dgroup.select(str); //Select group id,name, class by group name
					dgroup.completeMemebers(g); //Add members into the group selected
					name = new String[g.getMembers().size()];
					name = new String[g.getMembers().size()];
					int i=0;
					for(User u : g.getMembers()){		
						name[i] = u.getFirstName();
						//pseudo[i] = u.getPseudo();
						i++;
					}
					//Display checked btn when there are already marks for the gp
					Mark[] m = dm.selectGroupMark(Integer.toString(g.getId()));
					if (m.length!=0){ //If array not empty, display value
						request.setAttribute("marks", m);
					}
				}
				
				JSONObject result = new JSONObject();
				JSONObject list = new JSONObject();
				
				list.put("groups", name);
				result.put("result", list);
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(result.toString());
				
			} else if(!Isep.nullOrEmpty(scope, group, marks) && Auth.isTutor(request, group)){
				boolean querrysuccess = true;
				if(scope.equals("group")){
					String[] controls = marks.split(this.markDelimiter);
					for(String c : controls){
						String[] stringmrk = c.split(this.skillValueDelimiter);
						Mark mark = new Mark(group, Integer.parseInt(stringmrk[0]), Integer.parseInt(stringmrk[1]));
						querrysuccess = dmrk.createMulti(mark);
					}
					JSONObject result = new JSONObject();
					JSONObject field = new JSONObject();
					
					result.put("result", field
							.put("status", querrysuccess)
							.put("scope", "group")
							.put("target", group));
					response.setHeader("content-type", "application/json");
					response.getWriter().write(result.toString());
				}
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
