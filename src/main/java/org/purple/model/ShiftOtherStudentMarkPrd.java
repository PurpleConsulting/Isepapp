package org.purple.model;

import java.util.function.Predicate;

public class ShiftOtherStudentMarkPrd<T> implements Predicate<T>{

	
	private String ref;
	
	public ShiftOtherStudentMarkPrd(String ref){
		this.ref = ref;
	}
	
	@Override
	public boolean test(T u) {
		// TODO Auto-generated method stub
		if(((org.purple.bean.Mark) u).getOwner().equals(this.ref)){
			return false;
		} else {
			return true;
		}
			
	}
	
}

