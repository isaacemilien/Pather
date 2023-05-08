package pather;

/**
 * 
 */

public class Block extends GameObject{

    // Please type the coordinates of the seed room: -- result below --
    int[] seedRoomCoords = new int[2];

    String id = "";

    public Room seedRoom;
    public Room adjacentRoom;

    public String rotation = "horizontal";
    RoomSides placementSide = RoomSides.RIGHT;

    int[] adjacentRoomCoords = new int[2];

    public Block(Maze maze, String id){ 
        super(maze);
        this.id = id;

        // seedRoomCoords[0] = 0;
        // seedRoomCoords[1] = 0;

        // adjacentRoomCoords[0] = 0;
        // adjacentRoomCoords[1] = 0;
    }

    public boolean canPlace(String rotation){
        if(rotation.equals("horizontal")){
            if(placementSide.equals(RoomSides.RIGHT)){
                if(seedRoomCoords[0] + 1 == adjacentRoomCoords[0]){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(seedRoomCoords[0] - 1 == adjacentRoomCoords[0]){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            if(placementSide.equals(RoomSides.TOP)){
                if(seedRoomCoords[1] + 1 == adjacentRoomCoords[0]){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(seedRoomCoords[1] - 1 == adjacentRoomCoords[0]){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    public void deliverObjects(){
        seedRoom.setSeat(placementSide, id);
        adjacentRoom.setSeat(placementSide, id);
    }



    // Place block based on given coordinate and position
    public void place(int[] seedCoord, BlockRotations blockRotation, RoomSides roomSide){
        seedRoom = maze.getRoom(seedCoord[0], seedCoord[1]);
        adjacentRoomCoords = getAdjacentCoord(seedCoord, roomSide);
        adjacentRoom = maze.getRoom(adjacentRoomCoords[0], adjacentRoomCoords[1]);

        if(canPlace(rotation)){
            deliverObjects();
        }
    }
}
