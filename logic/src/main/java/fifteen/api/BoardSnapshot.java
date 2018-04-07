package fifteen.api;

import java.util.Arrays;

public class BoardSnapshot {
  private final int[][] board;

  public BoardSnapshot(int[][] board) {
    this.board = Arrays.copyOf(board, board.length);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int[] row : board) {
      for (int x = 0; x < board[0].length; x++) {
        builder.append(row[x]).append('\t');
      }
      builder.append('\n');
    }
    return builder.toString();
  }

}
