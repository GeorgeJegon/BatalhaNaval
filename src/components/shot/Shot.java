package components.shot;

import components.player.Player;

public class Shot {
  private int[]  position = new int[2];
  private Player player;

  public Shot(int i, int j, Player player) {
    this.position[0] = i;
    this.position[1] = j;
    this.player = player;
  }

  public Shot(int[] position, Player player) {
    this.position = position;
    this.player = player;
  }

  public void execute() {

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
