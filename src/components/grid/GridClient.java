package components.grid;

import javax.swing.JOptionPane;

import components.client.Client;
import components.shot.Shot;

public class GridClient extends Grid {
  public void receiveShot(int i, int j, Client client) {
    GridCell currentCell = this.get(i, j);
    if (currentCell.getStatus() == 0) {
      client.doShot(i, j);
    } else {
      JOptionPane.showMessageDialog(null, "Não pode atacar essa celula");
    }
  }

  @Override
  public void receiveShot(Shot shot) {
    // TODO Auto-generated method stub
    
  }
}
