package org.purple.model;

import java.util.function.Predicate;

public class  MyOwnClassMate<T> implements Predicate<T>{
	
	private String ref;
	
	public MyOwnClassMate(String ref){
		this.ref = ref;
	}
	
	@Override
	public boolean test(T u) {
		// TODO Auto-generated method stub
		if(((org.purple.bean.User) u).getPseudo().equals(this.ref)){
			return true;
		} else {
			return false;
		}
			
	}
	
}