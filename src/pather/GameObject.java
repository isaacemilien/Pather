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

    // Change room
    public void changeRoomPosition(Room oldRoom, Room newRoom){
        // remove self from old room center
        oldRoom.seats.put(RoomSides.MIDDLE, null);

        // place self in new room center
        newRoom.seats.put(RoomSides.MIDDLE, this);
    }
}
