package pather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.HashMap;
import java.util.Random;

public class Main extends Application {

    // Fields

    // Window parameters
    final int WIDTH = 600, HEIGHT = WIDTH;
    private Pane root = new Pane();
    Scene scene = new Scene(root);


    Maze maze = new Maze(5,5);
    Room room = new Room();

    Block block = new Block("This is my whole as fucking rahhhh shit");

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    boolean inputPressed = false;


    Player player = new Player(maze);

    // Methods

    // Capture input
    void processInput(){

        scene.setOnKeyPressed(e -> {
            // Save what movement key last pressed
            switch (e.getCode()) {
                // Up
                case W:
                    System.out.println("W pressed");
                    movementKey = MovementKeys.UP;

                    inputPressed = true;

                    break;

                // Left
                case A:
                    System.out.println("A pressed");
                    movementKey = MovementKeys.LEFT;

                    inputPressed = true;

                    break;

                // Down
                case S:
                    System.out.println("S pressed");
                    movementKey = MovementKeys.DOWN;

                    inputPressed = true;

                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;

                    inputPressed = true;

                    player.move(movementKey);
                    
                    System.out.println(maze.getRoom(0, 0).getSeat("middle"));
                    System.out.println(maze.getRoom(1, 0).getSeat("middle"));


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

        // System.out.println(inputPressed);

        if(inputPressed){
        }
    }

    private void initialize(){
        // Set window size
        root.setPrefSize(HEIGHT, WIDTH);

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

        // maze.setRoom(1, 1, room);
        System.out.println(maze.getRoom(0, 0).getSeat("Middle"));

        maze.board[0][0].setSeat("Middle", "fuck");
        maze.board[1][0].setSeat("Middle", "im to the right lol");

        System.out.println(maze.getRoom(0, 0).getSeat("Middle"));

        block.seedRoom = maze.getRoom(block.seedRoomCoords[0], block.seedRoomCoords[1]);


        int[] lol = block.getAdjacentCoord(new int[] {block.seedRoomCoords[0], block.seedRoomCoords[1]}, block.placementSide);

        block.adjacentRoom = maze.getRoom(lol[0], lol[1]);
        System.out.println(block.adjacentRoom.getSeat("Middle"));

        block.deliverObjects();

        System.out.println(maze.getRoom(0, 0).getSeat("right"));
        System.out.println(maze.getRoom(1, 0).getSeat("right"));


        System.out.println(maze.getRoom(0, 0).getSeat("middle"));
        System.out.println(maze.getRoom(1, 0).getSeat("middle"));

        // System.out.println(block.getAdjacentCoord(block.seedRoomCoords, "right")[1]);

        scene.setFill(Paint.valueOf("BLACK"));
        primaryStage.setTitle("Pather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
 