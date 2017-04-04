package model.solvers.mutation;

import model.population.Individual;
import model.population.Population;

public abstract class MutationAlgorithm {

	public Population mutate(Population population, double mutationPercent) {
		for (Individual ind : population.getPopulation()) {			
			mutateIndividual(ind, mutationPercent);
		}
		return population;
	}

	abstract void mutateIndividual(Individual ind, double mutationPercent);

}
