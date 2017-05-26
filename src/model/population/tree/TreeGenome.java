package model.population.tree;

import java.util.ArrayList;
import java.util.List;

import controller.PairTuple;
import model.population.Genome;

public class TreeGenome extends Genome {
	
	public Node root;

	public TreeGenome(Node root){
		super();
		this.root = root;
	}
	
	@Override
	public String toString() {
		return "TreeGenome: " + root;
	}

	@Override
	public Genome returnCopy() {
		return this;
	}

	public PairTuple<List<Node>, List<Node>> listNodes() {
		List<Node> operations = new ArrayList<>();
		List<Node> terminals = new ArrayList<>();
		listNodesRec(root, operations, terminals);
		return new PairTuple<>(operations, terminals);
	}

	private void listNodesRec(Node root, List<Node> operations,
			List<Node> terminals) {
		if (root.arity() == 0) {
			terminals.add(root);
		} else {
			operations.add(root);
		}
		
		for (Node child : root.children) {
			listNodesRec(child, operations, terminals);
		}
	}
	
	public boolean isValidProgramTree() {
		boolean valid = true;
		valid &= root != null;
		valid &= root.parent == null;
		valid &= root.isValid();
		return valid;
	}
}
