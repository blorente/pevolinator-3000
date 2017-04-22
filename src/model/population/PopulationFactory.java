package model.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.math.plot.utils.Array;

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
    
    public static Population createIntegerCombinatorics(int populationSize, int genomeSize, int seed, int n) {
    	 Population randomPopulation = new Population();
         Random random = new Random(seed);
         for (int i = 0; i < populationSize; i++) {
             Genome randomGenome = GenomeFactory.createRandomPermutation(genomeSize, n, random);
             Individual ind = new Individual(randomGenome);
             if(!ind.isPermutation()) {
				throw new RuntimeException("Yo dawg! You generated an invalid individual!");
             }
             randomPopulation.addIndividual(ind);
         }
         return randomPopulation;
    }
    
    public static Population createIntegerPermutations(Individual base, Integer[] points) {
    	Population res = new Population();
    	fillWithPermutations(res, base, points);
        return res;
    }
    
    private static void fillWithPermutations(Population pop, Individual base, Integer[] basePoints) {
    	Integer[] exchangePoints = basePoints.clone();
    	fillWithPermutationsRec(pop, base, basePoints, exchangePoints, 0);
    }

	private static void fillWithPermutationsRec(Population pop, Individual base, Integer[] basePoints, Integer[] exchangePoints, int index) {
		if(index >= exchangePoints.length - 1){
			Individual newInd = new Individual(base);
			newInd.swapGenes(basePoints, exchangePoints);
			if(!newInd.isPermutation()) {
				throw new RuntimeException("Yo dawg! You generated an invalid individual!");
            }
			pop.addIndividual(newInd);
	    }

	    for(int i = index; i < exchangePoints.length; i++){
	        //Swap the elements at indices index and i
	        Integer t = exchangePoints[index];
	        exchangePoints[index] = exchangePoints[i];
	        exchangePoints[i] = t;

	        //Recurse on the sub array exchangePoints[index+1...end]
	        fillWithPermutationsRec(pop, base, basePoints, exchangePoints, index+1);

	        //Swap the elements back
	        t = exchangePoints[index];
	        exchangePoints[index] = exchangePoints[i];
	        exchangePoints[i] = t;
	    }
	}
}
