package model.solvers.mutation;

import java.util.Comparator;

import model.population.Individual;
import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;

public class HeuristicMutationAlgorithm extends CombinatoricsMutation {
	
	private int numPoints;
	private Fitness fitnessMethod;
	private boolean isMinimization;
	
	public HeuristicMutationAlgorithm(int numPoints, Fitness fitnessMethod) {
		super();
		this.numPoints = numPoints;
		this.fitnessMethod = fitnessMethod;
		this.isMinimization = fitnessMethod.isMinimization();
	}

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Integer[] points = getPoints(numPoints, ind.getGenome().getGenes().size());
		Population permutations = PopulationFactory.createIndexPermutations(ind, points);
		if (isMinimization)
			permutations.evaluateMinimize(fitnessMethod);
		else 
			permutations.evaluateMinimize(fitnessMethod);
		Comparator<Individual> comp = isMinimization ? (a, b) -> b.compareTo(a) : (a, b) -> a.compareTo(b);
		ind = permutations.getBest(comp);
	}

}
