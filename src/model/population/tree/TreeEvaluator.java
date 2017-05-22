package model.population.tree;

public class TreeEvaluator {

	public static boolean evalTree(Node root, boolean[] variables, int numD) {
		switch(root.op) {
		case "NOT":
			return !evalTree(root.children.get(0), variables, numD);
		case "OR":
			return (evalTree(root.children.get(0), variables, numD) 
					|| evalTree(root.children.get(1), variables, numD));	
		case "AND":
			return (evalTree(root.children.get(0), variables, numD) 
					&& evalTree(root.children.get(1), variables, numD));	
		case "IF":
			boolean cond = evalTree(root.children.get(0), variables, numD);
			if (cond) {
				return evalTree(root.children.get(1), variables, numD);
			} else {
				return evalTree(root.children.get(2), variables, numD);
			}
		default:
			char varType = root.op.charAt(0);
			int index = Integer.parseInt(root.op.substring(1));
			if (varType == 'A') {
				return variables[numD + index];
			} else if (varType == 'D') {
				return variables[index];
			}
			throw new RuntimeException("Unrecognized variable name: " + root.op);
		}
	}
	
}
