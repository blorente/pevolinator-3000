package model.solvers.problems;

import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class MultiplexProblem extends Problem {

	private int numA;
	
	private static final int MAX_DEPTH = 3;
	private static final boolean IFS_ALLOWED = true;

	public MultiplexProblem(int populationSize, int numberGenerations, Fitness fitness, int numA) {
		super(populationSize, numberGenerations, fitness, 1, true);
		this.numA = numA;
	}

	@Override
	public Population createRandomPopulation(int seed) {
		return PopulationFactory.createProgramComplete(IFS_ALLOWED, MAX_DEPTH, populationSize, numA);
	}

}
