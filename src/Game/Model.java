package Game;

import java.util.ArrayList;

public class Model
{
    public static int WIDTH = 25;
    public static int HEIGHT = 25;
    public static int POS_SIZE = 25;
    public static int EATEN = 0;
    public static int AX;
    public static int AY;
    public static int TICK = 90000000;
    public static boolean DEAD = false;
    public static ArrayList<Square> body = new ArrayList<>();
    public static Direction CURR_DIR = Direction.LEFT;
    public static ArrayList<String> Scores = new ArrayList<>();

    public enum Direction
    {
        UP, DOWN, LEFT, RIGHT;
    }

    public static class Square
    {
        int x, y;
        public Square(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
