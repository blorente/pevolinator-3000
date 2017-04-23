package model.solvers.mutation;

import model.solvers.fitness.Fitness;

public class MutationAlgorithmData {
	
	public String name;

    public MutationAlgorithmData(String name) {
        this.name = name;
    }

    public static final MutationAlgorithmData Granular = new MutationAlgorithmData("Granular");
    public static final MutationAlgorithmData Insertion = new MutationAlgorithmData("Insertion");
    public static final MutationAlgorithmData Exchange = new MutationAlgorithmData("Exchange");
    public static final MutationAlgorithmData Reversion = new MutationAlgorithmData("Reversion");
    public static final MutationAlgorithmData Heuristic = new MutationAlgorithmData("Heuristic");
    public static final MutationAlgorithmData RoundInsertion = new MutationAlgorithmData("Round Insertion");
    public static final MutationAlgorithmData SpecialReversion = new MutationAlgorithmData("Special Reversion");

    public static final MutationAlgorithmData[] mutationAlgorithms = {
    		Granular,
    		Insertion,
    		Exchange,
    		Reversion,
    		Heuristic,
    		RoundInsertion,
    		SpecialReversion
    };
    
    public MutationAlgorithm createAlgorithm(int selected, int mutationPoints, Fitness fitnessFunction) {
    	if (selected == 0) {
    		return new GranularMutationAlgorithm();
    	} else if (selected == 1) {
    		return new InsertionMutationAlgorithm(mutationPoints);
    	} else if (selected == 2) {
    		return new ExchangeMutationAlgorithm();
    	} else if (selected == 3) {
    		return new ReversionMutationAlgorithm();
    	} else if (selected == 4) {
    		return new HeuristicMutationAlgorithm(mutationPoints, fitnessFunction);
    	} else if (selected == 5) {
    		return new RoundInsertionMutationAlgorithm(mutationPoints);
    	} else if (selected == 6) {
    		return new SpecialReversionMutationAlgorithm(fitnessFunction);
    	}
    	return null;
    }

    @Override
    public String toString() {
        return name;
    }

}
