package pather;

import javafx.scene.paint.Color;
import java.util.*;  



/**
* Manages collection of cells making up the ingame maze
*/

public class Maze {
    // Fields

    public Room[][] board;
    public int width = 0;
    public int height = 0;



    NodeHahaha[][] nodeGrid;

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

    // NodeHahaha start = new NodeHahaha(0, 0);
    // NodeHahaha end = new NodeHahaha(5, 0);

    // NodeHahaha top = new NodeHahaha(start.x, start.y + 1);
    // NodeHahaha bottom = new NodeHahaha(start.x, start.y - 1);
    // NodeHahaha left = new NodeHahaha(start.x - 1, start.y );
    // NodeHahaha right = new NodeHahaha(start.x + 1, start.y);


    List<NodeHahaha> openSet=new ArrayList<NodeHahaha>();  
    List<NodeHahaha> closedSet=new ArrayList<NodeHahaha>();  


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
        nodeGrid = new NodeHahaha[width][height];

        populateRooms();
        populateRoomSprites(51, 51, 60, 60, 50, "e3dbdbff");




        // right.gCost = distance(new int[] {start.x, start.y}, new int[] {right.x, right.y});
        // right.hCost = distance(new int[] {end.x, end.y}, new int[] {right.x, right.y});
        // bottom.gCost = distance(new int[] {start.x, start.y}, new int[] {bottom.x, bottom.y});
        // bottom.hCost = distance(new int[] {end.x, end.y}, new int[] {bottom.x, bottom.y});

        

        // System.out.println(distance(new int[]{right.x, right.y}, new int[]{end.x, end.y}));

        NodeHahaha start = nodeGrid[0][0];
        NodeHahaha end = nodeGrid[4][0];

        openSet.add(start);

        NodeHahaha currentNode;
        currentNode = start;


        System.out.println(end.x);
        // NodeHahaha currentNode = start;

        // while (true) {
        //     // set current node
        //     // NodeHahaha current =

        //     // get node from open set
        //     openSet.get(0);

        //     // Find node with lowest f cost in open set
        //     // go through each node in open set
        //     // get f cost
        //     // check if f cost lower than last f cost
        //     // save as current node;


        //     int tempF = 1000;
            
        //     for (int i = 0; i < openSet.size(); i++) {

        //         if(openSet.get(i).fCost < tempF){
        //             currentNode = openSet.get(i);
        //             tempF = currentNode.fCost;
        //         }
                
        //     }

        //     // remove current from open list
        //     openSet.remove(currentNode);

        //     closedSet.add(currentNode);

        //     if(currentNode.x == end.x && currentNode.y == end.y){
        //         System.out.println("end found");
        //         return;
        //     }

        //     // get each neighbor of the current node


        //     return;
        // }

        // getAdjacentNodes();
    }

    public void getAdjacentNodes(NodeHahaha stemNode){

        // up down left right

        NodeHahaha[] temp = new NodeHahaha[4];

        if(stemNode.y + 1 < 5){
            temp[0] = new NodeHahaha(stemNode.x, stemNode.y + 1);
        }
        if(stemNode.y + 1 > -1){
            temp[1] = new NodeHahaha(stemNode.x, stemNode.y - 1);
        }
        if(stemNode.y + 1 < 5){
            temp[0] = new NodeHahaha(stemNode.x, stemNode.y + 1);
        }
        if(stemNode.y + 1 > -1){
            temp[1] = new NodeHahaha(stemNode.x, stemNode.y - 1);
        }
        temp[1] = new NodeHahaha(stemNode.x, stemNode.y - 1);
        temp[2] = new NodeHahaha(stemNode.x - 1, stemNode.y);
        temp[3] = new NodeHahaha(stemNode.x + 1, stemNode.y);
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

                nodeGrid[i][j] = new NodeHahaha(i, j);



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
