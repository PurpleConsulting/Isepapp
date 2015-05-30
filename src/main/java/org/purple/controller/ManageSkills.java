package org.purple.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.purple.bean.Page;
import org.purple.bean.Skill;
import org.purple.bean.SubSkill;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoSkills;
import org.purple.model.DaoSubSkills;


/**
 * Servlet implementation class ManageSkills
 */
@WebServlet("/ManageSkills")
public class ManageSkills extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String paramSubSkillTitle = "sub_skill_title"; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageSkills() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		if(Auth.isRespo(request)){
			// -- create the dao to perorme queries
			Connection bddServletCo = Bdd.getCo();
			DaoSkills ds = new DaoSkills(bddServletCo);
			
			// -- prepare the skill we need to display
			Skill[] allskills = ds.selectAllSkills();
			for(Skill s : allskills){
				ds.completeSub_skills(s);
			}
			
			
			request.setAttribute("skills", allskills);
			
			p.setCss("bootstrap-select.min.css", "manage_skills.css");
			p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
			p.setContent("/skills/manage_skill.jsp");
			p.setTitle("ISEP / APP - Gestion des Compétences");
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			HttpSession s = request.getSession();
			Isep.bagPackHome(p, s);
			p.setTitle("ISEP / APP - Accueil");
			p.setWarning(true); p.setContent("/home/common.jsp");
			p.setWarningMessage("La page que vous tentez d'atteindre est réservé au tuteur d'APP.");	
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
		// -- Global
		ArrayList<String> _params = Collections.list(request.getParameterNames());
		
		// -- Alter skill request
		String skillId = request.getParameter("skill_id");
		String skillTitle = request.getParameter("skill_title");
		String skillSubTile = request.getParameter("skill_subtitle");
		// -- //--Complex operation to get le subskills sent
		ArrayList<String> subSkillParams = new ArrayList<String>();
		HashMap<String, String> subSkillsArgs = new HashMap();
		for(String _p : _params){
			if(_p.indexOf(this.paramSubSkillTitle) != -1){
				subSkillParams.add(_p);
			}
		}
		for(String s : subSkillParams){
			subSkillsArgs.put(s.substring(this.paramSubSkillTitle.length()), request.getParameter(s));
		}
		
		// -- Add skill request
		String addSkillFlag = request.getParameter("add_skill_flag");
		String newSkillTitle = request.getParameter("new_skill_title");
		String newSkillSubTitle = request.getParameter("new_skill_subtitle");
		
		// -- Add sub skill request
		String addSubSkillFlag = request.getParameter("add_subskill_flag");
		String newSubSkill = request.getParameter("new_subskill_title");
		String newDescription = request.getParameter("new_subskill_desc");
		
		// -- Delete skill request
		String delSkillFlag = request.getParameter("del_skill_flag");
		String delSkill = request.getParameter("del_skill");
		
		// -- Delete subskill request
		String delSubSkillFlag = request.getParameter("del_subskill_flag");
		String delSubSkill = request.getParameter("del_subskill");
		
		// --
		// Start of the opps
		// --
		
		if(Auth.isRespo(request)){
			// -- The user can access to the data base
			Connection bddServletCo = Bdd.getCo();
			DaoSkills ds = new DaoSkills(bddServletCo);
			DaoSubSkills dss = new DaoSubSkills(bddServletCo);
			
			
			if(!Isep.nullOrEmpty(skillId, skillTitle) && skillSubTile != null){
				/**
				 *  HERE THE USER TRY TO MODIFY AN EXISTING SKILL
				 */
				// --  this is a Alter skill/subskill request
				Skill skillbean = new Skill(Integer.parseInt(skillId), skillTitle, skillSubTile);
				//ArrayList<SubSkill> subSkilsBeans = new ArrayList<SubSkill>();
				Iterator i = subSkillsArgs.entrySet().iterator(); 
				while(i.hasNext()) { 
					Map.Entry element = (Map.Entry)i.next();
					skillbean.setSubSkills(new SubSkill(Integer.parseInt((String)element.getKey()),
							Integer.parseInt(skillId),
							(String)element.getValue()));
				}
				boolean  querrysuccess = true;
				querrysuccess = dss.updateMulti(skillbean.getSubSkills());
				querrysuccess = querrysuccess & ds.update(skillbean, "");
				if(querrysuccess){
					p.setSuccess(true);
					p.setSuccessMessage("la modification de la grille de compétance a bien été éffectuée.");
				} else {
					p.setError(true);
					p.setErrorMessage("une erreur est survenue lors de la modification de la grille de compétence. Nous vous invitons"
							+ "à véirifier que vos changements aient bien été appliqués, et sinon répéter l'oppération.");
				}
				p.setCss("bootstrap-select.min.css", "manage_skills.css");
				p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
				p.setContent("/skills/manage_skill.jsp");
				p.setTitle("ISEP / APP - Gestion des Compétences");
				
				int support = skillbean.getId();
				request.setAttribute("support", support);

			} else if(!Isep.nullOrEmpty(addSkillFlag, newSkillTitle) && newSkillSubTitle != null) {
				/**
				 *  HERE THE USER WANT TO ADD A NEW SKILL
				 */
				Skill skill = new Skill(newSkillTitle, newSkillSubTitle);
				boolean querysuccess = ds.create(skill);
				if(querysuccess){
					p.setSuccess(true);
					p.setSuccessMessage("l'ajout d'une compétence à bien été effectué. Ajoutez dès mainteant "
							+ "des sous compétences pour permettre la notation des étudiants.");
				} else {
					p.setError(true);
					p.setErrorMessage("l'ajout d'une compétence à provoqué une erreur interne. Nous vous invitons"
							+ " à bien vérifier la présence de celle-ci.");
				}
				p.setCss("bootstrap-select.min.css", "manage_skills.css");
				p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
				p.setContent("/skills/manage_skill.jsp");
				p.setTitle("ISEP / APP - Gestion des Compétences");
				
				int support = -1;
				request.setAttribute("support", support);
				
			} else if (!Isep.nullOrEmpty(addSubSkillFlag, newSubSkill, newDescription)){
				/**
				 * HERE THE USER WANT TO ADD A NEW SUBSKILL IN AN EXISTING SKILL
				 */
				SubSkill subSkill = new SubSkill(newSubSkill, newDescription);
				subSkill.setId_skills(Integer.parseInt(addSubSkillFlag));
				boolean querysuccess = dss.create(subSkill);
				if(querysuccess){
					p.setSuccess(true);
					p.setSuccessMessage("l'ajout d'une sous-compétence à bien été effectué.");
				} else {
					p.setError(true);
					p.setErrorMessage("l'ajout d'une sous-compétence à provoqué une erreur interne. Nous vous invitons"
							+ " à bien vérifier la présence de celle-ci.");
				}
				p.setCss("bootstrap-select.min.css", "manage_skills.css");
				p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
				p.setContent("/skills/manage_skill.jsp");
				p.setTitle("ISEP / APP - Gestion des Compétences");
				
				int support = Integer.parseInt(addSubSkillFlag);
				request.setAttribute("support", support);
				
			} else if(!Isep.nullOrEmpty(delSkill, delSkillFlag) && !delSkillFlag.equals("0")){
				/**
				 * HERE THE USER WANT TO DELETE A SKILL
				 */
				if(!delSkillFlag.equals("0")){
					Skill OldSkill = ds.select(delSkillFlag);
					boolean querrysuccess = ds.delete(OldSkill);
					if(querrysuccess){
						p.setSuccess(true);
						p.setSuccessMessage("la suppression de la compétence à bien été effectuée. "
								+ "Les sous compétences associées on bien été effacées elles aussi.");
					} else {
						p.setError(true);
						p.setErrorMessage("Une erreur est survenu lors de la suppression de la compétence.");
					}
				} else { p.setWarning(true); p.setWarningMessage("la compétence évaluation croisée ne peut être supprimer."); }
				p.setCss("bootstrap-select.min.css", "manage_skills.css");
				p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
				p.setContent("/skills/manage_skill.jsp");
				p.setTitle("ISEP / APP - Gestion des Compétences");
				
			} else if(!Isep.nullOrEmpty(delSubSkillFlag, delSubSkill) ) {
				/**
				 *  HERE THE USER WANT TO DELETE A SUBSKILL
				 */
				SubSkill ss = dss.select(delSubSkillFlag);
				System.out.print(ss.getId());
				boolean querrysuccess = dss.delete(ss);
				if(querrysuccess){
					p.setSuccess(true);
					p.setSuccessMessage("la suppression de la sous-compétence à bien été éffectuée.");
				} else {
					p.setError(true);
					p.setErrorMessage("Une erreur est survenu lors de la suppression de la compétence."
							+ " Nous vous invitons à vérifier que l'oppération ai bien été effectuée.");
				}
				
				p.setCss("bootstrap-select.min.css", "manage_skills.css");
				p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
				p.setContent("/skills/manage_skill.jsp");
				p.setTitle("ISEP / APP - Gestion des Compétences");
				
				int support = ss.getIdSkill();
				request.setAttribute("support", support);
			} else {
				p.setWarning(true);
				p.setWarningMessage("votre préscédente requête n'a pas pue être correctement interprétée."
						+ " Il y manque peut-être des informations. Veillez à bien remplire les champs des formulaires proposées.");
				p.setCss("bootstrap-select.min.css", "manage_skills.css");
				p.setJs("bootbox.min.js","bootstrap-select.min.js", "manage_skills.js");
				p.setContent("/skills/manage_skill.jsp");
				p.setTitle("ISEP / APP - Gestion des Compétences");
			}
			
			if(p.getContent().equals("/skills/manage_skill.jsp")){
				// -- load the content of the redirection page
				Skill[] allskills = ds.selectAllSkills();
				for(Skill s : allskills){
					ds.completeSub_skills(s);
				}
				request.setAttribute("skills", allskills);
				
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			HttpSession s = request.getSession();
			Isep.bagPackHome(p, s);
			p.setTitle("ISEP / APP - Accueil");
			p.setWarning(true); p.setContent("/home/common.jsp");
			p.setWarningMessage("La page que vous tentez d'atteindre est réservé au tuteur d'APP.");		
		}
		
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
	}

}
