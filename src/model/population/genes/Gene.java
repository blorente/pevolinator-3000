package model.population.genes;

import java.util.Random;

public interface Gene {
	int size();
	void mutateSelfGranular(double mutationPercent);
	void mutateSelfGranular(double mutationPercent, Random seed);
	void copyFrom(Gene source, int startIndex);
	
	int intValue();
	double doubleValue();
	boolean[] binaryValue();

	double getXMax();
	double getXMin();

	Gene createCopy(Gene source);
}
