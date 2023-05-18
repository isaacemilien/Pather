package pather;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.ArrayList; 


public class Enemy extends GameObject{
    // FIELDS

    // Move enemy on grid
    Room currentRoom;

    // Next room that will be moved to
    Room nextRoom;
    
    // Get player object
    Player player;

    // Set up pathfinding
    Pathfinding pathfinding = new Pathfinding(maze);

    // CONSTRUCTORS
    public Enemy(Maze maze, Player player){
        super(maze);

        // Change enemy model color to red
        model.setFill(Color.web("ff5555ff"));

        // Set player information
        this.player = player;

        // Set default room
        currentRoom = maze.getRoom(0, 0);
        setModelRoom(currentRoom, model);
    }

    // METHODS

    // Move enemy
    public void move(){

        // Get path to player, get first room in list
        ArrayList<Room> path = pathfinding.findPath(currentRoom, player.currentRoom);

        if(path.size() - 1 >= 0){
            nextRoom = path.get(path.size() - 1);
        }

        setModelRoom(nextRoom, model);

        changeRoom(currentRoom, nextRoom);
        currentRoom = nextRoom;

        killPlayer();
    }

    // Checks adjacent rooms, player in there? kill it 
    public void killPlayer(){
        // Get current rooms adjacent rooms
        ArrayList<Room> adjacentRooms = maze.getNeighbours(currentRoom.x, currentRoom.y);

        // Iterate through each room
        for (int i = 0; i < adjacentRooms.size(); i++) {
            // Check if room contains player in the middle
            if(adjacentRooms.get(i).containedObject == player){
                player.isDead = true;
            }
        }
    }
}
