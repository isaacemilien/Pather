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

    Block block = new Block(maze, "This is my whole as fucking rahhhh shit");

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    boolean inputPressed = false;


    Player player = new Player(maze);




    // Sqaure
    Sprite square = new Sprite(0, 0, 60, 60, Color.web("e3dbdbff"));
    Sprite square2 = new Sprite(90, 0, 60, 60, Color.web("e3dbdbff"));
    Sprite square3 = new Sprite(180, 0, 60, 60, Color.web("e3dbdbff"));
    Sprite square4 = new Sprite(270, 0, 60, 60, Color.web("e3dbdbff"));
    Sprite square5 = new Sprite(360, 0, 60, 60, Color.web("e3dbdbff"));

    Sprite[] squares = new Sprite[25];
 
    public void lol(int xCount, int yCount, double xOrigin, double yOrigin, double height, double width, double squareGap ){


        double x = xOrigin; 
        double y = yOrigin;


        int arrayCount = 0;

        for (int i = 0; i < yCount; i++) {
            for (int j = 0; j < xCount; j++) {
                squares[arrayCount] = new Sprite(x, y, height, width, Color.web("e3dbdbff"));
                x += width + squareGap;

                System.out.println(j);
                arrayCount++;
            }
            x = xOrigin;
            y += height + squareGap;
        }
    }



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

                    break;

                // Left
                case A:
                    System.out.println("A pressed");
                    movementKey = MovementKeys.LEFT;

                    break;

                // Down
                case S:
                    System.out.println("S pressed");
                    movementKey = MovementKeys.DOWN;

                    player.move(movementKey);
                    
                    System.out.println(maze.getRoom(0, 0).getSeat(RoomSides.MIDDLE));
                    System.out.println(maze.getRoom(1, 0).getSeat(RoomSides.MIDDLE));

                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;

                    player.move(movementKey);
                    
                    System.out.println(maze.getRoom(0, 0).getSeat(RoomSides.MIDDLE));
                    System.out.println(maze.getRoom(1, 0).getSeat(RoomSides.MIDDLE));


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


        

        // Draw square
        lol(5, 5, 20, 20, 60, 60, 40);

        System.out.println("slkdjflskfj");

        root.getChildren().addAll(squares);

        


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
 