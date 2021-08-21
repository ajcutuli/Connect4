import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {

        System.out.println("Enter 1 to play against Aric's AI in a GUI.");
        System.out.println("Enter 2 to play the result of Aric's AI vs an AI that makes random plays.");
        System.out.println("Enter 3 to play against Aric's AI in the console.");
        
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
            System.out.println("In 10,000 games, Aric's AI won " + 100*((double) winCount)/n) + "% of the time!");
        } else if (gameMode==3) {
            ConsoleCF game = new ConsoleCF(new AricAI());
            game.playOut();
            System.out.println(game.getWinner() + " has won!");
        }
    }
}
