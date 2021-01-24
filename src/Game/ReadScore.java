package Game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static Game.Model.*;

public class ReadScore
{
    static public void readScores()
    {
        Scores.clear();
        Scanner s = null;
        try
        {
            s = new Scanner(new File("src/Game/scores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try
        {
            while(s.hasNext())
            {
                String line = s.nextLine();
                String[] scores = line.split(",");
                Scores.addAll(Arrays.asList(scores));
            }
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
        } catch (Exception e)
        {
            System.out.println(e + "error during reading scores");
        }
    }

}
