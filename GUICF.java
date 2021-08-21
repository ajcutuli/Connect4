import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUICF extends CFGame {

    private GameBoard this_board;
    private boolean player1_red = false;
    private CFPlayer player1;
    private CFPlayer player2;
    private boolean player1_won = false;
    private boolean player2_won = false;
    private Container pane;
    private JButton[] arrows = new JButton[7];
    private JFrame frame = new JFrame("Connect Four");
    private JPanel board = new JPanel();
    private JLabel[][] squares = new JLabel[7][6];
    private int column;
    private volatile boolean arrowclick = false;

    public GUICF(CFPlayer ai) {
        // initialize CFGame
        super();

        // make board and give it a frame
        this_board = new GameBoard();
        pane = frame.getContentPane();

        // make panel of arrow buttons
        JPanel arrow_panel = new JPanel();
        arrow_panel.setLayout(new GridLayout(1, 7, 50, 10));
        for (int i = 0; i < arrows.length; ++i) {
            arrows[i] = new JButton("\u2193");
            arrows[i].addActionListener(new ArrowListener());
            arrow_panel.add(arrows[i]);
        }
        pane.add(arrow_panel, BorderLayout.NORTH);

        player1 = this.new HumanPlayer();
        player2 = ai;

        int coinFlip = (int) (2*Math.random());
        if (coinFlip == 0) {
            player1_red = true;
        }
        // else, player2 is red and goes first

        // play the game
        playOut();
    }

    public GUICF(CFPlayer ai1, CFPlayer ai2) {
        // initialize CFGame
        super();

        // make board and give it a frame
        this_board = new GameBoard();
        pane = frame.getContentPane();

        // make play button
        JButton play = new JButton("Play");
        play.addActionListener(new PlayListener());
        play.setPreferredSize(new Dimension(10, 20));
        pane.add(play, BorderLayout.NORTH);

        player1 = ai1;
        player2 = ai2;

        int coinFlip = (int) (2*Math.random());
        if (coinFlip == 0) {
            player1_red = true;
        }
        // else, player2 is red and goes first
    }

    private class ArrowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < arrows.length; ++i) {
                if (arrows[i] == e.getSource()) {
                    if (!(player1_won || player2_won)) {
                        column = i + 1;
                        arrowclick = true;
                        break;
                    }
                }
            }
        }
    }

    private class PlayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(player1_won || player2_won)) {
                playOut();
            }
        }
    }

    private class GameBoard extends JPanel {

        public GameBoard() {
            // build the frame of the board
            frame.setVisible(true);
            frame.setSize(800, 500);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            pane = frame.getContentPane();

            // make the board 6 rows by 7 columns
            board.setLayout(new GridLayout(6, 7));

            // make an array of empty squares
            for (int i = 0; i < 7; ++i) {
                for (int j = 0; j < 6; ++j) {
                    squares[i][j] = new JLabel();
                    squares[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                }
            }

            // add the squares to the board
            for (int j = 0; j < 6; ++j) {
                for (int i = 0; i < 7; ++i) {
                    board.add(squares[i][j]);
                }
            }

            // add the board to the pane
            pane.add(board, BorderLayout.CENTER);
        }

        private void paint(int x, int y, int color) {
            // if the color value is 1, paint the square red
            if (color == 1) {
                squares[x][y].setBackground(Color.RED);
                squares[x][y].setOpaque(true);
            }

            // if the color is -1, paint the square black
            else if (color == -1) {
                squares[x][y].setBackground(Color.BLACK);
                squares[x][y].setOpaque(true);
            }
        }
    }

    private boolean playGUI(int c) {
        // use CFGame's play method to see if c is valid
        // if c is valid, paint the appropriate square
        if (this.play(c)) {
            for (int j = 5; j >= 0; --j) {
                if (this.getState()[c - 1][j] != 0) {
                    if (this.getState()[c - 1][j] == 1) {
                        this_board.paint(c - 1, j, 1); // paint red
                    }
                    else {
                        this_board.paint(c - 1, j, -1); // paint black
                    }
                }
            }
            return true;
        }
        return false;

    }

    private class HumanPlayer implements CFPlayer {

        public int nextMove(CFGame g) {
            boolean invalid = true;
            // loop until the next move is valid
            while (invalid) {
                // loop until the arrowclick is false
                while (!arrowclick) {}
                // check whether the cell is zero or not
                for (int j = 0; j < 6; ++j) {
                    if (g.getState()[column - 1][j] == 0) {
                        // if the square's state is zero, the square is valid
                        invalid = false;
                    }
                }
                if (invalid) {
                    System.out.println("Invalid column.");
                    arrowclick = false;
                }
            }
            arrowclick = false;
            return column;
        }

        public String getName() {
            return "Human Player";
        }
    }

    public void playOut() {
        // initialize the winners to false
        player1_won = false;
        player2_won = false;

        // if player1 goes first
        if (player1_red) {
            // loop until the game is over
            while (!this.isGameOver()) {
                // call the GUI representation of player1's move
                this.playGUI(player1.nextMove(this));
                // check if it was a winning move
                if (this.isGameOver()) {
                    player1_won = true;
                    break;
                }
                // call the GUI representation of player2's move
                this.playGUI(player2.nextMove(this));
                // check if it was a winning move
                if (this.isGameOver()) {
                    player2_won = true;
                    break;
                }
            }
        }
        // if player2 goes first
        else {
            // loop until the game is over
            while (!this.isGameOver()) {
                // call the GUI representation of player2's move
                this.playGUI(player2.nextMove(this));
                // check if it was a winning move
                if (this.isGameOver()) {
                    player2_won = true;
                    break;
                }
                // call the GUI representation of player1's move
                this.playGUI(player1.nextMove(this));
                // check if it was a winning move
                if (this.isGameOver()) {
                    player1_won = true;
                    break;
                }
            }
        }

        // display the winner to the console
        if (player1_won) {
            System.out.println(player1.getName() + " won!");
        } else if (player2_won) {
            System.out.println(player2.getName() + " won!");
        } else {
            System.out.println("Draw.");
        }
    }
}
