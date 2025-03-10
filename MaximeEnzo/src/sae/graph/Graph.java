package sae.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Node> nodes;

    public Graph() {
    	nodes = new HashSet<>();
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node from, Node to) {
        if (nodes.contains(from) && nodes.contains(to)) {
            from.addNeighbour(to);
        }
        else {
        	System.out.println("Noeud pas dans Graphe");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : nodes) {
        	stringBuilder.append(node).append("\n");
        }
        return stringBuilder.toString();
    }
}