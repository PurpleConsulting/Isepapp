package org.purple.model;

import java.util.ArrayList;

public class Average extends Avg{

	private ArrayList<Avg> grid = new ArrayList<Avg>();
	private boolean scaleD20;
	private String title;
	
	public Average(){
		this.value = 0.0;
		this.setScaleD20(false);
		/**
		 * If the value of the scale is false
		 * We divide by the number of composite to get the average.
		 */
	}
	public Average(boolean scaleD20){
		this.value = 0.0;
		this.setScaleD20(scaleD20);
	}
	public Average(String title, boolean scaleD20){
		this.setTitle(title);
		this.value = 0.0;
		this.setScaleD20(scaleD20);
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
	public boolean isScaleD20() {
		return scaleD20;
	}
	public void setScaleD20(boolean scaleD20) {
		this.scaleD20 = scaleD20;
	}
	
	@Override
	public double compute() {
		// TODO Auto-generated method stub
		double res = 0.0;
		for(Avg mark : this.grid){
			res = res + mark.compute();
		}
		this.value = res;
		if(!this.scaleD20){// -- the sum is > 20 / we divide to get an average
			return (res / (double)this.grid.size());
		} else {// --  the sum is < 20 we take the sum to get an mark
			return res;
		}
	}
	
	public void push(Avg mark){
		this.grid.add(mark);
	}

}
