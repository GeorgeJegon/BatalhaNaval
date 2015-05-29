package components.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import components.orientations.Bottom;
import components.orientations.Left;
import components.orientations.Orientation;
import components.orientations.Right;
import components.orientations.Up;
import components.shot.Shot;
import components.weapons.Weapon;

public abstract class Grid {
  private GridCell[][]           board;
  private int                    gridSize         = 100;
  private ArrayList<Orientation> listOrientations = new ArrayList<Orientation>();

  public Grid() {
    this.initBoard();
    this.listOrientations.addAll(Arrays.asList(new Right(), new Up(),
        new Left(), new Bottom()));
  }

  public GridCell get(int index) {
    int i = (index / this.gridSize), j = (index % this.gridSize);
    return this.get(i, j);
  }
  
  public GridCell get(int[] position) {
    return this.get(position[0], position[1]);
  }

  public GridCell get(int i, int j) {
    if (this.validPosition(i, j)) {
      return this.board[i][j];
    }
    return null;
  }

  public int getGridSize() {
    return gridSize;
  }

  public void setGridSize(int gridSize) {
    this.gridSize = gridSize;
  }
  
  public void disableCell(int[] position){
    this.get(position).setStatus(1);
  }
  
  public void disableCells(ArrayList<int[]> listPositions){
    for(int[] position: listPositions){
      this.disableCell(position);
    }
  }

  public void addWeapons(ArrayList<Weapon> listWeapons) {
    Random r = new Random();
    int[] position = new int[2];
    int gridSize = 100;
    int cells;
    Orientation orientation;

    for (Weapon weapon : listWeapons) {
      cells = weapon.getCellsOccupation();
      do {
        position[0] = r.nextInt(gridSize);
        position[1] = r.nextInt(gridSize);
        orientation = listOrientations.get(r.nextInt(4));
      } while (!orientation.checkEmptyCells(this, cells, position));
      orientation.fillCells(this, weapon, position);
    }
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

  public abstract void receiveShot(Shot shot);
}
