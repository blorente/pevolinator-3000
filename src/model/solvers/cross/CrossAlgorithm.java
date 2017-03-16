package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import model.population.Individual;
import model.population.Population;

public class CrossAlgorithm {
	
	private int numPoints;
	private double crossRate;
	
	public CrossAlgorithm(int numPoints, double crossRate){
		this.numPoints = numPoints;
		this.crossRate = crossRate;
	}
	
	public Population cross(Population population) {		
		Population crossed = new Population();
		List<Individual> pop = population.getPopulation();
		Individual selected = null;
		boolean readyToPair = false;
		for (Individual ind : pop) {
			if (selected()) {
				if (readyToPair) {
					List<Individual> children = crossPair(ind, selected);
					for (Individual child : children) {
						crossed.addIndividual(child);
					}
					readyToPair = false;
					selected = null;
				} else {
					selected = ind;
					readyToPair = true;
				}
			} else {
				crossed.addIndividual(ind);
			}
		}
		
		if (readyToPair) {
			crossed.addIndividual(selected);
		}		
		
		return crossed;
	}

	private List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().totalSize();
		List<Individual> children = new ArrayList<>();
		SortedSet<Integer> crossIndices = new TreeSet<>();
		for (int i = 0; i < numPoints; i++) {
			crossIndices.add(newCrossPoint(crossIndices, genomeSize));
		}
		
		ind.crossSelf(selected, crossIndices);
		children.add(ind);
		children.add(selected);
		return children;
	}
	
	private Integer newCrossPoint(SortedSet<Integer> crossIndices, int size) {
		int rand = (int) (Math.random() * (size - 2)) + 1;
 		while (crossIndices.contains(rand)) {
 			rand = (int) (Math.random() * (size - 2)) + 1;
 		}	
		return rand;
	}

	private boolean selected() {
		return Math.random() < crossRate;
	}
}
