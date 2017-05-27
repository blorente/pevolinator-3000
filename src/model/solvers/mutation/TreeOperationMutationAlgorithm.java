package model.solvers.mutation;

import java.util.List;
import java.util.Random;

import model.population.Individual;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeOperationMutationAlgorithm extends MutationAlgorithm {	

	private static final boolean DEBUG = false;

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Random rand = new Random();
		double mutates = rand.nextDouble();
		if (mutates > mutationPercent)
			return;
		
		
		TreeGenome genome = (TreeGenome) ind.getGenome();
		List<Node> operations = genome.listNodes().left;		
		
		if (DEBUG) {
			System.out.println("Indiv to mutate:");
			System.out.println(genome);
		}
		
		if (genome.root.arity() == 0) {
			if (DEBUG) {
				System.out.println("Indiviual is terminal only. Aborting mutation.");
			}
			return;
		}
		
		Node selected = operations.get(rand.nextInt(operations.size()));
		Node mutated = selected.mutate(rand);
		if (selected.parent != null) {
			selected.parent.changeChild(selected, mutated);			
		} else {
			genome.root = mutated;
		}
		
		if (DEBUG) {
			System.out.println("Indiv mutated:");
			System.out.println(genome);
		}
	}

	@Override
	boolean isValid(Individual ind) {	
		return ((TreeGenome)ind.getGenome()).isValidProgramTree();
	}

}
