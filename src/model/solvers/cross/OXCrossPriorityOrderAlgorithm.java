package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;

public class OXCrossPriorityOrderAlgorithm extends CrossAlgorithm {

	public OXCrossPriorityOrderAlgorithm(double crossRate) {
		super(crossRate);
	}
	
	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().totalSize();
		int numPoints = (int) (Math.random() * (ind.getGenome().totalSize() - 1));
		
		List<Integer> nums = this.getCrossPointsList(genomeSize, numPoints);
		
		performOXPriorityOrderCross(ind, selected, nums);		
	
		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performOXPriorityOrderCross(Individual ind, Individual selected, List<Integer> points) {
		int genomeSize = ind.getGenome().totalSize();
		
		List<Integer> indPositions = getPointExchanged(points, ind.getGenome());
		List<Integer> selectedPositions = getPointExchanged(points, ind.getGenome());
		
		Gene firstChild[] = copyFromDiscreteInArrayPerPoint(ind.getGenome(), indPositions, genomeSize);
		Gene secondChild[] = copyFromDiscreteInArrayPerPoint(selected.getGenome(), selectedPositions, genomeSize);
		
		fixGenomePrioOrder(firstChild, selected.getGenome(), selectedPositions, genomeSize);
		fixGenomePrioOrder(secondChild, ind.getGenome(), indPositions, genomeSize);
		
		ind.getGenome().copyFromDiscrete(new Genome(arrayToList(firstChild)), 0, firstChild.length);
		selected.getGenome().copyFromDiscrete(new Genome(arrayToList(secondChild)), 0, secondChild.length);
		
	}
	
	private void fixGenomePrioOrder(Gene[] firstChild, Genome genome, List<Integer> selectedPositions, int genomeSize) {
		int i = 0;
		int j = 0;
		while(i < genomeSize){
			if(firstChild[i] == null){
				firstChild[i] = genome.getGene(selectedPositions.get(j));
				j++;
			}
			i++;
		}
		
	}

	protected Gene[] copyFromDiscreteInArrayPerPoint(Genome genome, List<Integer> points, int genomeSize) {
		Gene[] genes = new Gene[genomeSize];
		for(int i = 0; i < genomeSize; i++){
			if(!points.contains(i)){
				genes[i] = genome.getGene(i);
			}
		}
		
		return genes;
	}

	List<Integer> getPointExchanged(List<Integer> nums, Genome genome){
		List<Integer> positions = new ArrayList<>(nums.size());
		for(int i = 0; i < nums.size(); i++){
			Integer point = nums.get(i);
			for(int j = 0; j < genome.totalSize(); j++){
				if(genome.getGene(j).intValue() == point){
					positions.add(j);
				}
			}
		}
		
		return positions;
	}
}
