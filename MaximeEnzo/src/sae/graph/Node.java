package sae.graph;

import java.util.HashSet;
import java.util.Set;
import sae.dungeon.Coord;



public class Node {
	private String name;
	private Set<Node> neighbors;
	private Coord coord;
	private Node previous;
	private int cost;
	private int heuristic;
	
	public Node(String name, Coord coords) {
		super();
		this.coord = coords;
		this.name = name;
		neighbors = new HashSet<>();
		cost = 0;
		
	}
	
	public void addNeighbour(Node node) {
		neighbors.add(node);
	}
	
	public Set<Node> neighbors() {
		return neighbors;
	}
	
	public String toString() {
        return name + ", coord=" + coord + '}';
    }
	
	public Coord getCoords() {
		return coord;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrevious(Node node) {
		previous = node;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Node node = (Node) object;
        return name.equals(node.name);
    }

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getHeuristic() {
		return heuristic;
	}
	
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	

}
