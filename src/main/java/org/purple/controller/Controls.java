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
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.SubSkill;
import org.purple.bean.User;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.model.DaoGroups;
import org.purple.model.DaoSkills;
import org.purple.model.DaoSubSkills;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class Skills
 */
@WebServlet("/Controls")
public class Controls extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		//p.setCss(".css");
		p.setJs("fill_performances.js");
		p.setContent("mark/controls.jsp");
		request.setAttribute("pages", p);
		
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
		
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);

		//Close Dao
		dgp.close();
		ds.close();
		dv.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str = request.getParameter("string");
		
		Enumeration<String> NomsParam = request.getParameterNames();
		while(NomsParam.hasMoreElements()) {
			  String NomParam = (String)NomsParam.nextElement();

			  System.out.println("un " + NomParam );

			  String[] ValeursParam = request.getParameterValues(NomParam);

			  if (ValeursParam.length == 0)		  
				  System.out.println("<td><b>Aucune valeur</i></td>");

					 
				 	
				//for(int i=0; i < ValeursParam.length; i++) {
					System.out.println("valeur " + ValeursParam[0] );		
				//}
			
			  }	
			
		 // String[] ValeursParam = request.getParameterValues(NomParam);
		//  System.out.println( ValeursParam[0]);
		
		if (str != null){
			DaoGroups dgroup = new DaoGroups(Bdd.getCo());
			
			Group g = new Group();
			
			String[] name = null; //Store group members
			
			if (g != null){
				str=str.trim(); //Delete spaces before and after
				g = dgroup.select(str); //Select group id,name, class by group name
				dgroup.completeMemebers(g); //Add members into the group selected
				name = new String[g.getMembers().size()];
				int i=0;
				for(User u : g.getMembers()){		
					name[i] = u.getFirstName() + " ";
					i++;
				}
			}
			
			JSONObject result = new JSONObject();
			JSONObject list = new JSONObject();
			
			list.put("groups", name);
			result.put("result", list);
			
			response.setHeader("content-type", "application/json");
			response.getWriter().write(result.toString());
			
			
			
			dgroup.close();  // -- close Groups Dao
		}
	}
}
