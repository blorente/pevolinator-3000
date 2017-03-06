package model.solvers.fitness;

import model.population.Genome;

public class FirstFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		double valX = genome.getGene(0).doubleValue();
		double fOfX = -abs(valX*sin(sqrt(abs(valX))));
		return -fOfX;
	}
	
}
