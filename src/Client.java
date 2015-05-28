import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import components.grid.GridClient;
import components.player.Player;
import components.shot.Shot;

public class Client {
  private Socket             requestSocket;
  private ObjectInputStream  input;
  private ObjectOutputStream output;
  private String             ip;
  private int                port;
  private Player             player = new Player("George");
  private GridClient         gameGrid;

  public static void main(String args[]) throws InstantiationException,
      IllegalAccessException, ClassNotFoundException, IOException {
    Client client = new Client();
    client.run();
  }

  public Client() {
    this.ip = "127.0.0.1";
    this.port = 3322;
  }

  public Client(String ip) {
    this.ip = ip;
    this.port = 3322;
  }

  public Client(String ip, int port) {
    this.ip = ip;
    this.port = port;
  }

  private void run() throws UnknownHostException, ClassNotFoundException,
      IOException {
    this.initComunication();
  }

  private void initComunication() throws UnknownHostException, IOException,
      ClassNotFoundException {
    String str;
    Shot shot = new Shot(75, 75, this.player);
    ShotMessage shotMessage = new ShotMessage(shot);

    this.requestSocket = new Socket(this.ip, this.port);

    this.input = new ObjectInputStream(this.requestSocket.getInputStream());
    this.output = new ObjectOutputStream(this.requestSocket.getOutputStream());

    this.output.writeObject(shotMessage);

    while ((str = (String) this.input.readObject()) != null) {
      System.out.println(str);
    }

    input.close();
    output.close();
    this.requestSocket.close();
  }
}
