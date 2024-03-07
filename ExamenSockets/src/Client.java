import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner readNumber = new Scanner(System.in);

            String response = "";

            while (!response.contains("finish: ")) {
                System.out.print("Introduzca un número (0 - 100): ");
                String number = readNumber.nextLine();

                while (number.equals("") || !number.matches("\\d+")) {
                    System.out.println("Ese no es un número válido");
                    System.out.print("Introduzca un número (0 - 100): ");
                    number = readNumber.nextLine();
                }

                bfw.write(number);
                bfw.newLine();
                bfw.flush();

                response = bfr.readLine();

                System.out.println(response.replace("finish: ", ""));
            };

            readNumber.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
