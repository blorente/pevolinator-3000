package model.solvers.fitness;

import model.population.Genome;

public class FifthFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		double x = genome.getGene(0).doubleValue();
		double y = genome.getGene(1).doubleValue();
		double fOfXY = sumAll(new int[]{1, 5}, (a, i) -> i*cos((i+1) * a + i), new double[]{x}) * 
				sumAll(new int[]{1, 5}, (a, i) -> i*cos((i+1) * a + i), new double[]{y});
		return fOfXY;
	}

}
