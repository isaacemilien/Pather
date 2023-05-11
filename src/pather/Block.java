package pather;

import java.util.Arrays;
import java.util.Collections;



/**
 * 
 */

public class Block extends GameObject{




    // Please type the coordinates of the seed room: -- result below --
    int[] seedRoomCoords = new int[2];

    String id = "";

    public Room seedRoom;
    public Room adjacentRoom;

    // public BlockRotations rotation = ;
    RoomSides placementSide = RoomSides.RIGHT;

    int[] adjacentRoomCoords = new int[2];

    public Block(Maze maze, String id){ 
        super(maze);
        this.id = id;
    }

    // When can a block be placed
    // not a block on it already 
    // when there are two rooms next to each other
        // first room
        // second room
        // add one to x position of first room


    public boolean isCoordinateAdjacent(int[] firstCoord, int[] secondCoord){
        
        Integer transpositionValue = 1;

        // Convert int coordinate array to Integer array
        Integer[] firstIntegers = Arrays.stream( firstCoord ).boxed().toArray( Integer[]::new );
        Integer[] secondIntegers = Arrays.stream( secondCoord ).boxed().toArray( Integer[]::new );

        // Cycle through coordinate values
        for (int i = 0; i < 2; i++) {
            
            if(firstIntegers[i] + transpositionValue == secondIntegers[i] && firstIntegers[1 - i] == secondCoord[1 - i]){
                System.out.println("adjacent");
                return true;
            }

            if(i > 0 && transpositionValue == 1){
                transpositionValue = -1;
                i = -1;
            }
        }
        
        return false;
    }

    // when their right and left or top and bottom sides are free


    // public boolean canPlace(BlockRotations blockRotation){
    //     // Check which way block is oriented
    //     if(blockRotation.equals(BlockRotations.HORIZONTAL)){
    //         // Check which placement side block is going to be on
    //         if(placementSide.equals(RoomSides.RIGHT)){
    //             if(seedRoomCoords[0] + 1 == adjacentRoomCoords[0]){
    //                 return true;
    //             }else{
    //                 return false;
    //             }
    //         }else{
    //             if(seedRoomCoords[0] - 1 == adjacentRoomCoords[0]){
    //                 return true;
    //             }else{
    //                 return false;
    //             }
    //         }
    //     }else{
    //         if(placementSide.equals(RoomSides.TOP)){
    //             if(seedRoomCoords[1] + 1 == adjacentRoomCoords[0]){
    //                 return true;
    //             }else{
    //                 return false;
    //             }
    //         }else{
    //             if(seedRoomCoords[1] - 1 == adjacentRoomCoords[0]){
    //                 return true;
    //             }else{
    //                 return false;
    //             }
    //         }
    //     }
    // }

    public void deliverObjects(int[] seedCoord, int[] adjacentCoord, RoomSides roomSide){
        // seedRoom.setSeat(roomSide, this);
        // adjacentRoom.setSeat(adjacentSides.get(roomSide), this);

        maze.getRoom(seedCoord[0],seedCoord[1]).setSeat(roomSide, this);
        maze.getRoom(adjacentCoord[0],adjacentCoord[1]).setSeat(adjacentSides.get(roomSide), this);


        // System.
    }



    // Place block based on given coordinate and position
    public void place(int[] seedCoord, int[] recipientCoord, BlockRotations blockRotation, RoomSides roomSide){
        
        // seedRoom = maze.getRoom(seedCoord[0], seedCoord[1]);
        // adjacentRoomCoords = getAdjacentCoord(seedCoord, roomSide);
        // adjacentRoom = maze.getRoom(adjacentRoomCoords[0], adjacentRoomCoords[1]);

        if(isCoordinateAdjacent(seedCoord, recipientCoord)){
            deliverObjects(seedCoord, recipientCoord, roomSide);
        }
    }



}
