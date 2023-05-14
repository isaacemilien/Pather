package pather;

import javafx.scene.paint.Color;
import java.util.ArrayList; // import the ArrayList class
import javafx.scene.paint.Color;



/**
* Manages collection of cells making up the ingame maze
*/

public class Maze {
    
    // FIELDS
    public Room[][] board;
    public int width = 0;
    public int height = 0;

    // Pathfinding
    Room startRoom, endRoom;
    ArrayList<Room> openSet = new ArrayList<Room>();
    ArrayList<Room> closedSet = new ArrayList<Room>();

    // Node path leading to position
    ArrayList<Room> path = new ArrayList<Room>();

    // CONSTRUCTORS
    public Maze(int theWidth, int theHeight) {
        width = theWidth;
        height = theHeight;
        board = new Room[width][height];

        populateRooms();
        populateNeighbours();
        populateRoomSprites(51, 51, 60, 60, 50, "e3dbdbff");

        // Pathfinding
        // Set start room, end room locations
        startRoom = this.getRoom(0, 0);
        endRoom = this.getRoom(0, 4);

        // Add start node open set
        openSet.add(startRoom);

        // Room now being processed
        Room currentRoom;

        // Path finding set loop
        while (!openSet.isEmpty()) {
            
            int winner = 0;
            
            // Find room lowest fCost
            for (int i = 0; i < openSet.size(); i++) {

                if(openSet.get(i).fCost < openSet.get(winner).fCost){
                    winner = i;
                }
            }

            // Set current room
            currentRoom = openSet.get(winner);
            
            // Check arrived goal
            if(currentRoom.x == endRoom.x && currentRoom.y == endRoom.y){
                System.out.println("Reached end goal");

                // Populate path with rooms
                while(currentRoom.previousRoom != null){
                    // Colour path way to end position
                    currentRoom.roomSprite.setFill(Color.web("5599ffff"));

                    path.add(currentRoom.previousRoom);
                    currentRoom = currentRoom.previousRoom;
                }

                System.out.println("Path length: " + path.size());
                return;
            }

            // Access each neighbour of current room
            for (int i = 0; i < currentRoom.neighbours.size(); i++) {

                // Save reference of neighbour
                Room neighbour = currentRoom.neighbours.get(i);

                // Skip neighbour if in closed set
                if(closedSet.contains(neighbour))
                    continue;
                
                int tempG = currentRoom.gCost + 1;

                // Check if openset already contains neighbour, otherwise add to openlist
                if(openSet.contains(neighbour)){
                    // Check if new g cost to get to neighbour is better than already existing one
                    if(tempG < neighbour.gCost)
                        neighbour.gCost = tempG;
                }else{
                    neighbour.gCost = tempG;
                    openSet.add(neighbour);
                }

                // Set neighbour h cost (estimate distance)
                neighbour.hCost = heuristic(neighbour.x, neighbour.y, endRoom.x, endRoom.y);

                // Set neighbour f cost
                neighbour.fCost = neighbour.gCost + neighbour.hCost;
                neighbour.previousRoom = currentRoom;
            }

            // Move current room from open to closed set
            openSet.remove(winner);
            closedSet.add(currentRoom);
        }
    }

    // METHODS
    
    // Add a room to the board at given col and row (top left is 0,0)
    public void setRoom(int col, int row, Room r) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            board[col][row] = r;
        } 
    }

    // Return the room object (or null) for a given col and row (top left is 0,0)
    public Room getRoom(int col, int row) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            return board[col][row];
        } else {
            return null;
        }
    }

    // Fills empty maze grid with nodes
    void populateRooms(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Room();

                // Save room position to room
                getRoom(i, j).x = i;
                getRoom(i, j).y = j;
            }
        }
    }

    // Save each rooms neighbour
    public void populateNeighbours(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j].neighbours = getNeighbours(i, j);
            }
        }
    }

    // Add square sprite image to each room location
    public void populateRoomSprites(double xOrigin, double yOrigin, double height, double width, double squareGap, String color){
        double x = xOrigin; 
        double y = yOrigin;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j].roomSprite = new Sprite(x, y, height, width, Color.web(color));
                x += width + squareGap;
            }
            x = xOrigin;
            y += height + squareGap;
        }
    }

    // Returns array of all sprites attached to each room
    public Sprite[] getRoomSprites(){
        Sprite[] roomSprites = new Sprite[25];
        int roomCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                roomSprites[roomCount] = board[i][j].roomSprite;
                roomCount++;
            }
        }

        return roomSprites;
    }

    // Pathfinding heuristic, manhattan distance
    public int heuristic(int x1, int y1, int x2, int y2){
        return Math.abs((x1 - x2) + (y1 - y2));
    }

    // Get each current room neighbour
    public ArrayList<Room> getNeighbours(int x, int y){

        ArrayList<Room> neighbours = new ArrayList<>();

        // Up room
        neighbours.add(this.getRoom(x, y + 1));
        // Down room
        neighbours.add(this.getRoom(x, y - 1));
        // Right room
        neighbours.add(this.getRoom(x + 1, y));
        // Left room
        neighbours.add(this.getRoom(x - 1, y));

        // Remove null neighbours
        for (int i = 0; i < neighbours.size(); i++) {
            if(neighbours.get(i) == null){
                neighbours.remove(i);
            }
        }

        return neighbours;
    }
}
