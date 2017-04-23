package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.population.Genome;
import model.population.GenomeFactory;
import model.population.Individual;
import model.population.genes.Gene;
import model.population.genes.IntegerGene;

public class ERXCrossAlgorithm extends CrossAlgorithm {

	public ERXCrossAlgorithm(double crossRate) {
		super(crossRate);
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {

		performERXCross(ind, selected);
		
		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performERXCross(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().getGenes().size();
		boolean[][] adyacencyMatrix = new boolean[genomeSize][genomeSize];
		
		fillMatrix(adyacencyMatrix, ind, selected);
		
		int[] firstSon = createSon(adyacencyMatrix, ind, genomeSize);
		int[] secondSon = createSon(adyacencyMatrix, selected, genomeSize);
		
		List<Gene> firstSonList = intArrayToGeneList(firstSon, genomeSize);
		List<Gene> secondSonList = intArrayToGeneList(secondSon, genomeSize);
		
		
		ind.getGenome().copyFromDiscrete(new Genome(firstSonList), 0, genomeSize);
		selected.getGenome().copyFromDiscrete(new Genome(secondSonList), 0, genomeSize);
	}

	private List<Gene> intArrayToGeneList(int[] secondSon, int genomeSize) {
		List<Gene> list = new ArrayList<Gene>(genomeSize);
		for(int i = 0; i < genomeSize; i++){
			list.add(new IntegerGene(secondSon[i]));
		}
		return list;
	}

	private int[] createSon(boolean[][] adyacencyMatrix, Individual ind, int genomeSize) {
		int son[] = new int[genomeSize];
		
		int minActual = Integer.MAX_VALUE;
		int actualValue = ind.getGenome().getGene(0).intValue();
		
		//Quitar el primero de adyacencia de todos
		for(int i = 0; i < genomeSize; i++){
			adyacencyMatrix[i][actualValue] = false;
		}
		
		
		son[0] = actualValue;
		int i = 0;
		while(i < genomeSize){
			List<Integer> posibleNums = new ArrayList<Integer>();
			for(int j = 0; j < genomeSize; j++){
				if(adyacencyMatrix[actualValue][j]){
					if(numConections(adyacencyMatrix,j,genomeSize) < minActual){
						posibleNums = new ArrayList<Integer>();
						posibleNums.add(j);
					}else{
						posibleNums.add(j);
					}
				}
			}
			if(posibleNums.size() > 0){
				int posicion = (int) (Math.random() * (posibleNums.size()-1));
				actualValue = posibleNums.get(posicion);
			}else{
				int k = 0;
				boolean found = false;
				while(k < genomeSize && !found){
					if(!hasAlready(ind.getGenome().getGene(k).intValue(), son, i)){
						actualValue = ind.getGenome().getGene(k).intValue();
						found = true;
					}
					k++;
				}
			}
			for(int k = 0; k < genomeSize; k++){
				adyacencyMatrix[k][actualValue] = false;
			}
			son[i] = actualValue;
			i++;
		}
		
		
		
		
		return son;
	}

	private boolean hasAlready(int intValue, int[] son, int i) {
		for(int j = 0; j < i; j++){
			if(son[j] == intValue){
				return true;
			}
		}
		return false;
	}

	private int numConections(boolean[][] adyacencyMatrix, int j, int genomeSize) {
		int result = 0;
		
		for (int i = 0; i < genomeSize; i++){
			if(adyacencyMatrix[j][i]){
				result++;
			}
		}
		return result;
	}

	private void fillMatrix(boolean[][] adyacencyMatrix, Individual ind1, Individual ind2) {
		int genomeSize = ind1.getGenome().getGenes().size();
		for(int i = 0; i<genomeSize; i++){
			if(i>0 && i < genomeSize-1){
				adyacencyMatrix[ind1.getGenome().getGene(i).intValue()][ind1.getGenome().getGene(i-1).intValue()] = true;
				adyacencyMatrix[ind1.getGenome().getGene(i).intValue()][ind1.getGenome().getGene(i+1).intValue()] = true;
			}else if(i==0){
				adyacencyMatrix[ind1.getGenome().getGene(i).intValue()][ind1.getGenome().getGene(genomeSize-1).intValue()] = true;
				adyacencyMatrix[ind1.getGenome().getGene(i).intValue()][ind1.getGenome().getGene(i+1).intValue()] = true;
			}else{
				adyacencyMatrix[ind1.getGenome().getGene(i).intValue()][ind1.getGenome().getGene(i-1).intValue()] = true;
				adyacencyMatrix[ind1.getGenome().getGene(i).intValue()][ind1.getGenome().getGene(0).intValue()] = true;
			}
		}
		for(int i = 0; i<genomeSize; i++){
			if(i>0 && i < genomeSize-1){
				adyacencyMatrix[ind2.getGenome().getGene(i).intValue()][ind2.getGenome().getGene(i-1).intValue()] = true;
				adyacencyMatrix[ind2.getGenome().getGene(i).intValue()][ind2.getGenome().getGene(i+1).intValue()] = true;
			}else if(i==0){
				adyacencyMatrix[ind2.getGenome().getGene(i).intValue()][ind2.getGenome().getGene(genomeSize-1).intValue()] = true;
				adyacencyMatrix[ind2.getGenome().getGene(i).intValue()][ind2.getGenome().getGene(i+1).intValue()] = true;
			}else{
				adyacencyMatrix[ind2.getGenome().getGene(i).intValue()][ind2.getGenome().getGene(i-1).intValue()] = true;
				adyacencyMatrix[ind2.getGenome().getGene(i).intValue()][ind2.getGenome().getGene(0).intValue()] = true;
			}
		}
		
	}

	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	

}
