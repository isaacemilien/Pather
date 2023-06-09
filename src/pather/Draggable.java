package pather;

import javafx.scene.Node;

public class Draggable {

    private double mouseAnchorX;
    private double mouseAnchorY;
    Maze maze;
    Block currentBlock;

    public void makeDraggable(Node node, Maze maze, Block currentBlock){

        this.maze = maze;
        this.currentBlock = currentBlock;

        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);

            // System.out.println(mouseEvent.getSceneX() + " " + mouseEvent.getSceneY());
        });

        node.setOnMouseReleased(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            // System.out.println("released");
            
            
            // Room firstRoom = maze.getRoom(0, 0);
            // double roomX = firstRoom.roomSprite.getX() + 50;
            // double roomY = firstRoom.roomSprite.getY();
            
            
            double x = mouseEvent.getSceneX(); 
            double y = mouseEvent.getSceneY(); 
            
            // System.out.println(x + " " + y);



            boolean found = false;
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

                // System.out.println(foundRoom.seats.get(RoomSides.RIGHT));
            }else{
                System.out.println("NOT");
            }

        });

        // 100 more
    }
}