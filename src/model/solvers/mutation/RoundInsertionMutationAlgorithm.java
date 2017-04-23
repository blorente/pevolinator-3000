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
			int n = ind.getGenome().getGenes().size();
			int from = (int) Math.ceil(Math.random() * (n - 1));
			int to = (from + (int) Math.ceil(Math.random() * (n - 1))) % n;
			ind.swapGenes(from, to);
		}
	}

}
