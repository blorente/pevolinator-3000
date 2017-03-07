package model.reporter;

import model.population.Population;

public class ConsoleReporter implements PopulationReporter {
    @Override
    public void report(int iteration, Population population) {
        System.out.println("Reporting population " + iteration);
        System.out.println(population.toString());
        System.out.println("-----------------------------------");
    }
}
