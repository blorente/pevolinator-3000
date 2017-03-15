package model.solvers.fitness;

import java.util.function.DoubleBinaryOperator;

import model.population.Genome;

public abstract class Fitness {

	public double calculate(Genome genome) {
		throw new RuntimeException();
	}
	
	public double shiftingFitnessMin(double popMax, double fitness) {
		return popMax * 1.05 - fitness;
	}
	
	public double shiftingFitnessMax(double popMin, double fitness) {
		return popMin * 1.05 + fitness;
	}
	
	static double sin(double d) {
		return Math.sin(d);
	}
	
	static double cos(double d) {
		return Math.cos(d);
	}
	
	static double sqrt(double d) {
		return Math.sqrt(d);
	}
	
	static double abs(double d) {
		return Math.abs(d);
	}
	
	static double PI() {
		return Math.PI;
	}
	
	static double pow(double a, double b){
		return Math.pow(a, b);
	}
	
	static double sumAll(int range[], DoubleBinaryOperator operation, double freeVar[]) {
		double result = 0;
		for (int i = range[0]; i < range[1]; i++) {
			for (int free = 0; free < freeVar.length; free++) {
				result += operation.applyAsDouble(freeVar[free], (double)i);
			}
		}
		return result;
	}
}
