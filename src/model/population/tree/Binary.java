package model.population.tree;

import java.util.Random;

public class Binary extends Node {

	public Binary(String op, Node left, Node right, Node parent) {
		this.op = op;
		children.add(left);
		children.add(right);
		this.parent = parent;
	}
	
	public Binary(String op){
		this.op = op;
	}

	@Override
	public int arity() {
		return 2;
	}

	@Override
	public Node mutate(Random rand) {
		return swap();
	}

	private Node swap() {		
		if (op.equals(binaryNodes[0])) {
			this.op = binaryNodes[1];			
		}
		this.op = binaryNodes[0];
		return this;
	}

	public static Node createRandom(Random rand) {
		int position = rand.nextInt(binaryNodes.length);
		return new Binary(binaryNodes[position]);
	}
}