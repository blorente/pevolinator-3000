package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;
import model.population.genes.IntegerGene;

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
		
		SortedSet<Integer> points = this.getCrossPoints(genomeSize + 1, numPoints);
		
		performOXPriorityPosCross(ind, selected, points);		

		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performOXPriorityPosCross(Individual ind, Individual selected, SortedSet<Integer> points) {
		int genomeSize = ind.getGenome().getGenes().size();
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
		Gene nullGene = new IntegerGene(-1);
		int insertionSpot = 0;
		int extractionSpot = 0;
 		while(crossedSection.contains(nullGene)) {
			while (crossedSection.get(insertionSpot).intValue() != -1) {
				insertionSpot = (insertionSpot + 1) % size;
			}
			Gene toInsert = genome.getGene(extractionSpot);
			while (crossedSection.contains(toInsert)) {
				extractionSpot = (extractionSpot + 1) % size;
				toInsert = genome.getGene(extractionSpot);
			}
			
			takenValues[insertionSpot] = toInsert;
			crossedSection.set(insertionSpot, toInsert);
		}
	}
	
	protected Gene[] copyFromDiscreteInArrayPerPoint(Genome genome, SortedSet<Integer> points, int genomeSize) {
		Gene[] genes = new Gene[genomeSize];
		for (int i = 0; i < genomeSize; i++) {
			if (points.contains(i)) {
				genes[i] = genome.getGene(i);
			} else {				
				genes[i] = new IntegerGene(-1);
			}
		}
		
		return genes;
	}
	
	

}
