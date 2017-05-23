package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PairTuple;
import model.population.Individual;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeCrossAlgorithm extends CrossAlgorithm {

	public TreeCrossAlgorithm(double crossRate) {
		super(crossRate);
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		TreeGenome one = (TreeGenome) ind.getGenome();
		TreeGenome other = (TreeGenome) selected.getGenome();
		
		System.out.println("Indivs to cross:");
		System.out.println(one);
		System.out.println(other);
		
		PairTuple<List<Node>, List<Node>> oneNodes = one.listNodes();
		PairTuple<List<Node>, List<Node>> otherNodes = other.listNodes();
		
		Node oneNode = selectNode(oneNodes);
		Node otherNode = selectNode(otherNodes);
		
		// Pointer magic
		oneNode.parent.changeChild(oneNode, otherNode);
		otherNode.parent.changeChild(otherNode, oneNode);
		
		Node tmp = oneNode.parent;
		oneNode.parent = otherNode.parent;
		otherNode.parent = tmp;
		
		System.out.println("Indivs after cross:");
		System.out.println(one);
		System.out.println(other);
		
		List<Individual> ret = new ArrayList<>();
		ret.add(ind);
		ret.add(selected);
		return ret;
	}

	private Node selectNode(PairTuple<List<Node>, List<Node>> nodes) {
		Random rand = new Random();
		int isTerminal = rand.nextInt(10);
		Node ret = null;
		if (isTerminal == 9) {
			// It's terminal
			ret = nodes.right.get(rand.nextInt(nodes.right.size()));
		} else {
			// It's operation
			ret = nodes.left.get(rand.nextInt(nodes.left.size()));
		}
		return ret;
	}

	@Override
	boolean isValid(Individual ind) {
		return true;
	}

}
