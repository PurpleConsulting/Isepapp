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

import org.json.JSONObject;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.Dao;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;


/**
 * Servlet implementation class Tutors
 */
@WebServlet("/Tutors")
public class Tutors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tutors() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    protected void doRegular(HttpServletRequest request, HttpServletResponse response, Page p, DaoUsers du, DaoGroups dg){
    	p.setContent("users/tutor.jsp");
		p.setTitle("ISEP / APP - Les tuteurs");
		p.setJs("bootstrap-select.min.js", "bootbox.min.js", "tutor.js");
		p.setCss("bootstrap-select.min.css", "tutor.css");
		
		// -- we get the tutors
		User[] tutors = du.selectAllTutor();

		// -- we get the groups corresponding
		HashMap groups = new HashMap(); 
		for(User t : tutors){
			groups.put(t.getPseudo(),dg.selectAllName(Integer.toString(t.getId())));
		}
		// -- we get all class for the adding tutors form
		String[] allClass = dg.selectAllClass();

		// -- fill the request
		request.setAttribute("tutors", tutors);
		request.setAttribute("groups", groups);
		request.setAttribute("allClass", allClass);
		
		request.setAttribute("pages", p);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		// -- param
		
		if(Auth.isRespo(request)){
			
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			
			
			p.setContent("users/tutor.jsp");
			p.setTitle("ISEP / APP - Les tuteurs");
			p.setJs("bootstrap-select.min.js", "bootbox.min.js", "tutor.js");
			p.setCss("bootstrap-select.min.css", "tutor.css");
			
			// -- we get the tutors
			User[] tutors = du.selectAllTutor();

			// -- we get the groups corresponding
			HashMap groups = new HashMap(); 
			for(User t : tutors){
				groups.put(t.getPseudo(),dg.selectAllName(Integer.toString(t.getId())));
			}
			// -- we get all class for the adding tutors form
			String[] allClass = dg.selectAllClass();

			// -- fill the request
			request.setAttribute("tutors", tutors);
			request.setAttribute("groups", groups);
			request.setAttribute("allClass", allClass);
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("la page demandée n'est accessible qu'au responsable d'APP.");
		}
		
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		
		/**
		 * AJAX POST REQUEST PARAMS
		 */
		// --  fetch the group for the insert tutor
		String classParam = request.getParameter("class_name");
		
		/**
		 * POST REQUEST PARAMS
		 */
		// -- add tutor request
		String newFirstName = request.getParameter("new_first_name");
		String newLastName = request.getParameter("new_last_name");
		String newEmail = request.getParameter("new_email");
		String newClass = request.getParameter("new_class");
		String newPass = request.getParameter("new_password");
		String newPseudo = request.getParameter("new_pseudo");
		
		// -- delete tutor request
		String delTutorFlag = request.getParameter("delete-one-tutor");
		String oldTutor = request.getParameter("del-tutor");
		
		// -- delete all tutors request
		String delAllTutorsFlag = request.getParameter("delete-all-tutors");
		
		// -- alter student request
		String updatePseudo = request.getParameter("update_pseudo");
		String updateFirstName = request.getParameter("update_first_nane");
		String updateLastName = request.getParameter("update_last_name"); 
		String updateEmail = request.getParameter("update_email");
		String updatePassword = request.getParameter("update_password");
		String hasPsswdOrNot = request.getParameter("has-pass");
		String updateGroups = request.getParameter("update_group");
		
		if(Auth.isRespo(request)){
			/*  the use have access to the data base */
			Connection bddServletCo = Bdd.getCo();
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoUsers du = new DaoUsers(bddServletCo);
			
			if(!Isep.nullOrEmpty(classParam)){
				/**
				 * HERE THE USER ASK FOR SOME GROUP FOR THE NEW TUTOR
				 */

				Group[] groups = dg.selectGroupbyClass(classParam);
				JSONObject result = new JSONObject();
				ArrayList<JSONObject> jsGrooups = new ArrayList<JSONObject>();
				for(Group g : groups){
					JSONObject current = new JSONObject();
					current.put("id", g.getId());
					current.put("name", g.getName());
					jsGrooups.add(current);
				}
				result.put("groups", jsGrooups);
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(new JSONObject().put("result", result).toString());
				
			} else if(!Isep.nullOrEmpty(newFirstName, newLastName, newEmail, newPseudo, newClass)){
				/**
				 * HERE THE USER WANT TO ADD A NEW TUTOR
				 */
				User newTutor = new User(newFirstName, newLastName, newPseudo, newEmail, Auth.tutor);
				boolean querrySuccess = du.createTutor(newTutor);
				
				if(!Isep.nullOrEmpty(newPass) && querrySuccess){
					// -- this is an external teacher
					newTutor = du.select(newTutor.getPseudo());
					newTutor.setPassword(newPass);
					querrySuccess = querrySuccess & du.setPassword(newTutor);
				}
				
				if(!newClass.equals("null") && querrySuccess){
					// -- the new tutor has already a class
					newTutor = du.select(newTutor.getPseudo());
					boolean positionSuccess = dg.declareTutor(newTutor, newClass);
					if(!positionSuccess){
						p.setWarning(true);
						p.setWarningMessage("une erreur s'est produite lors de l'attribution des groupes au nouveau tuteur.");
					}
				}
				
				if(querrySuccess){
					p.setSuccess(true);
					p.setSuccessMessage("le nouveau tuteur a été correctement ajouté.");
					
				} else {
					p.setError(true);
					p.setErrorMessage("une erreur s'est produite lors de l'ajout du tuteur. "
							+ "Vérifiez que le pseudo soit bien unique.");
				}
				
				this.doRegular(request, response, p, du, dg);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
				
			} else if(!Isep.nullOrEmpty(delTutorFlag, oldTutor)){
				/**
				 * HERE THE USER TRY TO DELETE A USER
				 */
				User oldUser = du.select(oldTutor);
				boolean querrySuccess = du.delete(oldUser);
				if(querrySuccess){
					p.setSuccess(true);
					p.setSuccessMessage("le tuteur a bien été supprimé.");
				} else {
					p.setWarning(true);
					p.setWarningMessage("une erreur s'est produite lors de la suppression du tuteur.");
				}
				
				this.doRegular(request, response, p, du, dg);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			
			} else if (!Isep.nullOrEmpty(delAllTutorsFlag)){
				if(du.dropByPosition(Auth.tutor)){
					p.setSuccess(true);
					p.setSuccessMessage("les tuteurs ont bien été supprimés.");
				} else {
					p.setError(true);
					p.setErrorMessage("une erreur s'est produite lors de la suppression des tuteurs d'APP.");
				}
				this.doRegular(request, response, p, du, dg);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
				
			} else if (!Isep.nullOrEmpty(updatePseudo, updateFirstName, updateLastName, updateEmail, updateGroups,  updateGroups)){
				/**
				 *  HERE THE USER WANT TO UPDATE A TUTOR
				 */
				// --find the user 
				User alterTutor = du.select(updatePseudo);
				// -- perform the updates
				alterTutor.setFirstName(updateFirstName);
				alterTutor.setLastName(updateLastName);
				alterTutor.setMail(updateEmail);
				// -- save in the database
				boolean querrySuccess = du.update(alterTutor);
				
				if(!Isep.nullOrEmpty(hasPsswdOrNot, updatePassword)){
					// --  we add a password to the tutor
					alterTutor.setPassword(updatePassword);
					querrySuccess = querrySuccess & du.setPassword(alterTutor);
				}
				
				if(!updateGroups.equals("null") && querrySuccess){
					// -- wed add a new class to the tutor
					querrySuccess = querrySuccess & dg.retireTutor(alterTutor);
					querrySuccess = querrySuccess & dg.declareTutor(alterTutor, updateGroups);
				}
				
				if(querrySuccess){
					p.setInfo(true);
					p.setInfoMessage("la modification du tuteur a bien été prise en compte.");
				} else {
					p.setWarning(true);
					p.setWarningMessage("une erreur est survenue lors de la modification du tuteur.");
				}
				
				this.doRegular(request, response, p, du, dg);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			} else {
				p.setWarning(true);
				p.setWarningMessage("la requête demandée a mal été interprétée, veuillez vous assurer que vous"
						+ " remplissez tous les champs des formulaies proposés.");
				this.doRegular(request, response, p, du, dg);
				this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			p.setWarning(true);
			p.setWarningMessage("cette page est accessible uniquement au responsable d'APP.");
			Isep.bagPackHome(p, request.getSession());
			
			request.setAttribute("pages", p);
			this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
			
		}
	}

}
