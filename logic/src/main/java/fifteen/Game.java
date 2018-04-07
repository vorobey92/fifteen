package fifteen;

import fifteen.api.BoardController;
import fifteen.api.BoardSnapshot;
import fifteen.api.Point;
import fifteen.client.Input;
import fifteen.domain.Board;
import java.util.Scanner;

public class Game {

  public static void main(String[] args) {
    BoardController boardController = new CliBoard();
    Input input = new CliInput();

    boardController.startNewGame();
    while (!boardController.haveWon()) {
      System.out.println(boardController.getBoard());
      Point point = input.getUserInput();
      boardController.move(point);
    }

  }

  static class CliInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Point getUserInput() {
      String[] from = scanner.nextLine().split(" ");
      int targetX = Integer.parseInt(from[0]);
      int targetY = Integer.parseInt(from[1]);
      return new Point(targetX, targetY);
    }
  }

  static class CliBoard implements BoardController {

    private Board board = new Board();

    @Override
    public void startNewGame() {
      board.init();
    }

    @Override
    public BoardSnapshot getBoard() {
      return board.getSnapshot();
    }

    @Override
    public void move(Point cell) {
      board.move(cell);
    }

    @Override
    public boolean haveWon() {
      return board.haveWon();
    }
  }

}
