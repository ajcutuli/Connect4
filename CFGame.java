public class CFGame {
    //state[i][j]= 0 means the i,j slot is empty
    //state[i][j]= 1 means the i,j slot has red
    //state[i][j]=-1 means the i,j slot has black
    private final int[][] state;
    private boolean isRedTurn;
    private int setWinner;

    {
        state = new int[7][6];
        for (int i=0; i<7; i++)
            for (int j=0; j<6; j++)
                state[i][j] = 0;
        isRedTurn = true; //red goes first
    }

    public int[][] getState() {
        int[][] ret_arr = new int[7][6];
        for (int i=0; i<7; i++)
            for (int j=0; j<6; j++)
                ret_arr[i][j] = state[i][j];
        return ret_arr;
    }

    public boolean isRedTurn() {
        return isRedTurn;
    }

    public boolean play(int c) {
        if (c < 1 || c > 7 || state[c-1][0] != 0) return false;
        for (int j = 5; j >= 0; --j) {
            if (state[c - 1][j] == 0) {
                if (isRedTurn) {
                    state[c - 1][j] = 1;
                    isRedTurn = false;
                    return true;
                } else {
                    state[c - 1][j] = -1;
                    isRedTurn = true;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGameOver() {
        // check if there's a win
        // vertical win
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (state[i][j] != 0 && state[i][j] == state[i][j + 1] && state[i][j] == state[i][j + 2] && state[i][j] == state[i][j + 3]) {
                    if (state[i][j] == 1) {
                        setWinner = 1; // red wins
                    } else if (state[i][j] == -1) {
                        setWinner = -1; // black wins
                    }
                    return true; // game is over
                }
            }
        }
        // horizontal win
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (state[i][j] != 0 && state[i][j] == state[i + 1][j] && state[i][j] == state[i + 2][j] && state[i][j] == state[i + 3][j]) {
                    if (state[i][j] == 1) {
                        setWinner = 1; // red wins
                    } else if (state[i][j] == -1) {
                        setWinner = -1; // black wins
                    }
                    return true; // game is over
                }
            }
        }
        // diagonal down win
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (state[i][j] != 0 && state[i][j] == state[i + 1][j + 1] && state[i][j] == state[i + 2][j + 2] && state[i][j] == state[i + 3][j + 3]) {
                    if (state[i][j] == 1) {
                        setWinner = 1; // red wins
                    } else if (state[i][j] == -1) {
                        setWinner = -1; // black wins
                    }
                    return true; // game is over
                }
            }
        }
        // diagonal up win
        for (int i = 0; i < 4; ++i) {
            for (int j = 3; j < 6; ++j) {
                if (state[i][j] != 0 && state[i][j] == state[i + 1][j - 1] && state[i][j] == state[i + 2][j - 2] && state[i][j] == state[i + 3][j - 3]) {
                    if (state[i][j] == 1) {
                        setWinner = 1; // red wins
                    } else if (state[i][j] == -1) {
                        setWinner = -1; // black wins
                    }
                    return true; // game is over
                }
            }
        }
        // last case: no possible moves
        for (int i = 0; i < 7; ++i) { // only need to check top of each column
            if (state[i][0] == 0) return false; // there is a possible move, so the game is not over
        }
        setWinner = 0; // no possible moves and no winner, so it is a draw
        return true; // game is over
    }

    public int winner() {
        return setWinner;
    }
}
