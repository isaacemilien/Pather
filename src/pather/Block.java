package pather;

/**
 * 
 */

public class Block {

    // Please type the coordinates of the seed room: -- result below --
    int[] seedRoomCoords = new int[2];

    String id = "";

    public Room seedRoom;
    public Room adjacentRoom;

    public String rotation = "horizontal";
    String placementSide = "right";

    int[] adjacentRoomCoords = new int[2];

    public Block(String id){ 
        this.id = id;

        seedRoomCoords[0] = 0;
        seedRoomCoords[1] = 0;

        adjacentRoomCoords[0] = 0;
        adjacentRoomCoords[1] = 0;
    }

    public boolean canPlace(String rotation){
        if(rotation.equals("horizontal")){
            if(placementSide.equals("right")){
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
            if(placementSide.equals("up")){
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

    public int[] getAdjacentCoord(int[] seedCoord, String direction){
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

    public void deliverObjects(){
        seedRoom.setSeat(placementSide, id);
        adjacentRoom.setSeat(placementSide, id);
    }
}
