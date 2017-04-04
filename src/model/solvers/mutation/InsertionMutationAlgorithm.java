package model.solvers.mutation;

import model.population.Individual;

public class InsertionMutationAlgorithm extends MutationAlgorithm {
	
	private int numPoints;
	
	public InsertionMutationAlgorithm(int numPoints) {
		super();
		this.numPoints = numPoints;
	}

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		int n = ind.getGenome().getGenes().size();
		for (int i = 0; i < numPoints; i++) {
			int from = (int) Math.ceil(Math.random() * (n - 1));
			int to = (int) Math.ceil(Math.random() * (from - 1));
			ind.shiftInsert(from, to);
		}
	}

}
