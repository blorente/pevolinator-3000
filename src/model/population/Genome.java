package model.population;

import model.population.genes.Gene;

import java.util.ArrayList;
import java.util.List;

public class Genome {
	private List<Gene> genes;

	public Genome(int size) {
	    genes = new ArrayList<>(size);
    }

    public Genome() {
	    this.genes = new ArrayList<>();
    }

    public Genome(Genome source) {
	    genes = new ArrayList<>(source.genes.size());
	    for (Gene gene : source.genes) {
	        genes.add(gene.createCopy(gene));
        }
    }

    public void addGene(Gene gene) {
	    genes.add(gene);
    }

	public Gene getGene(int index) {
	    return genes.get(index);
	}
	
	public int totalSize() {
		int size = 0;
		for (Gene g : genes) {
			size += g.size();
		}
		return size;
	}

	public void copyFrom(Genome source, int from) {
        int[] startingGene = findStartingGene(from);
        int startingGeneIndex = startingGene[0];
        int startingGeneOffset = startingGene[1];

        // First gene
        genes.get(startingGeneIndex).copyFrom(source.getGene(startingGeneIndex), startingGeneOffset);
        // Copy the rest of the genes
        for (int gene = startingGeneIndex + 1; gene < genes.size(); gene++) {
            genes.get(gene).copyFrom(source.getGene(gene), 0);
        }
	}

    private int[] findStartingGene(int from) {
	    int index = 0;
	    int size = 0;
	    while(index < genes.size() && size < from) {
	        size += genes.get(index).size();
	        index++;
        }
        return new int[]{index - 1, (size - from)};
    }

    public void mutateSelf(double mutationPercent) {
	    for (Gene gene : genes) {
	        gene.mutateSelf(mutationPercent);
        }
    }

    @Override
    public String toString() {
	    StringBuilder res = new StringBuilder();
	    res.append("[");
	    for (Gene gene : genes) {
	        res.append(gene.toString()).append("|");
        }
        res.append("]");
        return res.toString();
    }

	public List<Gene> getGenes() {
		return genes;
	}
}
