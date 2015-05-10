package org.purple.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("query") != null){
			JSONObject result = new JSONObject();
			JSONObject js = new JSONObject();
			String[] array = {"ldivad","ldchanta","ldzozo","ldbilly","ldldldl","ldtruc"};
			js.put("pseudo", array);
			result.put("result", js);
			
			response.setHeader("content-type", "application/json");
			response.getWriter().write(result.toString());
		} else {
			response.setHeader("content-type", "application/json");
			response.getWriter().write("{\"result\": {\"page\": \"Searh\" }}");
		
		}
	}

}
