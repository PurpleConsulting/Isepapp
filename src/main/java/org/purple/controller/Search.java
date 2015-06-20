package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Page;
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
		Page p = new Page();
		if(request.getParameter("keyword") != null){
			String keyword = request.getParameter("keyword");
			keyword = keyword.trim();
			if(keyword.length() == 2){
				keyword = keyword.toUpperCase();
				if(Research.isRealVal("Groups","class",keyword)) response.sendRedirect("Promo#Group" + keyword );
			} else if(keyword.length() == 3){
				keyword = keyword.toUpperCase();
				if(Research.isRealVal("Groups","`name`",keyword)) response.sendRedirect("Groups?scope=" + keyword );
			} else if(Research.isRealVal("Users","CONCAT(first_name, ' ', last_name)",keyword)){
				String pseudo = Research.nameToSpeudo(keyword);
				response.sendRedirect("Students?pseudo=" + pseudo );
			}
			
		} else {
			response.sendRedirect("Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("query") != null){
			JSONObject result = new JSONObject();
			JSONObject js = new JSONObject();
			String[] students = Research.pseudoResearch("CONCAT(first_name, ' ', last_name)", "Users", "WHERE id_post = 4");
			String[] groups = Research.pseudoResearch("DISTINCT `name`", "Groups", "WHERE Groups.`id` > 0");
			String[] classes = Research.pseudoResearch("DISTINCT class", "Groups", "WHERE Groups.`id` > 0");
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