import model.population.Population;
import model.population.PopulationFactory;
import model.solvers.fitness.Fitness;
import model.solvers.fitness.MultiplexFitness;

public class TestyTest {
	public static void main(String[] args) {
		Population pop = PopulationFactory.createProgramIncremental(true, 3, 1000, 1);
		Fitness treeFitness = new MultiplexFitness(1);
		pop.evaluateMinimize(treeFitness);
		System.out.println(pop);		
	}
}
