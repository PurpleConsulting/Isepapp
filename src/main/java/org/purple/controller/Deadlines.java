package org.purple.controller;

import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Deadline;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class Deadlines
 */
@WebServlet("/Deadlines")
public class Deadlines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deadlines() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Les Daos
		Page p = new Page();
		if(Auth.isRespo(request)){
			DaoGroups dg = new DaoGroups(Bdd.getCo());
			DaoDeadline dl = new DaoDeadline(Bdd.getCo());
			DaoUsers u = new DaoUsers(Bdd.getCo());
			
			
			p.setCss("deadline.css");
			p.setJs("deadline.js");
			p.setContent("/deadline/deadline.jsp");
			p.setTitle("ISEP / APP - Deadline");
			request.setAttribute("pages", p);
			
			HttpSession session = request.getSession();
			User userSession = (User)session.getAttribute("user");
			request.setAttribute("usession", userSession);
	
			
			// -- get all the groups
			String[] groups = dg.selectAllClass();
			request.setAttribute("groups", groups);
			
			// -- get all the tutors
			User[] user=u.selectTutorbyGroup();
			request.setAttribute("user", user);
			
			// get all the deadlines
			Deadline[] deadline = dl.selectAllDeadlines();
			request.setAttribute("deadline", deadline);
		}
	
		this.getServletContext().getRequestDispatcher("/template.jsp")
		.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Page p = new Page();
		//Les Daos
		Connection bddServletCo = Bdd.getCo();
		DaoGroups dg = new DaoGroups(bddServletCo);
		DaoDeadline dl = new DaoDeadline(bddServletCo);
		DaoUsers u = new DaoUsers(bddServletCo);
		//--get les parametres
		String desc = request.getParameter("new_desc");
		String tuteur = request.getParameter("tuteur");
		String date = request.getParameter("new_date");
		String time = request.getParameter("new_time");
		String group = request.getParameter("new_grp");
		String number = request.getParameter("number");
		
		if(Auth.isRespo(request)){
			if(!Isep.nullOrEmpty(desc, date, group, time, tuteur)){
				String timeS = time+":00";
				String datetime = date+" "+timeS;
				
				Group[] gr = dg.selectGroupbyClass(group);
				//dline à inserer
				for(int i=0; i<gr.length;i++){
					Deadline dline=new Deadline();
					dline.setDescription(desc);
					dline.setResponsable(Integer.parseInt(tuteur));
					dline.setIdGroup(gr[i].getId());		 
					dline.setDateLimit(datetime);
					dl.create(dline);
				}
			}
			else if(!Isep.nullOrEmpty(number)){
				for(int i=0; i<=Integer.parseInt(number); i++){
					String datelim = request.getParameter("datelim"+i);
					String timelim = request.getParameter("timelim"+i);
					String idDeadline = request.getParameter("id"+i);
					
					if(!Isep.nullOrEmpty(datelim,timelim)){
					String datetimelim=datelim+" "+timelim;
					String datetimelimB=datetimelim.substring(0,16);
					
					Deadline dline=new Deadline();
					dline.setId(Integer.parseInt(idDeadline));
					dline.setDateLimit(datetimelimB+":00");
					dl.update(dline);
					}else{
						p.setWarning(true);
						p.setWarningMessage("L'oppération à mal été éffectuée, veuillez répéter l'oppération en remplissant correctement les champs proposés.");
				
					}
				}
			}
			else{
				p.setWarning(true);
				p.setWarningMessage("L'oppération à mal été éffectuée, veuillez répéter l'oppération en remplissant correctement les champs proposés.");
			}
		
		//Afficher la page
		
		// On ajout le css
		p.setCss("deadline.css");
		p.setJs("deadline.js");
		p.setContent("/deadline/deadline.jsp");
		p.setTitle("ISEP / APP - Deadline");
		request.setAttribute("pages", p);
		
		
		// -- get all the groups
		String[] groups = dg.selectAllClass();
		request.setAttribute("groups", groups);
		
		//Recuperer la session
		HttpSession session = request.getSession();
		User userSession = (User)session.getAttribute("user");
		request.setAttribute("usession", userSession);
				
		// -- get all the tutors
		User[] user=u.selectTutorbyGroup();
		request.setAttribute("user", user);
		
		// get all the deadlines
		Deadline[] deadline = dl.selectAllDeadlines();
		request.setAttribute("deadline", deadline);
		
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/template.jsp")
		.forward(request, response);
		
		
		
		}
	}

}
