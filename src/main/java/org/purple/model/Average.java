package org.purple.model;

import java.util.ArrayList;

import org.purple.constant.Isep;

public class Average extends Avg{

	private ArrayList<Avg> grid = new ArrayList<Avg>();
	private String title = "null";
	private double maxVal = 1;
	private boolean cross = false;
	
	public Average(){
		this.value = 0.0;
		/**
		 * If the value of the scale is false
		 * We divide by the number of composite to get the average.
		 */
	}
	public Average(boolean unit){
		this.value = 0.0;
	}
	public Average(String title, double maxVal){
		this.setTitle(title);
		this.value = 0.0;
		this.setMaxVal(maxVal);
	}
	
	public int getSize(){
		int i = 0;
		for(Avg a : this.grid ){
			Average av = (Average)a ;
			if(av.grid.size() != 0){
				i++;
			}
		}
		return i;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<Avg> getGrid() {
		return grid;
	}
	public void setGrid(ArrayList<Avg> grid) {
		this.grid = grid;
	}
	public double getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(double maxVal) {
		this.maxVal = maxVal;
	}
	public boolean isCross() {
		return cross;
	}
	public void setCross(boolean cross) {
		this.cross = cross;
	}
	@Override
	public double compute() {
		// TODO Auto-generated method stub
		if(this.grid.size() > 0){
			double sum = 0.0;
			int length = 0;
			for(Avg mark : this.grid){
				sum = sum + mark.compute();
				length = length + mark.status();
			}// --
			
			double res = (sum / (double)length);
			// -- Average for a skill:
			if(Double.compare(Isep.LANDMARK, this.maxVal) != 0){
				res = (res * Isep.LANDMARK) / this.maxVal;
			}
			
			// -- Average for Student / group / prom
			this.value = res;
			return Isep.round(res, 2);
		} else {
			return 0.0;
		}
		
	}
	
	public void push(Avg mark){
		this.grid.add(mark);
	}
	@Override
	public int status() {
		// TODO Auto-generated method stub
		if(this.grid.size() != 0){
			return 1;
		} else {
			return 0;
		}
	}

}
