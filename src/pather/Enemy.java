package pather;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Enemy extends GameObject{
    // FIELDS

    
    // Move enemy on grid
    public Circle enemyModel = new Circle(81, 191, 28);
    Room currentRoom;

    // CONSTRUCTORS
    public Enemy(Maze maze){
        super(maze);


        // Declare creation
        System.out.println("Enemy created");

        // Change enemy model color to red
        enemyModel.setFill(Color.RED);

    }

    // METHODS

    // Set room sprite coordinates to enemy model coordinate
    public void setEnemySprite(Room room){
        // Get given room x, y pos, add 30 because that is the ofset to get to center of shape because enemy model position from center
        double roomX = room.roomSprite.getX() + 30;
        double roomY = room.roomSprite.getY() + 30;


        // Set enemy model to room x y
        enemyModel.setCenterX(roomX);
        enemyModel.setCenterY(roomY);
    }

    // Change enemy room
    public void changeRoomPosition(Room oldRoom, Room newRoom){
        // remove self from old room center
        oldRoom.seats.put(RoomSides.MIDDLE, null);

        // place self in new room center
        newRoom.seats.put(RoomSides.MIDDLE, this);

        // Save new room as this objects current room
        currentRoom = newRoom;
    }
    
    // Move enemy
    public void move(Room oldRoom, Room newRoom){
        changeRoomPosition(oldRoom, newRoom);
        setEnemySprite(newRoom);
    }
}
