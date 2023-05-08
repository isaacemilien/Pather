package pather;

import java.util.HashMap;

public class Player {
    int[] position = new int[2];
    int[] nextPosition = new int[2];


    public HashMap<MovementKeys, String> directionValues = new HashMap<>();
    public String seatPosition = "";


    public Room room;
    public Room adjacentRoom;

    public Maze maze;

    public Player(Maze maze){
        position[0] = 0;
        position[1] = 0;
        // nextPosition[0] = 3;
        // nextPosition[1] = 2;


        this.maze = maze;

        maze.getRoom(position[0], position[1]).seats.put("middle", "This is the fucking player starting off :D");

        pairDirectionValues();
    }



    // Check if next coordinates are on the grid
    public boolean nextCoordExist(int gridX, int gridY, int nextX, int nextY){
        System.out.println(gridX);
        System.out.println(gridY);
        System.out.println(nextX);
        System.out.println(nextY);


        if(nextX < 0  || nextX > gridX || nextY < 0 || nextY > gridY){

            System.out.println("Position is out of bounds");

            return false;
        }

        System.out.println("Position exists on grid");


        return true;
    }

    // Convert pressed direction to seat position
    public void converDirectionToSeat(MovementKeys movementKey){
        seatPosition = directionValues.get(movementKey);
    }

    // check if there is a block on a given seat
    public boolean isBlock(Room room){
        if(room.getSeat(seatPosition).equals(null)){

            System.out.println("Not a block in that direction");


            return false;
        }

        System.out.println("There is a block leading in the direction LOLLLL");


        return true;
    }

    // Transpose coordinates of current position to new position based on given coordinates
    public int[] getAdjacentCoord(int[] seedCoord, String direction){
        System.out.println(direction);

        switch (direction) {
            case "right":
                
                return new int[] {seedCoord[0] + 1, seedCoord[1]};
        
            case "left":
                
                return new int[] {seedCoord[0] + 1, seedCoord[1]};
                
        
            case "up":
                
                return new int[] {seedCoord[0], seedCoord[1] + 1};
                
        
            case "down":
                
                return new int[] {seedCoord[0], seedCoord[1] - 1};
        
            default:
                return new int[] {666, 666};
        }
    }

    // leave current room 
    public void leaveRoom(Room currentRoom){
        // Change player coordinates
        position = nextPosition;

        // Set current room middle place to empty
        currentRoom.seats.put("middle", null);

        System.out.println("Left current room");
    }

    public void enterRoom(Room newRoom){
        newRoom.setSeat("middle", "This is the fucking player");

        System.out.println("Entered new room");
    }

    public void move(MovementKeys movementKey){
        room = maze.getRoom(position[0], position[1]);

        seatPosition = directionValues.get(movementKey);
        nextPosition = getAdjacentCoord(position, seatPosition);

        if(nextCoordExist(maze.width, maze.height, nextPosition[0], nextPosition[1]) && isBlock(room)){
            leaveRoom(room);
            enterRoom(maze.getRoom(nextPosition[0], nextPosition[1]));
        }
    }


    void pairDirectionValues(){
        directionValues.put(MovementKeys.UP, "up");
        directionValues.put(MovementKeys.LEFT, "left");
        directionValues.put(MovementKeys.DOWN, "down");
        directionValues.put(MovementKeys.RIGHT, "right");
    }



    
}
