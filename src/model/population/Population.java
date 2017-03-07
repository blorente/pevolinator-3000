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

	public void evaluateMinimize(Fitness fitness){
		totalFitness = 0.0;
		double popMax = Double.MIN_VALUE;
		for (Individual i : individuals){
			i.evaluate(fitness);
			popMax = Math.max(i.getFitness(), popMax);		
		}
		
		for (Individual i: individuals) {
			i.shiftMinimize(popMax, fitness);
			totalFitness += i.getFitness();	
		}
	}
	
	public void evaluateMaximize(Fitness fitness){
		totalFitness = 0.0;
		double popMin = Double.MAX_VALUE;
		for (Individual i : individuals){
			i.evaluate(fitness);
			popMin = Math.min(i.getFitness(), popMin);		
		}
		
		for (Individual i: individuals) {
			i.shiftMaximize(popMin, fitness);
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
		Individual indToAdd = new Individual(new Genome(indiv.getGenome()));
		individuals.add(indToAdd);
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
