import framework.Game;
import multiplayer.TreadHandler;
import quartettgame.QuartettDeckBuilder;
import quartettgame.QuartettGame;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by trixi on 2017.02.28..
 */
public class Main
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many players do u want to handle: ");
        int numberOfPlayer = scanner.nextInt();
        TreadHandler treadHandler = null;
        try
        {
             treadHandler = new TreadHandler(numberOfPlayer);
            treadHandler.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        while(true)
        {
            if (treadHandler.getServerThreads().size() == numberOfPlayer) break;
        }
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Game quartetGame = new QuartettGame(new QuartettDeckBuilder("masterCrokCards.csv"), numberOfPlayer, treadHandler.getServerThreads());
        quartetGame.run();
    }
}
