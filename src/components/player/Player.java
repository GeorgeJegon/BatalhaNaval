package components.player;

import java.io.Serializable;

public class Player implements Serializable {
  private static final long serialVersionUID = -4275866453135476393L;
  private int               remaingShots;
  private int               points;
  private int               clientID;
  private String            name             = new String();

  public Player(String name) {
    this.remaingShots = 20;
    this.points = 0;
    this.name = name;
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public int getClientID() {
    return this.clientID;
  }

  public void setClientID(int clientID) {
    this.clientID = clientID;
  }

  public int getRemaingShots() {
    return remaingShots;
  }

  public void setRemaingShots(int remaingShots) {
    this.remaingShots = remaingShots;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}