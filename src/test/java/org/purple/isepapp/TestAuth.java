package org.purple.isepapp;

import static org.junit.Assert.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.purple.bean.User;
import org.purple.model.Auth;

public class TestAuth {
	private HttpServletRequest[] reqs = new HttpServletRequest[4];
	private HttpSession[] sess = new HttpSession[4];
	private User[] us = {new User(1, "slefebvr", "sylvain", "lefebvre", "respo"),
			new User(8, "mmenceny", "matthieu", "manceny", "tutor"),
			new User(3, "ldivad", "loic", "Divad", "student"),
			new User(4, "nlefebvr", "natacha", "lefebvre", "administration")
	};
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this); 
	    for(int i = 0; i < 4; i++){
	    	this.reqs[i] = Mockito.mock(HttpServletRequest.class);
	    	this.sess[i] = Mockito.mock(HttpSession.class);
	    	Mockito.when(sess[i].getAttribute("user")).thenReturn(this.us[i]);
			Mockito.when(reqs[i].getSession()).thenReturn(sess[i]);
	    }
	}
	
	@Test
	public void testIsConnect(){
		if(Auth.isConnect(reqs[0])){
			
		} else {
			fail();
		}
	}
	
	@Test
	public void testIsRespo(){
		if(Auth.isRespo(reqs[0])){
			
		} else {
			fail();
		}
	}
	
	@Test
	public void testIsTutor(){
		if(Auth.isTutor(reqs[1])){
			
		} else {
			fail();
		}
	}
	
	@Test
	public void testIsThatTutor(){
		if(Auth.isTutor(reqs[1], "G8B")){
			
		} else {
			fail();
		}
	}
	
	@Test
	public void testIsStudent(){
		if(Auth.isStudent(reqs[2])){
			
		} else {
			fail();
		}
	}
	
	@Test
	public void testIsAdministrator(){
		if(Auth.isAdmin(reqs[3])){
			
		} else {
			fail();
		}
	}

}
