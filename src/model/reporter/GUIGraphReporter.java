package model.reporter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

import model.population.Individual;
import model.population.Population;

public class GUIGraphReporter extends PopulationReporter {
	
	private Plot2DPanel target;
	private JLabel fitnessResult;
	private JLabel indivResult;
	private List<Double> bestOverall;
	private List<Double> bestOnGeneration;
	private List<Double> averagesOnGeneration;
	private Individual absoluteBest;
	
	private static final Color colorBestOverall = Color.cyan;
	private static final Color colorBestGeneration = Color.red;
	private static final Color colorAverages = Color.green;
	
	public GUIGraphReporter(Plot2DPanel container, JLabel fitnessResult, JLabel indivResult) {
		target = container;
		this.fitnessResult = fitnessResult;
		this.indivResult = indivResult;
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
        target.addLegend("SOUTH");
		plotList("Best Overall", bestOverall, colorBestOverall);
		plotList("Best on Generation", bestOnGeneration, colorBestGeneration);
		plotList("Average on Generation", averagesOnGeneration, colorAverages);		
		fitnessResult.setText("Best fitness: " + absoluteBest.getAbsoluteFitness());
		System.out.println("Best fitness: " + absoluteBest.getAbsoluteFitness());
		indivResult.setText("Best individual: " + absoluteBest.getGenome());
		System.out.println("Best individual: " + absoluteBest.getGenome());
		System.out.println("Number of crosses: " + crosses);
		System.out.println("Number of mutations: " + mutations);
	}

	@Override
	public void report(int iteration, Population population, boolean isMinimization) {		
		double generationTotal = 0.0;
		Individual bestInGeneration = population.getPopulation().get(0);
		
		if (absoluteBest == null) {
			absoluteBest = population.getPopulation().get(0);
		}
		
		for (Individual i : population.getPopulation()) {
			generationTotal += i.getAbsoluteFitness();
			if (isBest(i, bestInGeneration, isMinimization)) {
				bestInGeneration = i;
			}
		}
		
		if (isBest(bestInGeneration, absoluteBest, isMinimization)) {
			absoluteBest = new Individual(bestInGeneration);
		}
				
		bestOverall.add(absoluteBest.getAbsoluteFitness());
		bestOnGeneration.add(bestInGeneration.getAbsoluteFitness());
		averagesOnGeneration.add(generationTotal / (double) population.getSize());		
	}

	private void plotList(String name, List<Double> list, Color color) {
		double[][] data = getDataFromList(list);
		target.addLinePlot(name, color, data);
	}

	private double[][] getDataFromList(List<Double> list) {
		double[][] result = new double[list.size()][2];
		for (int i = 0; i < list.size(); i++) {
			result[i] = new double[]{i, list.get(i)};
		}
		return result;
	}
	
	private boolean isBest(Individual left, Individual right, boolean isMinimization) {
		if (isMinimization)
			return left.getAbsoluteFitness() < right.getAbsoluteFitness();
		else 
			return left.getAbsoluteFitness() > right.getAbsoluteFitness();
	}
}
