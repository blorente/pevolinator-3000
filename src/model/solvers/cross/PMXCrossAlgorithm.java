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
		int genomeSize = ind.getGenome().totalSize();
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
		Genome temp = new Genome(ind.getGenome());	
		ind.getGenome().copyFromDiscrete(selected.getGenome(), first, last);
		selected.getGenome().copyFromDiscrete(temp, first, last);
		
		List<Gene> indCrossedWhole = ind.getGenome().getGenes();
		List<Gene> selectedCrossedWhole = ind.getGenome().getGenes();
		
		List<Gene> indCrossedSection = ind.getGenome().getGenes(first, last);
		List<Gene> selectedCrossedSection = selected.getGenome().getGenes(first, last);
		
		for (int i = 0; i < first; i++) {
			discardRepetitions(ind, selected, indCrossedWhole,
					indCrossedSection, i);
			discardRepetitions(selected, ind, selectedCrossedWhole,
					selectedCrossedSection, i);
		}
		
		for (int i = last; i < ind.getGenome().totalSize(); i++) {
			discardRepetitions(ind, selected, indCrossedWhole,
					indCrossedSection, i);
			discardRepetitions(selected, ind, selectedCrossedWhole,
					selectedCrossedSection, i);
		}
	}

	private void discardRepetitions(Individual ind, Individual selected,
			List<Gene> indCrossedWhole, List<Gene> indCrossedSection, int i) {
		int repeatIndex = indCrossedSection.indexOf(indCrossedWhole.get(i));
		if (repeatIndex != -1) {
			ind.getGenome().copyFromDiscrete(selected.getGenome(), repeatIndex, repeatIndex + 1);				
		}
	}

}
