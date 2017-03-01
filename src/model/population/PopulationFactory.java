package model.population;

import java.util.ArrayList;
import java.util.Random;

public class PopulationFactory {

    public static Population createRandomBinary(int populationSize, int genomeSize, int seed, double xmax, double xmin, double tolerance) {
        Population randomPopulation = new Population();
        Random random = new Random(seed);
        for (int i = 0; i < populationSize; i++) {
            Genome randomGenome = GenomeFactory.crateRandomBinary(genomeSize, random, xmax, xmin, tolerance);
            randomPopulation.addIndividual(new Individual(randomGenome));
        }
        return randomPopulation;
    }
}
