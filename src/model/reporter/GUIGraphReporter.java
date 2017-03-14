package model.reporter;

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import model.population.Population;

public class GUIGraphReporter implements PopulationReporter {
	
	private Plot2DPanel target;
	
	public GUIGraphReporter(JPanel container) {
		this.target = new Plot2DPanel();
		container.add(this.target);
	}



	@Override
	public void report(int iteration, Population population) {
		
	}

}
