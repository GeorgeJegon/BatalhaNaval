import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import components.grid.GridCell;
import components.grid.GridServer;
import components.player.Player;
import components.shot.Shot;
import components.weapons.Aerocarrier;
import components.weapons.BattleShip;
import components.weapons.Submarine;
import components.weapons.TorpedoBoat;
import components.weapons.Weapon;

import utils.Utils;

public class Server {
  private ArrayList<Weapon> listWeapons = new ArrayList<Weapon>();
  private ArrayList<Player> listPlayers = new ArrayList<Player>();
  private GridServer        gameGrid    = new GridServer();

  public static void main(String args[]) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    new Server().run();
  }

  private void run() throws InstantiationException, IllegalAccessException,
      ClassNotFoundException, IOException {
    this.initGrid();
    this.showWeaponPositions();
    this.initComunication();
    
  }

  private void initGrid() throws InstantiationException, IllegalAccessException {
    Utils.populateWeapons(this.listWeapons, Aerocarrier.class, 5);
    Utils.populateWeapons(this.listWeapons, Submarine.class, 3);
    Utils.populateWeapons(this.listWeapons, TorpedoBoat.class, 8);
    Utils.populateWeapons(this.listWeapons, BattleShip.class, 3);

    this.gameGrid.addWeapons(this.listWeapons);
  }

  private void initComunication() throws IOException, ClassNotFoundException {
    ObjectOutputStream output;
    ObjectInputStream input;
    ServerSocket server;
    Socket client;
    ShotMessage shotMessage;

    server = new ServerSocket(3322);
    System.out.println("Servidor iniciado na porta 3322");

    client = server.accept();
    System.out.println("Cliente conectado, do IP "
        + client.getInetAddress().getHostAddress());

    output = new ObjectOutputStream(client.getOutputStream());
    input = new ObjectInputStream(client.getInputStream());

    while ((shotMessage = (ShotMessage) input.readObject()) != null) {
      Shot shot = shotMessage.getShot();

      this.gameGrid.receiveShot(shot);

      Player player = shot.getPlayer();
      int[] position = shot.getPosition();
      GridCell cell = this.gameGrid.get(position[0], position[1]);
      Weapon weapon;

      if (!cell.isEmpty()) {
        weapon = cell.getContent();
        System.out.println("O player " + player.getName() + " conseguiu "
            + weapon.getPoints() + " pontos! Nova pontuação dele é "
            + player.getPoints() + " pontos");
      }
    }

    output.close();
    client.close();
    server.close();
  }

  private void test() {
    Player player = new Player("George");
    Weapon weapon;
    int[] position;
    Shot shot;

    for (int i = 0; i < 2; i++) {
      weapon = this.listWeapons.get(i);
      position = weapon.getAttachedPosition().get(0);
      shot = new Shot(position, player);
      this.gameGrid.receiveShot(shot);

      System.out.println("O player " + player.getName() + " conseguiu "
          + weapon.getPoints() + " pontos! Nova pontuação dele é "
          + player.getPoints() + " pontos");
    }

  }

  private void showWeaponPositions() {
    for (Weapon weapon : this.listWeapons) {
      System.out.println("\nEu me encontro nessas posições aqui:");
      for (int[] position : weapon.getAttachedPosition()) {
        System.out.println("(" + position[0] + "," + position[0] + ")");
      }
    }
  }
}
