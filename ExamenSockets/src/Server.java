import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int number = (int) (Math.random() * 100);
        System.out.println("[Debug] -> El número elegido es: " + number);

        try (ServerSocket server = new ServerSocket(12345)) {
            while(true){
                Socket clientSocket = server.accept();
                
                ServerThread thread = new ServerThread(clientSocket, number, 5);
                new Thread(thread).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
