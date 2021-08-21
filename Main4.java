import java.util.Scanner;
import CFPlayer;
import RandomAI;
import AricAI;
import ConsoleCF;
import GUICF;


public class Main4 {
    public static void main(String[] args) {

        Scanner reader = new Scanner (System.in);
        int gameMode = reader.nextInt();

        if (gameMode==1) {
            new GUICF(new AricAI());
        } else if (gameMode==2) {
            CFPlayer ai1 = new AricAI();
            CFPlayer ai2 = new RandomAI();
            int n = 10000;
            int winCount = 0;
            for (int i =0; i < n ; i++) {
                ConsoleCF game = new ConsoleCF(ai1, ai2);
                game.playOut();
                if(game.getWinner() == ai1.getName())
                    winCount++;
            }
            System.out.println(((double) winCount)/n);
        } else {
            ConsoleCF game = new ConsoleCF(new AricAI());
            game.playOut();
            System.out.println(game.getWinner() + " has won.");
        }
    }
}
