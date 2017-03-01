package model.solvers.fitness;

import model.population.Genome;

public interface Fitness {
	double calculate(Genome genome);
}
