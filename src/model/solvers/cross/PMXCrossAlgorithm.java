package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import model.population.Genome;
import model.population.Individual;
import model.population.Population;
import model.population.genes.Gene;
import model.population.genes.IntegerGene;

public class PMXCrossAlgorithm extends CrossAlgorithm {

	private static final int NUM_CROSS_POINTS = 2;

	public PMXCrossAlgorithm(double crossRate) {
		super(crossRate);
	}
	
	@Override
	boolean isValid(Individual ind) {
		return ind.isPermutation();
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		int genomeSize = ind.getGenome().getGenes().size();
		SortedSet<Integer> points = this.getCrossPoints(genomeSize, NUM_CROSS_POINTS);
		
		performPMXCross(ind, selected, points);		

		List<Individual> children = new ArrayList<>();
		children.add(ind);
		children.add(selected);	
		return children;
	}

	private void performPMXCross(Individual ind, Individual selected,
			SortedSet<Integer> points) {
		int first = points.first().intValue();
		int last = points.last().intValue();
		int genomeSize = ind.getGenome().getGenes().size();
		
		Genome indOrig = new Genome(ind.getGenome()); //debug
		Genome selOrig = new Genome(selected.getGenome()); //debug
				
		Genome newGenomeSel = getSelectedForCrossing(ind, first, last, genomeSize);
		Genome newGenomeInd = getSelectedForCrossing(selected, first, last, genomeSize);
		
		fill(newGenomeInd, selected.getGenome(), ind.getGenome(), genomeSize);
		fill(newGenomeSel, ind.getGenome(), selected.getGenome(), genomeSize);
		
		Genome indNonConflict = new Genome(newGenomeInd); // DEBUG
		Genome selNonConflict = new Genome(newGenomeSel); // DEBUG
		
		ind.getGenome().copyFromDiscrete(newGenomeInd, 0, genomeSize);
		if (!ind.isPermutation()) {  //DEBUG
			System.out.println("Somethong went wring with ind");
			System.out.println("Ind original: " + indOrig);
			System.out.println("Sel original: " + selOrig);
			System.out.println("Points:       (" + first + ", " + last + ")");
			System.out.println("indNonConflt: " + indNonConflict);
			System.out.println("selNonConflt: " + selNonConflict);
			System.out.println("newGenomeInd: " + newGenomeInd);
			System.out.println("newGenomeSel: " + newGenomeSel);
		}
		
		selected.getGenome().copyFromDiscrete(newGenomeSel, 0, genomeSize);		
		if (!selected.isPermutation()) { System.out.println("SOmethong went wring with selected"); }
	}
	
	private void fill(Genome newGenome, Genome parent, Genome otherChain, int genomeSize) {
		for(int i = 0; i < genomeSize; i++) {
			if (newGenome.getGenes().get(i).intValue() == -1) {
				Gene toAdd = parent.getGene(i);
				int index = newGenome.getGenes().indexOf(toAdd);
				if (index != -1) {
					toAdd = otherChain.getGene(index);
				}
				newGenome.setGene(i, toAdd);
				System.out.println(newGenome);
			}
		}		
	}

	private Genome getSelectedForCrossing(Individual ind, int first, int last, int genomeSize) {
		Gene nullGene = new IntegerGene(-1);
		List<Gene> selectedGenes = new ArrayList<>();
		for (int i = 0; i < genomeSize; i++) {
			if (i >= first && i <= last) {
				selectedGenes.add(ind.getGenome().getGene(i));
			} else {
				selectedGenes.add(nullGene);
			}
		}
		return new Genome(selectedGenes);
	}
}
