import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                ApiController th = new ApiController(clientSocket);
                new Thread(th).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}