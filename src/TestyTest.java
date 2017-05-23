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
		Population pop = PopulationFactory.createProgramComplete(true, 2, 4, 2);
		Fitness treeFitness = new MultiplexFitness(2);
		pop.evaluateMinimize(treeFitness);		
		System.out.println(pop);
		SelectionAlgorithm selection = new Roulette();
		pop = selection.select(pop);
		CrossAlgorithm cross = new TreeCrossAlgorithm(1);
		PopulationReporter reporter = new ConsoleReporter();
		pop = cross.cross(pop, reporter);
		System.out.println(pop);
		MutationAlgorithm mutator = new TreeTerminalMutationAlgorithm();
		pop = mutator.mutate(pop, 1.0, reporter);
		System.out.println(pop);
		MutationAlgorithm mutator2 = new TreeOperationMutationAlgorithm();
		pop = mutator2.mutate(pop, 1.0, reporter);
		System.out.println(pop);
		 
	}
}
