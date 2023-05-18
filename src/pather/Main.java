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

    // Last movement key pressed
    MovementKeys movementKey = MovementKeys.DOWN;

    Player player = new Player(maze);

    // Enemy
    Enemy enemy = new Enemy(maze, player);

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

                    break;

                // Right
                case D:
                    System.out.println("D pressed");
                    movementKey = MovementKeys.RIGHT;

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
            System.out.println("YOU WIN");
        }

        if(player.isDead){
            scene.setFill(Paint.valueOf("ff8484ff"));
            System.out.println("YOU DIED");
        }

    }

    private void initialize(){
        // Set window size
        root.setPrefSize(HEIGHT, WIDTH);

        // Add game object sprites
        root.getChildren().addAll(maze.getRoomSprites());
        root.getChildren().add(player.model);
        root.getChildren().add(enemy.model);
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
 