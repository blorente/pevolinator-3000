package model.solvers.fitness;

import java.util.Arrays;
import java.util.Random;

import model.population.Genome;
import model.population.Individual;
import model.population.Population;
import model.population.tree.TreeEvaluator;
import model.population.tree.TreeGenome;

public class MultiplexFitness extends Fitness {
	
	boolean[][] testCases;
	// D0
	// D1 
	// D2 
	// D3 
	// A0 
	// A1
	boolean[] results;
	int numA;
	
	double averageSize;
	
	private static final int PRUNING_AMOUNT = 2;
	private static final boolean DEBUG = true;
	
	public MultiplexFitness(int numA) {
		super();
		this.numA = numA;
		this.averageSize = 0;
		calculateInputTable(numA);
	}
	
	private void calculateInputTable(int numA) {
		int variables = numA + pow(2, numA);
		int casos = pow(2, numA + pow(2, numA));
		testCases = new boolean[casos][variables];
		
		int segment = 1;
		int caso = segment;
		for (int variable = 0; variable < variables; variable++) {
			caso = segment;
			while (caso < casos) {	
				for (int ndx = 0; ndx < segment; ndx++) {
					testCases[caso + ndx][variable] = true;
				}
				caso += 2 * segment;				
			}
			// Change column
			segment *= 2;
		}
		
		results = new boolean[casos];
		int aStart = pow(2, numA);
		for (int res = 0; res < casos; res++) {
			boolean[] selectedIndices = new boolean[numA];			
			for (int a = 0; a < numA; a++) {
				selectedIndices[a] = testCases[res][aStart + a];
			}
			results[res] = testCases[res][toDecimal(selectedIndices)];
		}

		System.out.println("Test Cases: " + Arrays.deepToString(testCases));
		System.out.println("Results: " + Arrays.toString(results));
	}
	
	private int toDecimal(boolean[] binary) {
		int result = 0;
		for (boolean bit : binary) {
		    result = result * 2 + (bit ? 1 : 0);
		}
		return result;
	}

	@Override
	public double calculate(Genome genome) {
		TreeGenome tree = (TreeGenome) genome;
		int wrong = 0;
		int right = 0;
		for (int testCase = 0; testCase < testCases.length; testCase++) {
			boolean testResult = TreeEvaluator.evalTree(tree.root, testCases[testCase], pow(2, numA));
			if (testResult != results[testCase]) {
				wrong++;
			} else {
				right++;
			}
		}
		
		// Tarpeian method for bloating control
		Random rand = new Random();
		if (tree.singleListNodes().size() > averageSize &&
			rand.nextInt(PRUNING_AMOUNT) == 0) {
			// Miss all cases + 1, infinitely worst fitness 
			
			if (DEBUG) {
				System.out.println("Tarpeian discarded: " + tree.root);
			}
			
			return pow(2, numA + pow(2, numA)) + 1;
		}
		
		return wrong;
	}
		
	@Override
	public void setCurrentPopulation(Population pop) {
		for (Individual ind : pop.getPopulation()) {
			TreeGenome genome = (TreeGenome)ind.getGenome();
			averageSize += genome.singleListNodes().size();
		}		
		averageSize /= pop.getPopulation().size();
	}

	@Override
	public boolean isMinimization() {
		// Contamos el total de fallos de todo el conjunto de entradas,
		// as� que es de minimizaci�n.
		return true;
	}

}
