import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest seatsRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:3000/seats"))
                    .GET()
                    .build();

            HttpResponse<String> seatsResponse = client.send(seatsRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Mapa de asientos actual: " + seatsResponse.body());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el número de asiento que desea reservar: ");
            int seat = scanner.nextInt();
            scanner.close();

            HttpRequest reserveRequest = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:3000/reserve"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"seatNumber\": " + seat + "}"))
                    .build();
            HttpResponse<String> reserveResponse = client.send(reserveRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Respuesta del servidor: " + reserveResponse.body());


            seatsResponse = client.send(seatsRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Mapa de asientos actual: " + seatsResponse.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
