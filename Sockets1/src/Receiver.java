import java.net.DatagramSocket;

public class Receiver {
    public static void main(String[] args) throws Exception {
        // Crear un socket DatagramSocket
        DatagramSocket socket = new DatagramSocket(12345);

        // Crear e iniciar múltiples hilos de recepción
        for (int i = 0; i < 10; i++) {
            new ReceiverThread(socket).start();
        }

        // No cerrar el socket hasta que todos los hilos hayan terminado
        // socket.close();
    }
}