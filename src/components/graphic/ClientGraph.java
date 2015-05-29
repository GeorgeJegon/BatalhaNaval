package components.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
  
  public void test(){
    
  }

  public ClientConnectWindow getConnectWindow() {
    return this.connectWindow;
  }

  public ClientGameWindow getGameWindow() {
    return this.gameWindow;
  }

  public void disableGridCell(int[] position) {
    super.disableGridCell(position);
    int gridSize = this.getGameGrid().getGridSize();
    int index = (position[0] * gridSize) + (position[1] % gridSize);
    JButton button = this.gameWindow.getListButtons().get(index);
    button.setBackground(Color.RED);
  }

  public void disableGridCells(ArrayList<int[]> listPositions) {
    for (int[] position : listPositions) {
      this.disableGridCell(position);
    }
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
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.gameWindow = new ClientGameWindow(this);
    this.gameWindow.setSize(screenSize);
    this.gameWindow.setResizable(true);
    this.gameWindow.setLocationRelativeTo(null);
  }
}
