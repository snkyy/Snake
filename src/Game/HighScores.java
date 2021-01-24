package Game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static Game.ReadScore.readScores;
import static Game.Model.*;


public class HighScores
{
    public static void start(Stage stage)
    {
        readScores();
        ArrayList<Button> scores = new ArrayList<>();
        VBox buttons = new VBox();
        buttons.setPadding(new Insets(205,0,0,227));
        for(int i = 0; i < 6; i++)
        {
            scores.add(new Button());
        }
        for(Integer i = 1; i <= 6; i++) //Integer is intentional here
        {
            scores.get(i-1).setText(i.toString()+".          " + Scores.get(i-1));
            scores.get(i-1).setPrefHeight(30);
            scores.get(i-1).setStyle("-fx-border-color: transparent; -fx-border-width:3px; " +
                    "-fx-background-color: transparent; -fx-text-fill: blueviolet; -fx-font-size: 20");
        }
        for(Button b: scores)
        {
            buttons.getChildren().add(b);
        }

        Canvas bg = new Canvas(WIDTH * POS_SIZE, HEIGHT * POS_SIZE);
        GraphicsContext gc = bg.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, WIDTH * POS_SIZE, HEIGHT * POS_SIZE);

        StackPane root = new StackPane();
        root.getChildren().add(bg);
        root.getChildren().add(buttons);
        stage.setScene(new Scene(root, WIDTH * POS_SIZE, HEIGHT * POS_SIZE));

        VBox title = new VBox();
        Text hs = new Text("Highscores");
        hs.setFill(Color.BLUEVIOLET);
        hs.setFont(new Font("", 60));
        title.getChildren().add(hs);
        title.setPadding(new Insets(70,0,0,135));
        root.getChildren().add(title);

        Button ExitButton = new Button("Main Menu");
        ExitButton.setPrefSize(170,50);
        VBox exit = new VBox();
        exit.setPadding(new Insets(515,0,0,212));
        exit.getChildren().add(ExitButton);
        ExitButton.setOnAction(value -> Menu.start(stage));
        root.getChildren().add(exit);

        try
        {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now)
                {
                    if(ExitButton.isHover())
                    {
                        ExitButton.setStyle("-fx-border-color: violet; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: violet; -fx-font-size: 15");
                    }
                    else
                    {
                        ExitButton.setStyle("-fx-border-color: blueviolet; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: blueviolet; -fx-font-size: 15");
                    }
                    }
            };
            timer.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
