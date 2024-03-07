import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket client;
    private int number;
    private int maxTrys;
    private int clientTrys; 
    
    public ServerThread(Socket client, int number, int maxTrys){
        this.client = client;
        this.number = number;
        this.maxTrys = maxTrys;
        this.clientTrys = 0;
    }

    @Override
    public void run() {
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            int clientNumber = 0;
            String response = "";
            while (!response.contains("finish: ")) {
                clientNumber = Integer.valueOf(bfr.readLine());
                this.clientTrys++;
                
                if (this.clientTrys < this.maxTrys) {
                    if (clientNumber > number) {
                        response = "Tu numero es mayor al buscado (Intentos restantes: " + (this.maxTrys - this.clientTrys) + ")";
                    } else if (clientNumber < number) {
                        response = "Tu numero es menor al buscado (Intentos restantes: " + (this.maxTrys - this.clientTrys) + ")";
                    } else {
                        response = "finish: Has encontrado el numero!!";
                        break;
                    }
                } else {
                    response = "finish: Has llegado al maximo de intentos, el numero era: " + this.number;
                }

                bfw.write(response);
                bfw.newLine();
                bfw.flush();
            }

            bfr.close();
            bfw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
