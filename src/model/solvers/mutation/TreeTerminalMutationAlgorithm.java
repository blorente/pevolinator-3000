package model.solvers.mutation;

import java.util.List;
import java.util.Random;

import controller.PairTuple;
import model.population.Individual;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeTerminalMutationAlgorithm extends MutationAlgorithm {

	private static final boolean DEBUG = false;
	
	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Random rand = new Random();
		double mutates = rand.nextDouble();
		if (mutates > mutationPercent)
			return;
		
		TreeGenome genome = (TreeGenome) ind.getGenome();
		List<Node> terminals = genome.listNodes().right;
		if (genome.root.arity() == 0) {
			// Root is a terminal
			terminals.add(genome.root);
		}
		
		if (DEBUG) {
			System.out.println("Indiv to mutate:");
			System.out.println(genome);
		}
		
		Node selected = terminals.get(rand.nextInt(terminals.size()));
		Node mutated = selected.mutate(rand);
		if (genome.root.arity() != 0) {
			// It's not the root
			selected.parent.changeChild(selected, mutated);
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
