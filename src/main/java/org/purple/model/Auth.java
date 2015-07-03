package org.purple.model;
import javax.servlet.http.HttpServletRequest;
import org.purple.bean.User;

/**
 * @author LoicMDIVAD
 */
public class Auth {
	
	public static final String respo = "respo";
	public static final String tutor = "tutor";
	public static final String student = "student";
	public static final String admin = "administration";
	
	/** Take an user request HttpServletRequest Return True if the User is connected, False otherwise.
	 *  @param request (HttpServletRequest)
	 *  @return (boolean)
	 */
	public static boolean isConnect(HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("user");
		if(u != null){
			return true;
		} else {
			return false;
		}
	}
		
	/**This function compare the user position with one of the available position (Auth.stutend, Auth.tutor...).
	 * If the positions matches the user had a good permission and the function return True, False otherwise.
	 * DON'T USE THIS LIGHT, better user Auth.isStudent() Auth.isRespo ...
	 * @param request (HttpServletRequest)
	 * @param position (String)
	 * @return (boolean)
	 */
	private static boolean isUser(HttpServletRequest request, String position){
		User u = (User)request.getSession().getAttribute("user");
		if(u != null && u.getPosition().equals(position)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param request
	 * @param group
	 * @return (boolean)
	 */
	private static boolean isGroupOwner(HttpServletRequest request, String group){
		User u = (User)request.getSession().getAttribute("user");
		int id = DaoGroups.returnTutor(group);
		if(u.getId() == id){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param request
	 * @param group
	 * @return (boolean)
	 */
	private static boolean isStudentOwner(HttpServletRequest request, String group){
		User u = (User)request.getSession().getAttribute("user");
		int id = DaoUsers.returnTutor(group);
		if(u.getId() == id){
			return true;
		} else {
			return false;
		}
	}
	
	/**Take a request from the user and returns True if is rôle is Respo (see Auth.respo) otherwise it returns False.
	 * Use only in a HttpServlet
	 * @param request (HttpServletRequest)
	 * @return (boolean)
	 */
	public static boolean isRespo(HttpServletRequest request){
		return isUser(request, Auth.respo);
	}
	
	/**Take a request from the user and returns True if is role is Tutor (see Auth.tutor) otherwise it returns False.
	 * Use only in a HttpServlet
	 * @param request (HttpServletRequest)
	 * @return (boolean)
	 */
	public static boolean isTutor(HttpServletRequest request){
		return isUser(request, Auth.tutor);
	}
	
	/**Take a request from the user and a group name (format: G8B). 
	 * Returns True if is role is Tutor and the group belong to him, otherwise it returns False.
	 * Use only in a HttpServlet
	 * @param request
	 * @param group
	 * @return
	 */
	public static boolean isTutor(HttpServletRequest request, String group){
		if(isConnect(request)){
			return isGroupOwner(request, group);
		} else{
			return false;
		}
	}
	
	/**Take a request from the user and a student pseudo (format: zkaneswa).
	 * Returns True if is role is Tutor and the student belong to him, otherwise it returns False.
	 * Use only in a HttpServlet
	 * @param request
	 * @param student
	 * @return
	 */
	public static boolean isStudentTutor(HttpServletRequest request, String student){
		if(isUser(request, Auth.tutor)){
			return isStudentOwner(request, student);
		} else{
			return false;
		}
	}
	
	/**Take a request from the user and returns True if is role is Student (see Auth.student) otherwise it returns False.
	 * Use only in a HttpServlet
	 * @param request
	 * @return
	 */
	public static boolean isStudent(HttpServletRequest request){
		return isUser(request, Auth.student);
	}
	
	/**Take a request from the user and returns True if is role is Admin (see Auth.admin) otherwise it returns False.
	 * Use only in a HttpServlet
	 * @param request
	 * @return
	 */
	public static boolean isAdmin(HttpServletRequest request){
		return isUser(request, Auth.admin);
	}
}
