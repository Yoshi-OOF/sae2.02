package sae.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphSoluce {
    private List<Node> soluce;

    public GraphSoluce() {
        soluce = new ArrayList<>();
    }

    public void add(Node node) {
        soluce.add(node);
    }

    public List<Node> getSoluce() {
        return new ArrayList<>(soluce); // Return a copy to preserve encapsulation
    }
}