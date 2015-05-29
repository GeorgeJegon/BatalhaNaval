package components.graphic;

import javax.swing.JFrame;
import components.client.Client;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientGameWindow extends JFrame {
  private static final long  serialVersionUID = 3725700307098065295L;
  private Client             client;
  private int                gridSize         = 10000;
  private int                breakGrid        = 100;
  private ArrayList<JButton> listButtons      = new ArrayList<JButton>();

  public ClientGameWindow(Client client) {
    this.client = client;
    this.initComponents();
    this.addButtons();
  }

  public ArrayList<JButton> getListButtons() {
    return this.listButtons;
  }

  private void initComponents() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    int width = breakGrid;
    int height = (gridSize / breakGrid);

    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[width + 1];
    gridBagLayout.rowHeights = new int[height + 1];
    gridBagLayout.columnWeights = new double[width + 1];
    gridBagLayout.rowWeights = new double[height + 1];
    gridBagLayout.columnWeights[width] = Double.MIN_VALUE;
    gridBagLayout.rowWeights[height] = Double.MIN_VALUE;

    getContentPane().setLayout(gridBagLayout);
  }

  private void addButtons() {
    Dimension dimension = new Dimension(13, 6);
    GridBagConstraints gridBagConstraints;
    JButton currentButton;
    int i, j;

    for (int x = 0; x < this.gridSize; x++) {
      i = (x / this.breakGrid);
      j = (x % this.breakGrid);

      gridBagConstraints = new GridBagConstraints();
      gridBagConstraints.insets = new Insets(0, 0, 0, 0);
      gridBagConstraints.gridx = j;
      gridBagConstraints.gridy = i;

      currentButton = new JButton();
      currentButton.setActionCommand("GridCell");
      currentButton.setName(new Integer(x).toString());
      currentButton.setToolTipText("(" + i + "," + j + ")");
      currentButton.setPreferredSize(dimension);
      currentButton.setOpaque(true);

      currentButton.addActionListener(this.buttonHandler());

      getContentPane().add(currentButton, gridBagConstraints);

      listButtons.add(currentButton);
    }
  }

  private ActionListener buttonHandler() {
    return new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        JButton gridButton = (JButton) event.getSource();
        String action = gridButton.getActionCommand();
        if (action.equals("GridCell")) {
          int x = Integer.parseInt(gridButton.getName());
          int i = (x / 100), j = (x % 100);
          client.getGameGrid().receiveShot(i, j, client);
        }
      }
    };
  }
}
