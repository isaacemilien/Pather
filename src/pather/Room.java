package pather;

import java.util.HashMap; // import the HashMap class
import java.util.ArrayList; // import the ArrayList class


/** 
 * Holds information about given coordinate in maze
 */

public class Room {
    // FIELDS
    HashMap<RoomSides, GameObject> seats = new HashMap<RoomSides, GameObject>();

    Sprite roomSprite;

    // Path finding information
    int x, y;
    int gCost, hCost, fCost;
    boolean pathable;
    Room previousRoom;
    ArrayList<Room> neighbours = new ArrayList<Room>();

    // CONSTRUCTORS

    public Room(){
        // Fill sides
        seats.put(RoomSides.MIDDLE, null);
        seats.put(RoomSides.TOP, null);
        seats.put(RoomSides.BOTTOM, null);
        seats.put(RoomSides.LEFT, null);
        seats.put(RoomSides.RIGHT, null);
    }

    // METHODS
    
    // Change what value seat holds
    public void setSeat(RoomSides roomSides, GameObject gameObject){
        seats.put(roomSides, gameObject);
    }

    // Return value held in seat
    public GameObject getSeat(RoomSides roomSides){
        return seats.get(roomSides);
    }
}
