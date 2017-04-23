package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;

public class OXCrossPriorityPosAlgorithm extends CrossAlgorithm {

	public OXCrossPriorityPosAlgorithm(double crossRate) {
		super(crossRate);
	}
	
	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().getGenes().size();
		int numPoints = (int) (Math.random() * (ind.getGenome().getGenes().size() - 1) + 1);
		
		SortedSet<Integer> points = this.getCrossPoints(genomeSize, numPoints);
		
		performOXPriorityPosCross(ind, selected, points);		

		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performOXPriorityPosCross(Individual ind, Individual selected, SortedSet<Integer> points) {
		int genomeSize = ind.getGenome().totalSize();
		Gene firstChild[] = copyFromDiscreteInArrayPerPoint(ind.getGenome(), points, genomeSize);
		Gene secondChild[] = copyFromDiscreteInArrayPerPoint(selected.getGenome(), points, genomeSize);
		
		List<Gene> indTakenPoints = arrayToList(firstChild);
		List<Gene> selectedTakenPoints = arrayToList(secondChild);
		
		fixGenomePerPoint(firstChild, indTakenPoints, selected.getGenome(), points.last(), genomeSize);
		fixGenomePerPoint(secondChild, selectedTakenPoints, ind.getGenome(), points.last(), genomeSize);
		
		ind.getGenome().copyFromDiscrete(new Genome(arrayToList(firstChild)), 0, firstChild.length);
		selected.getGenome().copyFromDiscrete(new Genome(arrayToList(secondChild)), 0, secondChild.length);
	}

	protected void fixGenomePerPoint(Gene[] takenValues, List<Gene> crossedSection, Genome genome, int last, int size) {
		int j = last;
		int i = last;
		while(i < size){
			if(takenValues[i] != null){
				i++;
			}
			else if(!crossedSection.contains(genome.getGene(j))){
				takenValues[i] = genome.getGene(j);
				crossedSection.add(genome.getGene(j));
				i++;
				if(j < size - 1)
					j++;
				else
					j = 0;
			}
		}
		i = 0;
		while(i < last){
			if(takenValues[i] != null){
				i++;
			}
			else if(!crossedSection.contains(genome.getGene(j))){
				takenValues[i] = genome.getGene(j);
				crossedSection.add(genome.getGene(j));
				i++;
				if(j < size - 1)
					j++;
				else
					j = 0;
			}
		}
	}
	
	protected Gene[] copyFromDiscreteInArrayPerPoint(Genome genome, SortedSet<Integer> points, int genomeSize) {
		Gene[] genes = new Gene[genomeSize];
		for(Integer point: points){
			genes[point] = genome.getGene(point);
		}
		
		return genes;
	}
	
	

}
