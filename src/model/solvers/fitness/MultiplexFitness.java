package model.solvers.fitness;

import java.util.Arrays;

import model.population.Genome;
import model.population.tree.TreeEvaluator;
import model.population.tree.TreeGenome;

public class MultiplexFitness extends Fitness {
	
	boolean[][] testCases;
	// D0 D1 D2 D3 A0 A1
	boolean[] results;
	int numA;
	
	public MultiplexFitness(int numA) {
		super();
		this.numA = numA;
		calculateInputTable(numA);
	}
	
	private void calculateInputTable(int numA) {
		int dimX = numA + pow(2, numA);
		int dimY = pow(2, numA + pow(2, numA));
		testCases = new boolean[dimX][dimY];
		
		int segment = 1;
		int line = segment;
		for (int i = 0; i < dimX; i++) {
			line = segment;
			while (line < dimY) {	
				for (int ndx = 0; ndx < segment; ndx++) {
					testCases[i][line + ndx] = true;
				}
				line += 2 * segment;				
			}
			// Change column
			segment *= 2;
		}
		
		results = new boolean[dimY];
		int aStart = pow(2, numA);
		for (int row = 0; row < dimY; row++) {
			boolean[] selectedIndices = new boolean[numA];			
			for (int a = 0; a < numA; a++) {
				selectedIndices[a] = testCases[aStart + a][row];
			}
			results[row] = testCases[toDecimal(selectedIndices)][row];
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
		for (int testCase = 0; testCase < testCases[0].length; testCase++) {
			boolean testResult = TreeEvaluator.evalTree(tree.root, testCases[testCase], pow(2, numA));
			if (testResult != results[testCase]) {
				wrong++;
			}
		}
		
		// TODO: Bloating and such
		
		return 0;
	}
	
	@Override
	public boolean isMinimization() {
		// Contamos el total de fallos de todo el conjunto de entradas,
		// así que es de minimización.
		return true;
	}

}
