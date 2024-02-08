import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 12345;

        try {
            Socket cliente = new Socket(HOST, PUERTO);
            System.out.println("Conectado al servidor.");

            BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            boolean[] mapaAsientos = getData(entrada);
            System.out.println("Mapa de asientos: " + java.util.Arrays.toString(mapaAsientos));

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Mapa de asientos actualizado: " + java.util.Arrays.toString(mapaAsientos));
                System.out.print("Ingrese el número de asiento que desea reservar (-1 para salir): ");
                int asiento = Integer.parseInt(teclado.readLine());

                salida.write(asiento + "\n");
                salida.flush();

                if (asiento == -1) {
                    break;
                }

                String respuesta = entrada.readLine();
                System.out.println(respuesta);

                if (respuesta.contains("Reserva exitosa.")) {
                    mapaAsientos[asiento - 1] = true;
                }
            }

            salida.close();
            entrada.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean[] getData(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        boolean[] data = new boolean[line.length()];

        for (int i = 0; i < line.length(); i++) {
            data[i] = line.charAt(i) == '1';
        }

        return data;
    }
}