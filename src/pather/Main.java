package pather;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

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

    Block block = new Block(maze, "This is my whole as fucking rahhhh shit");

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    boolean inputPressed = false;


    Player player = new Player(maze);

    // Block visualization

    Sprite blockSprite = new Sprite(111,66,50,30, Color.web("ccccccff"));





    // get surrounding coords
    




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

                    player.playerModel.setCenterY(player.playerModel.getCenterY()-110);

                    break;

                // Left
                case A:
                    System.out.println("A pressed");
                    movementKey = MovementKeys.LEFT;

                    player.playerModel.setCenterX(player.playerModel.getCenterX()-110);


                    break;

                // Down
                case S:
                    System.out.println("S pressed");
                    movementKey = MovementKeys.DOWN;

                    player.move(movementKey);
                    player.playerModel.setCenterY(player.playerModel.getCenterY()+110);


                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;

                    player.move(movementKey);
                    player.playerModel.setCenterX(player.playerModel.getCenterX()+110);

                    


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
        // System.out.println(maze.getRoomSprites()[0]);

        root.getChildren().add(player.playerModel);

        root.getChildren().add(blockSprite);
        



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

        block.place(new int[] {0,0}, new int[] {0,1}, BlockRotations.HORIZONTAL, RoomSides.RIGHT);

        System.out.println("ME LIKEY");
        // maze.getRoom(0, 1).setSeat(RoomSides.RIGHT, block);
        System.out.println(maze.getRoom(0, 1).getSeat(RoomSides.LEFT));








        scene.setFill(Paint.valueOf("191919ff"));
        primaryStage.setTitle("Pather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
 