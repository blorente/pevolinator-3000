package model.solvers;

import model.population.Population;

public interface SelectionAlgorithm {
	Population select(Population population);
}
