package components.weapons;

import java.util.ArrayList;

public abstract class Weapon {
  protected int              points           = 0;
  protected int              cellsOccupation  = 0;
  protected ArrayList<int[]> attachedPosition = new ArrayList<int[]>();

  public Weapon(int points, int cellsOccupation) {
    this.points = points;
    this.cellsOccupation = cellsOccupation;
  }

  public void addPosition(int[] position) {
    this.attachedPosition.add(position);
  }

  public void listPosition() {
    for (int[] position : this.attachedPosition) {
      System.out.println("(" + position[0] + ", " + position[1] + ")");
    }
  }

  // Getters and Setters
  public int getPoints() {
    return this.points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public int getCellsOccupation() {
    return this.cellsOccupation;
  }

  public void setCellsOccupation(int cellsOccupation) {
    this.cellsOccupation = cellsOccupation;
  }

  public ArrayList<int[]> getAttachedPosition() {
    return this.attachedPosition;
  }

  public void setAttachedPosition(ArrayList<int[]> attachedPosition) {
    this.attachedPosition = attachedPosition;
  }
}
