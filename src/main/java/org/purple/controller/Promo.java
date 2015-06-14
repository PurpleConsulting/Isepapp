package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Group;
import org.purple.bean.Missing;
import org.purple.bean.Page;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMissings;

/**
 * Servlet implementation class Promo
 */
@WebServlet("/Promo")
public class Promo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Promo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		
		if(!Auth.isConnect(request)){
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("il semble que vous ayez un problèe d'authetification. "
					+ "Essayer de vous reconnecter.");
		} else if(Auth.isStudent(request)){
			// --  the user is a student
			
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("La page que vous essayez d'atteindre n'est pas accécible aux étudiants");
			

		} else {
			// -- the user acces to the database
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoMissings dm = new DaoMissings(bddServletCo);
			
			// -- the user acces to the prom page
			p.setCss("promo.css"); p.setJs("promo.js");
			p.setContent("users/promo.jsp");
			p.setTitle("ISEP / APP - Promotion");
			
			// -- get all the groups
			String[] allClass = dg.selectAllClass();
			Group[] groups = dg.selectAll();
			HashMap<String, ArrayList<Group>> prom = new HashMap<String, ArrayList<Group>>();
			for(String c : allClass){
				ArrayList<Group> team = new ArrayList<Group>(); 
				prom.put(c, team);
			}
			
			for(Group g : groups){
				dg.completeTutor(g);
				dg.completeMemebers(g);
				prom.get(g.get_class()).add(g);
			}

			// --  missing per group
			HashMap<String, Missing[]> grpMissings = new HashMap<String, Missing[]>();
			for(Group g : groups){
				grpMissings.put(g.getName(), dm.selectForGroup(g.getName()));
			}
			
			
			request.setAttribute("groups", groups);
			request.setAttribute("allClass", allClass);
			request.setAttribute("prom",prom);
			request.setAttribute("missings", grpMissings);
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
