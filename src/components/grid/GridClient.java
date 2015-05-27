package components.grid;

import components.player.Player;
import components.shot.Shot;


public class GridClient extends Grid {
	@Override
	public void receiveShot(Shot shot) {
		int[] position = shot.getPosition();
		GridCell currentCell = this.get(position[0], position[1]);
		if (currentCell.getStatus() == 0) {
			// server.publish(shot);
		}

	}
}
