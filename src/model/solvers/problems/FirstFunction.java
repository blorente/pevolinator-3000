package model.solvers.problems;

import model.solvers.fitness.FirstFunctionFitness;

public class FirstFunction extends Problem {
	
	private static final double MIN = -250;
	private static final double MAX = 250;
	
	private static final int NUM_GENERATIONS = 100;
	private static final int POPULATION_SIZE = 100;

	public FirstFunction() {
		super();
		this.intervalMin = MIN;
		this.intervalMax = MAX;
		this.numberGenerations = NUM_GENERATIONS;
		this.populationSize = POPULATION_SIZE;
		this.fitness = new FirstFunctionFitness();
	}
	
}
