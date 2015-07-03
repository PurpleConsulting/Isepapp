package org.purple.model;

public abstract class Avg {

		protected double value;
		
		private String title = "null";
		
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public abstract double compute();
		
		public abstract int status();
}
