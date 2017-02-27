package model.solvers;

public class SolverParameters {
	private int crossPercent;
	private int mutationPercent;
	private int seed;
	
	public SolverParameters(int crossPercent, int mutationPercent, int seed) {
		this.crossPercent = crossPercent;
		this.mutationPercent = mutationPercent;
		this.seed = seed;
	}
	
	public int getCrossPercent() {
		return crossPercent;
	}
	
	public int getMutationPercent() {
		return mutationPercent;
	}
	
	public int getSeed() {
		return seed;
	}
}