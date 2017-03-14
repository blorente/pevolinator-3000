package model.reporter;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import model.population.Individual;
import model.population.Population;

public class GUIGraphReporter implements PopulationReporter {
	
	private Plot2DPanel target;
	private List<Double> bestOverall;
	private List<Double> bestOnGeneration;
	private List<Double> averagesOnGeneration;
	private Individual absoluteBest;
	
	private static final Color colorBestOverall = Color.cyan;
	private static final Color colorBestGeneration = Color.red;
	private static final Color colorAverages = Color.green;
	
	public GUIGraphReporter(Plot2DPanel container) {
		this.target = container;
		bestOverall = new ArrayList<>();
		bestOnGeneration = new ArrayList<>();
		averagesOnGeneration = new ArrayList<>();
	}

	@Override
	public void setup() {
		target.removeAllPlots();
	}

	@Override
	public void teardown() {
		plotList(bestOverall, colorBestOverall);
		plotList(bestOnGeneration, colorBestGeneration);
		plotList(averagesOnGeneration, colorAverages);
	}

	@Override
	public void report(int iteration, Population population) {		
		double generationTotal = 0.0;
		Individual bestInGeneration = population.getPopulation().get(0);
		
		if (absoluteBest == null) {
			absoluteBest = population.getPopulation().get(0);
		}
		
		for (Individual i : population.getPopulation()) {
			generationTotal += i.getAbsoluteFitness();
			if (i.getAbsoluteFitness() > bestInGeneration.getAbsoluteFitness()) {
				bestInGeneration = i;
			}
		}
		
		if (bestInGeneration.getAbsoluteFitness() > absoluteBest.getAbsoluteFitness()) {
			absoluteBest = new Individual(bestInGeneration);
		}
				
		bestOverall.add(absoluteBest.getAbsoluteFitness());
		bestOnGeneration.add(bestInGeneration.getAbsoluteFitness());
		averagesOnGeneration.add(generationTotal / (double) population.getSize());		
	}

	private void plotList(List<Double> list, Color color) {
		double[][] dataAverages = getDataFromList(list);
		target.addLinePlot("Hello", color, dataAverages);
	}

	private double[][] getDataFromList(List<Double> list) {
		double[][] result = new double[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			result[i] = new double[]{i, list.get(i)};
		}
		return result;
	}
}
