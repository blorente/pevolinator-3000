package model.solvers.fitness;

import model.population.Genome;

public class ThirdFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		double x = genome.getGene(0).doubleValue();
		double y = genome.getGene(1).doubleValue();
		double fOfXY = 21.5 + x * sin(4 * PI() * x) + y * sin(20 * PI() * x);
		return fOfXY;
	}	
}
