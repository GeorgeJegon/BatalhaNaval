package components.orientations;

import components.grid.Grid;
import components.weapons.Weapon;

public abstract class Orientation {
  protected int dx = 0, dy = 0;

  public boolean checkEmptyCells(Grid grid, int iterations,
      int[] initialPosition) {
    int x, y;

    if (!validBounds(grid, iterations, initialPosition)) {
      return false;
    }

    for (int i = 0; i < iterations; i++) {
      x = initialPosition[0] + (this.dx * i);
      y = initialPosition[1] + (this.dy * i);

      if (validPosition(grid, x, y) && !grid.get(x, y).isEmpty()) {
        return false;
      }
    }
    return true;
  }

  protected boolean validPosition(Grid grid, int x, int y) {
    int limit = grid.getGridSize();
    if ((x >= 0 && x < limit) && (y >= 0 && y < limit)) {
      return true;
    }
    return false;
  }

  public void fillCells(Grid grid, Weapon weapon, int[] initialPosition) {
    int iterations = weapon.getCellsOccupation();

    for (int i = 0; i < iterations; i++) {
      int[] position = new int[2];
      position[0] = initialPosition[0] + (this.dx * i);
      position[1] = initialPosition[1] + (this.dy * i);
      grid.get(position[0], position[1]).setContent(weapon);
      weapon.addPosition(position);
    }
  }

  protected boolean validBounds(Grid grid, int iterations, int[] position) {
    iterations--;
    int maxIndexOfX = (position[0] + (this.dx * iterations)), maxIndexOfY = (position[1] + (this.dy * iterations));
    return validPosition(grid, maxIndexOfX, maxIndexOfY);
  }
}
