package model.solvers.fitness;

import controller.PairTuple;
import model.population.Genome;
import model.population.genes.Gene;

public class HospitalFitness extends Fitness {

	private int n;
	private int[][] F;
	private int[][] D;
	
	public HospitalFitness(PairTuple<int[][], int[][]> params) {
		super();
		this.n = params.left[0].length;
		F = params.right;
		D = params.left;
	}

	@Override
	public double calculate(Genome genome) {
		int[] s = new int[genome.getGenes().size()];
		for (int i = 0; i < genome.getGenes().size(); i++){
			s[i] = genome.getGene(i).intValue();
		}
		
		int fTot = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				fTot += F[i][j]*D[s[i]][s[j]];
			}
		}
		
		return (double) fTot;
	}

	@Override
	public boolean isMinimization() {
		return true;
	}

}
