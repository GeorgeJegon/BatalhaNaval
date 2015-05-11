package components;

public abstract class Weapon {
  protected int points = 0;
  protected int cellsOccupation = 0;
  
  public Weapon(int points, int cellsOccupation) {
    this.points = points;
    this.cellsOccupation = cellsOccupation;
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
}
