package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.constant.Bdd;
import org.purple.model.Auth;
import org.purple.model.DaoSkills;

/**
 * Servlet implementation class Skills
 */
@WebServlet("/Skills")
public class Skills extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Skills() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		if(Auth.isConnect(request)){
			Connection bddServletCo = Bdd.getCo();
			DaoSkills dsk = new DaoSkills(bddServletCo);
			
			Skill[] skills = new Skill[0]; //dsk.selectAllSkills();
//			for(Skill s: skills ){
//				dsk.completeSub_skills(s);
//			
			p.setTitle("ISEP / APP - Les compétences");
			p.setCss("skill_display.css");
			p.setJs("skill_display.js");
			p.setContent("skills/skill.jsp");
			
			
			request.setAttribute("skills", skills);
			request.setAttribute("pages", p);
			request.getRequestDispatcher("/template.jsp").forward(request, response);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
