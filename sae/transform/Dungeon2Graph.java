package sae.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sae.dungeon.Dungeon;
import sae.dungeon.Room;
import sae.graph.Graph;
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
	
	@Override
    public String toString() {
        return graph.toString();
    }
	

}
