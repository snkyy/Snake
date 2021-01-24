package Game;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import static Game.Model.*;
import static Game.Model.POS_SIZE;

public class Menu
{
    public static void start(Stage stage)
    {
        StackPane menu = new StackPane();

        Canvas bg = new Canvas(WIDTH * POS_SIZE, HEIGHT * POS_SIZE);
        GraphicsContext gc = bg.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, WIDTH * POS_SIZE, HEIGHT * POS_SIZE);

        ArrayList<Button> buttons = new ArrayList<>();
        Button NewGameButton = new Button("New Game");
        Button HSButton = new Button("HighScores");
        Button ExitButton = new Button("Exit");
        buttons.add(NewGameButton);
        buttons.add(HSButton);
        buttons.add(ExitButton);

        for(Button b: buttons)
        {
            b.setPrefSize(170,50);
        }

        VBox menubox = new VBox();
        menubox.setAlignment(Pos.TOP_LEFT);
        menubox.setPadding(new Insets(225,0,0,227));
        menubox.setSpacing(50);
        menubox.getChildren().addAll(buttons);

        Text snake = new Text();
        snake.setText("SNAKE");
        snake.setFont(new Font("",75));
        snake.setFill(Color.BLUEVIOLET);
        VBox title = new VBox();
        title.getChildren().add(snake);
        title.setAlignment(Pos.TOP_LEFT);
        title.setPadding(new Insets(75,0,0,185));

        NewGameButton.setOnAction(value -> NewGame.start(stage));
        HSButton.setOnAction(value -> HighScores.start(stage));
        ExitButton.setOnAction(value ->
        {
            if(ExitButton.getText().equals("Exit"))
            {
                ExitButton.setText("Are you sure?");
            }
            else
            {
                stage.close();
            }
        });

        menu.getChildren().add(bg);
        menu.getChildren().add(title);
        menu.getChildren().add(menubox);

        Scene scene = new Scene(menu, WIDTH * POS_SIZE, HEIGHT * POS_SIZE);
        stage.setScene(scene);
        stage.show();

        try
        {
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now)
                {
                    for(Button b: buttons)
                    {
                        if(b.isHover())
                        {
                            b.setStyle("-fx-border-color: violet; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: violet; -fx-font-size: 15");
                        }
                        else
                        {
                            b.setStyle("-fx-border-color: blueviolet; -fx-border-width:3px; -fx-background-color: transparent; -fx-text-fill: blueviolet; -fx-font-size: 15");
                        }
                    }
                }
            };
            timer.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
