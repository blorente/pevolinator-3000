package model.solvers.fitness;

import java.util.List;

import model.population.Genome;
import model.population.genes.Gene;

public class FourthFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		List<Gene> xn = genome.getGenes();
		int numGenes = genome.getGenes().size();
//		for(int i = 0; i<numGenes; i++){
//			Gene g = xn.get(i);
//			final int j = i;
//			fTot += sumAll(new int[]{1, numGenes}, (a) -> sin(a)*pow(sin(((j+1)*pow(a,2))/PI()), 20), new double[]{g.doubleValue()});
//		}
//		
		double[] doubleGenes = new double[numGenes];
		for (int i = 0 ; i < numGenes; i++) {
			doubleGenes[i] = xn.get(i).doubleValue();
		}
		
		double fTot = sumAll(new int[]{1, numGenes}, (a, i) -> sin(a) * pow(sin(((i + 1)*pow(a, 2))/PI()), 20), doubleGenes);
		return -fTot;
	}

	@Override
	public boolean isMinimization() {
		return true;
	}

}
