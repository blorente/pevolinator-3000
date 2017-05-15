package model.population.tree;

import model.population.Genome;
import model.population.Individual;

public class TreeIndividual extends Individual {
	
	public Node root;

	public TreeIndividual(Genome genome) {
		super(genome);
		throw new RuntimeException("No debería pasar");
	}

	public TreeIndividual(Individual other) {
		super(other);
		throw new RuntimeException("No debería pasar");
	}
	
	public TreeIndividual(Node root){
		super();
		this.root = root;
	}

	@Override
	public String toString() {
		return "TreeIndividual: " + root;
	}
	
}
