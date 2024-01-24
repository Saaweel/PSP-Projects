import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class VotingServer {
    private static final int NUM_VOTES_TO_RECEIVE = 10;

    private Map<String, Integer> partyVotes;

    public VotingServer() {
        partyVotes = new HashMap<>();
        partyVotes.put("p1", 0);
        partyVotes.put("p2", 0);
        partyVotes.put("p3", 0);
        partyVotes.put("blanco", 0);

        startServer();
    }

    private void startServer() {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            System.out.println("Servidor de votación iniciado en el puerto " + 9876);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String vote = new String(packet.getData(), 0, packet.getLength());
                processVote(vote);

                if (getNumVotesReceived() >= NUM_VOTES_TO_RECEIVE) {
                    displayResults();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void processVote(String vote) {
        partyVotes.merge(vote, 1, Integer::sum);
        System.out.println("Voto recibido: " + vote);
        displayResults();
    }

    private int getNumVotesReceived() {
        return partyVotes.values().stream().mapToInt(Integer::intValue).sum();
    }

    private void displayResults() {
        System.out.println("Resultados finales:");
        for (Map.Entry<String, Integer> entry : partyVotes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votos");
        }
    }

    public static void main(String[] args) {
        new VotingServer();
    }
}