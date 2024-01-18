import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket client;

    public ClientThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente conectado!");

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String message = in.readLine();

            String[] numbers = message.split(";");

            System.out.println("Suma: " + (Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1])));

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
