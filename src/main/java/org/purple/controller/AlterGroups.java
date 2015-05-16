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
				p.setJs("bootstrap-select.min.js", "edit_group.js");
				p.setTitle("ISEP / APP - Edition de group");
				p.setContent("editor/edit_group.jsp");
				
				User[] teachers = du.selectAllTutor(); 		// -- We need to display all tutors
				Group group = dg.select(thegroup); 			// -- Get the group 
				dg.completeTutor(group);					// -- add the tutor of the group
				dg.completeMemebers(group);					// -- add the student of the group
				
				request.setAttribute("teachers", teachers);
				request.setAttribute("group", group);
				
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
		
		Page p = new Page();
		String scope = request.getParameter("scope");
		String newName = request.getParameter("new_name");
		String newTutor = request.getParameter("new_tutor");
		if(Auth.isTutor(request, scope) || Auth.isRespo(request)){
			
			DaoUsers du = new DaoUsers(Bdd.getCo());
			DaoGroups dg = new DaoGroups(Bdd.getCo());
			// -- reday to perfom query on the database
			
			if(!Isep.nullOrEmpty(scope, newName, newTutor)){
				// --  The form is completed, We Do the modifications!
				Group newGroup = new Group(newName, newTutor);
				Group redirectionGroup = new Group();
				
				boolean querrySucces  =  dg.update(newGroup, scope);
					p.setCss("bootstrap-select.min.css", "edit_group.css"); 
					p.setJs("bootstrap-select.min.js", "edit_group.js");
					p.setTitle("ISEP / APP - Edition de group");
					p.setContent("editor/edit_group.jsp");
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
				
				User[] teachers = du.selectAllTutor(); 		// -- We need to diplay all tutors
				dg.completeMemebers(redirectionGroup);
				dg.completeTutor(redirectionGroup);
				
				request.setAttribute("teachers", teachers);
				request.setAttribute("group", redirectionGroup);
				
				
			} else {
				
			}
	
			
		} else if(!Auth.isTutor(request, scope) && Auth.isTutor(request)) {

			
		}	
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

}
