package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;

public class OXCrossAlgorithm extends CrossAlgorithm {
	
	private static final int NUM_CROSS_POINTS = 2;

	public OXCrossAlgorithm(double crossRate) {
		super(crossRate);
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().totalSize();
		SortedSet<Integer> points = this.getCrossPoints(genomeSize, NUM_CROSS_POINTS);
		
		performOXCross(ind, selected, points);		
	
		return null;
	}

	private void performOXCross(Individual ind, Individual selected, SortedSet<Integer> points) {
		int first = points.first().intValue();
		int last = points.last().intValue();
		int genomeSize = ind.getGenome().totalSize();
		Gene firstChild[] = copyFromDiscreteInArray(ind.getGenome(), first, last, genomeSize);
		Gene secondChild[] = copyFromDiscreteInArray(selected.getGenome(), first, last, genomeSize);
		
		List<Gene> indCrossedSection = ind.getGenome().getGenes(first, last);
		List<Gene> selectedCrossedSection = selected.getGenome().getGenes(first, last);
		
		fixGenome(firstChild, indCrossedSection, selected.getGenome(), first, last, genomeSize);
		fixGenome(secondChild, selectedCrossedSection, ind.getGenome(), first, last, genomeSize);

		ind.getGenome().copyFromDiscrete(new Genome(arrayToList(firstChild)), 0, firstChild.length);
		selected.getGenome().copyFromDiscrete(new Genome(arrayToList(secondChild)), 0, secondChild.length);
	}
	

	
	public Gene[] copyFromDiscreteInArray(Genome source, int from, int to, int size) {
		Gene result[] = new Gene[size];
		
		for (int gene = from; gene < to; gene++) {
			result[gene] = source.getGene(gene);
		}
		return result;
	}
	
	protected void fixGenome(Gene[] takenValues, List<Gene> crossedSection, Genome genome,int first, int last, int size) {
		int j = last;
		int i = last;
		while(i < size){
			if(!crossedSection.contains(genome.getGene(j))){
				takenValues[i] = genome.getGene(j);
				crossedSection.add(genome.getGene(j));
				i++;
			}
			if(j < size - 1)
				j++;
			else
				j = 0;
		}
		i = 0;
		while(i <= first){
			if(!crossedSection.contains(genome.getGene(j))){
				takenValues[i] = genome.getGene(j);
				crossedSection.add(genome.getGene(j));
				i++;
			}
			if(j < size - 1)
				j++;
			else
				j = 0;
		}
	}

}
