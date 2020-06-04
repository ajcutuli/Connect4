package hw4;

import java.util.Scanner;

public class ConsoleCF extends CFGame {

    private CFPlayer player1; // red
    private CFPlayer player2; // black
    private CFGame g;

    public ConsoleCF(CFPlayer ai) { // argument could be an object of RandomAI or AricAI
        // pick a number 0 or 1
        int coinFlip = (int) (2*Math.random());
        if (coinFlip == 0) {
            // ai is red, Human Player is black
            player1 = ai;
            player2 = new HumanPlayer();
        }
        else {
            // Human Player is red, ai is black
            player1 = new HumanPlayer();
            player2 = ai;
        }
    }

    public ConsoleCF(CFPlayer ai1, CFPlayer ai2) { // RandomAI vs AricAI or RandomAI vs RandomAI or AricAI vs AricAI
        // pick a number 0 or 1
        int coinFlip = (int) (2*Math.random());
        if (coinFlip == 0) {
            // ai1 is red, ai2 is black
            player1 = ai1;
            player2 = ai2;
        }
        else {
            // ai2 is red, ai1 is black
            player1 = ai2;
            player2 = ai1;
        }
    }

    public void playOut() {
        g = new CFGame();
        while (!g.isGameOver()) {
            int c1 = player1.nextMove(g);
            g.play(c1);
            if (g.isGameOver()) {
                g.winner();
            }
            int c2 = player2.nextMove(g);
            g.play(c2);
            if (g.isGameOver()) {
                g.winner();
            }
        }
    }

    public String getWinner() {
        if (g.winner() == 1) return player1.getName();
        else if (g.winner() == -1) return player2.getName();
        else return "Draw";
    }

    private class HumanPlayer implements CFPlayer {

        @Override
        public int nextMove(CFGame g) {
            // print the state of the board
            int[][] board = g.getState();
            int count = 0;
            for (int j = 0; j < 6; ++j) {
                for (int i = 0; i < 7; ++i) {
                    if (board[i][j] == -1) { // this if else will make the board print out smoother since -1 is 2 digits and 0 and 1 are 1 digit
                        System.out.print(" " + board[i][j]);
                    }
                    else {
                        System.out.print("  " + board[i][j]);
                    }
                    ++count;
                    if (count == 7) {
                        System.out.println();
                        count = 0;
                    }
                }
            }
            // ask for a column to play
            Scanner cin = new Scanner(System.in);
            int c;
            do {
                System.out.println("What column will you play?");
                c = cin.nextInt();
            } while (c < 1 || c > 7 || board[c-1][0] != 0);
            return c;
        }

        @Override
        public String getName() {
            return "Human Player";
        }
    }
}
