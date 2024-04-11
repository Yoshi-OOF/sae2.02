package sae.transform;

import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Dungeon2Graph {
    private Graph graph;
    private Map<Room, Node> roomToNodeMap;

    public Dungeon2Graph(Dungeon dungeon) {
        this.graph = new Graph();
        this.roomToNodeMap = new HashMap<>();
        buildGraph(dungeon);
    }

    private void buildGraph(Dungeon dungeon) {
        // For each room in the dungeon, add a node to the graph.
        for (Room room : dungeon.getRooms()) {
            Node node = new Node(room.getName(), room.getCoords());
            graph.addNode(node);
            roomToNodeMap.put(room, node);
        }

        // Add edges between nodes that are adjacent rooms.
        for (Room room : dungeon.getRooms()) {
            Node fromNode = roomToNodeMap.get(room);
            for (Map.Entry<Direction, Room> entry : room.getNextRooms().entrySet()) {
                Node toNode = roomToNodeMap.get(entry.getValue());
                graph.addEdge(fromNode, toNode);
            }
        }
    }

    public Node mappedNode(Room room) {
        return roomToNodeMap.get(room);
    }

    public DungeonSoluce transform(GraphSoluce soluceGraph) {
        DungeonSoluce dungeonSoluce = new DungeonSoluce();
        Node previousNode = null; // To keep track of the previous node in the path

        // Iterate through each node in the graph solution
        for (Node currentNode : soluceGraph.getSoluce()) {
            // Skip the first node, since there's no direction to add yet
            if (previousNode != null) {
                // Find the corresponding rooms for the current and previous nodes
                Room currentRoom = findRoomForNode(currentNode);
                Room previousRoom = findRoomForNode(previousNode);
                
                // Determine the direction from the previous room to the current one
                Direction direction = getDirectionFromRooms(previousRoom, currentRoom);
                if (direction != null) {
                    dungeonSoluce.addDirection(direction); // Add the direction to the dungeon solution
                }
            }
            previousNode = currentNode; // Update the previous node to the current node for the next iteration
        }

        return dungeonSoluce;
    }

    // Helper method to find a room for a given node
    private Room findRoomForNode(Node node) {
        for (Map.Entry<Room, Node> entry : roomToNodeMap.entrySet()) {
            if (entry.getValue().equals(node)) {
                return entry.getKey();
            }
        }
        return null; // Return null if no room corresponds to the node (which should not happen)
    }

    // Helper method to get the direction between two rooms
    private Direction getDirectionFromRooms(Room fromRoom, Room toRoom) {
        for (Map.Entry<Direction, Room> entry : fromRoom.getNextRooms().entrySet()) {
            if (entry.getValue().equals(toRoom)) {
                return entry.getKey();
            }
        }
        return null; // Return null if there's no direct path (which should not happen if the path is valid)
    }

}
