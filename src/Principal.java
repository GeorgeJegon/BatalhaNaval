import components.orientations.*;
import components.weapons.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class Principal {
  static Properties prop;
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
    prop = getProp();
    int gridSize = Integer.parseInt(prop.getProperty("grid_size"));

    Weapon[][] grid = new Weapon[gridSize][gridSize];

    ArrayList<Weapon> listWeapons = new ArrayList<Weapon>();
    populateWeapons(listWeapons, Aerocarrier.class, 5);
    populateWeapons(listWeapons, Submarine.class, 3);
    populateWeapons(listWeapons, TorpedoBoat.class, 10);
    populateWeapons(listWeapons, BattleShip.class, 3);

    listOrientations.addAll(Arrays.asList(new Right(), new Up(), new Left(),
        new Bottom()));

    addWeaponToGrid(grid, listWeapons);
  }

  public static Properties getProp() throws IOException {
    Properties prop = new Properties();
    FileInputStream file = new FileInputStream("./src/config.properties");
    prop.load(file);
    return prop;
  }

  public static void addWeaponToGrid(Weapon[][] grid,
      ArrayList<Weapon> listWeapon) {
    Random r = new Random();
    int[] position = new int[2];
    int gridSize = Integer.parseInt(prop.getProperty("grid_size"));
    int cells;
    Orientation orientation;

    for (Weapon weapon : listWeapon) {
      cells = weapon.getCellsOccupation();
      do {
        position[0] = r.nextInt(gridSize);
        position[1] = r.nextInt(gridSize);
        orientation = listOrientations.get(r.nextInt(4));
      } while(!orientation.checkEmptyCells(grid, cells, position));
      orientation.fillCells(grid, weapon, position);
    }
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