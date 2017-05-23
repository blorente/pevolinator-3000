package model.population.tree;

import model.population.Genome;

public class TreeGenome extends Genome {
	
	public Node root;

	public TreeGenome(Node root){
		super();
		this.root = root;
	}
	
	@Override
	public String toString() {
		return "TreeGenome: " + root;
	}

	@Override
	public Genome returnCopy() {
		return this;
	}
	
	
}
