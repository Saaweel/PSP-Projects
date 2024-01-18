import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final String SERVER_IP = "localhost";
    private final int SERVER_PORT = 12345;

    public void startClient() {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
        ) {
            // Leer el usuario y contraseña del cliente
            System.out.print("Usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            // Enviar el usuario y contraseña al servidor
            writer.write(username + "\n");
            writer.write(password + "\n");
            writer.flush();

            // Obtener la respuesta del servidor
            String serverResponse = reader.readLine();
            System.out.println(serverResponse);

            if (serverResponse.startsWith("Credenciales aceptadas")) {
                // Leer el nombre del archivo a enviar
                System.out.print("Filename: ");
                String filename = scanner.nextLine();
                writer.write(filename + "\n");
                writer.flush();

                // Enviar el archivo al servidor
                sendFile(socket.getOutputStream(), filename);

                // Obtener la respuesta del servidor
                serverResponse = reader.readLine();
                System.out.println(serverResponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendFile(OutputStream outputStream, String filename) {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().startClient();
    }
}