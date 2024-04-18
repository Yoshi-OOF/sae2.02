package sae.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphSoluce {
	
	List<Node> soluce;
	
	public GraphSoluce() {
		soluce = new ArrayList<Node>();
	}
	
	public void add(Node node) {
		soluce.add(node);
	}
	
	public List<Node> getSoluce() {
        return new ArrayList<>(soluce);
    }
	
	public String toString() {
        return "GraphSoluce{" + "soluce=" + soluce + '}';
    }

}
