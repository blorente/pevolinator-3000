package model.solvers;

import model.population.Population;

public class Solver {
	private SolverParameters parameters;
	private Problem problem;
	private CrossAlgorithm cross;
	private SelectionAlgorithm selection;
	private MutationAlgorithm mutation;
	
	public Solver(SolverParameters parameters, Problem problem,
					CrossAlgorithm cross, SelectionAlgorithm selection) {
		this.parameters = parameters;
		this.problem = problem;
		this.cross = cross;
		this.selection = selection;
	}
	
	public void run(){
		Population population = new Population(parameters.getSeed());
		for (int generation = 0; generation < problem.getGenerations(); generation++) {
			population.evaluate(problem.getFitness());
			population = selection.select(population);
			population = cross.cross(population, parameters.getCrossPercent());
			population = mutation.mutate(population,parameters.getMutationPercent());
		}
	}
	
	
}
