package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.population.Genome;
import model.population.GenomeFactory;
import model.population.Individual;
import model.population.genes.Gene;

public class ERXSpecialCrossAlgorithm extends CrossAlgorithm {

	public ERXSpecialCrossAlgorithm(double crossRate) {
		super(crossRate);
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().getGenes().size();
		Random randomfirst = new Random(Integer.MAX_VALUE);
		Genome firstChild = GenomeFactory.createRandomPermutation(ind.getGenome().totalSize(), ind.getGenome().totalSize(), randomfirst);
		Random randomsecond = new Random(Integer.MAX_VALUE);
		Genome secondChild = GenomeFactory.createRandomPermutation(ind.getGenome().totalSize(), ind.getGenome().totalSize(), randomsecond);
		
		ind.getGenome().copyFromDiscrete(firstChild, 0, genomeSize);
		selected.getGenome().copyFromDiscrete(secondChild, 0, genomeSize);
		
		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	

}
