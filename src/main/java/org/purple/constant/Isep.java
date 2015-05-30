package org.purple.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpSession;

import org.purple.bean.Page;
import org.purple.bean.User;

public class Isep {

		
		public static final double LANDMARK = 20.0;
		
		public static final String LOCATION = "Europe/Paris";
		// -- This binding to a timezone id of orr.joda.time
		public static final String MYSQL_UTC = "%x-%m-%d %H:%i:%s";
		public static final String JODA_UTC = "yyyy-MM-dd HH:mm:ss";	
		
		public static final double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();
		    // -- Just to round with a number of decimal
		    BigDecimal bd;
		    try{
		    	bd = new BigDecimal(value);
		    } catch(NumberFormatException e){
		    	//e.printStackTrace();
		    	bd = new BigDecimal(0);
		    }
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}
		
		public static final boolean nullOrEmpty(String... param){
			for(String p : param){
				if(p == null || p.equals("")){
					return true;
				}
			}
			return false;
		}
		
		public static final void bagPackHome(Page p, HttpSession s){
			User u = (User) s.getAttribute("user");
			try{
				u.getId();
			} catch (NullPointerException e) {
				u = new User();
			}
			p.setJs("bootstrap-select.min.js","home_"+u.getPosition()+".js"); 
			p.setCss("bootstrap-select.min.css","home_"+u.getPosition()+".css");
		}
}
