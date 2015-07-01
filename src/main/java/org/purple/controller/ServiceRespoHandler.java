package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;

/**
 * Servlet implementation class ServiceRespoHandler
 */
@WebServlet("/ServiceRespoHandler")
public class ServiceRespoHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceRespoHandler() {
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
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/** AJAX PARAMS **/
		String markServiceParam = request.getParameter("mark-service");
		
		////////////////////// OPS \\\\\\\\\\\\\\\\\\\\\\
		
		JSONObject result = new JSONObject();
		
		if(Auth.isRespo(request)){
			
			// -- Database access
			Connection bddServletCo = Bdd.getCo();
			
			if(!Isep.nullOrEmpty(markServiceParam)){
				
				JSONObject prom = new JSONObject();
				
				for(int i = 1; i < 5; i++){
					JSONObject _class = new JSONObject();
					prom.put("G"+i, i*2);
					
				}
				
				result.put("result", new JSONObject().put("prom", prom));
				
			} else {
				
				// -- inproper request -- //
				result.put("err", true);
				result.put("err-message", "Requête incorrecte.");
				
			}
			
		} else {
			
			// -- non-repo user -- //
			result.put("err", true);
			result.put("err-message", "L'utilisateur n'est pas responsable d'APP.");
			
		}
		
		response.setHeader("content-type", "application/json");
		response.getWriter().write(result.toString());
	}

}
