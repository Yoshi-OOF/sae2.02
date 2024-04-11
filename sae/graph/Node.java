package sae.graph;
import java.util.HashSet;
import java.util.Set;

import sae.dungeon.Coord;

public class Node {
    private String name;
    private Set<Node> neighbors;
    private Coord coord;

    public Node(String name, Coord coord) {
        this.name = name;
        this.coord = coord;
        this.neighbors = new HashSet<>();
    }

    public void addNeighbour(Node node) {
        neighbors.add(node);
    }

    public String toString() {
        return "Node{name='" + name + "', neighbors=" + neighbors + ", coord=" + coord + '}';
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name);
    }

    public int hashCode() {
        return name.hashCode();
    }
    
	public Set<Node> neighbors() {
		return neighbors;
	}
}