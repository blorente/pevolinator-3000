package model.solvers.cross;

public class CrossAlgorithmData {

    public String name;

    public CrossAlgorithmData(String name) {
        this.name = name;
    }

    public static final CrossAlgorithmData NPointData = new CrossAlgorithmData("N Point");
    public static final CrossAlgorithmData PMXData = new CrossAlgorithmData("PMX");
    public static final CrossAlgorithmData OX = new CrossAlgorithmData("OX");
    public static final CrossAlgorithmData OXPrioPos = new CrossAlgorithmData("OX Prio Pos");
    public static final CrossAlgorithmData OXPrioOrder = new CrossAlgorithmData("Ox Prio Order");

    public static final CrossAlgorithmData[] crossAlgorithms = {
            NPointData,
            PMXData,
            OX,
            OXPrioPos,
            OXPrioOrder
    };
    
    public CrossAlgorithm createAlgorithm(int selected, int numCrossPoints, double crossRate) {
    	if (selected == 0) {
    		return new NPointCrossAlgorithm(numCrossPoints, crossRate);
    	} else if (selected == 1) {
    		return new PMXCrossAlgorithm(crossRate);
    	} else if (selected == 2) {
    		return new OXCrossAlgorithm(crossRate);
    	} else if (selected == 3) {
    		return new OXCrossPriorityPosAlgorithm(crossRate);
    	} else if (selected == 4) {
    		return new OXCrossPriorityOrderAlgorithm(crossRate);
    	}
    	return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
