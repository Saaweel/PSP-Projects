import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiverThread extends Thread {
    private DatagramSocket socket;

    public ReceiverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Buffer para almacenar los datos recibidos
            byte[] receiveData = new byte[1024];

            // Crear un paquete DatagramPacket para recibir los datos
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Recibir el paquete
            socket.receive(receivePacket); // Se bloquea hasta recibir datos

            // Obtener los datos recibidos
            byte[] data = receivePacket.getData();
            String message = new String(data, 0, receivePacket.getLength());

            // Imprimir el mensaje recibido
            System.out.println("Mensaje recibido:" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}