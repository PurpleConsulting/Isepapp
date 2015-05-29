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
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class AlterUser
 */
@WebServlet("/AlterUsers")
public class AlterUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterUsers() {
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
		
		
		// -- All type of request
		String scope = request.getParameter("scope"); /** student, tutor, ... **/
		
		// -- Alter student request
		String stdFirstName = request.getParameter("std_first_name");
		String stdLastName = request.getParameter("std_last_name");
		String stdPseudo = request.getParameter("std_pseudo");
		String stdIsepNo = request.getParameter("std_no");
		String stdEmail = request.getParameter("std_email");
		String stdNewGroup = request.getParameter("std_new_group");
		
		
		if(Auth.isConnect(request)){
				// -- Since the user is connected he can access to the database
				Connection bddServletCo = Bdd.getCo();
				DaoUsers du = new DaoUsers(bddServletCo);
				
				
			if(!Isep.nullOrEmpty(scope, stdFirstName, stdLastName, stdPseudo, stdEmail, stdNewGroup)){
				/**
				 *  HERE THE USER TRY TO UPDATE A STUDENT
				 */
				
				System.out.print("----------------------------------------------------");
				User subjectUser = du.select(scope);
				if(subjectUser.getId() != 0){
					subjectUser.setFirstName(stdFirstName); subjectUser.setLastName(stdLastName);
					subjectUser.setPseudo(stdPseudo);
					subjectUser.setMail(stdEmail); subjectUser.setGroup(stdNewGroup);
					try{ subjectUser.setIsepNo(Integer.parseInt(stdIsepNo));} 
					catch (NumberFormatException e){ }
					boolean querysuccess = du.update(subjectUser, "");
					if(querysuccess){
						p.setSuccess(true);
						p.setSuccessMessage("les modification sur le profile ont bien été apportées.");
						
					} else {
						p.setError(true);
						p.setErrorMessage("une erreur est ssurvenue lors de la modification du profil étudiant."
								+ " Les modifications demandées peuvent ne pas avoir été réalisées.");
					}
					
				} else {
					p.setWarning(true);
					p.setWarningMessage("le profil étudiant à modifier n'a pas été trouvé dans la base de donnée.");
				}
				

			}
			
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("/Students?pseudo="+stdPseudo).include(request, response);
		}
	}

}
