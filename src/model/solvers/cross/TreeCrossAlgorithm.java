package model.solvers.cross;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PairTuple;
import model.population.Individual;
import model.population.tree.Node;
import model.population.tree.TreeGenome;

public class TreeCrossAlgorithm extends CrossAlgorithm {
	
	private static final boolean DEBUG = false;

	public TreeCrossAlgorithm(double crossRate) {
		super(crossRate);
	}

	@Override
	List<Individual> crossPair(Individual ind, Individual selected) {
		TreeGenome one = (TreeGenome) ind.getGenome();
		TreeGenome other = (TreeGenome) selected.getGenome();
		
		if (DEBUG) {
			System.out.println("Indivs to cross:");
			System.out.println(one);
			System.out.println(other);
		}
		
		PairTuple<List<Node>, List<Node>> oneNodes = one.listNodes();
		PairTuple<List<Node>, List<Node>> otherNodes = other.listNodes();
		
		if (oneNodes.right.size() == 0 || 
				otherNodes.right.size() == 0) {
			// one or other only has one terminal, cannot cross
			List<Individual> ret = new ArrayList<>();
			ret.add(ind);
			ret.add(selected);
			return ret;
		}
		
		Node oneNode = selectNode(oneNodes);
		Node otherNode = selectNode(otherNodes);
		
		// Pointer magic
		if (oneNode.parent != null) {			
			oneNode.parent.changeChild(oneNode, otherNode);
		} else {
			one.root = otherNode;
		}
		
		if (otherNode.parent != null) {			
			otherNode.parent.changeChild(otherNode, oneNode);
		} else {
			other.root = oneNode;
		}
		
		Node tmp = oneNode.parent;
		oneNode.parent = otherNode.parent;
		otherNode.parent = tmp;
		
		if (DEBUG) {
			System.out.println("Indivs after cross:");
			System.out.println(one);
			System.out.println(other);
		}
		
		List<Individual> ret = new ArrayList<>();
		ret.add(ind);
		ret.add(selected);
		return ret;
	}

	private Node selectNode(PairTuple<List<Node>, List<Node>> nodes) {
		Random rand = new Random();
		int isTerminal = rand.nextInt(10);
		Node ret = null;
		// If it's a terminal tree, we want to cross only terminals
		if (isTerminal == 9 || nodes.left.size() == 0) { 
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
		return ((TreeGenome)ind.getGenome()).isValidProgramTree();
	}

}
