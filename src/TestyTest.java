import model.population.Population;
import model.population.PopulationFactory;
import model.reporter.ConsoleReporter;
import model.reporter.PopulationReporter;
import model.solvers.cross.CrossAlgorithm;
import model.solvers.cross.TreeCrossAlgorithm;
import model.solvers.fitness.Fitness;
import model.solvers.fitness.MultiplexFitness;
import model.solvers.mutation.MutationAlgorithm;
import model.solvers.mutation.TreeOperationMutationAlgorithm;
import model.solvers.mutation.TreeTerminalMutationAlgorithm;
import model.solvers.selection.Roulette;
import model.solvers.selection.SelectionAlgorithm;

public class TestyTest {
	public static void main(String[] args) {
		Population pop = PopulationFactory.createProgramIncremental(true, 2, 100, 1);
		Fitness treeFitness = new MultiplexFitness(2);
		CrossAlgorithm cross = new TreeCrossAlgorithm(1);
		PopulationReporter reporter = new ConsoleReporter();
		SelectionAlgorithm selection = new Roulette();
		MutationAlgorithm mutator2 = new TreeOperationMutationAlgorithm();

		for (int i = 0; i < 100; i++) {
			pop.evaluateMinimize(treeFitness);
			pop = selection.select(pop);
			pop = cross.cross(pop, reporter);	
			pop = mutator2.mutate(pop, 1.0, reporter);
		}
		System.out.println(pop);
	}
}
