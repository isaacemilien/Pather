package pather;

import javafx.scene.paint.Color;
import java.util.ArrayList; 
import javafx.scene.paint.Color;


public class Pathfinding {
    // FIELDS
    // Store the current maze
    Maze maze;

    // // Start and end rooms
    // Room startRoom, endRoom;


    // CONSTRUCTORS
    public Pathfinding(Maze maze){
        this.maze = maze;
    }

    // METHODS
    public ArrayList<Room> findPath(Room startRoom, Room endRoom){


        // Reset all colors, debug colors
        for (int i = 0; i < maze.board.length; i++) {
            for (int j = 0; j < maze.board.length; j++) {
                Room room = maze.getRoom(i,j);

                if(!room.notPathable && !room.winning){
                    room.roomSprite.setFill(Color.web("e3dbdbff"));
                }
            }
        }
        
        // Node path leading to position
        ArrayList<Room> path = new ArrayList<Room>();

        // room queue, discard sets
        ArrayList<Room> openSet = new ArrayList<Room>();
        ArrayList<Room> closedSet = new ArrayList<Room>();
        
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
                // System.out.println("Reached end goal");

                // Populate path with rooms
                while(currentRoom.previousRoom != null){
                    // Colour path way to end position
                    // currentRoom.roomSprite.setFill(Color.BLUE);



                    currentRoom.roomSprite.setFill(Color.web("ffff88ff"));

                    path.add(currentRoom.previousRoom);
                    currentRoom = currentRoom.previousRoom;
                }


                resetNodeValues();

                // remove final element from path 
                path.remove(path.size() - 1);

                return path;
            }

            // Access each neighbour of current room
            for (int i = 0; i < currentRoom.neighbours.size(); i++) {

                // Save reference of neighbour
                Room neighbour = currentRoom.neighbours.get(i);

                // Check if non pathable neighbours appears
                if(neighbour.notPathable){
                    System.out.println("this neighbour is not pathable");
                    continue;
                }

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

        resetNodeValues();
        return null;
    }
    
    // Pathfinding heuristic, manhattan distance
    public int heuristic(int x1, int y1, int x2, int y2){
        return Math.abs((x1 - x2) + (y1 - y2));
    }

    public void resetNodeValues(){
        for (int i = 0; i < maze.board.length; i++) {
            for (int j = 0; j < maze.board.length; j++) {

                maze.board[i][j].gCost = 0;
                maze.board[i][j].hCost = 0;
                maze.board[i][j].fCost = 0;

                maze.board[i][j].previousRoom = null;
            }
        }
    }
}
