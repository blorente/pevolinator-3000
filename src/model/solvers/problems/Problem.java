package model.solvers.problems;

import model.solvers.fitness.Fitness;

public class Problem {
	protected int populationSize;
	protected int numberGenerations;
	protected Fitness fitness;
	protected double intervalMin;
	protected double intervalMax;


	public Problem(int populationSize, int numberGenerations, Fitness fitness, double intervalMin, double intervalMax){
		this.populationSize = populationSize;
		this.numberGenerations = numberGenerations;
		this.fitness = fitness;
		this.intervalMax = intervalMax;
		this.intervalMin = intervalMin;
	}
	
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
