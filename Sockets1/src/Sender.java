import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) throws Exception {
        // Crear un socket DatagramSocket
        DatagramSocket socket = new DatagramSocket();

        // Datos a enviar
        String message = "Hola, mundo!";
        byte[] sendData = message.getBytes();

        // Dirección IP y puerto del destino
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        int port = 12345;

        // Crear un paquete DatagramPacket para enviar los datos
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

        // Enviar el paquete
        socket.send(sendPacket);
        
        // Cerrar el socket
        socket.close();
    }
}
