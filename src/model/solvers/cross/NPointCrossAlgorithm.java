package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;

public class NPointCrossAlgorithm extends CrossAlgorithm {

	private int numPoints;
	
	public NPointCrossAlgorithm(int numPoints, double crossRate) {
		super(crossRate);
		this.numPoints = numPoints;
	}

	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().totalSize();
		List<Individual> children = new ArrayList<>();
		SortedSet<Integer> crossIndices = getCrossPoints(genomeSize, numPoints);
		
		crossIndividuals(ind, selected, crossIndices);
		children.add(ind);
		children.add(selected);
		return children;
	}

	private void crossIndividuals(Individual ind, Individual selected,
			SortedSet<Integer> crossIndices) {
		for (Integer point : crossIndices) {
			Genome temp = new Genome(ind.getGenome());			
			ind.getGenome().copyFrom(selected.getGenome(), point.intValue());
			selected.getGenome().copyFrom(temp, point.intValue());
		}
	}

}
