package sae.solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithBFS implements Solver{

	private Node nodeA;
	private Node nodeB;
	private GraphSoluce soluce;
	
	public SolverWithBFS(Node A, Node B){
		this.nodeA = A;
		this.nodeB = B;
		this.soluce = new GraphSoluce();
		
	}
	
	public void resolve() {
		List<Node> file = new LinkedList<Node>();
		List<Node> marque = new ArrayList<Node>();
		marque.add(nodeA);
		file.add(nodeA);
		while (!file.isEmpty()) {
			Node current = file.remove(0);	
			for (Node voisin : current.neighbors()) {
				if (!marque.contains(voisin)) {
					voisin.setPrevious(current);
					marque.add(voisin);
					file.add(voisin);
				}
			}
		}
		
	    Node current = nodeB;	
        while (current != null) {
			this.soluce.add(current);
			current = current.getPrevious();
        }
        
	    
	}
	
	public GraphSoluce getSoluce() {
		return soluce;
	}

	@Override
	public int getSteps() {
		return soluce.getSoluce().size();
	}
	
}
