package pather;

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;

import java.util.ArrayList; // import the ArrayList class
import javafx.scene.paint.Color;

/**
* Manages collection of cells making up the ingame maze
*/

public class Maze {
    
    // FIELDS
    public Room[][] board;
    public int width = 0;
    public int height = 0;

    public int[][] unpathableRooms = {{0,6}, {0,5}, {0,4}, {0,3}, {2,6}, {2,5}, {2,3}, {2,2}, {3,2}, {4,3}, {4,4}, {4,5}, {6,4}, {6,5}, {1,0}, {6,1}, {5,1} };

    // CONSTRUCTORS
    public Maze(int theWidth, int theHeight) {
        width = theWidth;
        height = theHeight;
        board = new Room[width][height];

        // Add rooms to board
        populateRooms();

        // Add neighbours to each room, done seperatly from room populate to ensure accuracy
        populateNeighbours();

        populateRoomSprites(40, 40, 60, 60, 50, "e3dbdbff");
        populateUnpathableRooms(unpathableRooms, "4a4747ff");

        setWinningRoom(getRoom(5, 0), "75e353ff");
    }

    // METHODS
    
    // Add a room to the board at given col and row (top left is 0,0)
    public void setRoom(int col, int row, Room r) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            board[col][row] = r;
        } 
    }

    // Return the room object (or null) for a given col and row (top left is 0,0)
    public Room getRoom(int col, int row) {
        if (col >= 0 && col < width && row >= 0 && row < height) {
            return board[col][row];
        } else {
            return null;
        }
    }

    // Fills empty maze grid with nodes
    void populateRooms(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Room();

                // Save room position to room
                getRoom(i, j).x = i;
                getRoom(i, j).y = j;
            }
        }
    }

    // Save each rooms neighbour
    public void populateNeighbours(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j].neighbours = getNeighbours(i, j);
            }
        }
    }

    // Add square sprite image to each room location
    public void populateRoomSprites(double xOrigin, double yOrigin, double height, double width, double squareGap, String color){
        double x = xOrigin; 
        double y = yOrigin;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                Room room = getRoom(i, j);
                
                room.roomSprite = new Sprite(x, y, height, width, Color.web(color));
                x += width + squareGap;

                room.roomSprite.setArcWidth(20.0); 
                room.roomSprite.setArcHeight(20.0);  
            }
            x = xOrigin;
            y += height + squareGap;
        }
    }

    // Returns array of all sprites attached to each room
    public Sprite[] getRoomSprites(){
        Sprite[] roomSprites = new Sprite[width * height];
        int roomCount = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                roomSprites[roomCount] = board[i][j].roomSprite;
                roomCount++;
            }
        }

        return roomSprites;
    }

    // Set certain rooms as unpathable
    public void populateUnpathableRooms(int[][] unpathableRooms, String unpathableRoomColor){
        // Set unpathable rooms
        for (int i = 0; i < unpathableRooms.length; i++) {
            Room room = getRoom(unpathableRooms[i][0], unpathableRooms[i][1]);
            room.notPathable = true;
            room.roomSprite.setFill(Color.web(unpathableRoomColor));
        }
    }

    public void setWinningRoom(Room room, String color){
        room.roomSprite.setFill(Color.web(color));
        room.winning = true;
    }

    // Get each current room neighbour
    public ArrayList<Room> getNeighbours(int x, int y){

        ArrayList<Room> neighbours = new ArrayList<>();
        Room[] rooms = new Room[4];

        // Up room
        rooms[0] = getRoom(x, y + 1);
        // Down room
        rooms[1] = getRoom(x, y - 1);
        // Right room
        rooms[2] = getRoom(x + 1, y);
        // Left room
        rooms[3] = getRoom(x - 1, y);

        // Remove null neighbours
        for (int i = 0; i < rooms.length; i++) {
            Room room = rooms[i];

            if(room != null){
                neighbours.add(room);
            }
        }

        return neighbours;
    }
}
