package model.solvers.problems;

import java.util.List;

import controller.PairTuple;
import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class PlainFunctionProblem extends Problem {
	private List<PairTuple<Double,Double>> minMaxParameters;
	private double tolerance;

    public PlainFunctionProblem(int populationSize, int numberGenerations, Fitness fitness, List<PairTuple<Double,Double>> minMaxParameters, double tolerance, int genomeSize, boolean isMinimization) {
    	super(populationSize, numberGenerations, fitness, genomeSize, isMinimization);
        this.minMaxParameters = minMaxParameters;
        this.tolerance = tolerance;
    }

    @Override
	public Population createRandomPopulation(int seed) {
        return PopulationFactory.createRandomBinary(populationSize, genomeSize, seed, minMaxParameters, tolerance);
    }
}
