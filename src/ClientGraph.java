import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import components.client.Client;
import components.graphic.ClientConnectWindow;
import components.graphic.ClientGameWindow;

public class ClientGraph extends Client {
  private ClientConnectWindow connectWindow;
  private ClientGameWindow    gameWindow;

  public ClientGraph() {
    super("127.0.0.1", 3322);
    this.setName("George");
    this.connect();
    this.initComponents();
  }

  public void disableGridCell(int[] position) {
    super.disableGridCell(position);
    int gridSize = this.getGameGrid().getGridSize();
    int index = (position[0] * gridSize) + (position[1] % gridSize);
    JButton button = this.gameWindow.getListButtons().get(index);
    button.setEnabled(false);
    button.setBackground(Color.RED);
  }

  public void disableGridCells(ArrayList<int[]> listPositions) {
    for (int[] position : listPositions) {
      this.disableGridCell(position);
    }
  }

  private void initComponents() {
    // this.initConnectWindow();
    this.initGameWindow();
  }

  @SuppressWarnings("unused")
  private void initConnectWindow() {
    this.connectWindow = new ClientConnectWindow(this);
    this.connectWindow.setSize(239, 222);
    this.connectWindow.setVisible(true);
    this.connectWindow.setLocationRelativeTo(null);
  }

  private void initGameWindow() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.gameWindow = new ClientGameWindow(this);
    this.gameWindow.setSize(screenSize);
    this.gameWindow.setResizable(true);
    this.gameWindow.setLocationRelativeTo(null);
    this.gameWindow.setVisible(true);
  }
}
