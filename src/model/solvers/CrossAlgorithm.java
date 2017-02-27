package model.solvers;

import model.population.Population;

public interface CrossAlgorithm {

	Population cross(Population population, int crossPercent);
}
