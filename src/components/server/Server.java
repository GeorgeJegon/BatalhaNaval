package components.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utils.Utils;

import components.grid.GridServer;
import components.handlers.ClientHandler;
import components.player.Player;
import components.weapons.Aerocarrier;
import components.weapons.BattleShip;
import components.weapons.Submarine;
import components.weapons.TorpedoBoat;
import components.weapons.Weapon;

public class Server {
  private ArrayList<PrintWriter> listClientsOutputStream;
  public ArrayList<Weapon>       listWeapons;
  public ArrayList<Player>       listPlayers;
  public GridServer              gameGrid;

  public Server() {
    this.listClientsOutputStream = new ArrayList<PrintWriter>();
    this.listWeapons = new ArrayList<Weapon>();
    this.listPlayers = new ArrayList<Player>();
    this.gameGrid = new GridServer();

    this.initGrid();
    this.showWeaponPositions();

    Thread starter = new Thread(new ServerStart(this));
    starter.start();
  }

  private void initGrid() {
    try {
      Utils.populateWeapons(this.listWeapons, Aerocarrier.class, 5);
      Utils.populateWeapons(this.listWeapons, Submarine.class, 3);
      Utils.populateWeapons(this.listWeapons, TorpedoBoat.class, 8);
      Utils.populateWeapons(this.listWeapons, BattleShip.class, 3);
      this.gameGrid.addWeapons(this.listWeapons);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private void showWeaponPositions() {
    for (Weapon weapon : this.listWeapons) {
      System.out.println("\nEu me encontro nessas posições aqui:");
      for (int[] position : weapon.getAttachedPosition()) {
        System.out.println("(" + position[0] + "," + position[1] + ")");
      }
    }
  }

  public void broadcast(String message) {
    for (PrintWriter clientWriter : this.listClientsOutputStream) {
      this.publish(message, clientWriter);
    }
  }

  public void publish(String message, PrintWriter clientWriter) {
    clientWriter.println(message);
    clientWriter.flush();
  }

  public class ServerStart implements Runnable {
    private Server server;

    public ServerStart(Server server) {
      this.server = server;
    }

    public void run() {
      try {
        ServerSocket serverSocket = new ServerSocket(3322);
        System.out.println("Servidor iniciado na porta 3322!");

        while (true) {
          Socket clientSock = serverSocket.accept();
          System.out.println("Cliente conectado no IP: "
              + clientSock.getInetAddress().getHostAddress());

          PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
          listClientsOutputStream.add(writer);

          Thread listener = new Thread(new ClientHandler(this.server,
              clientSock, writer));
          listener.start();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
