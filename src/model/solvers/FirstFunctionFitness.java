package model.solvers;

import model.population.Genome;

public class FirstFunctionFitness implements Fitness {

	@Override
	public double calculate(Genome genome) {
		double valX = genome.getGene(0).doubleValue();
		double fOfX = -Math.abs(valX*Math.sin(Math.sqrt(Math.abs(valX))));
		return -fOfX;
	}
}
