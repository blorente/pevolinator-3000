package model.population;

import java.util.ArrayList;
import java.util.List;

import model.solvers.Fitness;

public class Population {
	private List<Individual> individuals;
	double totalFitness;
	
	public Population(int seed) {
		//TODO
	}
	
	public Population() {
		this.individuals = new ArrayList<>();
	}
	
	public void evaluate(Fitness fitness){
		totalFitness = 0.0;
		for (Individual i : individuals){
			i.evaluate(fitness);
			totalFitness += i.getFitness();
		}
	}
	
	public int getSize(){
		return individuals.size();
	}
	
	public List<Individual> getPopulation(){
		return individuals;
	}
	
	public double getTotalFitness(){
		return totalFitness;
	}
	
	public void addIndividual(Individual indiv){
		individuals.add(indiv);
	}
}
