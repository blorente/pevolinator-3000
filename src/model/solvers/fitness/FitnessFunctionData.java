package model.solvers.fitness;

import java.util.ArrayList;
import java.util.List;

import controller.PairTuple;

public class FitnessFunctionData {
	
	public String name;
	public int genomeSize;
	public List<PairTuple<Double, Double>> minMax;
	
	public FitnessFunctionData(String name, int genomeSize, double ...bounds) {
		this.name = name;
		this.genomeSize = genomeSize;
		
		minMax = new ArrayList<>();
		for (int i = 0; i < bounds.length; i += 2) {
			minMax.add(new PairTuple<Double, Double>(bounds[i], bounds[i + 1]));
		}
	}
	
	public static final FitnessFunctionData FirstFunctionData = new FitnessFunctionData("First Function", 1, -250.0, 250.0);
	public static final FitnessFunctionData SecondFunctionData = new FitnessFunctionData("Second Function", 2, -512.0, 512.0, -512.0, 512.0);
	public static final FitnessFunctionData ThirdFunctionData = new FitnessFunctionData("Third Function", 2, -3.0, 12.1, 4.1, 5.8);
	public static final FitnessFunctionData FourthFunctionData = new FitnessFunctionData("Fourth Function", -1, 0, Fitness.PI());
	public static final FitnessFunctionData FifthFunctionData = new FitnessFunctionData("Fifth Function", 2, -10.0, 10.0, -10.0, 10.0);
	public static final FitnessFunctionData HospitalFunctionData = new FitnessFunctionData("Hospital Problem", -2);
	public static final FitnessFunctionData MultiplexerFunctionData = new FitnessFunctionData("Multiplexer Function", -3);
	
	public static final FitnessFunctionData[] fitnessFunctions = {
		FirstFunctionData,
		SecondFunctionData,
		ThirdFunctionData,
		FourthFunctionData,
		FifthFunctionData,
		HospitalFunctionData,
		MultiplexerFunctionData
	};
	
	@Override
	public String toString() {
		return name;
	}

	public Fitness createAlgorithm(int funIndex, PairTuple<int[][], int[][]> combinatoricsProblemData, int numA) {
		if (funIndex == 0) {
            return new FirstFunctionFitness();
        } else if (funIndex == 1) {
            return new SecondFunctionFitness();
        } else if (funIndex == 2) {
            return new ThirdFunctionFitness();
        } else if (funIndex == 3) {
            return new FourthFunctionFitness();
        } else if (funIndex == 4) {
            return new FifthFunctionFitness();
        } else if (funIndex == 5) {  
        	return new HospitalFitness(combinatoricsProblemData);
        } else if (funIndex == 6) {  
        	return new MultiplexFitness(numA);
        }
		return new FirstFunctionFitness();
	}	
	
}
