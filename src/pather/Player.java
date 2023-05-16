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
    public HashMap<MovementKeys, RoomSides> directionValues = new HashMap<>();
    public RoomSides seatPosition;

    public Room currentRoom;
    public Room nextRoom;

    // Player model
    public Circle playerModel = new Circle(81, 81, 28);

    // CONSTRUCTORS
    public Player(Maze maze){
        super(maze);

        // Put default currentRoom into current currentRoom object;
        currentRoom = maze.getRoom(0, 0);

        // Place player in middle of current currentRoom
        currentRoom.seats.put(RoomSides.MIDDLE, this);

        pairDirectionValues();

        playerModel.setFill(Color.web("5599ffff"));
    }

    // METHODS

    // check if there is a block on a given seat
    public boolean isBlock(Room currentRoom, MovementKeys movementKey){
        if(Objects.isNull(currentRoom.getSeat(directionValues.get(movementKey)))){
            System.out.println("Not a block in that direction");

            return false;
        }
        System.out.println("There is a block leading in the direction LOLLLL");

        return true;
    }

    public void move(MovementKeys movementKey){


        if(isBlock(currentRoom, movementKey)){
            // THE X AND THE Y WERE REVERSED THE WHOLE TIME WTF I DONT EVEN KNOW WHAT TO THINK

            switch (movementKey) {
                case LEFT:
                    nextRoom = maze.getRoom(currentRoom.x, currentRoom.y - 1);
                    playerModel.setCenterX(playerModel.getCenterX()-110);

                    break;
            
                case RIGHT:
                    nextRoom = maze.getRoom(currentRoom.x, currentRoom.y + 1);
                    playerModel.setCenterX(playerModel.getCenterX()+110);

                    break;
            
                case DOWN:
                    nextRoom = maze.getRoom(currentRoom.x + 1, currentRoom.y);
                    playerModel.setCenterY(playerModel.getCenterY()+110);

                    break;
            
                case UP:
                    nextRoom = maze.getRoom(currentRoom.x - 1, currentRoom.y);
                    playerModel.setCenterY(playerModel.getCenterY()-110);

                    break;
            
                default:
                    break;
            }

            changeRoomPosition(currentRoom, nextRoom, RoomSides.MIDDLE);
            currentRoom = nextRoom;

        }


    }

    void pairDirectionValues(){
        directionValues.put(MovementKeys.UP, RoomSides.TOP);
        directionValues.put(MovementKeys.LEFT, RoomSides.LEFT);
        directionValues.put(MovementKeys.DOWN, RoomSides.BOTTOM);
        directionValues.put(MovementKeys.RIGHT, RoomSides.RIGHT);
    }
}
