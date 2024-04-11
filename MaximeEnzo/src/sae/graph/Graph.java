package sae.graph;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(Node node) {
        nodes.put(node.getName(), node);
    }

    public void addEdge(Node from, Node to) {
        if (nodes.containsKey(from.getName()) && nodes.containsKey(to.getName())) {
            from.addNeighbour(to);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes.values()) {
            sb.append(node).append("\n");
        }
        return sb.toString();
    }
}