package model.solvers.selection;

import model.population.Population;

public interface SelectionAlgorithm {
	Population select(Population population);
}
