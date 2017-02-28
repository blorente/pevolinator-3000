package model.population;

import model.population.genes.Gene;

public class Genome {
	private Gene[] genes;

	public Gene getGene(int i) {
		return genes[i];
	}
	
	public int totalSize() {
		int size = 0;
		for (Gene g : genes) {
			size += g.size();
		}
		return size;
	}

	public void copyFrom(Genome source, int intValue) {
				
	}
}
