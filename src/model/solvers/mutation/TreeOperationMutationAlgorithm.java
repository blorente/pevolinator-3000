package model.solvers.mutation;

import java.util.List;
import java.util.Random;

import model.population.Individual;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeOperationMutationAlgorithm extends MutationAlgorithm {

	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Random rand = new Random();
		double mutates = rand.nextDouble();
		if (mutates > mutationPercent)
			return;
		
		TreeGenome genome = (TreeGenome) ind.getGenome();
		List<Node> operations = genome.listNodes().left;
		
		System.out.println("Indiv to mutate:");
		System.out.println(genome);
		
		Node selected = operations.get(rand.nextInt(operations.size()));
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
