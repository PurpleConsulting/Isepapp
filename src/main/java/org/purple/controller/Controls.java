package org.purple.controller;

import java.io.IOException;

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
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.DaoGroups;
import org.purple.model.DaoSkills;

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
		p.setContent("/views-performances/fill_performances.jsp");
		request.setAttribute("pages", p);
		
		//Create instance Dao
		DaoGroups dgp = new DaoGroups(Bdd.getCo());
		DaoSkills ds = new DaoSkills(Bdd.getCo());
		
		//Display group name
		HttpSession s = request.getSession();
		User u = (User)s.getAttribute("user");

		String[] gp_name = dgp.selectAllName(Integer.toString(u.getId()));
		
		request.setAttribute("group_names", gp_name);
		
		//Display skills in tab
		Skill[] skills = ds.selectAllSkills();
		request.setAttribute("skills", skills);
		
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
		
		dgp.close();  // -- close Groups Dao
		ds.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str = request.getParameter("string");
		
		if (str != null){
			DaoGroups dgroup = new DaoGroups(Bdd.getCo());
			
			Group g = new Group();
			
			String[] name = null;
			
			if (g != null){
				str=str.trim();
				g = dgroup.select(str);
				dgroup.completeMemebers(g);
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
