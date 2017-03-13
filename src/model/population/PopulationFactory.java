package model.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PairTuple;

public class PopulationFactory {

    public static Population createRandomBinary(int populationSize, int genomeSize, int seed, List<PairTuple<Double,Double>> minMaxParameters, double tolerance) {
        Population randomPopulation = new Population();
        Random random = new Random(seed);
        for (int i = 0; i < populationSize; i++) {
            Genome randomGenome = GenomeFactory.crateRandomBinary(genomeSize, random, minMaxParameters, tolerance);
            randomPopulation.addIndividual(new Individual(randomGenome));
        }
        return randomPopulation;
    }
}
