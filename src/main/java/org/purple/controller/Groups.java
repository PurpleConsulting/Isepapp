package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Mark;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Average;
import org.purple.model.AvgBuilder;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMarks;
import org.purple.model.DaoMissings;
import org.purple.model.DaoSkills;
import org.purple.model.DaoValues;


/**
 * Servlet implementation class Group
 */
@WebServlet("/Groups")
public class Groups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Groups() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doLoad(HttpServletRequest request, DaoSkills ds, DaoMissings dm, DaoDeadline ddl, DaoMarks dmk, Group group){
    	// --------------------------------------------------------------------------------
    	// -- BUILDING THE MISSINGS GRID
    	// --------------------------------------------------------------------------------
		Missing[] allmissings = dm.selectForGroup(group.getName());
		
		
		// --------------------------------------------------------------------------------
    	// -- BUILDING THE DEADLINES AND DELIVERIES
    	// --------------------------------------------------------------------------------
		Deadline[] deadlines = ddl.selectByGroup(group.getName());
		
		
		// --------------------------------------------------------------------------------
    	// -- BUILDING THE GROUP AVERAGE
    	// --------------------------------------------------------------------------------
		double maxMark = DaoValues.fetchMax();
		Skill[] skills = ds.allSkill();// -- get all the skill for this session
		ArrayList<Mark> marks = dmk.selectByGroup(group.getName());
		Average average = AvgBuilder.groupAverage(marks, group, maxMark);
		
		// --------------------------------------------------------------------------------
    	// -- BUILDING LOAD THE REQUEST
    	// --------------------------------------------------------------------------------
		request.setAttribute("average", average);
		request.setAttribute("missings", allmissings);
		request.setAttribute("deadlines", deadlines);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); Page p = new Page();
		/*$$$ GET PARAMS $$$*/		
		String scope = request.getParameter("scope");
		
		// -- Authentication --
		if(!Auth.isRespo(request) && !Auth.isTutor(request) && !Auth.isAdmin(request)){ 
			// --------------------------------------------------------------------------------
	    	// -- NON AUTHORIZED
	    	// --------------------------------------------------------------------------------
			p.setWarning(true);
			p.setWarningMessage("la page sur laquelle vous tentez de vous rendre ne vous est pas accessible. "
					+ "Pour toutes réclamations, prenez contact avec le responsable d'APP actuel.");
			p.setContent("home.jsp");
			
		} else {
			// --------------------------------------------------------------------------------
	    	// -- AUTHORIZED
	    	// --------------------------------------------------------------------------------			
			if(Isep.nullOrEmpty(scope)){
				// -- Missing data
				p.setWarning(true);
				p.setWarningMessage("Aucun group ne correspond à cette rquête.");
				Isep.bagPackHome(p, request.getSession());
				p.setContent("home/common.jsp");
			} else {
				
				Connection bddServletCo = Bdd.getCo();
				DaoGroups dg = new DaoGroups(bddServletCo);
				
				Group group = dg.select(scope);  
				if(group.getId() == 0){
					
					// -- non-existent group
					p.setWarning(true);
					p.setWarningMessage("le groupe demandé n'a pas été retrouvé dans la base.");
					Isep.bagPackHome(p, request.getSession());
					p.setContent("homme/common.jsp");
					
				} else {
					
					// -- existent group 
					DaoMarks dmk = new DaoMarks(bddServletCo);
					DaoDeadline ddl = new DaoDeadline(bddServletCo);
					DaoMissings dm = new DaoMissings(bddServletCo);
					DaoValues dv = new DaoValues(bddServletCo);
					DaoSkills ds = new DaoSkills(bddServletCo);
					
					dg.completeMemebers(group);
					dg.completeTutor(group);
					
					this.doLoad(request, ds, dm, ddl, dmk, group);
					
					p.setTitle("ISEP / APP - Groupes "+group.getName());
					p.setContent("users/group.jsp");
					p.setCss("group.css");
					p.setJs("group.js");
					
					request.setAttribute("group", group);
					request.setAttribute("pages", p);
				}
				
				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		request.setAttribute("pages", p);
		request.getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
