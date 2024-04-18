package sae.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class Dungeon2Graph {
	
	private Map<Room,Node> couples;
	private Graph graph;
	
	/*
	Dungeon2Graph(Dungeon dungeon)
	On crée un graphe à partir d'un donjon.
	 -> On récupère la liste des salles du donjon.
	 -> Pour chaque salle, on crée un noeud correspondant.
	 -> On ajoute ce noeud au graphe.
	 -> On crée une correspondance entre la salle et le noeud.
	 -> Pour chaque salle, on ajoute une arête entre le noeud correspondant et les noeuds correspondants des salles voisines.
	/*/
	public Dungeon2Graph(Dungeon dungeon) {
		super();
		graph = new Graph();
		couples = new HashMap<>();
		List<Room> rooms = new ArrayList<>(dungeon.getRooms());
		
		for (int i = 0;i < rooms.size();i++) {
			Node node = new Node(rooms.get(i).getName(),rooms.get(i).getCoords());
			graph.addNode(node);
			couples.put(rooms.get(i),node);
		}
		
		for (Room room : rooms) {
			for (Room neighbour : room.getNextRooms().values()) {
				graph.addEdge(mappedNode(room), mappedNode(neighbour));
			}
		}
		
	}
	
	public Node mappedNode(Room room) {
		return couples.get(room);
	}
	
	// On parcourt la liste des salles du donjon pour trouver la salle correspondante au noeud donné.
	public Room mappedRoom(Node node) {
		for (Room room : couples.keySet()) {
			if (couples.get(room).equals(node)) {
				return room;
			}
		}
		return null;
	}
	
	@Override
    public String toString() {
        return graph.toString();
    }

	/*
	DungeonSoluce transform(GraphSoluce soluceGraph)
	On parcourt la liste des noeuds de la solution du graphe en partant de la fin.
	 -> Pour chaque noeud, on récupère le noeud suivant.
	 -> On récupère les salles correspondantes à ces noeuds.
	 -> On récupère la direction qui permet de passer de la salle courante à la salle suivante.
	 -> On ajoute cette direction à la solution du donjon.
	/*/
	public DungeonSoluce transform(GraphSoluce soluceGraph) {
		DungeonSoluce soluce = new DungeonSoluce();
		List<Node> nodes = soluceGraph.getSoluce();
		for (int i = nodes.size() - 1; i > 0; i--) {
			Node current = nodes.get(i);
			Node next = nodes.get(i - 1);
			Room currentRoom = mappedRoom(current);
			Room nextRoom = mappedRoom(next);
			
            Direction direction = null;
			for (Direction directionRetour : currentRoom.getNextRooms().keySet()) {
				if (currentRoom.getNextRooms().get(directionRetour).equals(nextRoom)) {
					direction = directionRetour;
				}
			}

			soluce.addDirection(direction);
		}
		return soluce;
	}
	
	
	

}
