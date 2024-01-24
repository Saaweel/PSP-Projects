import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class VotingClient {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Random random = new Random();

            while (true) {
                String[] voteOptions = {"p1", "p2", "p3", "blanco"};
                String randomVote = voteOptions[random.nextInt(voteOptions.length)];

                byte[] buffer = randomVote.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 9876);
                socket.send(packet);

                System.out.println("Voto aleatorio enviado al servidor: " + randomVote);

                // Espera antes de enviar el próximo voto
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}