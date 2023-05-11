package pather;

import java.util.HashMap; // import the HashMap class


/** 
 * Holds information about given coordinate in maze
 */

public class Room {
    // Fields
    HashMap<RoomSides, GameObject> seats = new HashMap<RoomSides, GameObject>();

    Sprite roomSprite;

    // Constructors

    public Room(){
        // Fill sides
        seats.put(RoomSides.MIDDLE, null);
        seats.put(RoomSides.TOP, null);
        seats.put(RoomSides.BOTTOM, null);
        seats.put(RoomSides.LEFT, null);
        seats.put(RoomSides.RIGHT, null);
    }

    // Methods
    
    // Change what value seat holds
    public void setSeat(RoomSides roomSides, GameObject gameObject){
        seats.put(roomSides, gameObject);
    }

    // Return value held in seat
    public GameObject getSeat(RoomSides roomSides){
        return seats.get(roomSides);
    }
}
