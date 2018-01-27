import com.connectfour.Disc;
import com.connectfour.Game;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.connectfour.Checker.leftmostHorizontalColumn;
import static com.connectfour.Checker.rightHorizontalColumn;

public class GameTest {
    private Game game = new Game();

    @Before
    public void setUp(){
        game.reset();
    }

    /*
    | | | |G| | | |
    | | | |G| | | |
    | | | |G| | | |
    | | |R|G|R| | |
    | | |R|R|G| | |
    | | |R|G|R| | |
    */
    @Test
    public void testVerticalWin(){
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    // Test horizontal border calculation logic
    @Test
    public void testHorizontalBorders() {
        // 0 from 0 to 3
        // 1 from 0 to 4
        // 2 from 0 to 5
        // 3 from 0 to 6
        // 4 from 1 to 6
        // 5 from 2 to 6
        // 6 from 3 to 6
        int column;
        column = 0;
        Assert.assertEquals(0, leftmostHorizontalColumn(column));
        Assert.assertEquals(3, rightHorizontalColumn(column));
        column = 1;
        Assert.assertEquals(0, leftmostHorizontalColumn(column));
        Assert.assertEquals(4, rightHorizontalColumn(column));
        column = 2;
        Assert.assertEquals(0, leftmostHorizontalColumn(column));
        Assert.assertEquals(5, rightHorizontalColumn(column));
        column = 3;
        Assert.assertEquals(0, leftmostHorizontalColumn(column));
        Assert.assertEquals(6, rightHorizontalColumn(column));
        column = 4;
        Assert.assertEquals(1, leftmostHorizontalColumn(column));
        Assert.assertEquals(6, rightHorizontalColumn(column));
        column = 5;
        Assert.assertEquals(2, leftmostHorizontalColumn(column));
        Assert.assertEquals(6, rightHorizontalColumn(column));
        column = 6;
        Assert.assertEquals(3, leftmostHorizontalColumn(column));
        Assert.assertEquals(6, rightHorizontalColumn(column));
    }

    /*
    | | | | | | | |
    | | | | | | | |
    | | | | | | | |
    | | | | | | | |
    | |G|G|G|G| |R|
    | |R|R|G|R| |R|
    */
    @Test
    public void testHorizontalWin(){
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 5);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 2);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    | | | | | | | |
    | | | | | | | |
    | | | | | | | |
    | |G|G|G| | | |
    |R|R|R|R| | | |
    */
    @Test
    public void testHorizontalWinLeft(){
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 4);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 1);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.RED);
    }

    /*
    | | | | | | | |
    | | | | | | | |
    | | | | | | | |
    | | | | | | | |
    | | |R|G|G|G|G|
    | | |R|G|R|R|R|
    */
    @Test
    public void testHorizontalWinRight(){
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.GREEN, 7);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    | |G| | | | | |
    | |R|G| | | | |
    | |G|R|G| | | |
    | |R|R|R|G| | |
    | |R|G|R|G| | |
    */
    @Test
    public void testDownDiagonalWin(){
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 2);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 2);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    |G| | | | | | |
    |R|G| | | | | |
    |G|R|G| | | | |
    |R|R|R|G| | | |
    |R|G|R|G| | | |
    */
    @Test
    public void testDownDiagonalWinLeft(){
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 1);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 1);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    | | | |G| | | |
    | | | |R|G| | |
    | | | |G|R|G| |
    | | | |R|R|R|G|
    | | | |R|G|R|G|
    */
    @Test
    public void testDownDiagonalWinRight(){
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 7);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 7);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 4);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 4);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    | | | | | |G| |
    | | | | |G|R| |
    | | | |G|R|G| |
    | | |G|R|R|R| |
    | | |G|R|G|R| |
    */
    @Test
    public void testUpDiagonalWin(){
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 6);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 6);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    | | | |G| | | |
    | | |G|R| | | |
    | |G|R|G| | | |
    |G|R|R|R| | | |
    |G|R|G|R| | | |
    */
    @Test
    public void testUpDiagonalWinLeft(){
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 1);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 1);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 4);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 4);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
    | | | | | | | |
    | | | | | | |G|
    | | | | | |G|R|
    | | | | |G|R|G|
    | | | |G|R|R|R|
    | | | |G|R|G|R|
    */
    @Test
    public void testUpDiagonalWinRight(){
        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 7);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 7);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.GREEN);
    }

    /*
     |G|R|G|R|R|G|G|
     |G|R|G|R|R|G|R|
     |G|R|G|R|R|G|G|
     |R|G|R|G|G|R|R|
     |R|G|R|G|G|R|G|
     |R|G|R|G|G|R|R|
     */
    @Test
    public void testNoWinner(){
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 2);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 1);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 1);
        game.insert(Disc.RED, 2);
        game.insert(Disc.GREEN, 1);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 3);
        game.insert(Disc.GREEN, 4);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 3);
        game.insert(Disc.RED, 4);
        game.insert(Disc.GREEN, 3);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 5);
        game.insert(Disc.GREEN, 6);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 5);
        game.insert(Disc.RED, 6);
        game.insert(Disc.GREEN, 5);

        Assert.assertTrue(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);

        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 7);
        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 7);
        game.insert(Disc.RED, 7);
        game.insert(Disc.GREEN, 7);
        game.insert(Disc.RED, 7);

        Assert.assertFalse(game.canInsert());
        Assert.assertEquals(game.getWinner(), Disc.EMPTY);
    }

    @Test
    public void testCannotInsertMoreThanLimitPerColumn(){
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 1);
        game.insert(Disc.RED, 1);
        game.insert(Disc.GREEN, 1);
        game.insert(Disc.RED, 1);
        Assert.assertTrue(game.insert(Disc.GREEN, 1));
        Assert.assertFalse(game.insert(Disc.RED, 1));
    }

}