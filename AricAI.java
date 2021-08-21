public class AricAI implements CFPlayer {

    @Override
    public int nextMove(CFGame g) {
        // check for a self win and win and check for opponent win and block
        // vertical win
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (g.getState()[i][j] == 0 && g.getState()[i][j + 1] + g.getState()[i][j + 2] + g.getState()[i][j + 3] == 3 ||
                        g.getState()[i][j] == 0 && g.getState()[i][j + 1] + g.getState()[i][j + 2] + g.getState()[i][j + 3] == -3) {
                    return i + 1;
                }
            }
        }
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 6; ++j) {

                // horizontal win
                // 0 x x x
                if (i <= 3) {
                    if (g.getState()[i][j] == 0 && g.getState()[i + 1][j] + g.getState()[i + 2][j] + g.getState()[i + 3][j] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i + 1][j] + g.getState()[i + 2][j] + g.getState()[i + 3][j] == -3) {
                        return i + 1;
                    }
                }
                // x 0 x x
                if (i >= 1 && i <= 4) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 1][j] + g.getState()[i + 1][j] + g.getState()[i + 2][j] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 1][j] + g.getState()[i + 1][j] + g.getState()[i + 2][j] == -3) {
                        return i + 1;
                    }
                }
                // x x 0 x
                if (i >= 2 && i <= 5) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 2][j] + g.getState()[i - 1][j] + g.getState()[i + 1][j] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 2][j] + g.getState()[i - 1][j] + g.getState()[i + 1][j] == -3) {
                        return i + 1;
                    }
                }
                // x x x 0
                if (i >= 3) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 3][j] + g.getState()[i - 2][j] + g.getState()[i - 1][j] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 3][j] + g.getState()[i - 2][j] + g.getState()[i - 1][j] == -3) {
                        return i + 1;
                    }
                }

                // diagonal down win
                /*
                0
                  x
                    x
                      x
                */
                if (i <= 3 && j <= 2) {
                    if (g.getState()[i][j] == 0 && g.getState()[i + 1][j + 1] + g.getState()[i + 2][j + 2] + g.getState()[i + 3][j + 3] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i + 1][j + 1] + g.getState()[i + 2][j + 2] + g.getState()[i + 3][j + 3] == -3) {
                        return i + 1;
                    }
                }
                /*
                 x
                   0
                     x
                       x
                */
                if (i >= 1 && j >= 1 && i <= 4 && j <= 3) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 1][j - 1] + g.getState()[i + 1][j + 1] + g.getState()[i + 2][j + 2] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 1][j - 1] + g.getState()[i + 1][j + 1] + g.getState()[i + 2][j + 2] == -3) {
                        return i + 1;
                    }
                }
                /*
                x
                  x
                    0
                      x
                */
                if (i >= 2 && j >= 2 && i <= 5 && j <= 4) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 2][j - 2] + g.getState()[i - 1][j - 1] + g.getState()[i + 1][j + 1] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 2][j - 2] + g.getState()[i - 1][j - 1] + g.getState()[i + 1][j + 1] == -3) {
                        return i + 1;
                    }
                }
                /*
                x
                  x
                    x
                      0
                */
                if (i >= 3 && j >= 3) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 3][j - 3] + g.getState()[i - 2][j - 2] + g.getState()[i - 1][j - 1] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 3][j - 3] + g.getState()[i - 2][j - 2] + g.getState()[i - 1][j - 1] == -3) {
                        return i + 1;
                    }
                }

                // diagonal up win
                /*
                      x
                    x
                  x
                0
                */
                if (j >= 3 && i <= 3) {
                    if (g.getState()[i][j] == 0 && g.getState()[i + 1][j - 1] + g.getState()[i + 2][j - 2] + g.getState()[i + 3][j - 3] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i + 1][j - 1] + g.getState()[i + 2][j - 2] + g.getState()[i + 3][j - 3] == -3) {
                        return i + 1;
                    }
                }
                /*
                      x
                    x
                  0
                x
                */
                if (i >= 1 && j >= 2 && i <= 4 && j <= 4) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 1][j + 1] + g.getState()[i + 1][j - 1] + g.getState()[i + 2][j - 2] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 1][j + 1] + g.getState()[i + 1][j - 1] + g.getState()[i + 2][j - 2] == -3) {
                        return i + 1;
                    }
                }
                /*
                      x
                    0
                  x
                x
                */
                if (i >= 2 && j >= 1 && i <= 5 && j <= 3) {
                     if (g.getState()[i][j] == 0 && g.getState()[i - 2][j + 2] + g.getState()[i - 1][j + 1] + g.getState()[i + 1][j - 1] == 3 ||
                             g.getState()[i][j] == 0 && g.getState()[i - 2][j + 2] + g.getState()[i - 1][j + 1] + g.getState()[i + 1][j - 1] == -3) {
                         return i + 1;
                     }
                }
                /*
                      0
                    x
                  x
                x
                */
                if (i >= 3 && j <= 2) {
                    if (g.getState()[i][j] == 0 && g.getState()[i - 3][j + 3] + g.getState()[i - 2][j + 2] + g.getState()[i - 1][j + 1] == 3 ||
                            g.getState()[i][j] == 0 && g.getState()[i - 3][j + 3] + g.getState()[i - 2][j + 2] + g.getState()[i - 1][j + 1] == -3) {
                        return i + 1;
                    }
                }
            }
        }
        return new RandomAI().nextMove(g); // give a random column
    }

    @Override
    public String getName() {
        return "Aric's AI";
    }
}
