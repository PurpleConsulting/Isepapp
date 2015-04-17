package org.purple.model;

import javax.servlet.http.HttpServletRequest;

import org.purple.bean.User;

public class Auth {
	
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
	
	public static boolean isRespo(HttpServletRequest request){
		return isUser(request, "respo");
	}
	
	public static boolean isTutor(HttpServletRequest request){
		return isUser(request, "tutor");
	}
	
	public static boolean isStudent(HttpServletRequest request){
		return isUser(request, "student");
	}
	
	public static boolean isAdmin(HttpServletRequest request){
		return isUser(request, "administration");
	}
}
