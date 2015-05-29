import java.util.ArrayList;

import utils.Utils;

import components.grid.GridServer;
import components.weapons.Aerocarrier;
import components.weapons.BattleShip;
import components.weapons.Submarine;
import components.weapons.TorpedoBoat;
import components.weapons.Weapon;

public class TestGridServer {
  public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    // TODO Auto-generated method stub
    ArrayList<Weapon> listWeapons = new ArrayList<Weapon>();
    GridServer gameGrid = new GridServer();
    
    Utils.populateWeapons(listWeapons, Aerocarrier.class, 5);
    Utils.populateWeapons(listWeapons, Submarine.class, 3);
    Utils.populateWeapons(listWeapons, TorpedoBoat.class, 8);
    Utils.populateWeapons(listWeapons, BattleShip.class, 3);
    
    
    
    gameGrid.addWeapons(listWeapons);
    
    showWeaponPositions(listWeapons);
    
    
  }
  
  private static void showWeaponPositions(ArrayList<Weapon> listWeapons) {
    for (Weapon weapon : listWeapons) {
      System.out.println("\nEu me encontro nessas posições aqui:");
      for (int[] position : weapon.getAttachedPosition()) {
        System.out.println("(" + position[0] + "," + position[0] + ")");
      }
    }
  }

}
