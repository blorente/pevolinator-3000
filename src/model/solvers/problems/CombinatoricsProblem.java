package model.solvers.problems;

import controller.PairTuple;
import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class CombinatoricsProblem extends Problem {
	
	private int n;
	private int[][] F;
	private int[][] D;

	public CombinatoricsProblem(int populationSize, int numberGenerations,
			Fitness fitness, boolean isMinimization, PairTuple<int[][], int[][]> params) {
		super(populationSize, numberGenerations, fitness, params.right[0].length,
				isMinimization);
		this.n = params.right[0].length;
		F = params.right;
		D = params.left;
	}


	@Override
	public Population createRandomPopulation(int seed) {
		return PopulationFactory.createIntegerCombinatorics(populationSize, genomeSize, seed, n);
	}

}
