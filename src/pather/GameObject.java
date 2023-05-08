package pather;

public class GameObject {
    
    Maze maze;

    public GameObject(Maze maze){
        this.maze = maze;
    }


    // Transpose coordinates of current position to new position based on given coordinates
    public int[] getAdjacentCoord(int[] seedCoord, RoomSides direction){

        switch (direction) {
            case RIGHT:
                
                return new int[] {seedCoord[0] + 1, seedCoord[1]};
        
            case LEFT:
                
                return new int[] {seedCoord[0] + 1, seedCoord[1]};
                
        
            case TOP:
                
                return new int[] {seedCoord[0], seedCoord[1] + 1};
                
        
            case BOTTOM:
                
                return new int[] {seedCoord[0], seedCoord[1] - 1};
        
            default:
                return new int[] {777, 777};
        }
    }
}
