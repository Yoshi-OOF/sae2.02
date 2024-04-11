package sae.graph;

import java.util.HashSet;
import java.util.Set;
import sae.dungeon.Coord;


public class Node {
	private String name;
	private Set<Node> neighbors;
	private Coord coord;
	
	public Node(String name, Coord coords) {
		super();
		this.coord = coords;
		this.name = name;
		neighbors = new HashSet<>();
	}
	
	public void addNeighbour(Node node) {
		neighbors.add(node);
	}
	
	public Set<Node> neighbors() {
		return neighbors;
	}
	
	public void addNeighbors(Node node) {
		neighbors.add(node);	
	}
	
	public String toString() {
        return "Node{name='" + name + "', coord=" + coord + '}';
    }
	
	public Coord getCoords() {
		return coord;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Node node = (Node) object;
        return name.equals(node.name);
    }
	

}
