import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
  public static void main(String args[]) {
    try {
      ServerSocket server = new ServerSocket(3322);
      System.out.println("Servidor iniciado na porta 3322");

      Socket client = server.accept();
      System.out.println("Cliente conectado do IP"
          + client.getInetAddress().getHostAddress());

      Scanner entry = new Scanner(client.getInputStream());

      while (entry.hasNextLine()) {
        System.out.println(entry.nextLine());
      }

      entry.close();
      server.close();
    } catch (IOException exception) {
      Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null,
          exception);
    }
  }
}
