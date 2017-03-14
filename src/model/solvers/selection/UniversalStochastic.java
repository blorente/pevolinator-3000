package model.solvers.selection;

import java.util.List;

import model.population.Individual;
import model.population.Population;

public class UniversalStochastic implements SelectionAlgorithm {

	private double accumulatedProb[];	
	
	@Override
	public Population select(Population population) {
		accumulatedProb = new double[population.getSize()];
		fillProbabilities(population);
		Population selectedPopulation = selectIndividuals(population);
		return selectedPopulation;
	}
	
	private Population selectIndividuals(Population population) {
		Population selectedPopulation = new Population();
		List<Individual> pop = population.getPopulation();
		double interval = 1/population.getSize();
		double rand = Math.random()/population.getSize();
		
		
		for(int i = 0; i < population.getSize(); i++){
			int j = 0;
			while(j < population.getSize() && accumulatedProb[j] < rand){
				j++;
			}
			selectedPopulation.addIndividual(pop.get(j));
			rand += interval;
		}
		return selectedPopulation;
	}
	
	
	
	
	private void fillProbabilities(Population population) {
		List<Individual> pop = population.getPopulation();
		double partialProbSum = 0.0;
		double simplProb;
		double totalFitness = population.getTotalFitness();
		
		for(int i = 0; i < population.getSize(); i++){
			simplProb = pop.get(i).getFitness()/totalFitness;
			partialProbSum += simplProb;
			accumulatedProb[i] = partialProbSum;
		}
	}

}
