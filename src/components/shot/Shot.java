package components.shot;

import java.io.Serializable;

import components.player.Player;

public class Shot implements Serializable {
  private static final long serialVersionUID = -3793135616015321624L;
  private int[]             position         = new int[2];
  private Player            player;
  private int               status;

  public Shot(int i, int j, Player player) {
    this.position[0] = i;
    this.position[1] = j;
    this.player = player;
  }

  public Shot(int[] position, Player player) {
    this.position = position;
    this.player = player;
  }

  public boolean success() {
    return this.status == 1;
  }
  
  public void setStatus (int status) {
    this.status = status;
  }

  public int[] getPosition() {
    return position;
  }

  public void setPosition(int[] position) {
    this.position = position;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

}
