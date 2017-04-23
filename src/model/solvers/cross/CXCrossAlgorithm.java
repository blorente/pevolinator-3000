package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;

public class CXCrossAlgorithm extends CrossAlgorithm {

	public CXCrossAlgorithm(double crossRate) {
		super(crossRate);
	}
	
	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		performCXCross(ind, selected);		
		
		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performCXCross(Individual ind, Individual selected) {
		Gene firstChild[] = generateSonCX(ind.getGenome(), selected.getGenome());
		Gene secondChild[] = generateSonCX(selected.getGenome(), ind.getGenome());
		
		ind.getGenome().copyFromDiscrete(new Genome(arrayToList(firstChild)), 0, firstChild.length);
		selected.getGenome().copyFromDiscrete(new Genome(arrayToList(secondChild)), 0, secondChild.length);
	}

	private Gene[] generateSonCX(Genome firstG, Genome secondG) {
		int genomeSize = firstG.totalSize();
		Gene[] son = new Gene[genomeSize];
		
		int startingV = firstG.getGene(0).intValue();
		son[0] = firstG.getGene(0);
		int nextV = secondG.getGene(0).intValue();
		
		while(nextV != startingV){
			int j = 0;
			boolean found = false;
			while(j < genomeSize && !found){
				if(firstG.getGene(j).intValue() == nextV){
					found = true;
				}else{
					j++;
				}
			}
			son[j] = firstG.getGene(j);
			nextV = secondG.getGene(j).intValue();
		}
		for(int i = 0; i < genomeSize; i++){
			if(son[i] == null){
				son[i] = secondG.getGene(i);
			}
		}
		
		return son;
	}

}
