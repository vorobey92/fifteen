package fifteen.domain;

import fifteen.api.BoardSnapshot;
import fifteen.api.Point;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

  private final static int EMPTY_CELL = 0;

  private final int rows = 4;
  private final int columns = 4;
  private final int[][] numbers = new int[columns][rows];

  public void init() {
    Random r = new Random();
    List<Integer> initialValues = IntStream.range(0, 16).boxed().collect(Collectors.toList());
    for (int y = 0; y < numbers.length; y++) {
      for (int x = 0; x < numbers[0].length; x++) {
        numbers[y][x] = initialValues.remove(r.nextInt(initialValues.size()));
      }
    }
  }

  /**
   * for tests
   */
  void initWinning() {
    List<Integer> initialValues = IntStream.range(1, 16).boxed().collect(Collectors.toList());
    for (int y = 0; y < numbers.length; y++) {
      for (int x = 0; x < numbers[0].length; x++) {
        if (isLastCell(x, y)) {
          numbers[y][x] = EMPTY_CELL;
          continue;
        }
        numbers[y][x] = initialValues.remove(0);
      }
    }
  }

  public boolean haveWon() {
    int rowMultiplier = 4;
    for (int y = 0; y < numbers.length; y++) {
      for (int x = 0; x < numbers[0].length; x++) {
        if (isLastCell(x, y) && isEmpty(x, y)) {
          continue;
        }
        if ((1 + x + y * rowMultiplier) != numbers[y][x]) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isLastCell(int x, int y) {
    return y + 1 == numbers.length && x + 1 == numbers[numbers.length - 1].length;
  }

  Number getNumberAt(int x, int y) {
    return numbers[y][x];
  }

  public BoardSnapshot getSnapshot() {
    return new BoardSnapshot(numbers);
  }

  public void move(Point movablePoint) {
    Point emptyCell = findEmptyAround(movablePoint);
    if (emptyCell == null) {
      return;
    }

    swap(movablePoint, emptyCell);
  }

  private void swap(Point movablePoint, Point emptyCell) {
    int temp = numbers[movablePoint.getY()][movablePoint.getX()];
    numbers[movablePoint.getY()][movablePoint.getX()] = numbers[emptyCell.getY()][emptyCell.getX()];
    numbers[emptyCell.getY()][emptyCell.getX()] = temp;
  }

  private Point findEmptyAround(Point movablePoint) {
    int targetX = movablePoint.getX();
    int targetY = movablePoint.getY();
    if (isLeftCellEmpty(targetX, targetY)) {
      return new Point(targetX - 1, targetY);
    }
    if (isRightCellEmpty(targetX, targetY)) {
      return new Point(targetX + 1, targetY);
    }
    if (isBottomCellEmpty(targetX, targetY)) {
      return new Point(targetX, targetY - 1);
    }
    if (isTopCellEmpty(targetX, targetY)) {
      return new Point(targetX, targetY + 1);
    }
    return null;
  }

  private boolean isLeftCellEmpty(int targetX, int targetY) {
    return targetX - 1 >= 0 && isEmpty(targetX - 1, targetY);
  }

  private boolean isRightCellEmpty(int targetX, int targetY) {
    return targetX + 1 < columns && isEmpty(targetX + 1, targetY);
  }

  private boolean isBottomCellEmpty(int targetX, int targetY) {
    return targetY - 1 >= 0 && isEmpty(targetX, targetY - 1);
  }

  private boolean isTopCellEmpty(int targetX, int targetY) {
    return targetY + 1 < rows && isEmpty(targetX, targetY + 1);
  }

  private boolean isEmpty(int x, int y) {
    return numbers[y][x] == EMPTY_CELL;
  }

}
