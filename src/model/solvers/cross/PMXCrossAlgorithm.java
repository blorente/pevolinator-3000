package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.Population;
import model.population.genes.Gene;

public class PMXCrossAlgorithm extends CrossAlgorithm {

	private static final int NUM_CROSS_POINTS = 2;

	public PMXCrossAlgorithm(double crossRate) {
		super(crossRate);
	}
	
	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().getGenes().size();
		SortedSet<Integer> points = this.getCrossPoints(genomeSize, NUM_CROSS_POINTS);
		
		performPMXCross(ind, selected, points);		

		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performPMXCross(Individual ind, Individual selected,
			SortedSet<Integer> points) {
		int first = points.first().intValue();
		int last = points.last().intValue();
		Genome previousInd = new Genome(ind.getGenome());
		ind.getGenome().copyFromDiscrete(selected.getGenome(), first, last);
		selected.getGenome().copyFromDiscrete(previousInd, first, last);
		
		List<Gene> indCrossedSection = ind.getGenome().getGenes(first, last);
		List<Gene> selectedCrossedSection = selected.getGenome().getGenes(first, last);
		
		for (int i = 0; i < first; i++) {
			discardRepetitions(ind, indCrossedSection,
					selectedCrossedSection, i);
			discardRepetitions(selected, selectedCrossedSection,
					indCrossedSection, i);
		}
		
		for (int i = last; i < ind.getGenome().totalSize(); i++) {
			discardRepetitions(ind, indCrossedSection,
					selectedCrossedSection, i);
			discardRepetitions(selected, selectedCrossedSection,
					indCrossedSection, i);
		}
	}

	private void discardRepetitions(Individual ind, List<Gene> indCrossedSection, List<Gene> selectedCrossedSection, int i) {
		int repeatIndex = indCrossedSection.indexOf(ind.getGenome().getGenes().get(i));
		if (repeatIndex != -1) {
			ind.getGenome().setGene(i,selectedCrossedSection.get(repeatIndex));				
		}
	}

}
