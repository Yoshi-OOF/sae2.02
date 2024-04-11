package sae.solver;

import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.*;

// A chaque fois qu'il passe dans un noeud il marque le previous de ce noeud (getPrevious) et à la fil il a plus qu'a remonter les prédécessseurs pour trouver le chemin
// Constructeur : SolverWithBFS(Node start, Node end)
public class SolverWithBFS implements Solver {
	private Node start;
	private Node end;
	private GraphSoluce soluce;
	private int steps;

	public SolverWithBFS(Node start, Node end) {
		this.start = start;
		this.end = end;
		this.soluce = new GraphSoluce();
		this.steps = 0;
	}

	@Override
	public void resolve() {
		Queue<Node> queue = new LinkedList<>();
		Set<Node> visited = new HashSet<>();
		Map<Node, Node> previous = new HashMap<>();
		queue.add(start);
		visited.add(start);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.equals(end)) {
				Node node = current;
				while (node != null) {
					soluce.add(node);
					node = previous.get(node);
				}
				return;
			}
			for (Node neighbor : current.neighbors()) {
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
					previous.put(neighbor, current);
				}
			}
			steps++;
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
