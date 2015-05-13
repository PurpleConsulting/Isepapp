package org.purple.bean;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.purple.constant.Isep;

public class Deadline {

		private int id; 
		private String description;
		private Boolean status;
		private String group;
		private DateTime dateLimit;
		private DateTime deliveryDate = new DateTime(2000, 01,01, 0,0,0);
		private Boolean completed = false;
		
		public Deadline(){
			
		}

		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
		public String getGroup() {
			return group;
		}
		public void setGroup(String group) {
			this.group = group;
		}
		public DateTime getDateLimit() {
			return dateLimit;
		}
		public String printDateLimit() {
			return dateLimit.toString("d/MM à HH:mm");
		}
		public void setDateLimit(String date) {
			this.dateLimit = DateTime.parse(date, DateTimeFormat.forPattern(Isep.JODA_UTC));
			this.dateLimit = this.dateLimit.withZone(DateTimeZone.forID(Isep.LOCATION));
		}
		public DateTime getDeliveryDate() {
			return deliveryDate;
		}
		public void setDeliveryDate(String date) {
			this.deliveryDate = DateTime.parse(date, DateTimeFormat.forPattern(Isep.JODA_UTC));
			this.deliveryDate = this.deliveryDate.withZone(DateTimeZone.forID(Isep.LOCATION));
		}
		public Boolean getCompleted() {
			return completed;
		}
		public void setCompleted(Boolean completed) {
			this.completed = completed;
		}
		
		
		
}
