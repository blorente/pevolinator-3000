package model.solvers.problems;

import java.util.List;

import main.PairTuple;
import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class Problem {
	private int populationSize;
	private int numberGenerations;
	private Fitness fitness;
	private List<PairTuple<Double,Double>> minMaxParameters;
	private double tolerance;
	private int genomeSize;

    public Problem(int populationSize, int numberGenerations, Fitness fitness, List<PairTuple<Double,Double>> minMaxParameters, double tolerance, int genomeSize) {
        this.populationSize = populationSize;
        this.numberGenerations = numberGenerations;
        this.fitness = fitness;
        this.minMaxParameters = minMaxParameters;
        this.tolerance = tolerance;
        this.genomeSize = genomeSize;
    }

    public Population createRandomPopulation(int seed) {
        return PopulationFactory.createRandomBinary(populationSize, genomeSize, seed, minMaxParameters, tolerance);
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
