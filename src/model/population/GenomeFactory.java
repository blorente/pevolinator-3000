package model.population;

import model.population.genes.BinaryArrayGene;
import model.population.genes.Gene;

import java.util.List;
import java.util.Random;

import main.PairTuple;

public class GenomeFactory {
    private static final double UNIFORM_MUTATION_PERCENT = 0.5;

    public static Genome crateRandomBinary(int genomeSize, Random seed, List<PairTuple<Double,Double>> minMaxParameters, double tolerance) {
        Genome res = new Genome();
        for (int i = 0; i < genomeSize; i++) {
            Gene randomGene = new BinaryArrayGene(minMaxParameters.get(i).right, minMaxParameters.get(i).left, tolerance);
            randomGene.mutateSelf(UNIFORM_MUTATION_PERCENT, seed);
            res.addGene(randomGene);
        }
        return res;
    }
}
