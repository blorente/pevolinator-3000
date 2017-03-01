package model.solvers.problems;

import model.solvers.fitness.Fitness;

public abstract class Problem {
	protected int populationSize;
	protected int numberGenerations;
	protected Fitness fitness;
	protected double intervalMin;
	protected double intervalMax;
	
	public int getPopulationSize() {
		return populationSize;
	}
	
	public int getGenerations() {		
		return numberGenerations;
	}

	public Fitness getFitness() {
		return fitness;
	}
}
