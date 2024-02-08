import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT = 12345;
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT);
            System.out.println("Servidor iniciado en el puerto " + PORT);

            boolean[] seatMap = new boolean[50];


            while (true) {
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());

                Thread clientThread = new Thread(new ClientHandler(client, seatMap));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}