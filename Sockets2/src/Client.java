import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("localhost", 12345);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String message = (int) (Math.random() * 100 + 1) + ";" + (int) (Math.random() * 100 + 1);

        out.write(message);
        out.newLine();
        out.flush();

        out.close();
        client.close();
    }
}
