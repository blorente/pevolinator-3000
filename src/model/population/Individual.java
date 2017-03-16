package model.population;

import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import model.solvers.fitness.Fitness;

public class Individual {
	private Genome genome;
	private double absoluteFitness;
	private double shiftedFitness;
	
	Individual(Genome genome) {
        this.genome = genome;
        absoluteFitness = 0.0;
        shiftedFitness = 0.0;
    }

    public Individual(Individual other) {
        this(other.genome);
        this.absoluteFitness = other.absoluteFitness;
        this.shiftedFitness = other.shiftedFitness;
    }

	public void evaluate(Fitness fitnessMethod) {
		this.absoluteFitness = fitnessMethod.calculate(genome);
	}
	
	public void shiftMinimize(double popMax, Fitness fitnessMethod) {
		this.shiftedFitness = fitnessMethod.shiftingFitnessMin(popMax, absoluteFitness);
	}
	
	public void shiftMaximize(double popMin, Fitness fitnessMethod) {
		this.shiftedFitness = fitnessMethod.shiftingFitnessMax(popMin, absoluteFitness);
	}
	
	public double getAbsoluteFitness() {
		return absoluteFitness;
	}
	
	public double getShiftedFitness() {
		return absoluteFitness;
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
	    ret.append("        Absolute Fitness: ").append(absoluteFitness).append("\n");
	    ret.append("        Shifted Fitness: ").append(shiftedFitness).append("\n");
	    ret.append("    }\n");
        return ret.toString();
    }

	public int compareTo(Individual a) {
		if(this.shiftedFitness < a.shiftedFitness)
			return -1;
		else if (this.shiftedFitness > a.shiftedFitness)
			return 1;
		else
			return 0;
	}
}
