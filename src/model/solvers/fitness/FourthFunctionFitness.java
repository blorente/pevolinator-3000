package model.solvers.fitness;

import java.util.List;

import model.population.Genome;
import model.population.genes.Gene;

public class FourthFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		List<Gene> xn = genome.getGenes();
		
		return super.calculate(genome);
	}

}
