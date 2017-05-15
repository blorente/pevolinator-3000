package model.population.tree;

import java.util.ArrayList;
import java.util.Random;

import controller.PairTuple;
import model.solvers.fitness.Fitness;
import model.solvers.fitness.FitnessFunctionData;

public abstract class Node {
	protected String op;
		
	/*public static final FitnessFunctionData[] fitnessFunctions = {
		FirstFunctionData,
		SecondFunctionData,
		ThirdFunctionData,
		FourthFunctionData,
		FifthFunctionData,
		HospitalFunctionData
	};*/
	
	@Override
	public String toString() {
		return op;
	}
	
	abstract int arity();
	abstract Node newRandom(Random rand);
	
	public class Binary extends Node {
		private final String[] binaryNodes = {
				"AND",
				"OR"
		};
		
		public Node left, right;
		
		public Binary(String op, Node left, Node right) {
			this.op = op;
			this.left = left;
			this.right = right;
		}

		@Override
		int arity() {
			return 2;
		}

		@Override
		public String toString() {
			return "(" + op + " " + left + " " + right + ")";
		}

		@Override
		Node newRandom(Random rand) {
			return swap();
		}

		private Node swap() {
			if (op.equals(binaryNodes[0])) {
				return new Binary(binaryNodes[1], left, right);
			}
			return new Binary(binaryNodes[0], left, right);
		}
	}
	
	public class Unary extends Node {
		private final String[] unaryNodes = {
				"NOT"
		};
		
		public Node operand;
		
		public Unary(String op, Node operand) {
			this.op = op;
			this.operand = operand;
		}

		@Override
		int arity() {
			return 1;
		}

		@Override
		public String toString() {
			return "(" + op + " " + operand + ")";
		}

		@Override
		Node newRandom(Random rand) {
			return this;
		}
	}
	
	public class Ternary extends Node {
		private final String[] ternaryNodes = {
				"IF"
		};
		
		public Node first, second, third;
		
		public Ternary(String op, Node first, Node second, Node third) {
			this.op = op;
			this.first = first;
			this.second = second;
			this.third = third;
		}

		@Override
		int arity() {
			return 3;
		}

		@Override
		public String toString() {
			return "(" + op + " " + first + " " + second + " " + third + ")";
		}

		@Override
		Node newRandom(Random rand) {
			return this;
		}
	}
	
	public class Terminal extends Node {
		private final String[] terminalNodes = {
				"A",
				"D"
		};
		
		private int maxA;
		private int id;
		
		public Terminal(String op, int id, int maxA) {
			this.op = op + id;
			this.id = id;
			this.maxA = maxA;
		}

		@Override
		int arity() {
			return 0;
		}

		@Override
		Node newRandom(Random rand) {
			int toAdd = id;
			String node = op.substring(0, 1);
			while (op.equals(node + toAdd)) {				
				node = terminalNodes[rand.nextInt(2)];
				if (node.equals("A")) {
					toAdd = rand.nextInt(maxA);
				} else {
					toAdd = rand.nextInt((int) Math.pow(2, maxA));
				}
			}			
			return new Terminal(node, toAdd, maxA);
		}
	}
}
