package model.population.tree;

import java.util.Random;

public class Terminal extends Node {

	
	
	private int maxA;
	private int id;
	
	public Terminal(String op, int id, int maxA, Node parent) {
		this.op = op + id;
		this.id = id;
		this.maxA = maxA;
		this.parent = parent;
	}
	
	public Terminal(String op, int id, int maxA) {
		this.op = op + id;
		this.id = id;
		this.maxA = maxA;
	}

	@Override
	public int arity() {
		return 0;
	}

	@Override
	public Node mutate(Random rand) {
		int toAdd = id;
		String node = op.substring(0, 1);
		while (op.equals(node + toAdd)) {				
			node = terminalNodes[rand.nextInt(2)];
			if (node.equals("A")) {
				toAdd = rand.nextInt(maxA);
			} else {
				toAdd = rand.nextInt((int) Math.pow(2, maxA));
			}
		}			
		return new Terminal(node, toAdd, maxA, parent);
	}
	
	
	public static Node createTerminalAux(Random rand,int maxA){
		int toAdd = -1;
		String node = "";
		
		node = terminalNodes[rand.nextInt(2)];
		if (node.equals("A")) {
			toAdd = rand.nextInt(maxA);
		} else {
			toAdd = rand.nextInt((int) Math.pow(2, maxA));
		}
		
		return new Terminal(node, toAdd, maxA);		
	}

	@Override
	public String toString() {
		return op;
	}
	
	
}