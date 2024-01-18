import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client1 {
    public static void main(String[] args) throws Exception {
        Client1 client = new Client1();
        client.startOutChannel();
        client.startInChannel();
    }

    private DatagramSocket outSocket;

    public Client1() throws Exception {
        outSocket = new DatagramSocket();
    }

    // Crear un hilo para enviar mensajes recogidos por teclado, esperar un segundo cada vez que se envia un mensaje
    public void startOutChannel() {
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    // Leer mensaje por teclado
                    System.out.print("Escribir: ");
                    String message = "Client1: " + System.console().readLine();
                    // Convertir el mensaje a bytes
                    byte[] data = message.getBytes();
                    // Crear un paquete con los datos, la direccion y el puerto del servidor
                    DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 12345);
                    // Enviar el paquete
                    outSocket.send(packet);
                    // Mostrar el mensaje enviado
                    System.out.println(message);
                    // Esperar un segundo
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void startInChannel() {
        Thread thread = new Thread(() -> {
            try {
                // Crear un socket DatagramSocket
                DatagramSocket socket = new DatagramSocket(12346);
                // Buffer para almacenar los datos recibidos
                byte[] receiveData = new byte[1024];

                // Crear un paquete DatagramPacket para recibir los datos
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                while (true) {
                    // Recibir el paquete
                    socket.receive(receivePacket); // Se bloquea hasta recibir datos

                    // Obtener los datos recibidos
                    byte[] data = receivePacket.getData();
                    String message = new String(data, 0, receivePacket.getLength());

                    // Imprimir el mensaje recibido
                    System.out.println(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
