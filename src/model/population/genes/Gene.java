package model.population.genes;

public interface Gene {
	int size();
	void mutate(int index);
	
	int intValue();
	double doubleValue();
}
