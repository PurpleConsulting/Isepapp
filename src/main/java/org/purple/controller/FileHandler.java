package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.purple.bean.Page;
import org.purple.constant.Isep;
import org.purple.model.Auth;

/**
 * Servlet implementation class FileHandler
 */
@WebServlet("/FileHandler")
public class FileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("OKOK: GET");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		/**
		 * POSSIBLE PARAMS
		 */
		// -- file request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// -- file request : subject
		String subject = request.getParameter("subject_file");
		// -- file request : subject
		String promo = request.getParameter("promo_file");
		// -- file request : subject
		String backup = request.getParameter("backup_file");
		
		System.out.print("OKOKOKK ---------------------------------> \n");
		
		if(Auth.isRespo(request)){
			
			System.out.print("OKOKOKK ---------------------------------> \n");
			
			if(isMultipart){
				System.out.print("OKOKOKK ---------------------------------> \n");
				
				JSONObject business = new JSONObject();
				business.put("success", true);
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write("{}");
				
			} else if(!Isep.nullOrEmpty(promo)){
				
			} else if(!Isep.nullOrEmpty(backup)){
				
			}
		}
	}

}
