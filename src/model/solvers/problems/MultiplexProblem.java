package model.solvers.problems;

import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class MultiplexProblem extends Problem {

	private int numA;
	private int creationMethod;	
	private int maxDepth;
	private boolean ifsAllowed;

	public MultiplexProblem(int populationSize, int numberGenerations, Fitness fitness, int numA, int treeCreationMethod, boolean ifsAllowed, int maxTreeDepth) {
		super(populationSize, numberGenerations, fitness, 1, true);
		this.numA = numA;
		this.creationMethod = treeCreationMethod;
		this.ifsAllowed = ifsAllowed;
		this.maxDepth = maxTreeDepth;
	}

	@Override
	public Population createRandomPopulation(int seed) {
		switch(creationMethod) {
		case 0:
			return PopulationFactory.createProgramIncremental(ifsAllowed, maxDepth, populationSize, numA);
		case 1:
			return PopulationFactory.createProgramComplete(ifsAllowed, maxDepth, populationSize, numA);
		case 2:
			return PopulationFactory.createProgramRampAndHalf(ifsAllowed, maxDepth, populationSize, numA);
		case 3:
			return PopulationFactory.createProgramWeighed(ifsAllowed, maxDepth, populationSize, numA);
		default:
			throw new RuntimeException("No known tree creation method selected");
		}
	}

}
