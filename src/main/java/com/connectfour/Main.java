package com.connectfour;

import java.util.Scanner;

/**
 * Main program of the Connect Four Game
 * https://en.wikipedia.org/wiki/Connect_Four
 */
public class Main {
    static final int ROWS = 6;
    static final int COLS = 7;
    static final int CONNECT_N = 4;     // easily change the rule from ConnectFour to ConnectThree or ConnectFive

    private static Disc turn = Disc.RED;        // RED plays first

    private Main() {
        Game game = new Game();

        game.paint();

        boolean inserted;
        // check if game still allows to insert.  If not, game ended
        while ( game.canInsert() ) {
            System.out.printf("Player %d [%s] - choose column (1-7): ", turn.ordinal(), turn.name());
            Scanner scanner = new Scanner(System.in);

            int column = scanner.nextInt();
            inserted = game.insert(turn, column);

            // if inserted, check if there is winner
            if (inserted) {
                if (game.getWinner() != Disc.EMPTY) {
                    System.out.printf("Player %d [%s] wins!\n", game.getWinner().ordinal(), game.getWinner().name());
                } else {
                    turn = Disc.values()[(turn.ordinal() % 2) + 1];    // Use ordinal for turn logic: 1,2,1,2,1,2...
                }
            }
        }

        if ( game.getWinner() == Disc.EMPTY ) {
            System.out.println("Game has NO WINNER!");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}