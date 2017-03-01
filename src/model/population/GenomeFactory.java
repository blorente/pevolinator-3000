package model.population;

import model.population.genes.BinaryArrayGene;
import model.population.genes.Gene;

import java.util.Random;

public class GenomeFactory {
    private static final double UNIFORM_MUTATION_PERCENT = 0.5;

    public static Genome crateRandomBinary(int genomeSize, Random seed, double xmax, double xmin, double tolerance) {
        Genome res = new Genome();
        for (int i = 0; i < genomeSize; i++) {
            Gene randomGene = new BinaryArrayGene(xmax, xmin, tolerance);
            randomGene.mutateSelf(UNIFORM_MUTATION_PERCENT, seed);
            res.addGene(randomGene);
        }
        return res;
    }
}
