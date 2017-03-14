package model.reporter;

import java.awt.Color;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import model.population.Population;

public class GUIGraphReporter implements PopulationReporter {
	
	private Plot2DPanel target;
	private static final Color color = Color.cyan;
	private static final double[][] data = {{3, 4, 5}, {40, 41, 42}};
	
	public GUIGraphReporter(Plot2DPanel container) {
		this.target = container;		
	}

	@Override
	public void report(int iteration, Population population) {
		target.addLinePlot("Hello", color, data);
	}

}
