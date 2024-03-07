import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Obtenemos todas las compras
        HttpRequest buysRequest = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:3000/compras"))
        .GET()
        .build();

        HttpResponse<String> buysResponse = client.send(buysRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Compras: " + buysResponse.body());

        // Eliminamos la compra con ID 1
        HttpRequest deleteBuyRequest = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:3000/compras/1"))
        .DELETE()
        .build();

        HttpResponse<String> deleteBuyResponse = client.send(deleteBuyRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Respuesta del servidor: " + deleteBuyResponse.body());

        // Obtenemos todas las compras
        buysRequest = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:3000/compras"))
        .GET()
        .build();

        buysResponse = client.send(buysRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Compras: " + buysResponse.body());

        // Obtenemos el numero de productos de la compra con ID 2
        HttpRequest productsRequest = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:3000/apiCompras/2/numProductos"))
        .GET()
        .build();

        HttpResponse<String> productsResponse = client.send(productsRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Productos de la compra con ID 2: " + productsResponse.body());

        // Obtenemos el numero de productos de la compra con ID 2 (Deberia dar error ya que la compra con ID 1 ha sido eliminada)
        productsRequest = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:3000/apiCompras/1/numProductos"))
        .GET()
        .build();

        productsResponse = client.send(productsRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Productos de la compra con ID 1: " + productsResponse.body());
    }
}
