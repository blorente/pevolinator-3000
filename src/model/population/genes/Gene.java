package model.population.genes;

import java.util.Random;

public interface Gene {
	int size();
	void mutateSelf(double mutationPercent);
	void mutateSelf(double mutationPercent, Random seed);
	void copyFrom(Gene source, int startIndex);
	
	int intValue();
	double doubleValue();
	boolean[] binaryValue();

	double getXMax();
	double getXMin();

	Gene createCopy(Gene source);
}
