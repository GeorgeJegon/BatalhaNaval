package components.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import components.handlers.ServerHandler;

public class Client {
  private Socket         clientSock;
  private BufferedReader reader;
  private PrintWriter    writer;
  private String         host;
  private int            port;
  private boolean        connected = false;
  private String         name;

  public Client() {

  }

  public Client(String host, int port) {
    this.host = host;
    this.port = port;
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

  public boolean isConnect() {
    return this.connected;
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

  public void doShot(int i, int j) {
    this.send("shot:" + i + ":" + j);
  }

  public void send(String message) {
    this.writer.println(message);
    this.writer.flush();
  }

  private void listenThread() {
    Thread ServerHandler = new Thread(new ServerHandler(this.reader));
    ServerHandler.start();
  }
}
