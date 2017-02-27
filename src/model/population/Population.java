package model.population;

import java.util.ArrayList;
import java.util.List;

import model.solvers.Fitness;

public class Population {
	private List<Individual> individuals;
	
	public Population(int seed) {
		//TODO
	}
	
	public Population() {
		this.individuals = new ArrayList<>();
	}
	
	public void evaluate(Fitness fitness){
		for (Individual i : individuals){
			i.evaluate(fitness);
		}
	}
}
