import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost", 12345);
        Scanner sc = new Scanner(System.in);
        System.out.println("/api/getProducts");
        System.out.println("/api/getSuppliers");
        System.out.println("/api/getCustomer:id");
        System.out.println("/api/deleteProduct:id");
        System.out.print("Introduce tu petición: ");
        String msg = sc.nextLine();
        
        BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bfw.write(msg);
        bfw.newLine();
        bfw.flush();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(bfr.readLine());

        sc.close();
        socket.close();
    }
}