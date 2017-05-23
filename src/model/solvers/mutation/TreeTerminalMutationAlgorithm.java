package model.solvers.mutation;

import java.util.List;
import java.util.Random;

import controller.PairTuple;
import model.population.Individual;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeTerminalMutationAlgorithm extends MutationAlgorithm {

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Random rand = new Random();
		double mutates = rand.nextDouble();
		if (mutates > mutationPercent)
			return;
		
		TreeGenome genome = (TreeGenome) ind.getGenome();
		List<Node> terminals = genome.listNodes().right;
		
		System.out.println("Indiv to mutate:");
		System.out.println(genome);
		
		Node selected = terminals.get(rand.nextInt(terminals.size()));
		Node mutated = selected.mutate(rand);
		selected.parent.changeChild(selected, mutated);
		
		System.out.println("Indiv mutated:");
		System.out.println(genome);
	}

	@Override
	boolean isValid(Individual ind) {
		return true;
	}

}
