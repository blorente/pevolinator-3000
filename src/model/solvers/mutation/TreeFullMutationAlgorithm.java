package model.solvers.mutation;

import java.util.List;
import java.util.Random;

import model.population.Individual;
import model.population.PopulationFactory;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeFullMutationAlgorithm extends MutationAlgorithm {

	private int maxDepth;
	private boolean ifsAllowed;
	private int maxA;
	private boolean DEBUG = true;
	
	public TreeFullMutationAlgorithm(int maxDepth, boolean ifsAllowed, int maxA){
		this.maxDepth = maxDepth;
		this.ifsAllowed = ifsAllowed;
		this.maxA = maxA;
	}
	
	@Override
	void mutateIndividual(Individual ind, double mutationPercent) {
		Random rand = new Random();
		double mutates = rand.nextDouble();
		if (mutates > mutationPercent)
			return;
		
		TreeGenome genome = (TreeGenome) ind.getGenome();
		List<Node> allNodes = genome.singleListNodes();
		
		
		Node selected = allNodes.get(rand.nextInt(allNodes.size()));
		if (DEBUG) {
			System.out.println("Indiv to mutate:");
			System.out.println(genome);
			System.out.println("Selected node: " + selected);
		}
		int nodeDepth = selected.depth();
		Individual mutated = new Individual(new TreeGenome(PopulationFactory.createProgramIndividualIncremental(ifsAllowed, (maxDepth - nodeDepth + 1),0,rand,maxA)));
		TreeGenome newGenome = (TreeGenome) mutated.getGenome();
		if(selected.parent == null){
			genome.root = newGenome.root;
		}else{
			selected.parent.changeChild(selected, newGenome.root);
		}
		newGenome.root.parent = selected.parent;
		
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
