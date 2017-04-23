package model.solvers.mutation;

import model.population.Individual;
import model.solvers.fitness.Fitness;

public class SpecialReversionMutationAlgorithm extends CombinatoricsMutation {

	private Fitness fitness;
	
	public SpecialReversionMutationAlgorithm(Fitness fitness) {
		super();
		this.fitness = fitness;
	}

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Individual newInd = new Individual(ind);
		Integer[] points = getPoints(2, ind.getGenome().getGenes().size());
		int start = points[0].intValue();
		int end = points[1].intValue();
		int segmentSize = end - start;
		for (int i = 0; i < segmentSize / 2; i++) {
			newInd.swapGenes(start + i, end - i);
		}
		
		newInd.evaluate(fitness);
		if (fitness.isMinimization() && ind.getAbsoluteFitness() > newInd.getAbsoluteFitness()) {
			ind = newInd;
		}
		
		if (!fitness.isMinimization() && ind.getAbsoluteFitness() < newInd.getAbsoluteFitness()) {
			ind = newInd;
		}
	}

}
