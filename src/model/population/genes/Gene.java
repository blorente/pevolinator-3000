package model.population.genes;

import java.util.Random;

public abstract class Gene {
	abstract public int size();
	abstract public void mutateSelfGranular(double mutationPercent);
	abstract public void mutateSelfGranular(double mutationPercent, Random seed);
	abstract public void copyFrom(Gene source, int startIndex);
	
	abstract public int intValue();
	abstract public double doubleValue();
	abstract public boolean[] binaryValue();

	abstract public double getXMax();
	abstract public double getXMin();

	abstract public Gene createCopy(Gene source);
	
	@Override
	public boolean equals(Object o){
		Gene g = (Gene) o;
		return this.doubleValue() == g.doubleValue();
	}
	
	@Override
	public int hashCode() {		
		return intValue();
	}
	
	
}
