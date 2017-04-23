package model.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
	    Individual base = new Individual(GenomeFactory.createRandomPermutation(genomeSize, n, random));
	    int numPermutations = factorial(genomeSize);
	    for (int i = 0; i < populationSize / numPermutations; i++) {
	    	randomPopulation.insertAll(createPermutations(base, numPermutations));
	    }
	    int remainingPermutations = populationSize - randomPopulation.getSize();
	    Population lastBatch = createPermutations(base, remainingPermutations);
	    for (int i = 0; i  < lastBatch.getSize(); i++) {
	    	randomPopulation.addIndividual(lastBatch.getPopulation().get(i));
	    }
	    
	    return randomPopulation;
    }   
    
    private static int factorial(int n) {
		if (n == 0) { return 1; }
		else {
			return n * factorial(n - 1);
		}
	}
    
    public static Population createPermutations(Individual base, int amount) {
    	Integer[] allIndices = new Integer[base.getGenome().getGenes().size()];
		for (int i = 0; i < allIndices.length; i++) {
			allIndices[i] = i;
		}
		Population res = new Population();
    	fillWithPermutations(res, base, allIndices, amount);
		return res;
    }

	public static Population createIndexPermutations(Individual base, Integer[] points) {
    	Population res = new Population();
    	int maxAmount = factorial(base.getGenome().getGenes().size());
    	fillWithPermutations(res, base, points, maxAmount);
        return res;
    }
    
    private static void fillWithPermutations(Population pop, Individual base, Integer[] basePoints, int amount) {
    	Integer[] exchangePoints = basePoints.clone();
    	fillWithPermutationsRec(pop, base, basePoints, amount, exchangePoints, 0);
    }

	private static void fillWithPermutationsRec(Population pop, Individual base, Integer[] basePoints, int amount, Integer[] exchangePoints, int index) {
		if(index >= exchangePoints.length - 1){
			Individual newInd = new Individual(base);
			newInd.swapGenes(basePoints, exchangePoints);
			if(!newInd.isPermutation()) {
				throw new RuntimeException("Yo dawg! You generated an invalid individual!");
            }
			pop.addIndividual(newInd);
			return;
	    }

	    for(int i = index; i < exchangePoints.length; i++){
	        //Swap the elements at indices index and i
	        Integer t = exchangePoints[index];
	        exchangePoints[index] = exchangePoints[i];
	        exchangePoints[i] = t;

	        //Recurse on the sub array exchangePoints[index+1...end]
	        fillWithPermutationsRec(pop, base, basePoints, amount, exchangePoints, index+1);
	        if (pop.getSize() == amount) {
	        	return;
	        }
	        
	        //Swap the elements back
	        t = exchangePoints[index];
	        exchangePoints[index] = exchangePoints[i];
	        exchangePoints[i] = t;
	    }
	}
}
