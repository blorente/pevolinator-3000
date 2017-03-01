package model.solvers.problems;

import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class Problem {
	private int populationSize;
	private int numberGenerations;
	private Fitness fitness;
	private double intervalMin;
	private double intervalMax;
	private double tolerance;
	private int genomeSize;

    public Problem(int populationSize, int numberGenerations, Fitness fitness, double intervalMin, double intervalMax, double tolerance, int genomeSize) {
        this.populationSize = populationSize;
        this.numberGenerations = numberGenerations;
        this.fitness = fitness;
        this.intervalMin = intervalMin;
        this.intervalMax = intervalMax;
        this.tolerance = tolerance;
        this.genomeSize = genomeSize;
    }

    public Population createRandomPopulation(int seed) {
        return PopulationFactory.createRandomBinary(populationSize, genomeSize, seed, intervalMax, intervalMin, tolerance);
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
