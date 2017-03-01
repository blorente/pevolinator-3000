package model.reporter;

import model.population.Population;

public interface PopulationReporter {
    void report(int iteration, Population population);
}
