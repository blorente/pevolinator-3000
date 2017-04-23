package model.population.genes;

import java.util.Random;

public class IntegerGene extends Gene {
	
	int value;
	
	public IntegerGene(int value) {
		this.value = value;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public void mutateSelfGranular(double mutationPercent) {
		throw new RuntimeException("Integers don't have this. Fkn nub.");
	}

	@Override
	public void mutateSelfGranular(double mutationPercent, Random seed) {
		throw new RuntimeException("Integers don't have this. Fkn nub.");
	}

	@Override
	public void copyFrom(Gene source, int startIndex) {
		if (startIndex > 0)
			throw new RuntimeException("Plz report");
		value = source.intValue();
	}

	@Override
	public int intValue() {
		return value;
	}

	@Override
	public double doubleValue() {
		return (double) value;
	}

	@Override
	public boolean[] binaryValue() {
		throw new RuntimeException("This is an integer gene. Scrub");
	}

	@Override
	public double getXMax() {
		throw new RuntimeException("This is an integer gene. Scrub");		
	}

	@Override
	public double getXMin() {
		throw new RuntimeException("This is an integer gene. Scrub");
	}

	@Override
	public Gene createCopy(Gene source) {
		return new IntegerGene(source.intValue());
	}
	
	@Override
    public String toString() {
	    StringBuilder res = new StringBuilder();	   
        res.append(intValue());
        return res.toString();
    }
	
	

}
