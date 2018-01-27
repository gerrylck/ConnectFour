package com.connectfour;

import static com.connectfour.Main.ROWS;
import static com.connectfour.Main.COLS;
import static com.connectfour.Main.CONNECT_N;

/**
 * Checker contains game rules and checks if the last disc insert comes off winner
 */
public class Checker {
    /**
     * Check if last disc insert to last row and column comes off winner
     * @param board game board matrix
     * @param lastDisc last inserted disc
     * @param lastRow row of last inserted position
     * @param lastColumn column of last inserted position
     * @return true if winner is there
     */
    public static boolean checkWin(final Disc[][] board, Disc lastDisc, int lastRow, int lastColumn) {
        boolean hasWin;

        // Lines to check
        // 1. check vertical line
        // 2. check horizontal line
        // 3. check down diagonal line
        // 4. check up diagonal line

        // 1. check vertical
        hasWin = checkVertical(board, lastDisc, lastRow, lastColumn);
        if ( hasWin ) return true;

        // 2. check horizontal line
        hasWin = checkHorizontal(board, lastDisc, lastRow, lastColumn);
        if ( hasWin ) return true;

        // 3. check down diagonal line
        hasWin = checkDownDiagonal(board, lastDisc, lastRow, lastColumn);
        if ( hasWin ) return true;

        // 4. check up diagonal line
        hasWin = checkUpDiagonal(board, lastDisc, lastRow, lastColumn);
        if ( hasWin ) return true;

        return false;
    }

    /**
     * Check vertical line.  This check is easy.  Just check the three disc underneath
     * @param board game board matrix
     * @param disc last inserted disc
     * @param row row of last inserted position
     * @param column column of last inserted position
     * @return true if winner is there from vertical line
     */
    private static boolean checkVertical(Disc[][] board, Disc disc, int row, int column) {
        // it could win only if the disc is on the top of three(6-4+1) rows
        if ( row >= ROWS - CONNECT_N + 1) {
            for (int i = 1; i < CONNECT_N; i++) {
                if ( board[row - i][column] != disc ) {
                    break;
                }
                if ( i == CONNECT_N - 1) return true;  // loop confirmed all three are same disc
            }
        }
        return false;
    }

    /**
     * Return the leftmost column the horizontal check should start check
     * @param column insertedColumn
     * @return leftmost column it should start checking
     */
    public static int leftmostHorizontalColumn(int column) {
        return Math.max(0, column - CONNECT_N + 1);
    }

    /**
     * Return the rightmost column the horizontal check should end check
     * @param column insertedColumn
     * @return rightmost column it should end check
     */
    public static int rightHorizontalColumn(int column) {
        return Math.min(COLS - 1, column + CONNECT_N - 1);
    }

    /**
     * Check horizontal line.  Need to check from the left three to the right three to find any connectfour
     * @param board game board matrix
     * @param disc last inserted disc
     * @param row row of last inserted position
     * @param column column of last inserted position
     * @return true if winner is there from horizontal line
     */
    private static boolean checkHorizontal(Disc[][] board, Disc disc, int row, int column) {
        // Need to check from the left three to the right three to find any connectfour
        // 0 from 0 to 3
        // 1 from 0 to 4
        // 2 from 0 to 5
        // 3 from 0 to 6
        // 4 from 1 to 6
        // 5 from 2 to 6
        // 6 from 3 to 6
        int left = leftmostHorizontalColumn(column);   // ensure not out of bound
        int right = rightHorizontalColumn(column);   // ensure not out of bound

        int countSame = 0;  // if countSame equals to four, win found

        for (int i=left; i<=right; i++) {
            if ( board[row][i] == disc ) {
                countSame++;
            } else {
                countSame = 0;
            }
            if ( countSame >= CONNECT_N ) return true;
        }
        return false;
    }

    /**
     * Check down diagonal line.  Similar to the idea of horizontal, but this time is down diagonal
     * @param board game board matrix
     * @param disc last inserted disc
     * @param row row of last inserted position
     * @param column column of last inserted position
     * @return true if winner is there from down diagonal line
     */
    private static boolean checkDownDiagonal(Disc[][] board, Disc disc, int row, int column) {
        //System.out.printf("row:%d col:%d\n", row, column);

        // Similar to the idea of horizontal, but this time is down diagonal
        // need to find the left top pos first
        // 1,2 > 4,-1
        // 4,3 > 7,0
        int left_row = row + CONNECT_N - 1;
        int left_col = column - CONNECT_N + 1;
        // col may go out of bound, adjust row accordingly
        // 4,-1 > 3,0
        // 7,0 > 7,0
        if ( left_col < 0 ) {
            left_row = left_row + left_col;
            left_col = 0;
        }
        // row may go out of bound, adjust col accordingly
        // 7,0 > 5,2
        if ( left_row > ROWS - 1 ) {
            left_col = left_col + (left_row - (ROWS - 1));
            left_row = ROWS - 1;
        }

        // and then the right bottom pos
        // 1,2 > -2,5
        int right_row = row - CONNECT_N + 1;
        int right_col = column + CONNECT_N - 1;
        // row may go out of bound, adjust col accordingly
        // -2,5 > 0,3
        if ( right_row < 0 ) {
            right_col = right_col + right_row;
            right_row = 0;
        }
        // col may go out of bound, adjust row accordingly
        // -2,5 > 0,3
        if ( right_col >= COLS ) {
            right_row = right_row - (COLS - right_col) - 1;
            right_col = COLS - 1;
        }
        // if countSame equals to four, win found
        int countSame = 0;

        // left_row = 3
        // left_col = 0
        // right_row = 0
        // right_col = 3
        // 1,2 > (3,0) (2,1) (1,2) (0,3)
        for (int i=left_col; i<=right_col; i++) {
            if ( board[left_row-i+left_col][i] == disc ) {
                countSame++;
            } else {
                countSame = 0;
            }
            if ( countSame >= CONNECT_N ) return true;
        }

        return false;
    }

    /**
     * Check up diagonal line.   Similar to the idea of horizontal, but this time is up diagonal
     * @param board game board matrix
     * @param disc last inserted disc
     * @param row row of last inserted position
     * @param column column of last inserted position
     * @return true if winner is there from up diagonal line
     */
    private static boolean checkUpDiagonal(Disc[][] board, Disc disc, int row, int column) {
        //System.out.printf("row:%d col:%d\n", row, column);

        // Similar to the idea of horizontal, but this time is up diagonal
        // need to find the left bottom pos first
        // 1,2 > -2,-1
        int left_row = row - CONNECT_N + 1;
        int left_col = column - CONNECT_N + 1;
        // row may go out of bound, adjust col accordingly
        // -2,-1 > 0,1
        if ( left_row < 0 ) {
            left_col = left_col - left_row;
            left_row = 0;
        }
        // col may go out of bound, adjust row accordingly
        // 0,1 > 0,1
        if ( left_col < 0 ) {
            left_row = left_row - left_col;
            left_col = 0;
        }

        // and then the right up pos
        // 1,2 > 4,5
        int right_row = row + CONNECT_N - 1;
        int right_col = column + CONNECT_N - 1;
        // row may go out of bound, adjust col accordingly
        // 4,5 > 4,5
        if ( right_row >= ROWS ) {
            right_col = right_col + (ROWS - right_row) - 1;
            right_row = ROWS - 1;
        }
        // col may go out of bound, adjust row accordingly
        // 4,5 > 4,5
        if ( right_col >= COLS ) {
            right_row = right_row - (COLS - right_col) - 1;
            right_col = COLS - 1;
        }
        // if countSame equals to four, win found
        int countSame = 0;

        // left_row = 0
        // left_col = 1
        // right_row = 4
        // right_col = 5
        // 1,2 > (0,1) (1,2) (2,3) (3,4) (4,5)
        for (int i=left_col; i<=right_col; i++) {
            if ( board[left_row+i-left_col][i] == disc ) {
                countSame++;
            } else {
                countSame = 0;
            }
            if ( countSame >= CONNECT_N ) return true;
        }

        return false;
    }
}