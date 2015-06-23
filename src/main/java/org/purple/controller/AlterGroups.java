package org.purple.controller;

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
 * Servlet implementation class AlterGroups
 */
@WebServlet("/AlterGroups")
public class AlterGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @see HttpServlet#HttpServlet()
	 */
	public AlterGroups() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		String thegroup = request.getParameter("scope");
		if (!Isep.nullOrEmpty(thegroup)) {
			// -- the group is real

			if (Auth.isTutor(request, thegroup) || Auth.isRespo(request)) {
				Connection bddServletCo = Bdd.getCo();
				DaoUsers du = new DaoUsers(bddServletCo);
				DaoGroups dg = new DaoGroups(bddServletCo);
				DaoDeadline dl = new DaoDeadline(bddServletCo);
				// -- ready to perfom query on the database

				Group group = dg.select(thegroup); // -- Get the group
				if (group.getId() != 0) {
					User[] teachers = du.selectAllTutor(); // -- We need to
															// display all
															// tutors
					dg.completeTutor(group); // -- add the tutor of the group
					dg.completeMemebers(group); // -- add the student of the
												// group
					Deadline[] allDeadlines = dl.selectByGroup(group.getName());
					for(Deadline d : allDeadlines){
						dl.checkOut(d);
					}
					
					request.setAttribute("deadlines", allDeadlines);
					request.setAttribute("teachers", teachers);
					request.setAttribute("group", group);

					p.setCss("bootstrap-select.min.css", "edit_group.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js",
							"edit_group.js");
					p.setTitle("ISEP / APP - Edition de groupes");
					p.setContent("editor/edit_group.jsp");
				} else {
					p.setWarning(true);
					p.setWarningMessage("le groupe que vous essayez d'atteindre n'a pas été trouvé.");
					HttpSession s = request.getSession();
					Isep.bagPackHome(p, s);
					p.setTitle("ISEP / APP - Accueil");
					p.setContent("home/common.jsp");
				}

				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (Auth.isTutor(request)) {
				// -- tutor but not for this group
				Isep.bagPackHome(p, request.getSession());
				p.setTitle("ISEP / APP - Erreur");
				p.setWarning(true);
				p.setWarningMessage("vous ne semblez pas être le propriétaire de ce groupe. Les tuteurs ne peuvent éditer que leurs "
						+ "propres groupes. Vous remarquez une erreur sur un groupe ? Contactez le responsable d'APP.");

			} else {
				// -- student / admin / or non connected user
				Isep.bagPackHome(p, request.getSession());
				p.setTitle("ISEP / APP - Erreur");
				p.setWarning(true);
				p.setWarningMessage("cette page n'est accessible qu'aux tuteurs et responsable d'APP.");

			}

		} else {
			// -- Missing parameter : Name of the group
			Isep.bagPackHome(p, request.getSession());
			p.setTitle("ISEP / APP - Erreur");
			p.setWarning(true);
			p.setWarningMessage("la page demandée n'a pas pu être retrouvée car il manque "
					+ "des renseignements dans l'adresse demandée.");
		}

		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

		if (Auth.isTutor(request, scope) || Auth.isRespo(request)) {

			Connection bddServletCo = Bdd.getCo();
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoDeadline dl = new DaoDeadline(bddServletCo);
			// -- we are ready to perform query on the database

			Group redirectionGroup = new Group();
			// -- since the user is authorize on the page we already know
			// he will be able to return on a altergroup page

			if (!Isep.nullOrEmpty(scope, newName, newTutor) && Auth.isRespo(request)) {
				/**
				 * HERE THE USER WANT TO CHANGE THE GROUP PROPERTIES GROUP NAME
				 * AND TUTOR
				 */

				// -- The form is completed, We Do the modifications!
				Group newGroup = dg.select(scope);
				newGroup.setName(newName);
				newGroup.setTutor(newTutor);

				boolean querrySucces = dg.update(newGroup);
				if (querrySucces) {
					p.setSuccess(true);
					p.setSuccessMessage("la mise à jour du groupe a bien été éffectuée.");
					redirectionGroup = dg.select(newName); // -- Get the group
				} else {
					p.setError(true);
					p.setErrorMessage("une erreur est survenue lors de la mise à jour du groupe."
							+ " Assurez-vous de bien entrer un nom de groupe d'au moins 3 caractères, "
							+ "et veillez également à ce que ce nom de groupe ne soit pas déjà utilisé.");
					redirectionGroup = dg.select(scope); // -- Get the group
				}

				p.setCss("bootstrap-select.min.css", "edit_group.css");
				p.setJs("bootstrap-select.min.js", "bootbox.min.js",
						"edit_group.js");
				p.setTitle("ISEP / APP - Edition de groupes");
				p.setContent("editor/edit_group.jsp");

			} else if (!Isep.nullOrEmpty(scope, newName, newTutor) && Auth.isTutor(request, scope)) {
				/**
				 * HERE THE USER WANT TO CHANGE THE GROUP PROPERTIES GROUP NAME
				 * AND TUTOR /!\ BUT HE IS NOT RESPO /!\
				 */
				redirectionGroup = dg.select(scope);
				p.setCss("bootstrap-select.min.css", "edit_group.css");
				p.setJs("bootstrap-select.min.js", "bootbox.min.js", "edit_group.js");
				p.setTitle("ISEP / APP - Edition de groupes");
				p.setWarning(true);
				p.setWarningMessage("les propriétés du groupe, nom et tuteur ne sont modifiables que par le responsable d'APP."
						+ " Prendre contact avec lui si une modification est nécessaire.");

			} else if (!Isep.nullOrEmpty(stdFirstName, stdLastName, stdPseudo,
					stdEmail, scope)) {
				/**
				 * HERE THE USER WANT TU ADD A STUDENT IN THE GROUP
				 */
				redirectionGroup = dg.select(scope); // -- Get the group
				User newUser = new User(stdFirstName, stdLastName, stdPseudo,
						stdEmail, Auth.student);
				newUser.setGroup(scope);

				boolean querrySucces = du.create(newUser);
				if (querrySucces) {
					p.setSuccess(true);
					p.setSuccessMessage("l'étudiant " + newUser.getFirstName()
							+ " " + newUser.getLastName() + ""
							+ " a bien été ajouté au groupe " + scope + ".");
				} else {
					p.setError(true);
					p.setErrorMessage("une erreur s'est produite lors de l'ajout de l'étudiant "
							+ newUser.getPseudo()
							+ ". \n"
							+ "Veuillez vérifier que ses pseudo et numéro ISEP ne soient pas déjà utilisés.");
				}

				p.setCss("bootstrap-select.min.css", "edit_group.css");
				p.setJs("bootstrap-select.min.js", "bootbox.min.js",
						"edit_group.js");
				p.setTitle("ISEP / APP - Edition de groupes");
				p.setContent("editor/edit_group.jsp");

			} else if (!Isep.nullOrEmpty(deleteStd, suggestionDeleteStd)) {
				/**
				 * HERE THE USER WANT TO DELETE A USER FROM THE GROUP.
				 * 
				 */
				if (deleteStd.equals(suggestionDeleteStd)) {
					User exStudent = du.select(deleteStd);
					du.addGroup(exStudent);
					boolean querrySucces = du.delete(exStudent);
					redirectionGroup = dg.select(exStudent.getGroup());
					if (querrySucces) {
						p.setSuccess(true);
						p.setSuccessMessage("la suppression de l'étudiant "
								+ exStudent.getFirstName() + " "
								+ exStudent.getLastName()
								+ " a bien été réalisée.");
					} else {
						p.setError(true);
						p.setErrorMessage("une erreur est survenue lors de la suppression de l'étudiant.");
						redirectionGroup.setId(-1);
					}
				} else {
					p.setWarning(true);
					p.setWarningMessage("l'opération a mal été éffectuée, veuillez répéter l'opération en remplissant correctement les champs proposés.");
				}
				p.setCss("bootstrap-select.min.css", "edit_group.css");
				p.setJs("bootstrap-select.min.js", "bootbox.min.js",
						"edit_group.js");
				p.setTitle("ISEP / APP - Edition de groupes");
				p.setContent("editor/edit_group.jsp");

			} else if (!Isep.nullOrEmpty(deleteGrp, suggestionGroup)) {
				/**
				 * HERE THE USER WANT TO DELETE A GROUP
				 */
				if (deleteGrp.equals(suggestionGroup)) {
					Group exGrp = dg.select(deleteGrp);
					boolean querrySucces = dg.delete(exGrp);
					redirectionGroup = dg.select(deleteGrp);
					if (querrySucces) {
						p.setSuccess(true);
						p.setSuccessMessage("le groupe demandé a bien été supprimé.");
						Isep.bagPackHome(p, request.getSession());
						p.setContent("home/respo.jsp");
						redirectionGroup.setId(-1); // -- we skip the error,
													// there is no group because
													// we just deleted it
					} else {
						p.setWarning(true);
						p.setWarningMessage("ce groupe n'a pas pu être supprimé car une erreur s'est produite.");
						p.setCss("bootstrap-select.min.css", "edit_group.css");
						p.setJs("bootstrap-select.min.js", "bootbox.min.js",
								"edit_group.js");
						p.setTitle("ISEP / APP - Edition de groupes");
						p.setContent("editor/edit_group.jsp");
					}
				}

			} else if (!Isep.nullOrEmpty(newGrp, newGrpTutor)
					&& Auth.isRespo(request)) {
				if (!newGrp.substring(0, 1).equals("G"))
					newGrp = "G" + newGrp;
				if (newGrpTutor.equals("(vide)"))
					newGrpTutor = "null";
				/**
				 * HERE THE USER WANT TO ADD A GROUP
				 */
				// -- We prepare the group for the SQL INSERT
				Group newG = new Group(newGrp, newGrpTutor);
				boolean querrySucces = dg.create(newG);
				if (querrySucces) {

					p.setSuccess(true);
					p.setSuccessMessage("le groupe "
							+ newGrp
							+ " vient d'être créé. Vous pouvez dès maintenant y ajouter des étudiants.");
					p.setCss("bootstrap-select.min.css", "edit_group.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js",
							"edit_group.js");
					p.setTitle("ISEP / APP - Edition de groupes");
					p.setContent("editor/edit_group.jsp");
				} else {
					p.setError(true);
					p.setErrorMessage("il s'est produit une erreur lors de l'ajout du groupe."
							+ " Assurez-vous que le nom de groupe soit au format 'G1A' et qu'il n'est pas encore utilisé.");
					p.setCss("bootstrap-select.min.css", "home_respo.css");
					p.setJs("bootstrap-select.min.js", "bootbox.min.js",
							"home_respo.js");
					p.setTitle("ISEP / APP - Accueil");
					p.setContent("home/respo.jsp");

				}
				redirectionGroup = dg.select(newGrp);

			} else if (!Isep.nullOrEmpty(scope)) {
				// -- No action are corresponding to this
				redirectionGroup = dg.select(scope);
				p.setCss("bootstrap-select.min.css", "edit_group.css");
				p.setJs("bootstrap-select.min.js", "bootbox.min.js",
						"edit_group.js");
				p.setTitle("ISEP / APP - Edition de groupes");
				p.setWarning(true);
				p.setWarningMessage("votre requête a mal été interprétée. Etes-vous sûr d'être propriétaire de ce groupe ?");

			}

			if (redirectionGroup.getId() == 0) {
				// -- Last verification
				p.setContent("error/500.jsp");
				p.setTitle("ISEP / APP - Erreur");
			} else {
				User[] teachers = du.selectAllTutor(); // -- We need to display
														// all tutors
				dg.completeMemebers(redirectionGroup);
				dg.completeTutor(redirectionGroup);
				Deadline[] allDeadlines = dl.selectByGroup(redirectionGroup.getName());
				for(Deadline d : allDeadlines){
					dl.checkOut(d);
				}
				
				request.setAttribute("teachers", teachers);
				request.setAttribute("group", redirectionGroup);
			}

			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("cette page n'est accessible qu'aux tuteurs et au responsable d'APP.");
			p.setTitle("ISEP / APP - Erreur");

		}

		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

}
