package Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Random;
import static Game.Model.*;

public class Engine
{
    public static void engine(GraphicsContext gc)
    {
        /* GAME OVER */
        if(DEAD)
        {
            return;
        }

        /* MOVING BODY OF THE SNAKE */
        for(int i = body.size()-1; i > 0; i--)
        {
            body.get(i).x = body.get(i-1).x;
            body.get(i).y = body.get(i-1).y;
        }

        /* MOVING HEAD OF THE SNAKE */
        switch(CURR_DIR)
        {
            case UP:
                body.get(0).y--; break;
            case DOWN:
                body.get(0).y++; break;
            case LEFT:
                body.get(0).x--; break;
            case RIGHT:
                body.get(0).x++; break;
        }

        /* CHECKING IF THE SNAKE IS OUT OF BOUNDS */
        if(body.get(0).x < 0 || body.get(0).x >= WIDTH || body.get(0).y < 0 || body.get(0).y >= HEIGHT)
        {
            DEAD = true;
        }

        /* CHECKING SELF-OVERLAP */
        for(int i = 1; i < body.size(); i++)
        {
            if(body.get(0).x == body.get(i).x && body.get(0).y == body.get(i).y)
            {
                DEAD = true;
                break;
            }
        }

        /* ADDING NEW SEGMENTS */
        if(body.get(0).x == AX && body.get(0).y == AY)
        {
            body.add(new Square(-1, -1));
            EATEN++;
            CreateApple();
        }

        /* BACKGROUND */
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0,WIDTH * POS_SIZE,HEIGHT * POS_SIZE);

        /* APPLE */
        gc.setFill(Color.DEEPPINK);
        gc.fillOval(AX * POS_SIZE, AY * POS_SIZE, POS_SIZE, POS_SIZE);

        /* SNAKE */
        for(Square p: body)
        {
            gc.setFill(Color.MEDIUMPURPLE);
            gc.fillRect(p.x * POS_SIZE, p.y * POS_SIZE, POS_SIZE-1, POS_SIZE-1);
        }

        /* SCORE */
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Points: " + EATEN * 10, 10, 30);
    }

    public static void CreateApple()
    {
        while(true)
        {
            Random rand = new Random();
            AX = rand.nextInt(WIDTH);
            AY = rand.nextInt(HEIGHT);
            for(Square p: body)
            {
                if(p.x == AX && p.y == AY) // WE DONT WANT AN APPLE TO SPAWN INSIDE A SNAKE
                {
                    continue;
                }
            }
            break;
        }
    }
}
