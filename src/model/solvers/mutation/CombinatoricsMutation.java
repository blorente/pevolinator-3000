package model.solvers.mutation;

import java.util.Set;
import java.util.TreeSet;

import model.population.Individual;
import model.population.genes.Gene;

public abstract class CombinatoricsMutation extends MutationAlgorithm {

	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}
	

}
