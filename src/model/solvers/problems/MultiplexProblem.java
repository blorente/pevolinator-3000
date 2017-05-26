package model.solvers.problems;

import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class MultiplexProblem extends Problem {

	private int numA;
	private int creationMethod;
	
	
	private static final int MAX_DEPTH = 3;
	private static final boolean IFS_ALLOWED = true;

	public MultiplexProblem(int populationSize, int numberGenerations, Fitness fitness, int numA, int treeCreationMethod) {
		super(populationSize, numberGenerations, fitness, 1, true);
		this.numA = numA;
		this.creationMethod = treeCreationMethod;
	}

	@Override
	public Population createRandomPopulation(int seed) {
		switch(creationMethod) {
		case 0:
			return PopulationFactory.createProgramIncremental(IFS_ALLOWED, MAX_DEPTH, populationSize, numA);
		case 1:
			return PopulationFactory.createProgramComplete(IFS_ALLOWED, MAX_DEPTH, populationSize, numA);
		case 2:
			return PopulationFactory.createProgramRampAndHalf(IFS_ALLOWED, MAX_DEPTH, populationSize, numA);
		case 3:
			return PopulationFactory.createProgramWeighed(IFS_ALLOWED, MAX_DEPTH, populationSize, numA);
		default:
			throw new RuntimeException("No known tree creation method selected");
		}
	}

}
