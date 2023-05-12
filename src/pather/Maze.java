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





    // Path stuff
    int[] startPos = {0,0};
    int[] goalPos = {5,0};

    // check adjacent coordinates
    // top
    int[] topCoord = {startPos[0], startPos[1] + 1};

    // bottom
    int[] bottomCoord = {startPos[0], startPos[1] - 1};

    // left
    int[] leftCoord = {startPos[0] - 1, startPos[1]};

    // right
    int[] rightCoord = {startPos[0] + 1, startPos[1]};

    NodeHahaha start = new NodeHahaha(0, 0);
    NodeHahaha end = new NodeHahaha(5, 0);

    NodeHahaha top = new NodeHahaha(start.x, start.y + 1);
    NodeHahaha bottom = new NodeHahaha(start.x, start.y - 1);
    NodeHahaha left = new NodeHahaha(start.x - 1, start.y );
    NodeHahaha right = new NodeHahaha(start.x + 1, start.y);

    // distance from the start
    // count how many x to start



    // [5, 1] ~ [1, 2]


    // x
    // how many to reach the others x

    public int distance(int[] start, int[] end){
        // x
        int y;
        int x;

        if(start[0] >= end[0]){
            x = start[0] - end[0];
        }else{
            x = end[0] - start[0];
        }
        if(start[1] >= end[1]){
            y = start[1] - end[1];
        }else{
            y = end[1] - start[1];

        }

        return x + y;
    }

    // Constructors

    public Maze(int theWidth, int theHeight) {
        width = theWidth;
        height = theHeight;
        board = new Room[width][height];

        populateRooms();
        populateRoomSprites(51, 51, 60, 60, 50, "e3dbdbff");




        right.gCost = distance(new int[] {start.x, start.y}, new int[] {right.x, right.y});
        right.hCost = distance(new int[] {end.x, end.y}, new int[] {right.x, right.y});
        bottom.gCost = distance(new int[] {start.x, start.y}, new int[] {bottom.x, bottom.y});
        bottom.hCost = distance(new int[] {end.x, end.y}, new int[] {bottom.x, bottom.y});


        System.out.println(distance(new int[]{right.x, right.y}, new int[]{end.x, end.y}));
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
