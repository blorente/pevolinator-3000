package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.genes.Gene;
import model.population.genes.IntegerGene;

public class NPointPermutationCrossAlgorithm extends CrossAlgorithm {
	
	int numPoints;

	public NPointPermutationCrossAlgorithm(int numPoints, double crossRate) {
		super(crossRate);
		this.numPoints = numPoints;
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().totalSize();
		List<Individual> children = new ArrayList<>();
		SortedSet<Integer> crossIndices = getCrossPoints(genomeSize, numPoints);
		
		crossIndividuals(ind, selected, crossIndices);
		children.add(ind);
		children.add(selected);
		return children;
	}

	private void crossIndividuals(Individual ind, Individual selected, SortedSet<Integer> crossIndices) {
		int genomeSize = ind.getGenome().totalSize();
		int[] orderedValues = createNuewOrderedValues(genomeSize);
		
		int[] firstPath = createAPath(genomeSize);
		int[] secondPath = createAPath(genomeSize);
		
		int[] firstPositionsArray = getPositions(orderedValues, firstPath);
		int[] secondPositionsArray = getPositions(orderedValues, secondPath);
		
		int[] firstFinalPositions = crossPositions(firstPositionsArray, secondPositionsArray, crossIndices);
		int[] secondFinalPositions = crossPositions(secondPositionsArray, firstPositionsArray, crossIndices);
		
		int[] firstGenome = createGenome(orderedValues, firstFinalPositions);
		int[] secondGenome = createGenome(orderedValues, secondFinalPositions);
		
		Gene[] firstGenomeGene = intToGeneInArray(firstGenome);
		Gene[] secondGenomeGene = intToGeneInArray(secondGenome);
		
		ind.getGenome().copyFromDiscrete(new Genome(arrayToList(firstGenomeGene)), 0, firstGenome.length);
		selected.getGenome().copyFromDiscrete(new Genome(arrayToList(secondGenomeGene)), 0, secondGenome.length);
		
	}

	private Gene[] intToGeneInArray(int[] firstGenome) {
		int genomeSize = firstGenome.length;
		Gene[] genome = new Gene[genomeSize];
		
		for (int i = 0; i < genomeSize; i++){
			genome[i] = new IntegerGene(firstGenome[i]);
		}
		
		return genome;
	}

	private int[] createGenome(int[] orderedValues, int[] finalPositions) {
		int genomeSize = orderedValues.length;
		int[] genome = new int[genomeSize];
			
		for(int i = 0; i < genomeSize; i++){
			int j = 0;
			boolean found = false;
			while(!found){
				if(orderedValues[j] == finalPositions[i]){
					genome[i] = orderedValues[j];
					orderedValues[j] = -1;
					found = true;
				}else if(orderedValues[j] != -1){
					j++;
				}
			}
		}
		
		return null;
	}

	private int[] crossPositions(int[] firstPositionsArray, int[] secondPositionsArray, SortedSet<Integer> crossIndices) {
		int genomeSize = firstPositionsArray.length;
		int[] resultPositions = new int[genomeSize];
		int previous = 0;
		boolean first = true;
		for(Integer index: crossIndices){
			if(first){
				for(int i = previous; i < index; i++){
					resultPositions[i] = firstPositionsArray[i];
				}
				first = false;
			}else{
				for(int i = previous; i < index; i++){
					resultPositions[i] = secondPositionsArray[i];
				}
				first = true;
			}
			previous = index;
		}
		
		
		
		return resultPositions;
	}

	private int[] getPositions(int[] values, int[] path) {
		int genomeSize = path.length;
		int[] positions = new int[genomeSize];
		
		for(int i = 0; i < genomeSize; i++){
			int j = 0;
			boolean found = false;
			while(!found){
				if(path[i] == values[j]){
					positions[i] = j;
					i++;
					values[j] = -1; //As -1 is not a valid number
					found = true;
				}
				else{
					j++;
				}
			}
		}
		
		return null;
	}

	private int[] createAPath(int genomeSize) {
		List<Integer> usedValues = new ArrayList<Integer>(genomeSize);
		int[] path = new int[genomeSize];
		
		int i = 0;
		while(i < genomeSize){
			int value = (int) (Math.random() * (genomeSize-1) + 1);
			if(!usedValues.contains(value)){
				usedValues.add(value);
				i++;
			}
		}
		return path;
	}

	private int[] createNuewOrderedValues(int genomeSize) {
		int[] array = new int[genomeSize];
		for(int i = 0; i<genomeSize; i++){
			array[i] = i+1;
		}
		return array;
	}

	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

}
