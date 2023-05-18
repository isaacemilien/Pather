package pather;
import java.util.HashMap;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
/* 
 * Obejct placed in game space
 */

public class GameObject {

    // FIELDS
    Maze maze;

    public Circle model = new Circle(0, 0, 28);

    // CONSTRUCTORS
    public GameObject(Maze maze){
        this.maze = maze;
    }

    // METHODS
    
    // Change room
    public void changeRoom(Room oldRoom, Room newRoom){
        // remove self from old room center
        oldRoom.containedObject = null;

        // place self in new room center
        newRoom.containedObject = this;
    }

    // Set room sprite coordinates to enemy model coordinate
    public Circle setModelRoom(Room room, Circle model){
        // Get given room x, y pos, add 30 because that is the ofset to get to center of shape because enemy model position from center
        double roomX = room.roomSprite.getX() + 30;
        double roomY = room.roomSprite.getY() + 30;

        // Set enemy model to room x y
        model.setCenterX(roomX);
        model.setCenterY(roomY);

        return model;
    }
}
