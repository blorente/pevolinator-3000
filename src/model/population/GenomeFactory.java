package model.population;

import model.population.genes.BinaryArrayGene;
import model.population.genes.Gene;
import model.population.genes.IntegerGene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import controller.PairTuple;

public class GenomeFactory {
    private static final double UNIFORM_MUTATION_PERCENT = 0.5;

    public static Genome crateRandomBinary(int genomeSize, Random seed, List<PairTuple<Double,Double>> minMaxParameters, double tolerance) {
        Genome res = new Genome();
        for (int i = 0; i < genomeSize; i++) {
            Gene randomGene = new BinaryArrayGene(minMaxParameters.get(i).right, minMaxParameters.get(i).left, tolerance);
            randomGene.mutateSelfGranular(UNIFORM_MUTATION_PERCENT, seed);
            res.addGene(randomGene);
        }
        return res;
    }
    
    public static Genome createRandomPermutation(int genomeSize, int n, Random seed) {
    	Genome res = new Genome();
    	List<Integer> bag = new ArrayList<>();
    	for (int i = 1; i <= n; i++) {
    		bag.add(i-1);
    	}
    	Collections.shuffle(bag, seed);
    	for (Integer i : bag) {
    		res.addGene(new IntegerGene(i.intValue()));
    	}
    	return res;
    }
}
