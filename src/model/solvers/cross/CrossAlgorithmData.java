package model.solvers.cross;

public class CrossAlgorithmData {

    public String name;

    public CrossAlgorithmData(String name) {
        this.name = name;
    }

    public static final CrossAlgorithmData NPointData = new CrossAlgorithmData("N Point Cross");
    public static final CrossAlgorithmData PMXData = new CrossAlgorithmData("PMX Cross");

    public static final CrossAlgorithmData[] crossAlgorithms = {
            NPointData,
            PMXData
    };
    
    public CrossAlgorithm createAlgorithm(int selected, int numCrossPoints, double crossRate) {
    	if (selected == 0) {
    		return new NPointCrossAlgorithm(numCrossPoints, crossRate);
    	} else if (selected == 1) {
    		return new PMXCrossAlgorithm(crossRate);
    	}
    	return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
