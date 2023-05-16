package pather;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.ArrayList; 


public class Enemy extends GameObject{
    // FIELDS

    // Move enemy on grid
    public Circle enemyModel = new Circle(81, 191, 28);
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

        // Declare creation

        // Change enemy model color to red
        enemyModel.setFill(Color.RED);

        // Set player information
        this.player = player;

        // Set default room
        currentRoom = maze.getRoom(4, 4);
        setEnemySprite(currentRoom);

        // // Set enemy to default position
        // move(currentRoom, currentRoom);

        // pathfinding.findPath(currentRoom, player.room);

        // // Get path to player, get first room in list
        // nextRoom = pathfinding.path.get(pathfinding.path.size() - 2);

        // // Log room position
        // System.out.println("Next room positions \n X: " + nextRoom.x + " Y: " + nextRoom.y);

        // // Move from current room to next give room in path
        // move(currentRoom, nextRoom);

        // move();
        // move();

    }

    // METHODS

    // Set room sprite coordinates to enemy model coordinate
    public void setEnemySprite(Room room){
        // Get given room x, y pos, add 30 because that is the ofset to get to center of shape because enemy model position from center
        double roomX = room.roomSprite.getX() + 30;
        double roomY = room.roomSprite.getY() + 30;

        // Set enemy model to room x y
        enemyModel.setCenterX(roomX);
        enemyModel.setCenterY(roomY);
    }

    // Change enemy room
    public void changeRoomPosition(Room oldRoom, Room newRoom){
        // remove self from old room center
        oldRoom.seats.put(RoomSides.MIDDLE, null);

        // place self in new room center
        newRoom.seats.put(RoomSides.MIDDLE, this);

        // Save new room as this objects current room
        currentRoom = newRoom;
    }
    
    // Move enemy
    public void move(){

        // Get path to player, get first room in list

        ArrayList<Room> path = pathfinding.findPath(currentRoom, player.currentRoom);

        System.out.println("Player current position is [" + player.currentRoom.x + ", " + player.currentRoom.y + "]");

        nextRoom = path.get(path.size() - 1);

        setEnemySprite(nextRoom);
        System.out.println(path.size());
        // System.out.println(nextRoom.x + " " + nextRoom.y);

        // // Log room position
        // System.out.println("Next room positions \n X: " + nextRoom.x + " Y: " + nextRoom.y);

        changeRoomPosition(currentRoom, nextRoom);
        killPlayer();
    }

    // Checks adjacent rooms, player in there? kill it 
    public void killPlayer(){
        // Get current rooms adjacent rooms
        ArrayList<Room> adjacentRooms = maze.getNeighbours(currentRoom.x, currentRoom.y);

        // Iterate through each room
        for (int i = 0; i < adjacentRooms.size(); i++) {
            // Check if room contains player in the middle
            if(adjacentRooms.get(i).seats.get(RoomSides.MIDDLE) == player){
                System.out.println("Killing this yute");
            }
        }
    }
}
