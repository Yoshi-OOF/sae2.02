package sae.solver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithAstar implements Solver {
	private Node start;
	private Node end;
	private GraphSoluce soluce;
	private int steps;
	
	public SolverWithAstar(Node start, Node end) {
		super();
		this.start = start;
		this.end = end;
		this.steps = 0;
	}

	/*
	resolve()
	Résolution du plus court chemin entre deux noeuds avec l'algorithme A*.
	1. On crée une liste ouverte et une liste fermée.
	2. On ajoute le noeud de départ à la liste ouverte.
	3. Tant que la liste ouverte n'est pas vide, on retire le noeud avec le plus petit coût de la liste ouverte.
	 -> Si le noeud courant est le noeud d'arrivée, on remonte le chemin à partir du noeud d'arrivée en ajoutant chaque noeud à la solution.
	 -> Pour chaque voisin du noeud courant, si le voisin n'est pas dans la liste fermée et que le voisin n'est pas dans la liste ouverte ou que le coût du voisin est inférieur au coût du noeud courant, on met à jour le coût du voisin, on calcule l'heuristique du voisin, on ajoute le voisin à la liste ouverte et on lui attribue le noeud courant comme précédent.
	4. On ajoute le noeud courant à la liste fermée.
	5. On incrémente le nombre d'étapes.
	6. On remonte le chemin à partir du noeud d'arrivée en ajoutant chaque noeud à la solution.
	/*/	
	@Override
	public void resolve() {
		soluce = new GraphSoluce();
		List<Node> closedList = new ArrayList<>();
		PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(n -> n.getHeuristic()));
		openList.add(start);
		start.setHeuristic(calculateHeuristic(start));
		
		while (!openList.isEmpty()) {
			Node current = openList.poll();
			steps++;
			if (current.equals(end)) {
				reconstructPath(current);
				return;
			}
			for (Node neighbour : current.neighbors()) {
				if (!(closedList.contains(neighbour) || (openList.contains(neighbour) && neighbour.getCost() < current.getCost()))) {
					neighbour.setCost(current.getCost() + 1);
					calculateHeuristic(neighbour);
					openList.add(neighbour);
					neighbour.setPrevious(current);
				}
			}
			closedList.add(current);
		}
		
	}
	
	private int calculateHeuristic(Node node) {
        return distance(node, end) + node.getCost();
    }

    private int distance(Node a, Node b) {
    	int x1 = a.getCoords().getX();
    	int y1 = a.getCoords().getY();
    	int x2 = b.getCoords().getX();
    	int y2 = b.getCoords().getY();
    	int dx = Math.abs(x1 - x2);
    	int dy = Math.abs(y1 - y2);
    	return dx + dy;
    }

	private void reconstructPath(Node end) {
		Node current = end;    
        while (current != null) {
            soluce.add(current);
            current = current.getPrevious();
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