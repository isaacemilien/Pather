package pather;

import java.util.Arrays;
import java.util.Collections;

/**
 *  Object representing path between two rooms
 */

public class Block extends GameObject{

    // FIELDS
    int[] seedRoomCoords = new int[2];

    public Room seedRoom;
    public Room adjacentRoom;

    RoomSides placementSide = RoomSides.RIGHT;

    int[] adjacentRoomCoords = new int[2];

    // CONSTRUCTORS
    public Block(Maze maze){ 
        super(maze);
    }

    // METHODS
    
    // Check whether two positions next each other
    public boolean isCoordinateAdjacent(int[] firstCoord, int[] secondCoord){
        
        Integer transpositionValue = 1;

        // Convert int coordinate array to Integer array
        Integer[] firstIntegers = Arrays.stream( firstCoord ).boxed().toArray( Integer[]::new );
        Integer[] secondIntegers = Arrays.stream( secondCoord ).boxed().toArray( Integer[]::new );

        // Cycle through coordinate values
        for (int i = 0; i < 2; i++) {
            
            if(firstIntegers[i] + transpositionValue == secondIntegers[i] && firstIntegers[1 - i] == secondCoord[1 - i]){
                return true;
            }

            if(i > 0 && transpositionValue == 1){
                transpositionValue = -1;
                i = -1;
            }
        }
        
        return false;
    }

    // Set object in two rooms position  
    public void deliverObjects(int[] seedCoord, int[] adjacentCoord, RoomSides roomSide){
        // First room
        maze.getRoom(seedCoord[0],seedCoord[1]).setSeat(roomSide, this);

        // Second room
        maze.getRoom(adjacentCoord[0],adjacentCoord[1]).setSeat(adjacentSides.get(roomSide), this);
    }

    // Add block between two rooms
    public void place(int[] seedCoord, int[] recipientCoord, BlockRotations blockRotation, RoomSides roomSide){

        // Check valid to place room
        if(isCoordinateAdjacent(seedCoord, recipientCoord)){
            deliverObjects(seedCoord, recipientCoord, roomSide);
        }
    }



}
