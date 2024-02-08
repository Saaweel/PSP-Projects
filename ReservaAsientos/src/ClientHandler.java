import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private boolean[] seatMap;

    public ClientHandler(Socket client, boolean[] seatMap) {
        this.client = client;
        this.seatMap = seatMap;
    }

    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            sendData(writer, seatMap);

            while (true) {
                int asiento = Integer.parseInt(reader.readLine());

                if (asiento == -1) {
                    break;
                }

                if (asiento - 1 < 0 || asiento - 1 >= seatMap.length) {
                    writer.write("El número de asiento no es válido.\n");
                } else if (seatMap[asiento - 1]) {
                    writer.write("El asiento ya está ocupado.\n");
                } else {
                    seatMap[asiento - 1] = true;
                    writer.write("Reserva exitosa.\n");
                    System.out.println("Asiento " + asiento + " reservado por " + client.getInetAddress());
                }
                writer.flush();
            }

            writer.close();
            reader.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData(BufferedWriter writer, boolean[] data) throws IOException {
        for (boolean datum : data) {
            writer.write(datum ? "1" : "0");
        }
        writer.write("\n");
        writer.flush();
    }
}