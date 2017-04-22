package model.solvers.mutation;

import model.population.Individual;

public class ExchangeMutationAlgorithm extends MutationAlgorithm {

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {		
		Integer[] points = getPoints(2, ind.getGenome().totalSize());
		ind.swapGenes(points[0].intValue(), points[1].intValue());
	}

}
