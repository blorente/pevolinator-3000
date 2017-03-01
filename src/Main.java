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

    private static final double CROSS_PERCENT = 1;
    private static final double MUTATION_PERCENT = 1;

    private static final int POPULATION_SIZE = 4;
    private static final int NUMBER_GENERATIONS = 4;
    private static final double X_MIN = -250;
    private static final double X_MAX = 250;
    private static final double TOLERANCE = 0.001;
    private static final int GENOME_SIZE = 1;

    private static final int NUMBER_CROSS_POINTS = 1;


    public static void main(String[] args) {
        SolverParameters parameters = new SolverParameters(CROSS_PERCENT, MUTATION_PERCENT, 0);

        Fitness fitness = new FirstFunctionFitness();
        Problem firstFunction = new Problem(POPULATION_SIZE, NUMBER_GENERATIONS, fitness, X_MIN, X_MAX, TOLERANCE, GENOME_SIZE);

        SelectionAlgorithm selectionAlgorithm = new RouletteSelectionAlgorithm();
        CrossAlgorithm crossAlgorithm = new CrossAlgorithm(NUMBER_CROSS_POINTS, parameters.getCrossPercent());
        MutationAlgorithm mutationAlgorithm = new MutationAlgorithm();

        PopulationReporter reporter = new ConsoleReporter();

        Solver solver = new Solver(parameters, firstFunction, selectionAlgorithm, crossAlgorithm, mutationAlgorithm, reporter);
        solver.run();
    }
}
