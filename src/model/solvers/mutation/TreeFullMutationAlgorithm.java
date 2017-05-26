package model.solvers.mutation;

import model.population.Individual;
import model.population.tree.TreeGenome;

public class TreeFullMutationAlgorithm extends MutationAlgorithm {

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Unimplemented full tree mutation.");
	}

	@Override
	boolean isValid(Individual ind) {		
		return ((TreeGenome)ind.getGenome()).root != null;
	}

}
