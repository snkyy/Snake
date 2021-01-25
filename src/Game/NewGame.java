package Game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static Game.Engine.*;
import static Game.Model.*;
import static Game.ReadScore.readScores;
import static Game.Restart.*;

public class NewGame
{
    public static Pane root = new Pane();
    public static void start(Stage stage)
    {
        restart();
        CreateApple();
        Canvas board = new Canvas(WIDTH * POS_SIZE, HEIGHT * POS_SIZE);
        GraphicsContext gc = board.getGraphicsContext2D();
        root = new Pane();
        root.getChildren().add(board);

        new AnimationTimer()
        {
            long last = 0;
            private int tmp = 0;
            @Override
            public void handle(long now)
            {
                if(now - last >= TICK)
                {
                    last = now;
                    engine(gc);
                }
                if(DEAD && tmp == 0)
                {
                    readScores();
                    Scores.add(String.valueOf(EATEN * 10));
                    Scores.sort((a, b) ->
                    {
                        if(a.length() > b.length())
                        {
                            return -1;
                        }
                        else if(a.length() == b.length())
                        {
                            return b.compareTo(a);
                        }
                        else
                        {
                            return 1;
                        }
                    });
                    Scores.remove(6);
                    try
                    {
                        PrintWriter writer = new PrintWriter("src/Game/scores.txt");
                        writer.close();
                        BufferedWriter writer2 = new BufferedWriter(new FileWriter("src/Game/scores.txt"));
                        for(String s: Scores)
                        {
                            writer2.write(s+",");
                        }
                        writer2.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    tmp = 1;
                    gc.setFill(Color.BLUEVIOLET);
                    gc.setFont(new Font("", 50));
                    gc.fillText("YOU DIED", 185, 330);
                    Button back = new Button();
                    back.setText("Main Menu");
                    back.setPrefSize(170,50);
                    VBox b = new VBox();

                    b.setPadding(new Insets(420,0,0,220));
                    b.getChildren().add(back);
                    root.getChildren().add(b);
                    back.setOnAction(value -> Menu.start(stage));
                    AnimationTimer timer = new AnimationTimer() {
                        @Override
                        public void handle(long now)
                        {
                                if(back.isHover())
                                {
                                    back.setStyle("-fx-border-color: violet; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: violet; -fx-font-size: 15");
                                }
                                else
                                {
                                    back.setStyle("-fx-border-color: blueviolet; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: blueviolet; -fx-font-size: 15");
                                }
                        }
                    };
                    timer.start();
                    this.stop();
                }
            }
        }.start();

        Scene scene = new Scene(root, WIDTH * POS_SIZE, HEIGHT * POS_SIZE);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->
        {
            switch(key.getCode())
            {
                case UP: if(CURR_DIR != Direction.DOWN) CURR_DIR = Direction.UP; break;
                case DOWN: if(CURR_DIR != Direction.UP) CURR_DIR = Direction.DOWN; break;
                case LEFT: if(CURR_DIR != Direction.RIGHT) CURR_DIR = Direction.LEFT; break;
                case RIGHT: if(CURR_DIR != Direction.LEFT) CURR_DIR = Direction.RIGHT; break;
            }
        });

        stage.setScene(scene);
        stage.setTitle("Snake");
        stage.show();
    }
}
