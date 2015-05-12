import components.weapons.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class Principal {

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
	Properties prop = getProp();
	int gridSize = Integer.parseInt(prop.getProperty("gridSize"));

    Weapon[][] grid = new Weapon[gridSize][gridSize];
    ArrayList<Weapon> listWeapons = new ArrayList<Weapon>();

    populateWeapons(listWeapons, Aerocarrier.class, 5);
    populateWeapons(listWeapons, Submarine.class, 3);
    populateWeapons(listWeapons, TorpedoBoat.class, 10);
    populateWeapons(listWeapons, BattleShip.class, 3);

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

    for (Weapon weapon : listWeapon) {
      position[0] = r.nextInt(10);
      position[1] = r.nextInt(10);
      System.out.println("OI");
      checkEmptyCells(grid, weapon, position);
    }
  }

  public static void checkEmptyCells(Weapon[][] grid, Weapon weapon,
      int[] position) {
    int x = position[0], y = position[1], cells = weapon.getCellsOccupation();

    if (grid[x][y] == null) {
      // ArrayOutOfBounds - Right 
      if ((y + cells) < 100 && walkThroughGrid(grid, position, 0, 1, cells)) {
        System.out.println("Pode Direita");
      }

      // ArrayOutOfBounds - Left
      if ((y - cells) >= 0 && walkThroughGrid(grid, position, 0, -1, cells)) {
        System.out.println("Pode Esquerda");
      }

      // ArrayOutOfBounds - Bottom
      if ((x + cells) < 100 && walkThroughGrid(grid, position, 1, 0, cells)) {
        System.out.println("Pode para Baixo");
      }

      // ArrayOutOfBounds - Top
      if ((x - cells) >= 0 && walkThroughGrid(grid, position, -1, 0, cells)) {
        System.out.println("Pode para Cima");
      }

    }
  }

  public static boolean walkThroughGrid(Weapon[][] grid,int [] position, int dx, int dy,
      int stop) {
    int x = position[0], y = position[1];
    for (int i = 1; i <= stop; i++) {
      if (grid[x + (dx * i)][y + (dy * i)] != null) {
        return false;
      }
    }
    return true;
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