package components.graphic;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import components.client.Client;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import utils.Utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ClientGameWindow extends JFrame {
  private static final long  serialVersionUID = 3725700307098065295L;
  private Client             client;
  private int                gridSize         = 10000;
  private int                breakGrid        = 100;
  private ArrayList<JButton> listButtons      = new ArrayList<JButton>();
  private JLabel             lblScorePoints, lblRemaingShotsNumber;

  public ClientGameWindow(Client client) {
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(this.closeHandler());

    this.lblRemaingShotsNumber = new JLabel();
    this.lblScorePoints = new JLabel();

    this.client = client;
    this.initContainer();
    this.initLeftPanel();
    this.initRightPanel();
  }

  public ArrayList<JButton> getListButtons() {
    return this.listButtons;
  }
  
  public void updateRemaingShotsNumber(String remaingShots){
    this.lblRemaingShotsNumber.setText(remaingShots);
  }
  
  public void updateScorePoints(String score) {
    this.lblScorePoints.setText(score);
  }

  private void initContainer() {
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0 };
    gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
    getContentPane().setLayout(gridBagLayout);
  }

  private void initLeftPanel() {
    JPanel panel = new JPanel();
    GridBagConstraints gbc_panel = new GridBagConstraints();
    gbc_panel.fill = GridBagConstraints.BOTH;
    gbc_panel.gridx = 0;
    gbc_panel.gridy = 0;
    getContentPane().add(panel, gbc_panel);
    this.initLeftPanelComponents(panel);
  }

  private void initRightPanel() {
    JPanel panel = new JPanel();
    Font labelsFont = new Font("Tahoma", Font.PLAIN, 23);
    GridBagLayout layout = Utils.createGridBagLayout(1, 4);
    JLabel lblScore = new JLabel("Pontuação:");
    JLabel lblRemaingShots = new JLabel("Tiros Restantes:");
    GridBagConstraints lblScoreConstraints = Utils.createGridBagConstraints(0,
        0);
    GridBagConstraints lblScorePointsConstraints = Utils
        .createGridBagConstraints(0, 1);
    GridBagConstraints lblRemaingShotsConstraints = Utils
        .createGridBagConstraints(0, 2);
    GridBagConstraints lblRemaingShotsNumberConstraints = Utils
        .createGridBagConstraints(0, 3);

    panel.setSize(500, 600);
    panel.setLayout(layout);

    lblScore.setFont(labelsFont);
    lblScore.setHorizontalAlignment(SwingConstants.CENTER);

    lblScorePoints.setFont(labelsFont);
    lblScorePoints.setHorizontalAlignment(SwingConstants.CENTER);

    lblRemaingShots.setFont(labelsFont);
    lblRemaingShots.setHorizontalAlignment(SwingConstants.CENTER);

    lblRemaingShotsNumber.setFont(labelsFont);
    lblRemaingShotsNumber.setHorizontalAlignment(SwingConstants.CENTER);

    lblScoreConstraints.insets = new Insets(30, 0, 0, 0);
    lblScorePointsConstraints.insets = new Insets(30, 0, 50, 0);
    lblRemaingShotsNumberConstraints.insets = new Insets(30, 0, 0, 30);
    lblRemaingShotsNumberConstraints.insets = new Insets(30, 0, 50, 0);

    panel.add(lblScore, lblScoreConstraints);
    panel.add(lblScorePoints, lblScorePointsConstraints);
    panel.add(lblRemaingShots, lblRemaingShotsConstraints);
    panel.add(lblRemaingShotsNumber, lblRemaingShotsNumberConstraints);

    getContentPane().add(panel, Utils.createGridBagConstraints(1, 0));
  }

  private void initLeftPanelComponents(JPanel leftPanel) {
    int width = breakGrid;
    int height = (gridSize / breakGrid);

    leftPanel.setLayout(Utils.createGridBagLayout(width, height));
    this.addButtons(leftPanel);
  }

  private void addButtons(JPanel target) {
    Dimension dimension = new Dimension(8, 6);
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

      target.add(currentButton, gridBagConstraints);

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

  private WindowAdapter closeHandler() {
    return new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        if (JOptionPane.showConfirmDialog(null,
            "Deseja realmente fechar a aplicação?", "Sair Jogo",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
          client.disconnect();
          event.getWindow().dispose();
        }
      }
    };
  }
}
