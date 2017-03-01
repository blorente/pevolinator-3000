package model.population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.solvers.fitness.Fitness;

public class Population {
	private List<Individual> individuals;
	private double totalFitness;

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

    @Override
    public String toString() {
	    StringBuilder ret = new StringBuilder();
	    ret.append("Population {\n");
	    for (Individual ind : individuals) {
	        ret.append(ind.toString());
	        ret.append("\n");
        }
        ret.append("    Total Fitness=");
        ret.append(totalFitness);
        ret.append("\n");
        ret.append("}\n");
        return ret.toString();
    }
}
