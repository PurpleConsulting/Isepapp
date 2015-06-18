package org.purple.bean;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.purple.constant.Isep;

public class Deadline {

		private int id = 0; 
		private String description = "null";
		private String group = "null";
		private int idGroup=0;
		private Boolean status = false;
		private DateTime dateLimit = new DateTime(2000, 01,01, 0,0,0);
		private DateTime deliveryDate = new DateTime(2000, 01,01, 0,0,0);
		private Boolean completed = false;
		private int responsable=0;
		private int cross=0;
		private String deliveryPath = "null";
		
		
		public Deadline(){
			
		}
    
		public Deadline(int id, String description, String datelimit, int createur, Boolean status, String group){
			this.setId(id);
			this.setDescription(description);
			this.setDateLimit(datelimit);
			this.setResponsable(createur);
			this.setStatus(status);
			this.setGroup(group);
		}
		
		public Deadline(int id, String description, String datelimit, int createur, Boolean status){
			this.setId(id);
			this.setDescription(description);
			this.setDateLimit(datelimit);
			this.setResponsable(createur);
			this.setStatus(status);
			
		}

    
		public Deadline(int id, String datelimit, Boolean status, String group){
			this.setId(id);
			this.setDateLimit(datelimit);
			this.setStatus(status);
			this.setGroup(group);
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
		public String printDeliveryDate() {
			return deliveryDate.toString("d/MM à HH:mm");
		}
		public Boolean getCompleted() {
			return completed;
		}
		public void setCompleted(Boolean completed) {
			this.completed = completed;
		}

		public int getResponsable() {
			return responsable;
		}
		public void setResponsable(int responsable) {
			this.responsable = responsable;
		}

		public int getIdGroup() {
			return idGroup;
		}

		public void setIdGroup(int idGroup) {
			this.idGroup = idGroup;
		}

		public int getCross() {
			return cross;
		}

		public void setCross(int cross) {
			this.cross = cross;
		}

		public String getDeliveryPath() {
			return deliveryPath;
		}

		public void setDeliveryPath(String deliveryPath) {
			this.deliveryPath = deliveryPath;
		}
		
		
}
