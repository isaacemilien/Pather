package pather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.ArrayList; 


public class Main extends Application {

    //FIELDS 
    // Window parameters
    final int WIDTH = 600, HEIGHT = WIDTH;
    private Pane root = new Pane();
    Scene scene = new Scene(root);

    Maze maze = new Maze(5,5);
    Room room = new Room();

    Block block = new Block(maze);

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    boolean inputPressed = false;

    Player player = new Player(maze);

    // Block visualization
    Sprite blockSprite = new Sprite(111,66,50,30, Color.web("ccccccff"));

    // Enemy
    Enemy enemy = new Enemy(maze, player);

    // Pathfinding class
    Pathfinding pathfinding = new Pathfinding(maze);


    // METHODS

    // Capture input
    void processInput(){

        scene.setOnKeyPressed(e -> {
            // Save what movement key last pressed
            switch (e.getCode()) {
                // Up
                case W:
                    System.out.println("W pressed");
                    movementKey = MovementKeys.UP;

                    player.move(movementKey);
                    enemy.move();


                    System.out.println("Player current position is [" + player.currentRoom.x + ", " + player.currentRoom.y + "]");


                    break;

                // Left
                case A:
                    System.out.println("A pressed");
                    movementKey = MovementKeys.LEFT;

                    player.move(movementKey);
                    enemy.move();
                    System.out.println("Player current position is [" + player.currentRoom.x + ", " + player.currentRoom.y + "]");



                    break;

                // Down
                case S:
                    System.out.println("S pressed");
                    movementKey = MovementKeys.DOWN;

                    player.move(movementKey);
                    enemy.move();
                    System.out.println("Player current position is [" + player.currentRoom.x + ", " + player.currentRoom.y + "]");




                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;


                    // player.currentRoom = maze.getRoom(0, 1);

                    player.move(movementKey);
                    // player.playerModel.setCenterX(player.playerModel.getCenterX()+110);

                    // Show player current position
                    System.out.println("Player current position is [" + player.currentRoom.x + ", " + player.currentRoom.y + "]");

                    enemy.move();

            
                    break;

                // Rotate
                case R:
                    System.out.println("R pressed");
                    movementKey = MovementKeys.ROTATE;

                
                    break;
            } 
        });
    }

    private void update(){
    }

    private void initialize(){
        // Set window size
        root.setPrefSize(HEIGHT, WIDTH);

        root.getChildren().addAll(maze.getRoomSprites());

        root.getChildren().add(player.playerModel);

        root.getChildren().add(blockSprite);

        // Add enemy to root
        root.getChildren().add(enemy.enemyModel);

        

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now){
                // Cap frames
                if (now - lastUpdate >= 64_000_000) {
                    processInput();
                    update();

                    lastUpdate = now;
                }
            }
        };

        timer.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize();
    
        block.place(maze.getRoom(0, 0), BlockRotations.HORIZONTAL, RoomSides.BOTTOM);

        System.out.println(maze.getRoom(1, 0).getSeat(RoomSides.TOP));
        maze.getRoom(1, 0).roomSprite.setFill(Color.RED);

        scene.setFill(Paint.valueOf("191919ff"));
        primaryStage.setTitle("Pather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
 