import components.grid.Grid;
import components.grid.GridServer;
import components.orientations.*;
import components.player.Player;
import components.shot.Shot;
import components.weapons.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Principal {
  static Properties             prop;
  static ArrayList<Orientation> listOrientations = new ArrayList<Orientation>();

  /**
   * @param args
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException
   */
  public static void main(String[] args) throws ClassNotFoundException,
      InstantiationException, IllegalAccessException, IOException {
    // TODO Auto-generated method stub

    ArrayList<Weapon> listWeapons = new ArrayList<Weapon>();
    ArrayList<Player> listPlayers = new ArrayList<Player>();
    Grid gameGrid = new GridServer();

    populateWeapons(listWeapons, Aerocarrier.class, 5);
    populateWeapons(listWeapons, Submarine.class, 3);
    populateWeapons(listWeapons, TorpedoBoat.class, 8);
    populateWeapons(listWeapons, BattleShip.class, 3);

    gameGrid.addWeapons(listWeapons);

    for (Weapon weapon : listWeapons) {
      System.out.println("\nEu ocupo estas celulas\n");
      weapon.listPosition();
    }

    Player currentPlayer = new Player("George");

    while (currentPlayer.getRemaingShots() > 0) {
      int[] position = new int[2];
      position[0] = Integer.parseInt(JOptionPane.showInputDialog(null,
          "Posição x:"));
      position[1] = Integer.parseInt(JOptionPane.showInputDialog(null,
          "Posição y:"));
      gameGrid.receiveShot(new Shot(position, currentPlayer));
      JOptionPane.showMessageDialog(null, "Jogador: " + currentPlayer.getName()
          + "\n Pontos: " + currentPlayer.getPoints() + "\n Tiros Restantes: "
          + currentPlayer.getRemaingShots() + "\n Pontuação do Elemento: "
          + gameGrid.get(position[0], position[1]).getContent().getPoints());
    }
  }

  public static Properties getProp() throws IOException {
    Properties prop = new Properties();
    FileInputStream file = new FileInputStream("./src/config.properties");
    prop.load(file);
    return prop;
  }

  public static void populateWeapons(ArrayList<Weapon> listWeapons, Class c,
      int quantity) throws InstantiationException, IllegalAccessException {
    while (quantity-- > 0) {
      listWeapons.add((Weapon) createInstance(c));
    }
  }

  public static Object createInstance(Class c) throws InstantiationException,
      IllegalAccessException {
    return c.newInstance();
  }
}