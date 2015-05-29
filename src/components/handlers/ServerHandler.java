package components.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;

import components.client.Client;
import components.grid.GridClient;

public class ServerHandler implements Runnable {
  private BufferedReader reader;
  private Client         client;

  public ServerHandler(Client client, BufferedReader reader) {
    this.reader = reader;
    this.client = client;
  }

  public void run() {
    String stream;
    try {
      while ((stream = reader.readLine()) != null) {
        String[] data = stream.split(":");
        String actionType = data[0];
        System.out.println(stream);

        if (actionType.equals("shotSuccess")) {
          JOptionPane.showMessageDialog(null,
              "Você acertou um tiro!\nSua nova pontuação é de: " + data[1]
                  + " pontos, e restam " + data[2] + " tiros!");
        } else if (actionType.equals("disableGridCells")) {
          int totalData = (data.length - 1) / 2;
          for (int i = 1; i < totalData; i += 2) {
            int[] position = { Integer.parseInt(data[i]) , Integer.parseInt(data[i+1])};
            this.client.disableGridCell(position);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}