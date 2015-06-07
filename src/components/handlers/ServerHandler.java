package components.handlers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import components.client.Client;

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
      while ((stream = this.reader.readLine()) != null) {
        String[] data = stream.split(":");
        String actionType = data[0];
        System.out.println(stream);

        if (actionType.equals("shotSuccess")) {

          this.client.updateScore(data[1]);
          this.client.updateRemaingShots(data[2]);
          this.client.setRemaingShots(Integer.parseInt(data[2]));

        } else if (actionType.equals("connected")) {

          this.client.updateScore(data[1]);
          this.client.updateRemaingShots(data[2]);
          this.client.setClientID(Integer.parseInt(data[3]));

        } else if (actionType.equals("gameOver")) {
          
          JOptionPane.showMessageDialog(null, data[1]);
          this.reader.close();

        } else if (actionType.equals("shotFail")) {

          this.client.updateRemaingShots(data[2]);
          this.client.setRemaingShots(Integer.parseInt(data[2]));

        } else if (actionType.equals("disconnectedSuccess")) {
          this.reader.close();

        } else if (actionType.equals("disableGridCellSuccess")) {
          int totalData = (data.length - 1);
          for (int i = 1; i < totalData; i += 2) {
            int[] position = { Integer.parseInt(data[i]),
                Integer.parseInt(data[i + 1]) };
            this.client.disableGridCell(position, 1);
          }
        } else if (actionType.equals("disableGridCells")) {

          int totalData = (data.length - 1);
          for (int i = 1; i < totalData; i += 2) {
            int[] position = { Integer.parseInt(data[i]),
                Integer.parseInt(data[i + 1]) };
            this.client.disableGridCell(position, 0);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}