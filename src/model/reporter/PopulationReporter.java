package model.reporter;

import model.population.Population;

public interface PopulationReporter {
	void setup();
	void teardown();
    void report(int iteration, Population population);
}
