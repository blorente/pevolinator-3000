package model.solvers.problems;

import model.population.Population;
import model.solvers.fitness.Fitness;

public abstract class Problem {

	protected int populationSize;
	protected int numberGenerations;
	protected Fitness fitness;
	protected int genomeSize;
	protected boolean isMinimization;
	
	
	public Problem(int populationSize, int numberGenerations, Fitness fitness,
			int genomeSize, boolean isMinimization) {
		this.populationSize = populationSize;
		this.numberGenerations = numberGenerations;
		this.fitness = fitness;
		this.genomeSize = genomeSize;
		this.isMinimization = isMinimization;
	}

	public abstract Population createRandomPopulation(int seed);

	public int getPopulationSize() {
		return populationSize;
	}

	public int getGenerations() {		
		return numberGenerations;
	}

	public Fitness getFitness() {
		return fitness;
	}

	public boolean isMinimization() {
		return isMinimization;
	}

}