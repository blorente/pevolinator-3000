package controller;

import model.reporter.GUIGraphReporter;
import model.reporter.GUITextReporter;
import model.reporter.PopulationReporter;
import model.solvers.Solver;
import model.solvers.SolverParameters;
import model.solvers.cross.CrossAlgorithm;
import model.solvers.cross.CrossAlgorithmData;
import model.solvers.cross.NPointCrossAlgorithm;
import model.solvers.cross.PMXCrossAlgorithm;
import model.solvers.fitness.*;
import model.solvers.mutation.GranularMutationAlgorithm;
import model.solvers.mutation.InsertionMutationAlgorithm;
import model.solvers.mutation.MutationAlgorithm;
import model.solvers.mutation.MutationAlgorithmData;
import model.solvers.problems.CombinatoricsProblem;
import model.solvers.problems.PlainFunctionProblem;
import model.solvers.problems.Problem;
import model.solvers.selection.Roulette;
import model.solvers.selection.SelectionAlgorithm;
import model.solvers.selection.SelectionAlgorithmData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import org.math.plot.Plot2DPanel;

public class Controller {

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
    private static final int NUMBER_MUTATION_POINTS = 2;
    
    private double mutationPercent;
    private double crossPercent;
    private double elitismPercent;
    private int populationSize;
    private int numberGenerations;
    private List<PairTuple<Double,Double>> minMaxParameters;
    private double tolerance;
    private int genomeSize;
    private int numberCrossPoints;
    private int numberMutationPoints;
    private Fitness fitness;
    private int seed;

	private SelectionAlgorithm selectionAlgorithm;
	private Problem problem;
    private PairTuple<int[][], int[][]> combinatoricsProblemData;

    private CrossAlgorithm crossAlgorithm;
    private MutationAlgorithm mutationAlgorithm;

	private int funIndex;


    public Controller() {
        this.tolerance = TOLERANCE;

        this.genomeSize = GENOME_SIZE;
        this.numberCrossPoints = NUMBER_CROSS_POINTS;
        this.numberMutationPoints = NUMBER_MUTATION_POINTS;
        this.numberGenerations = NUMBER_GENERATIONS;
        this.populationSize = POPULATION_SIZE;
        this.minMaxParameters = MinMaxParameters;
        this.selectionAlgorithm = SelectionAlgorithmData.Roulette.algorithm();
        this.fitness = new FirstFunctionFitness();
        this.problem = new PlainFunctionProblem(populationSize, numberGenerations, fitness,  minMaxParameters, tolerance, genomeSize, fitness.isMinimization());
        this.crossAlgorithm = new NPointCrossAlgorithm(numberCrossPoints, crossPercent);
        this.mutationAlgorithm = new InsertionMutationAlgorithm(1);
    }
    
    private void launch(PopulationReporter reporter) {
    	SolverParameters parameters = new SolverParameters(crossPercent, mutationPercent, elitismPercent, seed);
        System.out.println(parameters);        

        if (funIndex == 5) {        	
        	fitness = new HospitalFitness(combinatoricsProblemData);
        	problem = new CombinatoricsProblem(populationSize, numberGenerations, fitness, fitness.isMinimization(), combinatoricsProblemData);
        } else {
        	problem = new PlainFunctionProblem(populationSize, numberGenerations, fitness,  minMaxParameters, tolerance, genomeSize, fitness.isMinimization());
            mutationAlgorithm = new GranularMutationAlgorithm();
            crossAlgorithm = new NPointCrossAlgorithm(numberCrossPoints, crossPercent);
        }

        Solver solver = new Solver(parameters, problem, selectionAlgorithm, crossAlgorithm, mutationAlgorithm, reporter);
        solver.run();
    }

    public void launch(JTextComponent target) {
        launch(new GUITextReporter(target));
    }

	public void launch(Plot2DPanel target) {
		launch(new GUIGraphReporter(target));	
	}

    public void setFitnessFunction(String function) {
        if (function.equals(FitnessFunctionData.fitnessFunctions[0].toString())) {
            fitness = new FirstFunctionFitness();
            funIndex = 0;
        } else if (function.equals(FitnessFunctionData.fitnessFunctions[1].toString())) {
            fitness = new SecondFunctionFitness();
            funIndex = 1;
        } else if (function.equals(FitnessFunctionData.fitnessFunctions[2].toString())) {
            fitness = new ThirdFunctionFitness();
            funIndex = 2;
        } else if (function.equals(FitnessFunctionData.fitnessFunctions[3].toString())) {
            fitness = new FourthFunctionFitness();
            funIndex = 3;
        } else if (function.equals(FitnessFunctionData.fitnessFunctions[4].toString())) {
            fitness = new FifthFunctionFitness();
            funIndex = 4;
        } else if (function.equals(FitnessFunctionData.fitnessFunctions[5].toString())) {    
            funIndex = 5;
        }
        
        this.genomeSize = FitnessFunctionData.fitnessFunctions[funIndex].genomeSize;
        this.minMaxParameters = new ArrayList<>(FitnessFunctionData.fitnessFunctions[funIndex].minMax);
    }

    public void setCrossPercent(double crossPercent) {
        this.crossPercent = crossPercent;
    }

    public void setMutationPercent(double mutationPercent) {
        this.mutationPercent = mutationPercent;
    }

    public void setTolerance(double value) {
        this.tolerance = value;
    }

    public void setNumberGenerations(int numberGenerations) {
        this.numberGenerations = numberGenerations;
    }

    public void setGenomeSize(int genomeSize) {
        this.genomeSize = genomeSize;
        for (int i = 0; i < genomeSize; i++) {
        	this.minMaxParameters.add(new PairTuple<Double, Double>(this.minMaxParameters.get(0)));
        }
    }

    public void setNumberCrossPoints(int numberCrossPoints) {
        this.numberCrossPoints = numberCrossPoints;
    }
    
    public void setNumberMutationPoints(int numberMutationPoints) {
        this.numberMutationPoints = numberMutationPoints;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

	public void setElitismPercent(double elitismPercent) {
		this.elitismPercent = elitismPercent;		
	}

	public void setSelectionAlgorithm(int selectedIndex) {
		this.selectionAlgorithm = SelectionAlgorithmData.selectionAlgorithms[selectedIndex].algorithm();
	}
	
	public void setCrossAlgorithm(int selectedIndex) {
		this.crossAlgorithm = CrossAlgorithmData.crossAlgorithms[selectedIndex].createAlgorithm(selectedIndex, numberCrossPoints, crossPercent);
	}
	
	public void setMutationAlgorithm(int selectedIndex) {
		this.mutationAlgorithm = MutationAlgorithmData.mutationAlgorithms[selectedIndex].createAlgorithm(selectedIndex, numberMutationPoints);
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}
	
	public void setInputFilePath(String path) {
		combinatoricsProblemData = readDATFile(path);
		System.out.println("Distances:" + Arrays.deepToString(combinatoricsProblemData.left));
		System.out.println("Fluxes:" + Arrays.deepToString(combinatoricsProblemData.right));
	}

	private PairTuple<int[][], int[][]> readDATFile(String path) {
		int[][] dist = null, flux = null;
		File file = new File(path);
		Scanner reader = null;

	    try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    int n = reader.nextInt();
	    dist = new int[n][n];
	    flux = new int[n][n];
	    
	    for(int i = 0; i < n * n; i++) {
	    	dist[i / n][i % n] = reader.nextInt();
	    }
	    for(int i = 0; i < n * n; i++) {
	    	flux[i / n][i % n] = reader.nextInt();
	    }
		reader.close();
		
		return new PairTuple<>(dist, flux);

	}
}
