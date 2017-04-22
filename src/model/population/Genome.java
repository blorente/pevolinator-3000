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
	
	public void copyFromDiscrete(Genome source, int from, int to) {
		for (int gene = from; gene < to; gene++) {
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

	public List<Gene> getGenes(int first, int last) {		
		return genes.subList(first, last);
	}

	public void shiftInsert(int from, int to) {
		Gene tmp = genes.get(from);
		for (int i = from; i > to; i--) {
			genes.set(i, genes.get(i - 1));
		}
		genes.set(to, tmp);
	}
	
	public void swapGenes(int first, int second) {
		assert(first <= second);
		Gene tmp = genes.get(first);
		genes.set(first, genes.get(second));
		genes.set(second, tmp);
	}
}
