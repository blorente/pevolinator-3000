package model.reporter;

import model.population.Population;

public abstract class PopulationReporter {
	protected int mutations;
	protected int crosses;
	
	public abstract void setup();
	public abstract void teardown();
	public abstract void report(int iteration, Population population, boolean isMinimization);
	public void reportMutation() {
		this.mutations ++;
	}
	
	public void reportCross() {
		this.crosses++;
	}
}
