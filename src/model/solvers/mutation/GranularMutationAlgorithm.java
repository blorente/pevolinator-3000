package model.solvers.mutation;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;

public class GranularMutationAlgorithm extends MutationAlgorithm {

	void mutateIndividual(Individual ind, double mutationPercent) {
		Genome genome = ind.getGenome();
		for (Gene gene : genome.getGenes()) {
	        gene.mutateSelfGranular(mutationPercent);
	    }
	}

}
