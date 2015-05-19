package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoGroups;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class AlterGroups
 */
@WebServlet("/AlterGroups")
public class AlterGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterGroups() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		if(request.getParameter("scope") != null){
			// --  the group is real
			String thegroup = request.getParameter("scope");
			
			if(Auth.isTutor(request, thegroup) || Auth.isRespo(request)){
				
				DaoUsers du = new DaoUsers(Bdd.getCo());
				DaoGroups dg = new DaoGroups(Bdd.getCo());
				// -- reday to perfom query on the database
				
				p.setCss("bootstrap-select.min.css", "edit_group.css"); 
				p.setJs("bootstrap-select.min.js","bootbox.min.js", "edit_group.js");
				p.setTitle("ISEP / APP - Edition de group");
				p.setContent("editor/edit_group.jsp");
				
				User[] teachers = du.selectAllTutor(); 		// -- We need to display all tutors
				Group group = dg.select(thegroup); 			// -- Get the group 
				dg.completeTutor(group);					// -- add the tutor of the group
				dg.completeMemebers(group);					// -- add the student of the group
				
				request.setAttribute("teachers", teachers);
				request.setAttribute("group", group);
				
				du.close();
				dg.close(); // -- we close the connection
				
			} else if(!Auth.isTutor(request, thegroup) && Auth.isTutor(request)) {
				p.setCss(""); p.setJs("");
				p.setContent("home.jsp");
				p.setTitle("ISEP / APP - Authorisation");
				p.setWarning(true);
				p.setWarningMessage("Vous ne semblez pas être le propriétaire de ce groupe. Les tuteurs ne peuvent éditer que leurs "
						+ "propres groupes. Vous remarquez une erreur sur un group? Contactez le reponsable d'APP.");
				
			}
			
		} else {
			
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
		
		// -- First form alternative -- modify group
		String scope = request.getParameter("scope");
		String newName = request.getParameter("new_name");
		String newTutor = request.getParameter("new_tutor");
		
		// -- Second form alternative -- add a user to a group
		String stdFirstName = request.getParameter("std_first_name");
		String stdLastName = request.getParameter("std_last_name");
		String stdPseudo = request.getParameter("std_pseudo");
		String stdEmail = request.getParameter("std_email");
		
		// -- Third form alternative -- delete a user from a group
		String deleteStd = request.getParameter("delete-std");
		String suggestionDeleteStd = request.getParameter("suggestion-std");
		
		// -- forth form alternative -- delete an entire group
		String deleteGrp = request.getParameter("delete-grp");
		String suggestionGroup = request.getParameter("suggestion-grp");
		
		// -- fith form alternative -- add a group
		String newGrp = request.getParameter("new_grp");
		String newGrpTutor = request.getParameter("new_grp_tutor");
		
		if(Auth.isTutor(request, scope) || Auth.isRespo(request)){
			
			DaoUsers du = new DaoUsers(Bdd.getCo());
			DaoGroups dg = new DaoGroups(Bdd.getCo());
			// -- we are ready to perform query on the database
			
			Group redirectionGroup = new Group();
			// -- since the user is authorize on the page we already know 
			//    he will be able to return on a altergroup page
			
			if(!Isep.nullOrEmpty(scope, newName, newTutor)){
				/**
				 * HERE THE USER WANT TO CHANGE THE GROUP PROPERTY
				 * GROUP NAME AND TUTOR
				 */
				
				// --  The form is completed, We Do the modifications!
				Group newGroup = new Group(newName, newTutor);
				
				boolean querrySucces  =  dg.update(newGroup, scope);
				if(querrySucces){
					p.setSuccess(true);
					p.setSuccessMessage("La mise à jour du group à bien été éffectuée.");
					redirectionGroup = dg.select(newName); 			// -- Get the group 
				} else {
					p.setError(true);
					p.setErrorMessage("Une erreur est survenu lors de la mise à jour du group."
							+ " Assurez vous de bien entrer un nom de group d'au moins 3 carractères, "
							+ "et veillez également à ce que ce nom de group ne soit pas déjà utilisé.");
					redirectionGroup = dg.select(scope); 			// -- Get the group 
				}
				
				p.setCss("bootstrap-select.min.css", "edit_group.css"); 
				p.setJs("bootstrap-select.min.js","bootbox.min.js", "edit_group.js");
				p.setTitle("ISEP / APP - Edition de group");
				p.setContent("editor/edit_group.jsp");
				
			} else if(!Isep.nullOrEmpty(stdFirstName, stdLastName, stdPseudo, stdEmail, scope)) {
				/**
				 * HERE THE USER WANT TU ADD A STUDENT IN THE GROUP
				 */
				redirectionGroup = dg.select(scope); 				// -- Get the group 
				User newUser = new User(stdFirstName, stdLastName, stdPseudo, stdEmail, Auth.student);
				newUser.setGroup(scope);
				
				boolean querrySucces = du.create(newUser);
				if(querrySucces){
					p.setSuccess(true);
					p.setSuccessMessage("l'étudiant " + newUser.getFirstName() + " " +  newUser.getLastName() + ""
							+ " a bien été ajouter au groupe " + scope + "." );
				} else {
					p.setError(true);
					p.setErrorMessage("une erreur c'est produite lors de l'ajout de l'étudiant " + newUser.getPseudo() +". \n"
							+ "Veuillez vérifier que ces pseudo et numéro isep ne soit pas déjà utilisés.");
				}
				
				p.setCss("bootstrap-select.min.css", "edit_group.css"); 
				p.setJs("bootstrap-select.min.js","bootbox.min.js", "edit_group.js");
				p.setTitle("ISEP / APP - Edition de group");
				p.setContent("editor/edit_group.jsp");
				
			} else if(!Isep.nullOrEmpty(deleteStd, suggestionDeleteStd)){
				/**
				 * HERE THE USER WANT TO DELETE A USER FROM THE GROUP.
				 * 
				 */
				if(deleteStd.equals(suggestionDeleteStd)){
					User exStudent = du.select(deleteStd);
					du.addGroup(exStudent);
					boolean querrySucces = du.delete(exStudent);
					redirectionGroup = dg.select(exStudent.getGroup());
					if(querrySucces){
						p.setSuccess(true);
						p.setSuccessMessage("la suppréssion de l'étudiant " + exStudent.getFirstName() + " " + exStudent.getLastName() + " à bien été réalisée.");
					} else{
						p.setError(true);
						p.setErrorMessage("Une erreur est survenue lors de la suppréssion de l'étudiant.");
						redirectionGroup.setId(-1);
					}
					
					
				} else{
					p.setWarning(true);
					p.setWarningMessage("l'oppération à mal été éffectuée, veuillez répéter l'oppération en remplissant correctement les champs proposés.");
					
				}
					p.setCss("bootstrap-select.min.css", "edit_group.css"); 
					p.setJs("bootstrap-select.min.js","bootbox.min.js", "edit_group.js");
					p.setTitle("ISEP / APP - Edition de group");
					p.setContent("editor/edit_group.jsp");
					
					
			}else if(!Isep.nullOrEmpty(deleteGrp, suggestionGroup) && Auth.isRespo(request)){
				/**
				 * HERE THE USER WANT TO DELETE A GROUP
				 */
				if(deleteGrp.equals(suggestionGroup)){
					Group exGrp = dg.select(deleteGrp);
					boolean querrySucces = dg.delete(exGrp);
					redirectionGroup = dg.select(deleteGrp);
					if(querrySucces){
						p.setSuccess(true);
						p.setSuccessMessage("le groupe demendé à bien été supprimé.");
						p.setCss("bootstrap-select.min.css", "home_respo.css"); 
						p.setJs("bootstrap-select.min.js","bootbox.min.js", "home_respo.js");
						p.setTitle("ISEP / APP - Accueil");
						p.setContent("home/respo.jsp");
						redirectionGroup.setId(-1); // -- we skip the error, there is no group because we just deleted it 
					} else{
						p.setWarning(true);
						p.setWarningMessage("ce groupe n'a pas peu être supprimé car une erreur c'est produite.");
						p.setCss("bootstrap-select.min.css", "edit_group.css"); 
						p.setJs("bootstrap-select.min.js","bootbox.min.js", "edit_group.js");
						p.setTitle("ISEP / APP - Edition de group");
						p.setContent("editor/edit_group.jsp");
					}
				}
				
			} else if(!Isep.nullOrEmpty(newGrp, newGrpTutor) && Auth.isRespo(request)){
				if(!newGrp.substring(0, 1).equals("G")) newGrp = "G" + newGrp; 
				if(newGrpTutor.equals("(vide)")) newGrpTutor = "null";
				/**
				 * HERE THE USER WANT TO ADD A GROUP
				 */// -- We prepare the group for the SQL INSERT
				Group newG = new Group(newGrp, newGrpTutor);
				boolean querrySucces = dg.create(newG);
				if(querrySucces){
					
					p.setSuccess(true);p.setSuccessMessage("Le groupe " + newGrp +" viens d' être crée. Vous pouvez dès maintenant y ajouter des étudiants.");
					p.setCss("bootstrap-select.min.css", "edit_group.css"); 
					p.setJs("bootstrap-select.min.js","bootbox.min.js", "edit_group.js");
					p.setTitle("ISEP / APP - Edition de group");
					p.setContent("editor/edit_group.jsp");
				} else {
					p.setError(true);p.setErrorMessage("Il s'est produit une erreur lors de l'ajourt du groupe."
							+ " Assurez vous que le nom de groupe soit au format 'G1A' et qu'il n'est pas encore utilisé.");
					p.setCss("bootstrap-select.min.css", "home_respo.css"); 
					p.setJs("bootstrap-select.min.js","bootbox.min.js", "home_respo.js");
					p.setTitle("ISEP / APP - Accueil");
					p.setContent("home/respo.jsp");
					
				}
				redirectionGroup = dg.select(newGrp);
				
			}
			
			User[] teachers = du.selectAllTutor(); 		// -- We need to display all tutors
			if(redirectionGroup.getId() == 0){
				// -- Last verification
				p.setContent("error/500.jsp"); 
				p.setTitle("ISEP / APP - Erreur");
			} else { 
				dg.completeMemebers(redirectionGroup); 
				dg.completeTutor(redirectionGroup); 
			}
			request.setAttribute("teachers", teachers);
			request.setAttribute("group", redirectionGroup);
			
			du.close();
			dg.close(); // -- We close the Dao connection
			
		} else if(!Auth.isTutor(request, scope) && Auth.isTutor(request)) {
			
			
		}
		
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

}
