package model.solvers.fitness;

import java.util.List;

import model.population.Genome;
import model.population.genes.Gene;

public class FourthFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		List<Gene> xn = genome.getGenes();
		int numGenes = genome.getGenes().size();

		double[] doubleGenes = new double[numGenes];
		for (int i = 0 ; i < numGenes; i++) {
			doubleGenes[i] = xn.get(i).doubleValue();
		}
		
		double fTot = 0.0;
		for (int i = 0; i < doubleGenes.length; i++) {
			double a = doubleGenes[i];
			fTot += sin(a) * pow(sin(((i + 1)*pow(a, 2))/PI()), 20);
		}
		
		//double fTot = sumAll(new int[]{1, numGenes}, (a, i) -> sin(a) * pow(sin(((i + 1)*pow(a, 2))/PI()), 20), doubleGenes);
		return -fTot;
	}

	@Override
	public boolean isMinimization() {
		return true;
	}

}
