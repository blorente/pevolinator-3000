package model.solvers.fitness;

import java.util.List;

import model.population.Genome;
import model.population.genes.Gene;

public class FourthFunctionFitness extends Fitness {

	@Override
	public double calculate(Genome genome) {
		List<Gene> xn = genome.getGenes();
		int numGenes = genome.getGenes().size();
		double fTot = 0.0;
		for(Gene g: xn){
			fTot += sumAll(new int[]{1, numGenes}, (a, i) -> sin(a)*pow(((i+1)*pow(a,2))/PI() , 20), new double[]{g.doubleValue()});
		}
		return fTot;
	}

}
