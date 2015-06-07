package components.graphic;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import components.client.Client;

public class ClientGraph extends Client {
  private ClientConnectWindow connectWindow;
  private ClientGameWindow    gameWindow;

  public ClientGraph() {
    super("127.0.0.1", 3322);
    this.initComponents();
  }

  public ClientConnectWindow getConnectWindow() {
    return this.connectWindow;
  }

  public ClientGameWindow getGameWindow() {
    return this.gameWindow;
  }

  public void disableGridCell(int[] position, int currentPlayer) {
    super.disableGridCell(position, currentPlayer);
    int gridSize = this.getGameGrid().getGridSize();
    int index = (position[0] * gridSize) + (position[1] % gridSize);
    JButton button = this.gameWindow.getListButtons().get(index);
    if (currentPlayer == 1) {
      button.setBackground(Color.RED);
    } else {
      button.setBackground(Color.YELLOW);
    }
  }

  public void disableGridCells(ArrayList<int[]> listPositions, int currentPlayer) {
    for (int[] position : listPositions) {
      this.disableGridCell(position, currentPlayer);
    }
  }

  public void updateScore(String score) {
    this.gameWindow.updateScorePoints(score);
  }

  public void updateRemaingShots(String remaingShots) {
    this.gameWindow.updateRemaingShotsNumber(remaingShots);
  }

  private void initComponents() {
    this.initGameWindow();
    this.initConnectWindow();
  }

  private void initConnectWindow() {
    this.connectWindow = new ClientConnectWindow(this, this.getGameWindow());
    this.connectWindow.setSize(239, 222);
    this.connectWindow.setVisible(true);
    this.connectWindow.setLocationRelativeTo(null);
  }

  private void initGameWindow() {
    this.gameWindow = new ClientGameWindow(this);
    this.gameWindow.setSize(1000, 639);
    this.gameWindow.setResizable(true);
    this.gameWindow.setLocationRelativeTo(null);
  }
}
