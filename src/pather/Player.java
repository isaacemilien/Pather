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

    public Room currentRoom;
    public Room nextRoom;

    // Player model
    public Circle playerModel = new Circle(81, 81, 28);

    // CONSTRUCTORS
    public Player(Maze maze){
        super(maze);
        position[0] = 0;
        position[1] = 0;

        // Put default currentRoom into current currentRoom object;
        currentRoom = maze.getRoom(0, 0);

        System.out.println(currentRoom.y);


        

        // Place player in middle of current currentRoom
        currentRoom.seats.put(RoomSides.MIDDLE, this);



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
    public boolean isBlock(Room currentRoom){
        if(Objects.isNull(currentRoom.getSeat(RoomSides.RIGHT))){
            System.out.println("Not a block in that direction");

            return false;
        }
        System.out.println("There is a block leading in the direction LOLLLL");

        return true;
    }

    // leave current currentRoom 
    public void leaveRoom(Room currentRoom){
        // Change player coordinates
        position = nextPosition;

        // Set current currentRoom middle place to empty
        currentRoom.seats.put(RoomSides.MIDDLE, null);
    }

    public void enterRoom(Room newRoom){
        newRoom.setSeat(RoomSides.MIDDLE, this);

        currentRoom = newRoom;
    }

    public void move(MovementKeys movementKey){
        // currentRoom = maze.getRoom(position[0], position[1]);

        // seatPosition = directionValues.get(movementKey);
        // nextPosition = getAdjacentCoord(position, seatPosition);

        // nextRoom = maze.getRoom(nextPosition[0], nextPosition[1]);

        // if(movementKey == MovementKeys.DOWN){
        //     nextRoom = maze.getRoom(currentRoom.x, currentRoom.y + 1);

        //     currentRoom.seats.put(RoomSides.MIDDLE, null);
        //     nextRoom.seats.put(RoomSides.MIDDLE, this);

        //     currentRoom = nextRoom;
        // }

        // if(nextCoordExist(maze.width, maze.height, nextPosition[0], nextPosition[1]) /*&& isBlock(currentRoom)*/){
            


        // }
        




        // FUCK YOU IM REDOING THE ENTIRE MOVEMENT BECAUSE SUCK OUT
        
        // down key pressed
        // move the player into the currentRoom below
        // if(movementKey == MovementKeys.DOWN){            
        //     nextRoom = maze.getRoom(currentRoom.x, currentRoom.y + 1);
            
        //     changeRoomPosition(currentRoom, nextRoom);
        // }

        // THE X AND THE Y WERE REVERSED THE WHOLE TIME WTF I DONT EVEN KNOW WHAT TO THINK

        switch (movementKey) {
            case LEFT:
                nextRoom = maze.getRoom(currentRoom.x, currentRoom.y - 1);
                
                changeRoomPosition(currentRoom, nextRoom);
                break;
        
            case RIGHT:
                nextRoom = maze.getRoom(currentRoom.x, currentRoom.y + 1);
                
                changeRoomPosition(currentRoom, nextRoom);
                break;
        
            case DOWN:
                
                nextRoom = maze.getRoom(currentRoom.x + 1, currentRoom.y);
                // currentRoom = get
                // System.out.println("Hi ^_^" + (currentRoom.x + 1)+ " "+ currentRoom.y);
                System.out.println(currentRoom.x);
                System.out.println(currentRoom.y);
                
                changeRoomPosition(currentRoom, nextRoom);
                

                break;
        
            case UP:
                nextRoom = maze.getRoom(currentRoom.x - 1, currentRoom.y);
                    
                changeRoomPosition(currentRoom, nextRoom);                
                break;
        
            default:
                break;
        }
    }

    // Change player room
    public void changeRoomPosition(Room oldRoom, Room newRoom){
        // remove self from old room center
        oldRoom.seats.put(RoomSides.MIDDLE, null);

        // place self in new room center
        newRoom.seats.put(RoomSides.MIDDLE, this);

        // Save new room as this objects current room
        currentRoom = newRoom;
    }

    void pairDirectionValues(){
        directionValues.put(MovementKeys.UP, RoomSides.TOP);
        directionValues.put(MovementKeys.LEFT, RoomSides.LEFT);
        directionValues.put(MovementKeys.DOWN, RoomSides.BOTTOM);
        directionValues.put(MovementKeys.RIGHT, RoomSides.RIGHT);
    }
}
