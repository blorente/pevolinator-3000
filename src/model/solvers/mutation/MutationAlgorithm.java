package model.solvers.mutation;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import model.population.Individual;
import model.population.Population;
import model.reporter.PopulationReporter;

public abstract class MutationAlgorithm {

	public Population mutate(Population population, double mutationPercent, PopulationReporter reporter) {
		for (Individual ind : population.getPopulation()) {				
			mutateIndividual(ind, mutationPercent);
			if(!isValid(ind)) {
				throw new RuntimeException("Invalid individual generated from mutation: " + ind);
			}
			reporter.reportMutation();
		}
		return population;
	}

	abstract void mutateIndividual(Individual ind, double mutationPercent);
	abstract boolean isValid(Individual ind);

	protected Integer[] getPoints(int numPoints, int size) {
		assert(numPoints <= size);
		SortedSet<Integer> points = new TreeSet<>();
		Random r = new Random();
		while(points.size() != numPoints) {
			points.add(r.nextInt(size));
		}
		return points.toArray(new Integer[points.size()]);
	}

}
