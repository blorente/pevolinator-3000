package model.solvers;

import java.util.Random;

public class SolverParameters {
	private double crossPercent;
	private double mutationPercent;
	private double elitismPercent;
	private int seed;
	
	public SolverParameters(double crossPercent, double mutationPercent, double elitismPercent) {
		this.crossPercent = crossPercent;
		this.mutationPercent = mutationPercent;
		this.elitismPercent = elitismPercent;
		Random rand = new Random();
		this.seed = rand.nextInt();
	}
	
	public SolverParameters(double crossPercent, double mutationPercent, double elitismPercent, int seed) {
		this.crossPercent = crossPercent;
		this.mutationPercent = mutationPercent;
		this.elitismPercent = elitismPercent;
		this.seed = seed;
	}
	
	public double getCrossPercent() {
		return crossPercent;
	}
	
	public double getMutationPercent() {
		return mutationPercent;
	}

	public int getSeed() {
		return seed;
	}

	@Override
	public String toString() {
		return "SolverParameters{" +
				"crossPercent=" + crossPercent +
				", mutationPercent=" + mutationPercent +
				", seed=" + seed +
				'}';
	}

	public double elitismPercent() {
		return elitismPercent;
	}
}