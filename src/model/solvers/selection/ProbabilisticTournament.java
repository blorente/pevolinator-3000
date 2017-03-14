package model.solvers.selection;

import java.util.ArrayList;
import java.util.List;

import model.population.Individual;
import model.population.Population;

public class ProbabilisticTournament implements SelectionAlgorithm {

	private double WinningRate = 0.7;
	
	@Override
	public Population select(Population population) {
		List<Individual> pop = population.getPopulation();
		Population selectedPopulation = new Population();
		Individual selected = null;
		boolean readyToCompete = false;
		
		for(Individual ind : pop){
			if(readyToCompete){
				List<Individual> tournamentWinners = competeTwice(ind, selected);
				for (Individual child : tournamentWinners) {
					selectedPopulation.addIndividual(child);
				}
				selected = null;
				readyToCompete = false;
			}
			else{
				selected = ind;
				readyToCompete = true;
			}
		}
		if (readyToCompete) {
			selectedPopulation.addIndividual(selected);
		}
		
		
		return selectedPopulation;
	}

	private List<Individual> competeTwice(Individual ind1, Individual ind2) {
		List<Individual> selected = new ArrayList<>();;
		Individual best, worst;
		if(ind1.getFitness() > ind2.getFitness()){
			best = ind1;
			worst = ind2;
		}
		else{
			best = ind2;
			worst = ind1;
		}
		for(int i = 0; i < 2; i++){
			double rand = Math.random();
			if(rand < WinningRate){
				selected.add(best);
			}
			else{
				selected.add(worst);
			}
		}		
		return selected;
	}

}
