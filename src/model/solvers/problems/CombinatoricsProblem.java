package model.solvers.problems;

import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class CombinatoricsProblem extends Problem {
	
	private int n;
	private int[][] F;
	private int[][] D;

	public CombinatoricsProblem(int populationSize, int numberGenerations,
			Fitness fitness, int genomeSize, boolean isMinimization, int n,
			int[][] f, int[][] d) {
		super(populationSize, numberGenerations, fitness, genomeSize,
				isMinimization);
		this.n = n;
		F = f;
		D = d;
	}


	@Override
	public Population createRandomPopulation(int seed) {
		return PopulationFactory.createIntegerCombinatorics(populationSize, genomeSize, seed, n);
	}

}
