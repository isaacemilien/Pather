package pather;

import java.util.HashMap; // import the HashMap class


/** 
 * Holds information about given coordinate in maze
 */

public class Room {
    // Fields
    HashMap<String, String> seats = new HashMap<String, String>();

    // Constructors

    public Room(){
        // Fill seats
        seats.put("middle", null);
        seats.put("up", null);
        seats.put("down", null);
        seats.put("left", null);
        seats.put("right", null);
    }

    // Methods
    
    // Change what value seat holds
    public void setSeat(String seatPosition, String seatValue){
        seats.put(seatPosition, seatValue);
    }

    // Return value held in seat
    public String getSeat(String seatPosition){
        return seats.get(seatPosition);
    }
}
