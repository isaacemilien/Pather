package pather;
import java.util.HashMap;

/* 
 * Obejct placed in game space
 */

public class GameObject {

    // FIELDS
    Maze maze;

    public HashMap<RoomSides, RoomSides> adjacentSides = new HashMap<>();

    // CONSTRUCTORS
    public GameObject(Maze maze){
        this.maze = maze;

        pairAdjacentSideValues();
    }

    // METHODS
    
    void pairAdjacentSideValues(){
        adjacentSides.put(RoomSides.BOTTOM, RoomSides.TOP);
        adjacentSides.put(RoomSides.RIGHT, RoomSides.LEFT);
        adjacentSides.put(RoomSides.TOP, RoomSides.BOTTOM);
        adjacentSides.put(RoomSides.LEFT, RoomSides.RIGHT);
    }


    // Transpose coordinates of current position to new position based on given coordinates
    public int[] getAdjacentCoord(int[] seedCoord, RoomSides direction){

        switch (direction) {
            case RIGHT:
                
                return new int[] {seedCoord[0] + 1, seedCoord[1]};
        
            case LEFT:
                
                return new int[] {seedCoord[0] + 1, seedCoord[1]};
        
            case TOP:
                
                return new int[] {seedCoord[0], seedCoord[1] + 1};
        
            case BOTTOM:
                
                return new int[] {seedCoord[0], seedCoord[1] - 1};
        
            default:
                return new int[] {777, 777};
        }
    }
}
