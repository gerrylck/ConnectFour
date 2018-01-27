package com.connectfour;

import java.util.Arrays;

import static com.connectfour.Main.ROWS;
import static com.connectfour.Main.COLS;

/**
 * Game class to manage game flow and store game state
 */
public class Game {
    /**
     Vertical Board
     5| | | | | | | |
     4| | | | | | | |
     3| | | |R| | | |
     2| | |G|R|G| | |
     1| |G|R|G|G|R| |
     0|R|G|R|G|R|R|G|
       0 1 2 3 4 5 6
     */
    private Disc[][] board = new Disc[ROWS][COLS];  // row 6 column 7
    private final int TOTAL_TURN = ROWS * COLS;
    private int turnCounter = 0;
    private Disc winner = Disc.EMPTY;

    /**
     * Init game board
     */
    public Game() {
        reset();
    }

    /**
     * Reset game board
     */
    public void reset() {
        for (Disc[] row: board) Arrays.fill(row, Disc.EMPTY);
    }

    /**
     * Paint game board
     */
    public void paint() {
        for (int i = ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("");
    }

    /**
     * Insert disc to specific column of the board
     * @param disc disc to insert
     * @param column column to insert.  First column is 1
     * @return true if disc has been inserted to game board
     */
    public boolean insert(Disc disc, int column) {
        // Needs to be within allowed column range
        if ( column < 1 || column > COLS ) {
            return false;
        }

        // column index starts from 0 while user interface starts from 1
        column = column - 1;

        // insert disc to specific column of the board
        // if column is already full, insertedRow will not get updated
        int insertedRow = -1;
        for (int i = 0; i < ROWS ; i++) {
            if ( board[i][column] == Disc.EMPTY ) {
                board[i][column] = disc;
                insertedRow = i;
                turnCounter++;
                break;
            }
        }

        // failed to insert
        if ( insertedRow == -1 ) {
            return false;
        } else {
            // paint board after insert
            paint();

            boolean hasWinner = Checker.checkWin(board, disc, insertedRow, column);
            if ( hasWinner ) {
                winner = disc;
                turnCounter = TOTAL_TURN;    // winner is there, cannot insert more
            }
            return true;
        }
    }

    /**
     * Check if game still allows disc to insert.  If not, game ended
     * @return true if board still accepts more disc to insert
     */
    public boolean canInsert() {
        return turnCounter < TOTAL_TURN;
    }

    /**
     * Return winner disc.  If disc is EMPTY, it means no winner yet
     * @return winner disc. If disc is EMPTY, it means no winner yet
     */
    public Disc getWinner() {
        return winner;
    }
}