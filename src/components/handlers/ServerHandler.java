package components.handlers;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerHandler implements Runnable {
  private BufferedReader reader;

  public ServerHandler(BufferedReader reader) {
    this.reader = reader;
  }

  public void run() {
    String stream;
    try {
      while ((stream = reader.readLine()) != null) {
        System.out.println(stream);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}