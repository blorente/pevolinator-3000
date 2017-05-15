package model.population.tree;

import model.population.Genome;
import model.population.Individual;

public class TreeIndividual extends Individual {

	public TreeIndividual(Genome genome) {
		super(genome);
		throw new RuntimeException("No debería pasar");
	}

	public TreeIndividual(Individual other) {
		super(other);
		throw new RuntimeException("No debería pasar");
	}

}
