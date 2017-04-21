package model.solvers.mutation;

import model.solvers.cross.CrossAlgorithm;
import model.solvers.cross.CrossAlgorithmData;
import model.solvers.cross.NPointCrossAlgorithm;
import model.solvers.cross.PMXCrossAlgorithm;

public class MutationAlgorithmData {
	
	public String name;

    public MutationAlgorithmData(String name) {
        this.name = name;
    }

    public static final MutationAlgorithmData Granular = new MutationAlgorithmData("Granular");
    public static final MutationAlgorithmData Insertion = new MutationAlgorithmData("Insertion");

    public static final MutationAlgorithmData[] mutationAlgorithms = {
    		Granular,
    		Insertion
    };
    
    public MutationAlgorithm createAlgorithm(int selected, int mutationPoints) {
    	if (selected == 0) {
    		return new GranularMutationAlgorithm();
    	} else if (selected == 1) {
    		return new InsertionMutationAlgorithm(mutationPoints);
    	}
    	return null;
    }

    @Override
    public String toString() {
        return name;
    }

}
