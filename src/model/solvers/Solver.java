package model.solvers;

import model.population.Population;
import model.population.PopulationFactory;
import model.reporter.PopulationReporter;
import model.solvers.cross.CrossAlgorithm;
import model.solvers.problems.Problem;
import model.solvers.selection.SelectionAlgorithm;

public class Solver {
	private SolverParameters parameters;
	private Problem problem;
	private CrossAlgorithm cross;
	private SelectionAlgorithm selection;
	private MutationAlgorithm mutation;
	private PopulationReporter reporter;
	
	public Solver(SolverParameters parameters, Problem problem,
				  SelectionAlgorithm selection, CrossAlgorithm cross, MutationAlgorithm mutation, PopulationReporter reporter) {
		this.parameters = parameters;
		this.problem = problem;
		this.selection = selection;
		this.cross = cross;
		this.mutation = mutation;
		this.reporter = reporter;
	}
	
	public void run() {
		Population population = problem.createRandomPopulation(parameters.getSeed());
		for (int generation = 0; generation < problem.getGenerations(); generation++) {
			population.evaluateMinimize(problem.getFitness());
			reporter.report(generation, population);
			population = selection.select(population);
			population = cross.cross(population);
			population = mutation.mutate(population,parameters.getMutationPercent());
		}
		reporter.report(problem.getGenerations(), population);
	}
	
	
}
