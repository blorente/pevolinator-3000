package main;
import java.util.ArrayList;
import java.util.List;

import model.reporter.ConsoleReporter;
import model.reporter.PopulationReporter;
import model.solvers.MutationAlgorithm;
import model.solvers.Solver;
import model.solvers.SolverParameters;
import model.solvers.cross.CrossAlgorithm;
import model.solvers.fitness.FirstFunctionFitness;
import model.solvers.fitness.Fitness;
import model.solvers.problems.Problem;
import model.solvers.selection.RouletteSelectionAlgorithm;
import model.solvers.selection.SelectionAlgorithm;

public class Main {
	private static final List<PairTuple<Double,Double>> MinMaxParameters = new ArrayList<PairTuple<Double,Double>>(){{ add(new PairTuple<>(X_MIN,X_MAX));}};

    private static final double CROSS_PERCENT = 0.25;
    private static final double MUTATION_PERCENT = 0.01;
    private static final double ELITISM_PERCENT = 0.5;

    private static final int POPULATION_SIZE = 4;
    private static final int NUMBER_GENERATIONS = 40;
    private static final double X_MIN = -250;
    private static final double X_MAX = 250;
    private static final double TOLERANCE = 0.001;
    private static final int GENOME_SIZE = 1;

    private static final int NUMBER_CROSS_POINTS = 1;

    public static void main(String[] args) {
        SolverParameters parameters = new SolverParameters(CROSS_PERCENT, MUTATION_PERCENT,ELITISM_PERCENT);

        Fitness fitness = new FirstFunctionFitness();
        Problem firstFunction = new Problem(POPULATION_SIZE, NUMBER_GENERATIONS, fitness, MinMaxParameters, TOLERANCE, GENOME_SIZE);

        SelectionAlgorithm selectionAlgorithm = new RouletteSelectionAlgorithm();
        CrossAlgorithm crossAlgorithm = new CrossAlgorithm(NUMBER_CROSS_POINTS, parameters.getCrossPercent());
        MutationAlgorithm mutationAlgorithm = new MutationAlgorithm();

        PopulationReporter reporter = new ConsoleReporter();

        Solver solver = new Solver(parameters, firstFunction, selectionAlgorithm, crossAlgorithm, mutationAlgorithm, reporter);
        solver.run();
    }
}
