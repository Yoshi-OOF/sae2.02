package sae.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sae.dungeon.Coord;
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
	Explication :
	On parcourt la liste des noeuds de la solution du graphe en partant de la fin.
	Pour chaque noeud, on récupère le noeud suivant.
	On récupère les salles correspondantes à ces deux noeuds.
	On récupère la direction entre ces deux salles.
	On ajoute cette direction à la solution.
	On retourne la solution.
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
				if (currentRoom.getNextRooms().get(direction).equals(nextRoom)) {
					direction = directionRetour;
				}
			}

			soluce.addDirection(direction);
		}
		return soluce;
	}
	

}
