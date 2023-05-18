package pather;

import java.util.HashMap; // import the HashMap class
import java.util.ArrayList; // import the ArrayList class


/** 
 * Holds information about given coordinate in maze
 */

public class Room {
    // FIELDS

    GameObject containedObject = null;

    Sprite roomSprite;

    boolean winning = false;

    // Path finding information
    int x, y;
    int gCost, hCost, fCost;
    boolean notPathable;
    Room previousRoom;
    ArrayList<Room> neighbours = new ArrayList<Room>();

    // CONSTRUCTORS

    // METHODS
}
