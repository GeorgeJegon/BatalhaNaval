import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
  public static void main(String args[]) throws IOException {
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
  }
}
