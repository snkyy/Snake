package Game;

import javafx.scene.layout.Pane;

import static Game.Model.*;

public class Restart
{
    public static void restart()
    {
        EATEN = 0;
        CURR_DIR = Direction.LEFT;
        DEAD = false;
        body.clear();
        body.add(new Square(WIDTH / 2, HEIGHT / 2));
        body.add(new Square(WIDTH / 2, HEIGHT / 2));
        NewGame.root = new Pane();
    }
}
