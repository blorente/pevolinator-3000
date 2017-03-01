package model.solvers;

public class SolverParameters {
	private double crossPercent;
	private double mutationPercent;
	private int seed;
	
	public SolverParameters(double crossPercent, double mutationPercent, int seed) {
		this.crossPercent = crossPercent;
		this.mutationPercent = mutationPercent;
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
}