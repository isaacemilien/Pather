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
            blockSprite.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            blockSprite.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
        });

        blockSprite.setOnMouseReleased(mouseEvent -> {            
            double x = mouseEvent.getSceneX(); 
            double y = mouseEvent.getSceneY(); 
            
            Room foundRoom = new Room();

            for (int i = 0; i < maze.board[0].length; i++) {
                for (int j = 0; j < maze.board[1].length; j++) {
                    Room firstRoom = maze.getRoom(j, i);
                    double roomX = firstRoom.roomSprite.getX() + 50;
                    double roomY = firstRoom.roomSprite.getY();
    
                   for (int k = 0; k < 2; k++) {
                        if(x >= roomX && y > roomY && x <= roomX + 50 && y <= roomY + 50){
                            found = true;
                            foundRoom = firstRoom;
                            break;
                        }
                        roomX = firstRoom.roomSprite.getX();
                        roomY = firstRoom.roomSprite.getY() + 50;
                   }
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

                blockSprite.setX(positionNextX);
                blockSprite.setY(positionNextY);

                System.out.println(blockSprite.getX() + " " +  blockSprite.getY());
            }else{
                System.out.println("NOT");
            }

        });

        // 100 more
    }
}