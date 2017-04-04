package model.solvers.cross;

import model.population.Population;


public abstract class CrossAlgorithm {
	
	protected double crossRate;
	
	public CrossAlgorithm(double crossRate){
		this.crossRate = crossRate;
	}
	
	public abstract Population cross(Population population);
}
