package sae.solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public class SolverWithBFS implements Solver{

    private Node nodeA;
    private Node nodeB;
    private GraphSoluce soluce;
    private int steps;
    
    public SolverWithBFS(Node A, Node B){
        this.nodeA = A;
        this.nodeB = B;
        this.steps = 0;
    }
    
    /*
    resolve()
    Résolution du plus court chemin entre deux noeuds avec l'algorithme de parcours en largeur.
    1. On crée une file et une liste de marquage.
    2. On ajoute le noeud de départ à la file et à la liste de marquage.
    3. Tant que la file n'est pas vide, on retire le premier élément de la file.
     -> Pour chaque voisin du noeud courant, si le voisin n'est pas marqué, on le marque, on l'ajoute à la file et on lui attribue le noeud courant comme précédent.
    4. On incrémente le nombre d'étapes.
    5. On remonte le chemin à partir du noeud d'arrivée en ajoutant chaque noeud à la solution.
    /*/
    @Override
    public void resolve() {
    	this.soluce = new GraphSoluce();
        List<Node> file = new LinkedList<Node>();
        List<Node> marque = new ArrayList<Node>();
        marque.add(nodeA);
        file.add(nodeA);
        while (!file.isEmpty()) {
            Node current = file.remove(0);    
            for (Node voisin : current.neighbors()) {
                if (!marque.contains(voisin)) {
                    voisin.setPrevious(current);
                    marque.add(voisin);
                    file.add(voisin);
                }
            }
            steps++;
        }
        Node current = nodeB;    
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