package pather;

import java.util.HashMap;
import java.util.Objects;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/* 
 * Game object player controls
 */

public class Player extends GameObject {

    // FIELDS
    int[] position = new int[2];
    int[] nextPosition = new int[2];

    public HashMap<MovementKeys, RoomSides> directionValues = new HashMap<>();
    public RoomSides seatPosition;

    public Room room;
    public Room adjacentRoom;

    // Player model
    public Circle playerModel = new Circle(81, 81, 28);

    // CONSTRUCTORS
    public Player(Maze maze){
        super(maze);
        position[0] = 0;
        position[1] = 0;

        maze.getRoom(position[0], position[1]).seats.put(RoomSides.MIDDLE, this);

        pairDirectionValues();

        playerModel.setFill(Color.web("5599ffff"));
    }

    // METHODS

    // Check if next coordinates are on the grid
    public boolean nextCoordExist(int gridX, int gridY, int nextX, int nextY){
        if(nextX < 0  || nextX > gridX || nextY < 0 || nextY > gridY){
            System.out.println("Position is out of bounds");

            return false;
        }

        System.out.println("Position exists on grid");

        return true;
    }

    // Convert pressed direction to seat position
    public void converDirectionToSeat(MovementKeys movementKey){
        seatPosition = directionValues.get(movementKey);
    }

    // check if there is a block on a given seat
    public boolean isBlock(Room room){
        if(Objects.isNull(room.getSeat(RoomSides.RIGHT))){
            System.out.println("Not a block in that direction");

            return false;
        }
        System.out.println("There is a block leading in the direction LOLLLL");

        return true;
    }

    // leave current room 
    public void leaveRoom(Room currentRoom){
        // Change player coordinates
        position = nextPosition;

        // Set current room middle place to empty
        currentRoom.seats.put(RoomSides.MIDDLE, null);

        System.out.println("Left current room");
    }

    public void enterRoom(Room newRoom){
        newRoom.setSeat(RoomSides.MIDDLE, this);

        System.out.println("Entered new room");
    }

    public void move(MovementKeys movementKey){
        room = maze.getRoom(position[0], position[1]);

        seatPosition = directionValues.get(movementKey);
        nextPosition = getAdjacentCoord(position, seatPosition);

        if(nextCoordExist(maze.width, maze.height, nextPosition[0], nextPosition[1]) && isBlock(room)){
            leaveRoom(room);
            enterRoom(maze.getRoom(nextPosition[0], nextPosition[1]));
        }
    }

    void pairDirectionValues(){
        directionValues.put(MovementKeys.UP, RoomSides.TOP);
        directionValues.put(MovementKeys.LEFT, RoomSides.LEFT);
        directionValues.put(MovementKeys.DOWN, RoomSides.BOTTOM);
        directionValues.put(MovementKeys.RIGHT, RoomSides.RIGHT);
    }
}
