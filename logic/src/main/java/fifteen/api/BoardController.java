package fifteen.api;

public interface BoardController {

  void startNewGame();

  BoardSnapshot getBoard();

  void move(Point cell);

  boolean haveWon();

}
