package model.population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.solvers.fitness.Fitness;

public class Population {
	private List<Individual> individuals;
	private double totalFitness;

    public Population() {
        this.individuals = new ArrayList<Individual>();
    }

	public void evaluateMinimize(Fitness fitness){
		totalFitness = 0.0;
		double popMax = Double.MIN_VALUE;
		for (Individual i : individuals){
			i.evaluate(fitness);
			popMax = Math.max(i.getAbsoluteFitness(), popMax);		
		}
		
		for (Individual i: individuals) {
			i.shiftMinimize(popMax, fitness);
			totalFitness += i.getShiftedFitness();	
		}
	}
	
	public void evaluateMaximize(Fitness fitness){
		totalFitness = 0.0;
		double popMin = Double.MAX_VALUE;
		for (Individual i : individuals){
			i.evaluate(fitness);
			popMin = Math.min(i.getAbsoluteFitness(), popMin);		
		}
		
		for (Individual i: individuals) {
			i.shiftMaximize(popMin, fitness);
			totalFitness += i.getShiftedFitness();	
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
		Individual indToAdd = new Individual(indiv.getGenome().returnCopy());
		individuals.add(indToAdd);
	}
	
	public void addIndividualNoCopy(Individual indiv) {
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

	public Population saveElite(double elitismPercent, Comparator<Individual> c) {
		Population elitism = new Population();
		int sizeElite = (int)  Math.ceil(individuals.size() * elitismPercent); //We decided to make the ceil of the percentage because if the user want a 0.1% we think is better to save an individual than none.
		Collections.sort(individuals, c);
		for(int i = 0; i < sizeElite; i++){
			elitism.addIndividual(new Individual(individuals.get(i).getGenome()));
		}
		
		return elitism;
	}

	public void dropWorse(double elitismPercent, Comparator<Individual> c) {
		int sizeElite = (int)  Math.ceil(individuals.size() * elitismPercent);
		Collections.sort(individuals, c);
		int loopLimit = this.getSize() - sizeElite;
		for(int i = getSize()-1; i > loopLimit-1; i--){
			individuals.remove(i);
		}
	}

	public void insertAll(Population elitism) {
		for(Individual i: elitism.individuals){
			 individuals.add(i);
		}
		
	}

	public Individual getBest(Comparator<Individual> comp) {
		Collections.sort(individuals, comp);
		Individual ret = new Individual(individuals.get(0));
		return ret;
	}
}
