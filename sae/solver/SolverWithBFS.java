package sae.solver;

import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

public class SolverWithBFS implements Solver {
    private GraphSoluce graphSoluce;
    private Node startNode;
    private Node endNode;
    private List<Node> path;

    public SolverWithBFS(GraphSoluce graphSoluce, Node startNode, Node endNode) {
        this.graphSoluce = graphSoluce;
        this.startNode = startNode;
        this.endNode = endNode;
        this.path = new ArrayList<>();
    }
    
    @Override
	public GraphSoluce getSoluce() {
		for (Node node : path) {
			graphSoluce.add(node);
		}
		return graphSoluce;
	}

	@Override
	public void resolve() {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> parent = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(endNode)) {
                Node node = current;
                while (node != null) {
                    path.add(node);
                    node = parent.get(node);
                }
                Collections.reverse(path);
                return;
            }

            for (Node neighbor : current.neighbors()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }
	}

	@Override
	public int getSteps() {
		return path.size();
	}
    
}
