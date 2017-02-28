package model.population;

import java.util.Set;
import java.util.SortedSet;

import model.solvers.Fitness;

public class Individual {
	private Genome genome;
	private double fitness;
	public void evaluate(Fitness fitnessMethod) {
		this.fitness = fitnessMethod.calculate(genome);
	}
	public double getFitness() {
		return fitness;
	}
	public Genome getGenome() {
		return genome;
	}
	
	public void crossSelf(Individual selected, SortedSet<Integer> crossIndices) {
		for (Integer point : crossIndices) {
			Genome temp = new Genome(genome);			
			genome.copyFrom(selected.genome, point.intValue());
			selected.genome.copyFrom(temp, point.intValue());
		}
	}
}
