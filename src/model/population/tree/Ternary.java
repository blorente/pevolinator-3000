package model.population.tree;

import java.util.Random;

public class Ternary extends Node {
	public Ternary(String op, Node first, Node second, Node third, Node parent) {
		this.op = op;
		children.add(first);
		children.add(second);
		children.add(third);
		this.parent = parent;
	}
	
	public Ternary(String op){
		this.op = op;
	}

	

	@Override
	public int arity() {
		return 3;
	}

	@Override
	public Node mutate(Random rand) {
		return this;
	}
	
	public static Node createRandom(Random rand) {
		int position = rand.nextInt(ternaryNodes.length);
		return new Ternary(ternaryNodes[position]);
	}
}