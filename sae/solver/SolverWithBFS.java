package sae.solver;

import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

public class SolverWithBFS implements Solver {
    private Node startNode;
    private Node endNode;
    private GraphSoluce soluce;
    private int steps;

    public SolverWithBFS(Node start, Node end) {
        this.startNode = start;
        this.endNode = end;
        this.soluce = new GraphSoluce();
        this.steps = 0;
    }

    @Override
    public void resolve() {
        if (startNode.equals(endNode)) {
            soluce.add(startNode);
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> predecessors = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);
        predecessors.put(startNode, null); // Start node has no predecessor

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            steps++; // Count the number of steps or nodes polled from the queue

            if (current.equals(endNode)) {
                reconstructPath(predecessors, endNode);
                return;
            }

            for (Node neighbor : current.neighbors()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }
    }

    private void reconstructPath(Map<Node, Node> predecessors, Node endNode) {
        LinkedList<Node> path = new LinkedList<>();
        Node current = endNode;
        while (current != null) {
            path.addFirst(current);
            current = predecessors.get(current);
        }
        for (Node node : path) {
            soluce.add(node);
        }
    }

    @Override
    public GraphSoluce getSoluce() {
        return soluce;
    }

    @Override
    public int getSteps() {
        return steps;
    }
}
