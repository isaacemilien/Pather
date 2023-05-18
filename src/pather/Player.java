package pather;

import javafx.scene.paint.Color;

/* 
 * Game object player controls
 */

public class Player extends GameObject {

    // FIELDS
    public Room currentRoom;
    public Room nextRoom;

    public boolean isDead = false;

    // CONSTRUCTORS
    public Player(Maze maze){
        super(maze);

        // Put default currentRoom into current currentRoom object;
        currentRoom = maze.getRoom(3, 6);

        // Place player in middle of current currentRoom
        currentRoom.containedObject = this;

        model.setFill(Color.web("5599ffff"));

        setModelRoom(currentRoom, model);
    }

    // METHODS
    public void move(MovementKeys movementKey){
        // THE X AND THE Y WERE REVERSED THE WHOLE TIME WTF I DONT EVEN KNOW WHAT TO THINK

        switch (movementKey) {
            case LEFT:
                nextRoom = maze.getRoom(currentRoom.x, currentRoom.y - 1);

                break;
        
            case RIGHT:
                nextRoom = maze.getRoom(currentRoom.x, currentRoom.y + 1);

                break;
        
            case DOWN:
                nextRoom = maze.getRoom(currentRoom.x + 1, currentRoom.y);

                break;
        
            case UP:
                nextRoom = maze.getRoom(currentRoom.x - 1, currentRoom.y);

                break;
        
            default:
                break;
        }

        if(nextRoom != null && !nextRoom.notPathable){
            changeRoom(currentRoom, nextRoom);
            currentRoom = nextRoom;    
            setModelRoom(currentRoom, model);
        }
    }

    public boolean hasWon(){
        if(currentRoom.winning){
            return true;
        }
        return false;
    }
}
