package model.solvers.mutation;

import model.population.Individual;

public class ReversionMutationAlgorithm extends MutationAlgorithm {

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Integer[] points = getPoints(2, ind.getGenome().getGenes().size());
		int start = points[0].intValue();
		int end = points[1].intValue();
		int segmentSize = end - start;
		for (int i = 0; i < segmentSize / 2; i++) {
			ind.swapGenes(start + i, end - i);
		}
	}

}
