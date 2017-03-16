package model.solvers.selection;

import model.solvers.fitness.Fitness;
import model.solvers.fitness.FitnessFunctionData;

public class SelectionAlgorithmData {

	public String name;
	private SelectionAlgorithm algorithm;	
	
	public SelectionAlgorithmData(String name, SelectionAlgorithm algorithm) {
		super();
		this.name = name;
		this.algorithm = algorithm;
	}

	public static final SelectionAlgorithmData Roulette = 
			new SelectionAlgorithmData("Roulette", new Roulette());
	public static final SelectionAlgorithmData ProbabilisticTournament = 
			new SelectionAlgorithmData("Probabilistic Tour.", new ProbabilisticTournament());
	public static final SelectionAlgorithmData DeterministicTournament = 
			new SelectionAlgorithmData("Deterministic Tour.", new DeterministicTournament());
	public static final SelectionAlgorithmData UniversalStochastic = 
			new SelectionAlgorithmData("Universal Stochastic", new UniversalStochastic());
	
	public static final SelectionAlgorithmData[] selectionAlgorithms = {
			Roulette,
			ProbabilisticTournament,
			DeterministicTournament,
			UniversalStochastic
	};
	
	@Override
	public String toString() {
		return name;
	}

	public SelectionAlgorithm algorithm() {
		return algorithm;
	}
}
