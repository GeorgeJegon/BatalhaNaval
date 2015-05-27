package components.grid;

import java.util.ArrayList;

import components.player.Player;
import components.shot.Shot;
import components.weapons.Weapon;

public class GridServer extends Grid {
	public void receiveShot(Shot shot) {
		  int[] position = shot.getPosition();
		  Player currentPlayer = shot.getPlayer();
		  GridCell currentCell = this.get(position[0], position[1]);
		  Weapon currentWeapon;
		  ArrayList<int[]> weaponPositions;
		  
		  if (currentCell.getStatus() == 0) {
			  currentCell.setStatus(1);
			  if (!currentCell.isEmpty()) {
				currentWeapon = currentCell.getContent();
				weaponPositions = currentWeapon.getAttachedPosition();			
				for (int[] _position: weaponPositions){
					this.get(_position[0], _position[1]).setStatus(1);
				}	
				currentPlayer.addPoints(currentWeapon.getPoints());
			  } 
			  currentPlayer.setRemaingShots(currentPlayer.getRemaingShots() - 1);
		  }
	  }
}
