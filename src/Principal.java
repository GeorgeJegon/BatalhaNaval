import components.weapons.*;
import java.util.ArrayList;
import java.util.Random;

public class Principal {

  /**
   * @param args
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public static void main(String[] args) throws ClassNotFoundException,
      InstantiationException, IllegalAccessException {
    // TODO Auto-generated method stub
    Weapon[][] grid = new Weapon[100][100];
    ArrayList<Weapon> listWeapons = new ArrayList<Weapon>();
    
    populateWeapons(listWeapons, Aerocarrier.class, 5);
    populateWeapons(listWeapons, Submarine.class, 3);
    populateWeapons(listWeapons, TorpedoBoat.class, 10);
    populateWeapons(listWeapons, BattleShip.class, 3);
    
    addWeaponToGrid(grid, listWeapons);
  }
  
  public static void addWeaponToGrid(Weapon[][] grid, ArrayList<Weapon> listWeapon){
    Random r = new Random();
    int x, y, z;
    
    for(Weapon w: listWeapon){
      x = r.nextInt(10);
      y = r.nextInt(10);
      z = w.getCellsOccupation();
      
      
      if (grid[x][y] != null) {
        
      }
      
      System.out.println(l);
      System.out.println(x + " - " + y + " - " + z);      
    }
  }

  public static void populateWeapons(ArrayList<Weapon> listWeapons,
      Class c, int quantity) throws InstantiationException,
      IllegalAccessException {
    while (quantity-- > 0) {
      listWeapons.add((Weapon) createInstance(c));
    }
  }

  public static Object createInstance(Class c) throws InstantiationException,
      IllegalAccessException {
    return c.newInstance();
  }
}