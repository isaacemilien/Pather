package pather;

import javafx.scene.paint.Color;


/**
* Manages collection of cells making up the ingame maze
*/

public class Maze {
    // Fields

    public Room[][] board;
    public int width = 0;
    public int height = 0;


    // Constructors

    public Maze(int theWidth, int theHeight) {
        width = theWidth;
        height = theHeight;
        board = new Room[width][height];

        populateRooms();
        populateRoomSprites(51, 51, 60, 60, 50, "e3dbdbff");
    }

    // Methods
    
    // Add a room to the board at given col and row (top left is 0,0)
    // Do nothing if coordinates are off the grid
    public void setRoom(int col, int row, Room r) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            board[col][row] = r;
        } 
    }

    // Return the room object (or null) for a given col and row (top left is 0,0)
    // Return null if coordinates are off grid
    public Room getRoom(int col, int row) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            return board[col][row];
        } else {
            return null;
        }
    }

    void populateRooms(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Room();
            }
        }
    }

    public void populateRoomSprites(double xOrigin, double yOrigin, double height, double width, double squareGap, String color){


        double x = xOrigin; 
        double y = yOrigin;


        int arrayCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                

                board[i][j].roomSprite = new Sprite(x, y, height, width, Color.web(color));
                x += width + squareGap;

                System.out.println(j);
                arrayCount++;
            }
            x = xOrigin;
            y += height + squareGap;
        }
    }

    public Sprite[] getRoomSprites(){
        Sprite[] roomSprites = new Sprite[25];
        int roomCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                roomSprites[roomCount] = board[i][j].roomSprite;
                roomCount++;
            }
        }

        return roomSprites;
    }

}
