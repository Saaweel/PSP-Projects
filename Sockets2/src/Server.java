import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(12345)) {
            System.out.println("Esperando conexión de clientes...");

            while (true) {
                Socket client = server.accept();
                
                ClientThread clientThread = new ClientThread(client);
                
                clientThread.start();
            }
        }
    }    
}