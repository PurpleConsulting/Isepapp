package org.purple.controller;

import java.io.IOException;
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
			DaoGroups dgp = new DaoGroups(Bdd.getCo());
			DaoSkills ds = new DaoSkills(Bdd.getCo());
			DaoValues dv = new DaoValues(Bdd.getCo());
			
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
			
			p.setJs("bootstrap-select.min.js","controls.js");
			p.setCss("bootstrap-select.min.css","controls.css");
			p.setTitle("ISEP / APP - Evaluation");
			p.setContent("mark/controls.jsp");
			request.setAttribute("pages", p);
			
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);

			//Close Dao
			dgp.close();
			ds.close();
			dv.close();
			
		
		} else {
			p.setTitle("ISEP / APP - Home");
			p.setContent("home/student.jsp");
			p.setWarning(true);
			p.setWarningMessage("la page que vous essayer d'atteidre n'est accécible que par les tuteurs d'APP.");
			request.setAttribute("pages", p);
			
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Retrieve group name selected
		String str = request.getParameter("string");
		
		// -- 
		String scope = request.getParameter("scope");
		String group = request.getParameter("group");
		String marks = request.getParameter("marks");
		
		if(Auth.isTutor(request) || Auth.isRespo(request)){
			// -- Now the user is allowed to perfom queries on the database
			DaoGroups dgroup = new DaoGroups(Bdd.getCo());
			DaoMarks dmrk = new DaoMarks(Bdd.getCo());
			
			if(!Isep.nullOrEmpty(str) && Auth.isTutor(request, str)){
				// -- Find the group
				Group g = new Group();
				String[] name = null; //Store group members
				
				if (g != null){
					g = dgroup.select(str); //Select group id,name, class by group name
					dgroup.completeMemebers(g); //Add members into the group selected
					name = new String[g.getMembers().size()];
					int i=0;
					for(User u : g.getMembers()){		
						name[i] = u.getFirstName();
						//pseudo[i] = u.getPseudo();
						i++;
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
			dgroup.close();
			dmrk.close();
		}
		
	}
}
