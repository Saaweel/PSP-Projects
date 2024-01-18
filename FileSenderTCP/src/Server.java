import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private final int PORT = 12345;
    private final String FILES_DIRECTORY = "files";
    private final Map<String, String> USERS = new HashMap<>();

    public Server() {
        USERS.put("root", "");
        USERS.put("admin", "admin");
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor escuchando en " + PORT + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleConnection(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConnection(Socket socket) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            // Leer el usuario y contraseña del cliente
            String username = reader.readLine();
            String password = reader.readLine();

            // Verificar las credenciales
            if (validateCredentials(username, password)) {
                writer.write("Credenciales aceptadas.\n");
                writer.flush();

                // Leer el nombre del archivo a recibir
                String filename = reader.readLine();

                // Guardar el archivo en el servidor
                saveFile(socket.getInputStream(), username, filename);

                writer.write("Archivo recibido con éxito.\n");
            } else {
                writer.write("Credenciales erroneas.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateCredentials(String username, String password) {
        return USERS.containsKey(username) && USERS.get(username).equals(password);
    }

    private void saveFile(InputStream inputStream, String username, String filename) {
        File userDirectory = new File(FILES_DIRECTORY + File.separator + username);
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }

        File file = new File(userDirectory, filename);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().startServer();
    }
}