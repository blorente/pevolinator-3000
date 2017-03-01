package model.solvers;

import model.population.Individual;
import model.population.Population;

public class MutationAlgorithm {

	public Population mutate(Population population, double mutationPercent) {
		for (Individual ind : population.getPopulation()) {
			ind.mutateSelf(mutationPercent);
		}
		return population;
	}

}
