import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private Socket client;

	public Client() {
		initClient();
	}

	private void initClient() {
		try {
			client = new Socket("127.0.0.1", 3322);
			Scanner keyboard = new Scanner(System.in);
			PrintStream output = new PrintStream(client.getOutputStream());

			while (keyboard.hasNextLine()) {
				output.println(keyboard.nextLine());
			}

			output.close();
			keyboard.close();
		} catch (IOException exception) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null,
					exception);
		}
	}

}
