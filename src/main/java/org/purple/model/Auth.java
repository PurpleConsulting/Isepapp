package org.purple.model;

import javax.servlet.http.HttpServletRequest;

import org.purple.bean.User;

public class Auth {
	
	public static final String respo = "respo";
	public static final String tutor = "tutor";
	public static final String student = "student";
	public static final String admin = "administration";
	
	public static boolean isConnect(HttpServletRequest request){
		User u = (User)request.getSession().getAttribute("user");
		if(u != null){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isUser(HttpServletRequest request, String position){
		User u = (User)request.getSession().getAttribute("user");
		if(u != null && u.getPosition().equals(position)){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isGroupOwner(HttpServletRequest request, String group){
		User u = (User)request.getSession().getAttribute("user");
		int id = DaoGroups.returnTutor(group);
		if(u.getId() == id){
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isStudentOwner(HttpServletRequest request, String group){
		User u = (User)request.getSession().getAttribute("user");
		int id = DaoUsers.returnTutor(group);
		if(u.getId() == id){
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isRespo(HttpServletRequest request){
		return isUser(request, Auth.respo);
	}
	
	public static boolean isTutor(HttpServletRequest request){
		return isUser(request, Auth.tutor);
	}
	public static boolean isTutor(HttpServletRequest request, String group){
		if(isConnect(request)){
			return isGroupOwner(request, group);
		} else{
			return false;
		}
	}
	public static boolean isStudentTutor(HttpServletRequest request, String student){
		if(isUser(request, Auth.tutor)){
			return isStudentOwner(request, student);
		} else{
			return false;
		}
	}
	
	public static boolean isStudent(HttpServletRequest request){
		return isUser(request, Auth.student);
	}
	
	public static boolean isAdmin(HttpServletRequest request){
		return isUser(request, Auth.admin);
	}
}