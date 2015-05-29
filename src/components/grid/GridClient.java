package components.grid;

import javax.swing.JOptionPane;

import components.client.Client;
import components.shot.Shot;

public class GridClient extends Grid {
  public void receiveShot(int i, int j, Client client) {
    GridCell currentCell = this.get(i, j);
    int[] position = { i, j };
    if (client.getRemaingShots() > 0) {
      if (currentCell.getStatus() == 0) {
        client.doShot(i, j);
      } else {
        JOptionPane.showMessageDialog(null, "Célula já foi atacada.");
      }
    } else {
      JOptionPane.showMessageDialog(null, "Seus tiros já acabaram");
    }

  }

  @Override
  public void receiveShot(Shot shot) {
    // TODO Auto-generated method stub

  }
}
