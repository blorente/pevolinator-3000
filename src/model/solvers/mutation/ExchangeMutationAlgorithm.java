package model.solvers.mutation;

import model.population.Individual;

public class ExchangeMutationAlgorithm extends CombinatoricsMutation {

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {		
		Integer[] points = getPoints(2, ind.getGenome().getGenes().size());
		ind.swapGenes(points[0].intValue(), points[1].intValue());
	}
}
