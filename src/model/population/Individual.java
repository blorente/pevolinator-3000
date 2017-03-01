package model.population;

import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import model.solvers.fitness.Fitness;

public class Individual {
	private Genome genome;
	private double fitness;

    Individual(Genome genome) {
        this.genome = genome;
        fitness = 0.0;
    }

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

	public void mutateSelf(double mutationPercent) {
		genome.mutateSelf(mutationPercent);
	}

    @Override
    public String toString() {
	    StringBuilder ret = new StringBuilder();
	    ret.append("    Individual {\n");
	    ret.append("        Genome: ").append(genome.toString()).append("\n");
	    ret.append("        Fitness: ").append(fitness).append("\n");
	    ret.append("    }\n");
        return ret.toString();
    }
}
