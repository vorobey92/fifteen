package fifteen.domain;

import fifteen.api.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

  private Board board;

  @Before
  public void setup() {
    board = new Board();
    board.initWinning();
  }

  @Test
  public void isWin() {
    assertTrue(board.haveWon());
  }

  @Test
  public void isNotWin() {
    board.init();
    assertFalse(board.haveWon());
  }

  @Test
  public void testVerticalMove() {
    board.move(new Point(3, 2));
    assertEquals(12, board.getNumberAt(3, 3));
  }

  @Test
  public void testHorizontalMove() {
    board.move(new Point(2, 3));
    assertEquals(15, board.getNumberAt(3, 3));
  }

  @Test
  public void testDiagonalMove() {
    board.move(new Point(2, 2));
    assertEquals(11, board.getNumberAt(2, 2));
    assertEquals(0, board.getNumberAt(3, 3));
  }

  @Test
  public void testMoveNotWorked() {
    board.move(new Point(0, 0));
    assertTrue(board.haveWon());
  }
}