package model.solvers.fitness;

import model.population.Genome;

public class SecondFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		double x = genome.getGene(0).doubleValue();
		double y = genome.getGene(1).doubleValue();
		double fOfXY = -(y + 47) * sin(sqrt(abs(y + (x / 2.0) + 47))) - x * sin(sqrt(abs(x - (y + 47))));
		return -fOfXY;
	}

}
