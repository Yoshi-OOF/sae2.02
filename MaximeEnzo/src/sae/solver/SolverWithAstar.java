package sae.solver;

import sae.graph.GraphSoluce;
import sae.graph.Node;
import java.util.*;

public class SolverWithAstar implements Solver {
    private Node startNode;
    private Node endNode;
    private GraphSoluce solution;
    private int steps;

    public SolverWithAstar(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.solution = new GraphSoluce();
        this.steps = 0;
    }

    @Override
    public void resolve() {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(this::calculateHeuristic));
        Map<Node, Node> predecessors = new HashMap<>();
        Map<Node, Integer> gCosts = new HashMap<>();
        Set<Node> closedSet = new HashSet<>();

        gCosts.put(startNode, 0);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            steps++;

            if (current.equals(endNode)) {
                reconstructPath(predecessors, endNode);
                return;
            }

            closedSet.add(current);

            for (Node neighbor : current.neighbors()) {
                if (closedSet.contains(neighbor)) continue;
                
                int tentativeGCost = gCosts.get(current) + distance(current, neighbor);

                if (tentativeGCost < gCosts.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    predecessors.put(neighbor, current);
                    gCosts.put(neighbor, tentativeGCost);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
    }

    private int calculateHeuristic(Node node) {
        return distance(node, endNode);
    }

    private int distance(Node a, Node b) {
        // Calculate the distance between two nodes.
        // Implement this based on your Node and Coord structure.
    	int x1 = a.getCoord().getX();
    	int y1 = a.getCoord().getY();
    	int x2 = b.getCoord().getX();
    	int y2 = b.getCoord().getY();
    	int dx = Math.abs(x1 - x2);
    	int dy = Math.abs(y1 - y2);
    	return dx + dy;
    }

    private void reconstructPath(Map<Node, Node> predecessors, Node endNode) {
        LinkedList<Node> path = new LinkedList<>();
        Node current = endNode;
        while (current != null) {
            path.addFirst(current);
            current = predecessors.get(current);
        }
        for (Node node : path) {
            solution.add(node);
        }
    }

    @Override
    public GraphSoluce getSoluce() {
        return solution;
    }

    @Override
    public int getSteps() {
        return steps;
    }
}
