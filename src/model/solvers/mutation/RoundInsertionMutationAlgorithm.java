package model.solvers.mutation;

import model.population.Individual;

public class RoundInsertionMutationAlgorithm extends CombinatoricsMutation {

	private int numPoints;
	
	public RoundInsertionMutationAlgorithm(int numPoints) {
		super();
		this.numPoints = numPoints;
	}

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		for (int i = 0; i < numPoints; i++) {
			Integer[] points = getPoints(2, ind.getGenome().getGenes().size());
			ind.swapGenes(points[0].intValue(), points[1].intValue());
		}
	}

}
