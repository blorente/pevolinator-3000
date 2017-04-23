package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;

public class IndexCrossAlgorithm extends CrossAlgorithm {

	public IndexCrossAlgorithm(double crossRate) {
		super(crossRate);
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		Genome newInd = performIndexCross(ind.getGenome(), selected.getGenome());
		Genome newSel = performIndexCross(selected.getGenome(), ind.getGenome());
		
		List<Individual> children = new ArrayList<>();
		children.add(new Individual(newInd));
		children.add(new Individual(newSel));
		return children;
	}

	private Genome performIndexCross(Genome indices, Genome contents) {
		List<Gene> newGenes = new ArrayList<>();
		for (Gene g : indices.getGenes()) {
			newGenes.add(contents.getGene(g.intValue()));
		}
		return new Genome(newGenes);
	}

	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

}
