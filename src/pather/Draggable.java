package pather;

import javafx.scene.Node;

public class Draggable {

    private double mouseAnchorX;
    private double mouseAnchorY;
    Maze maze;
    Block currentBlock;
    Sprite blockSprite;

    boolean found = false;


    public void makeDraggable(Sprite blockSprite, Maze maze, Block currentBlock){

        this.maze = maze;
        this.currentBlock = currentBlock;
        this.blockSprite = blockSprite;

        blockSprite.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        blockSprite.setOnMouseDragged(mouseEvent -> {

            if(!found){
                blockSprite.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
                blockSprite.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);

            }


        });

        blockSprite.setOnMouseReleased(mouseEvent -> {
            // blockSprite.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            // blockSprite.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            // System.out.println("released");
            
            
            // Room firstRoom = maze.getRoom(0, 0);
            // double roomX = firstRoom.roomSprite.getX() + 50;
            // double roomY = firstRoom.roomSprite.getY();
            
            System.out.println(mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
            
            double x = mouseEvent.getSceneX(); 
            double y = mouseEvent.getSceneY(); 
            
            // System.out.println(x + " " + y);



            Room foundRoom = new Room();

            for (int i = 0; i < maze.board[0].length; i++) {
                for (int j = 0; j < maze.board[1].length; j++) {
                    Room firstRoom = maze.getRoom(j, i);
                    double roomX = firstRoom.roomSprite.getX() + 50;
                    double roomY = firstRoom.roomSprite.getY();
    
                   // System.out.println(roomX + " " + roomY);
    
    
                   for (int k = 0; k < 2; k++) {
                        if(x >= roomX && y > roomY && x <= roomX + 50 && y <= roomY + 50){
                            found = true;
                            foundRoom = firstRoom;
                            break;
                        }
                        roomX = firstRoom.roomSprite.getX();
                        roomY = firstRoom.roomSprite.getY() + 50;
                   }
                    // roomX = firstRoom.roomSprite.getX();
                    // roomY = firstRoom.roomSprite.getY() + 50;
    
                    // if(x >= roomX && y > roomY && x <= roomX + 50 && y <= roomY + 50){
                    //     found = true;
                    //     break;
                    // }else{
                    // }
                }
            }

            if(found){
                System.out.println("Inbetween");
                System.out.println(foundRoom.x + " " + foundRoom.y);
                currentBlock.place(foundRoom);


                // Get the room

                // get position from the room
                double foundRoomX = foundRoom.roomSprite.getX();
                double foundRoomY = foundRoom.roomSprite.getY();
                

                double positionNextX = foundRoomX +60;
                double positionNextY = foundRoomY +16;

                // blockSprite.setCenterX = positionNextX;

                blockSprite.setX(positionNextX);
                blockSprite.setY(positionNextY);

                System.out.println(blockSprite.getX() + " " +  blockSprite.getY());
                // System.out.println(foundRoom.seats.get(RoomSides.RIGHT));
            }else{
                System.out.println("NOT");
            }

        });

        // 100 more
    }
}