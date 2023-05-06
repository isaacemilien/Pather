package pather;


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

}
