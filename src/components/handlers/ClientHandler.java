package components.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import components.grid.GridCell;
import components.player.Player;
import components.server.Server;
import components.shot.Shot;
import components.weapons.Weapon;

public class ClientHandler implements Runnable {
  private BufferedReader reader;
  private Socket         clientSocket;
  private PrintWriter    clientWriter;
  private Player         player;
  private Server         server;

  public ClientHandler(Server server, Socket socket, PrintWriter writer)
      throws IOException {
    this.server = server;
    this.clientSocket = socket;
    this.clientWriter = writer;
    this.reader = new BufferedReader(new InputStreamReader(
        this.clientSocket.getInputStream()));
  }

  public void run() {
    String message;
    try {
      while ((message = this.reader.readLine()) != null) {
        ArrayList<String> response = new ArrayList<String>();
        String[] data = message.split(":");
        String actionType = data[0];

        if (actionType.equals("connect")) {

          this.player = new Player(data[1]);
          this.server.listPlayers.add(player);

        } else if (actionType.equals("disconnect")) {

        } else if (actionType.equals("shot")) {

          int[] position = new int[2];
          position[0] = Integer.parseInt(data[1]);
          position[1] = Integer.parseInt(data[2]);
          Shot shot = new Shot(position, this.player);
          this.server.gameGrid.receiveShot(shot);
          response.add("disableGridCells");

          if (shot.success()) {

            GridCell cell = this.server.gameGrid.get(position);
            Weapon weapon = cell.getContent();
            ArrayList<int[]> listPositions = weapon.getAttachedPosition();

            for (int[] weaponPosition : listPositions) {
              response.add(weaponPosition[0] + ":" + weaponPosition[1]);
            }

            this.server.publish("shotSuccess:" + this.player.getPoints() + ":"
                + this.player.getRemaingShots(), clientWriter);
            this.server.broadcast(String.join(":", response));
          } else {
            response.add(position[0] + ":" + position[1]);
            this.server.broadcast(String.join(":", response));
          }
        }
        System.out.println(message);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
