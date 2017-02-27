package model.population;

public interface Gene {
	int size();
	void mutate(int index);
	
	int intValue();
	double doubleValue();
}
