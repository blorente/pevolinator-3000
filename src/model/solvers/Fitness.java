package model.solvers;

import model.population.Genome;

public interface Fitness {
	double calculate(Genome genome);
}
