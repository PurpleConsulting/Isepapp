package org.purple.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Research;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String keyword = request.getParameter("keyword");
		Connection bddServletCo = Bdd.getCo();
		
		Page p = new Page();
		if(!Isep.nullOrEmpty(keyword)){
			byte[] b = keyword.getBytes(StandardCharsets.ISO_8859_1);
			keyword = new String(b,StandardCharsets.UTF_8);
			keyword = keyword.trim();
			String KW = keyword.toUpperCase();
			if(Research.isRealVal("Groups","class",KW)){
				// -- class research

				response.sendRedirect("Promo#Group" + keyword );
				
			} else if(Research.isRealVal("Groups","`name`", KW)){
				// -- group research 
				
				response.sendRedirect("Groups?scope=" + keyword );
				
			} else if(Research.isRealVal("Users","CONCAT(first_name, ' ', last_name)",keyword)){
				// -- student research
				
				String pseudo = Research.nameToSpeudo(keyword);
				response.sendRedirect("Students?pseudo=" + pseudo );
			} else {
				
				p.setTitle("ISEP / APP - Recherche");
				p.setContent("search.jsp");p.setInfo(true);
				p.setCss("search.css"); p.setJs("search.js");
				p.setInfoMessage("Aucun résultat exacte n'a été retrouvé pour les termes `" +keyword+ "`."
						+ " Cette page présente les résultats contenant les mots cléfs proposés.");
				
				
				String[] cls = {};
				HashMap<String, Group> grp = new HashMap<String, Group>();
				HashMap<String, User> usr = new HashMap<String, User>();
				try {
					usr = Research.fuzzyStudent(keyword);
					grp = Research.fuzzyGroup(keyword);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				request.setAttribute("classes", cls);
				request.setAttribute("groups", grp);
				request.setAttribute("students", usr);
				request.setAttribute("keyword", keyword);
				request.setAttribute("pages", p);
				request.getRequestDispatcher("/template.jsp").forward(request, response);
			}
			
		} else {
			response.sendRedirect("Home");
		}
		
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String query = request.getParameter("query");
		
		if(!Isep.nullOrEmpty(query)){
			JSONObject result = new JSONObject();
			JSONObject js = new JSONObject();
			String[] students = Research.exactResearch("CONCAT(first_name, ' ', last_name)", "Users", "WHERE id_post = 4");
			String[] groups = Research.exactResearch("DISTINCT `name`", "Groups", "WHERE Groups.`id` > 0");
			String[] classes = Research.exactResearch("DISTINCT class", "Groups", "WHERE Groups.`id` > 0");
			js.put("student", students);
			js.put("group", groups);
			js.put("classes", classes);
			result.put("result", js);
			
			response.setHeader("content-type", "application/json");
			response.getWriter().write(result.toString());
			
		} else {
			response.setHeader("content-type", "application/json");
			response.getWriter().write("{\"result\": {\"page\": \"Searh\" }}");
		
		}
	}

}
