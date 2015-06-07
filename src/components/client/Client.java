package components.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import components.grid.GridClient;
import components.handlers.ServerHandler;

public class Client {
  private Socket         clientSock;
  private BufferedReader reader;
  private PrintWriter    writer;
  private String         host;
  private int            port;
  private boolean        connected    = false;
  private String         name;
  private GridClient     gameGrid;
  private int            clientID;
  private int            remaingShots = 20;

  public Client() {
    this.gameGrid = new GridClient();
  }

  public Client(String host, int port) {
    this.host = host;
    this.port = port;
    this.gameGrid = new GridClient();
  }

  public int getClientID() {
    return this.clientID;
  }

  public void setClientID(int clientID) {
    this.clientID = clientID;
  }

  public GridClient getGameGrid() {
    return this.gameGrid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getRemaingShots() {
    return this.remaingShots;
  }

  public void setRemaingShots(int remaingShots) {
    this.remaingShots = remaingShots;
  }

  public boolean isConnect() {
    return this.connected;
  }

  public void disableGridCell(int[] position, int currentPlayer) {
    this.gameGrid.disableCell(position);
  }

  public void disableGridCells(ArrayList<int[]> listPositions, int currentPlayer) {
    this.gameGrid.disableCells(listPositions);
  }

  public void updateScore(String score) {
    System.out.println("Pontuação: " + score);
  }

  public void updateRemaingShots(String remaingShots) {
    System.out.println("Tiros restantes: " + remaingShots);
  }

  public void connect() {
    try {
      this.clientSock = new Socket(this.host, this.port);
      this.connected = true;
      InputStreamReader streamReader = new InputStreamReader(
          this.clientSock.getInputStream());
      this.reader = new BufferedReader(streamReader);
      this.writer = new PrintWriter(this.clientSock.getOutputStream());

      listenThread();

      this.send("connect:" + this.name);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {
    try {
      this.send("disconnect:" + this.name);
      this.clientSock.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void doShot(int i, int j) {
    this.send("shot:" + i + ":" + j);
  }

  public void send(String message) {
    this.writer.println(message);
    this.writer.flush();
  }

  private void listenThread() {
    Thread ServerHandler = new Thread(new ServerHandler(this, this.reader));
    ServerHandler.start();
  }
}
