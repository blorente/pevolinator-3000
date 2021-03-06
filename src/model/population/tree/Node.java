package model.population.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import controller.PairTuple;
import model.solvers.fitness.Fitness;
import model.solvers.fitness.FitnessFunctionData;

public abstract class Node {
	protected String op;
	public Node parent;
	public List<Node> children = new ArrayList<Node>();
	final static String[] unaryNodes = {
			"NOT"
	};
	final static String[] binaryNodes = {
			"AND",
			"OR"
	};
	final static String[] ternaryNodes = {
			"IF"
	};
	private final static String[][] allOperations = {
		unaryNodes,
		binaryNodes,
		ternaryNodes
	};
	
	static final String[] terminalNodes = {
			"A",
			"D"
	};
	
	
	public abstract int arity();
	public abstract Node mutate(Random rand);
	
	public static Node createOp(Random rand, boolean ifsAllowed){
		int frequencies[] = new int[allOperations.length];
		int total = 0;
		int randNum;
		boolean notFound = true;
		
		for(int i = 0; i < allOperations.length; i++){
			total += allOperations[i].length;
			frequencies[i] = total;
		}
		
		if (!ifsAllowed) {
			frequencies[2] = -1;
			total -= allOperations[2].length;
		}
		
		randNum = rand.nextInt(total);
		int i = 0;
		while(notFound){
			if(frequencies[i] > randNum){
				notFound = false;
			}else{
				i++;
			}
		}
		switch(i){
		case 0:
			return Unary.createRandom(rand);
		case 1:
			return Binary.createRandom(rand);
		case 2:
			return Ternary.createRandom(rand);
		}
		return null;
	}
	
	public static Node createTerminal(Random rand,int maxA) {
		return Terminal.createTerminalAux(rand,maxA);
	}
		
	@Override
	public String toString() {
		return "(" + op + " " + children + ")";
	}
	
	public static Node createNode(Random rand, int maxA, boolean ifsAllowed) {
		int totalOps = 0;
		int totalTerms = 0;
		
		for(int i = 0; i < allOperations.length; i++){
			totalOps += allOperations[i].length;
		}
		
		totalTerms = maxA + (int) Math.pow(2, maxA);
		
		int category = rand.nextInt(totalTerms+totalOps);
		if (category < totalTerms) {
			return Node.createTerminal(rand, maxA);
		} else {
			return Node.createOp(rand, ifsAllowed);
		}
	}
	
	public void changeChild(Node oneNode, Node otherNode) {
		int childIndex = children.indexOf(oneNode);
		if (childIndex == -1) {
			throw new RuntimeException("Could not find child " + oneNode + " in " + this);
		}
		children.set(childIndex, otherNode);
	}
	
	public boolean isValid() {
		boolean valid = true;
		for (Node child : children) {
			valid &= child.parent == this;
			valid &= child.isValid();
		}
		return valid;
	}
	public int depth() {
		Node parentAux = parent;
		int counter = 1;
		
		while(parentAux != null){
			parentAux = parentAux.parent;
			counter++;
		}
		
		return counter;
	}
}
