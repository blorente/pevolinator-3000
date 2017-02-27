package model.population;

import model.solvers.Fitness;

public class Individual {
	private Genome genome;
	private double fitness;
	public void evaluate(Fitness fitnessMethod) {
		this.fitness = fitnessMethod.calculate(genome);
	}
}
