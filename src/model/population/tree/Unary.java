package model.population.tree;

import java.util.Random;

public class Unary extends Node {
	public Unary(String op) {
		this.op = op;
	}
	
	public Unary(String op, Node operand, Node parent) {
		this.op = op;
		children.add(operand);
		this.parent = parent;
	}

	public static Node createRandom(Random rand) {
		int position = rand.nextInt(unaryNodes.length);
		return new Unary(unaryNodes[position]);
	}

	@Override
	public int arity() {
		return 1;
	}

	@Override
	public Node mutate(Random rand) {
		return this;
	}
}