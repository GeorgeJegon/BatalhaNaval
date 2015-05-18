package components.grid;

public class Grid {
  private GridCell[][] board;
  private int          gridSize = 100;

  public Grid() {
    this.initBoard();
  }

  public GridCell get(int index){
    int i = (index/this.gridSize) , j =  (index % this.gridSize);
    return this.get(i, j);
  }
  
  public GridCell get(int i, int j){
    if (this.validPosition(i, j)) {
     return this.board[i][j];
    }
    return null;
  }

  private boolean validPosition(int i, int j) {
    return (this.validPositionRow(i) && this.validPositionCol(j));
  }

  private boolean validPositionRow(int i) {
    return (i >= 0 && i < this.gridSize);
  }

  private boolean validPositionCol(int j) {
    return (j >= 0 && j < this.gridSize);
  }

  private void initBoard() {
    this.board = new GridCell[this.gridSize][this.gridSize];
    for (int i = 0; i < this.gridSize; i++) {
      for (int j = 0; j < this.gridSize; j++) {
        this.board[i][j] = new GridCell();
      }
    }
  }
}
