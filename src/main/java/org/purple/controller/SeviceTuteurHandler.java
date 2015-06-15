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

import org.json.JSONObject;
import org.purple.bean.Group;
import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;
import org.purple.model.DaoMissings;
import org.purple.model.DaoUsers;

/**
 * Servlet implementation class SeviceTuteurHandler
 */
@WebServlet("/SeviceTuteurHandler")
public class SeviceTuteurHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeviceTuteurHandler() {
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
		JSONObject result = new JSONObject();
		/**
		 * AJAX PARAMETERS PART
		 */
		// -- find the group and propose a new password
		String tutorPseudo = request.getParameter("isExternal");
		// -- submit a new password
		String oldPassword = request.getParameter("old-pwd");
		String newPassword = request.getParameter("new-pwd");
		/**
		 * POST PARAMETERS PART
		 */
		request.getParameter("");
		
		if(Auth.isTutor(request)){
			
			Connection bddServletCo = Bdd.getCo();
			DaoUsers du = new DaoUsers(bddServletCo);
			DaoGroups dg = new DaoGroups(bddServletCo);
			DaoMissings dm = new DaoMissings(bddServletCo);
			DaoDeadline dd = new DaoDeadline(bddServletCo);
			User t = (User)request.getSession().getAttribute("user");
			
			if(!Isep.nullOrEmpty(tutorPseudo)){
				User extUser = du.select(tutorPseudo);
				boolean extFlag = du.hasPassword(extUser);
				JSONObject ext = new JSONObject().put("isExt", extFlag);
				
				// -- now lets fetch the groups belonging to this tutor
				Group[] tutorGrps = dg.selectGroupbyTutor(t);
				ArrayList<JSONObject> jsonGrp = new ArrayList<JSONObject>();
				for(Group g : tutorGrps){
					JSONObject obj = new JSONObject();
					obj.put("id", g.getId());
					obj.put("name", g.getName());
					obj.put("missings", dm.selectForGroup(g.getName()).length);
					obj.put("deliveries", dd.depositPerGroups(g).length);
					jsonGrp.add(obj);
				}
				
				ext.put("groups", jsonGrp);
				result.put("result", ext);
				
			} else if (!Isep.nullOrEmpty(oldPassword, newPassword)){
				JSONObject ops = new JSONObject();
				t.setPassword(oldPassword);
				boolean querySuccess = du.confirmPwd(t);
				ops.put("password", querySuccess);
				if(querySuccess){
					t.setPassword(newPassword);
					querySuccess = du.setPassword(t);
				}
				ops.put("insert", querySuccess);
				result.put("result", ops);
			}
			
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JSONObject err = new JSONObject().put("status", false);
			JSONObject msg = new JSONObject().put("massage", "L'utilisateur n'est pas connecté.");
			result.put("err", err);
			result.put("message", msg);
			
		}
		
		response.setHeader("content-type", "application/json");
		response.getWriter().write(result.toString());
	}

}
