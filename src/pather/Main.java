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
    final int WIDTH = 800, HEIGHT = WIDTH;
    private Pane root = new Pane();
    Scene scene = new Scene(root);

    Maze maze = new Maze(7,7);
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

    // Draggable
    Draggable draggable = new Draggable();

    // METHODS

    // Capture input
    void captureKeys(){

        scene.setOnKeyPressed(e -> {

            // Save what movement key last pressed
            switch (e.getCode()) {
                // Up
                case W:
                    System.out.println("W pressed");
                    movementKey = MovementKeys.UP;

                    // player.move(movementKey);
                    // enemy.move();

                    break;

                // Left
                case A:
                    System.out.println("A pressed");
                    movementKey = MovementKeys.LEFT;

                    // player.move(movementKey);
                    // enemy.move();

                    break;

                // Down
                case S:
                    System.out.println("S pressed");
                    movementKey = MovementKeys.DOWN;

                    // player.move(movementKey);
                    // enemy.move();

                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;

                    // player.move(movementKey);
                    // enemy.move();

                    break;

                // Rotate
                case R:
                    System.out.println("R pressed");
                    movementKey = MovementKeys.ROTATE;

                    break;
            } 

            update();

        });
    }

    private void update(){
        player.move(movementKey);
        enemy.move();

        if(player.hasWon()){
            scene.setFill(Paint.valueOf("75e353ff"));
        }

        if(player.isDead){
            scene.setFill(Paint.valueOf("ff8484ff"));
        }

    }

    private void initialize(){
        // Set window size
        root.setPrefSize(HEIGHT, WIDTH);

        // Add game object sprites
        root.getChildren().addAll(maze.getRoomSprites());
        root.getChildren().add(player.model);
        // root.getChildren().add(blockSprite);
        root.getChildren().add(enemy.model);

        // draggable.makeDraggable(blockSprite, maze, block);

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now){
                // Cap frames
                if (now - lastUpdate >= 64_000_000) {
                    lastUpdate = now;
                }
            }
        };

        timer.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        initialize();
        captureKeys();
    
        scene.setFill(Paint.valueOf("191919ff"));
        primaryStage.setTitle("Pather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
 