package components.orientations;

import components.weapons.Weapon;

public abstract class Orientation {
  protected int dx = 0, dy = 0;

  public boolean checkEmptyCells(Weapon[][] grid, int iterations,
      int[] initialPosition) {
    int x, y;

    if (!validBounds(grid, iterations, initialPosition)) {
      return false;
    }

    for (int i = 0; i < iterations; i++) {
      x = initialPosition[0] + (this.dx * i);
      y = initialPosition[1] + (this.dy * i);

      if (validPosition(grid, x, y) && grid[x][y] != null) {
        return false;
      }
    }
    return true;
  }

  protected boolean validPosition(Weapon[][] grid, int x, int y) {
    int limitX = grid.length, limitY = grid[0].length;
    if ((x >= 0 && x < limitX) && (y >= 0 && y < limitY)) {
      return true;
    }
    return false;
  }

  public void fillCells(Weapon[][] grid, Weapon weapon, int[] initialPosition) {
    int iterations = weapon.getCellsOccupation();
 
    for (int i = 0; i < iterations; i++) {
      int[] position = new int[2];
      position[0] = initialPosition[0] + (this.dx * i);
      position[1] = initialPosition[1] + (this.dy * i);
      grid[position[0]][position[1]] = weapon;
      weapon.addPosition(position);
    }
  }

  protected boolean validBounds(Weapon[][] grid, int iterations, int[] position) {
    iterations--;
    int maxIndexOfX = (position[0] + (this.dx * iterations)), maxIndexOfY = (position[1] + (this.dy * iterations));
    return validPosition(grid, maxIndexOfX, maxIndexOfY);
  }
}
