package org.purple.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
}
