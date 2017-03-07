package controller;

import model.reporter.GUITextReporter;
import model.reporter.PopulationReporter;
import model.solvers.MutationAlgorithm;
import model.solvers.Solver;
import model.solvers.SolverParameters;
import model.solvers.cross.CrossAlgorithm;
import model.solvers.fitness.*;
import model.solvers.problems.Problem;
import model.solvers.selection.RouletteSelectionAlgorithm;
import model.solvers.selection.SelectionAlgorithm;

import javax.swing.text.JTextComponent;

public class Controller {

    private static final double CROSS_PERCENT = 0.25;
    private double crossPercent;
    private static final double MUTATION_PERCENT = 0.01;
    private double mutationPercent;
    private static final int POPULATION_SIZE = 4;
    private int populationSize;

    private static final int NUMBER_GENERATIONS = 100;
    private int numberGenerations;
    private static final double X_MIN = -250;
    private double xMin;
    private static final double X_MAX = 250;
    private double xMax;
    private static final double TOLERANCE = 0.001;
    private double tolerance;

    private static final int GENOME_SIZE = 1;
    private int genomeSize;
    private static final int NUMBER_CROSS_POINTS = 1;
    private int numberCrossPoints;
    private Fitness fitness;

    public Controller() {
        this.xMax = X_MAX;
        this.xMin = X_MIN;
        this.tolerance = TOLERANCE;

        this.genomeSize = GENOME_SIZE;
        this.numberCrossPoints = NUMBER_CROSS_POINTS;
        this.numberGenerations = NUMBER_GENERATIONS;
        this.populationSize = POPULATION_SIZE;
        this.fitness = new FirstFunctionFitness();
    }

    public void launch(JTextComponent target) {
        SolverParameters parameters = new SolverParameters(crossPercent, mutationPercent);
        System.out.println(parameters);

        Problem firstFunction = new Problem(populationSize, numberGenerations, fitness, xMin, xMax, tolerance, genomeSize);

        SelectionAlgorithm selectionAlgorithm = new RouletteSelectionAlgorithm();
        CrossAlgorithm crossAlgorithm = new CrossAlgorithm(numberCrossPoints, parameters.getCrossPercent());
        MutationAlgorithm mutationAlgorithm = new MutationAlgorithm();

        PopulationReporter reporter = new GUITextReporter(target);

        Solver solver = new Solver(parameters, firstFunction, selectionAlgorithm, crossAlgorithm, mutationAlgorithm, reporter);
        solver.run();
    }

    public void setFitnessFunction(String function) {
        if (function.equals(Fitness.fitnessFunctions[0])) {
            fitness = new FirstFunctionFitness();
        } else if (function.equals(Fitness.fitnessFunctions[1])) {
            fitness = new SecondFunctionFitness();
        } else if (function.equals(Fitness.fitnessFunctions[2])) {
            fitness = new ThirdFunctionFitness();
        } else if (function.equals(Fitness.fitnessFunctions[3])) {
            fitness = new FourthFunctionFitness();
        } else if (function.equals(Fitness.fitnessFunctions[4])) {
            fitness = new FifthFunctionFitness();
        }
    }

    public void setCrossPercent(double crossPercent) {
        this.crossPercent = crossPercent;
    }

    public void setMutationPercent(double mutationPercent) {
        this.mutationPercent = mutationPercent;
    }

    public void setxMax(double value) {
        this.xMax = value;
    }

    public void setxMin(double value) {
        this.xMin = value;
    }

    public void setTolerance(double value) {
        this.tolerance = value;
    }

    public void setNumberGenerations(int numberGenerations) {
        this.numberGenerations = numberGenerations;
    }

    public void setGenomeSize(int genomeSize) {
        this.genomeSize = genomeSize;
    }

    public void setNumberCrossPoints(int numberCrossPoints) {
        this.numberCrossPoints = numberCrossPoints;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }
}
