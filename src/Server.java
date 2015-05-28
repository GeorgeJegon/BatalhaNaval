import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import components.grid.GridServer;
import components.player.Player;
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
    System.out.println("Cliente conectado do IP"
        + client.getInetAddress().getHostAddress());

    output = new ObjectOutputStream(client.getOutputStream());
    input = new ObjectInputStream(client.getInputStream());

    while ((shotMessage = (ShotMessage) input.readObject()) != null) {
      System.out.println(shotMessage.getName());
    }

    output.close();
    client.close();
    server.close();
  }
}
